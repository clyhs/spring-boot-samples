package org.abigfish.oauth2.authorization.jwt.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.abigfish.oauth2.authorization.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpointAuthenticationFilter;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;


@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Value("${check-user-scopes}")
	private Boolean checkUserScopes;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private ClientDetailsService clientDetailsService;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public OAuth2RequestFactory requestFactory() {
		CustomOauth2RequestFactory requestFactory = new CustomOauth2RequestFactory(clientDetailsService);
		requestFactory.setCheckUserScopes(true);
		return requestFactory;
	}

//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(jwtAccessTokenConverter());
//	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		//从数据库读取clientId secrect
		clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
		//也可以从配置文件application.xml读取配置的
//		InMemoryClientDetailsServiceBuilder build = clients.inMemory();
//		build.withClient(config.getClientId())
//        .secret(passwordEncoder.encode(config.getClientSecret()))
//        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
//        .refreshTokenValiditySeconds(60 * 60 * 24 * 15)
//        .authorizedGrantTypes("refresh_token", "password", "authorization_code")//OAuth2支持的验证模式
//        .redirectUris("http://abigfish.net")
//        .scopes("all");
	}

	@Bean
	public TokenEndpointAuthenticationFilter tokenEndpointAuthenticationFilter() {
		return new TokenEndpointAuthenticationFilter(authenticationManager, requestFactory());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore)
				.authenticationManager(authenticationManager).userDetailsService(userDetailsService);
		
		if(null!=jwtAccessTokenConverter){
			endpoints.tokenEnhancer(jwtAccessTokenConverter);
		}
		
		if (checkUserScopes)
			endpoints.requestFactory(requestFactory());
	}

//	@Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		JwtAccessTokenConverter converter = new CustomTokenEnhancer();
//		//JWT认证，提供了对称加密以及非对称的实现。
//		//非对称加密
//		
//		converter.setKeyPair(
//				new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
//		
//		//对称加密
//		//converter.setSigningKey("111111");
//		return converter;
//	}

	/*
	 * Add custom user principal information to the JWT token
	 * JWT中，需要在token中携带额外的信息，这样可以在服务之间共享部分用户信息,比如共享用户email
	 */
//	class CustomTokenEnhancer extends JwtAccessTokenConverter {
//		@Override
//		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//			User user = (User) authentication.getPrincipal();
//
//			Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
//
//			info.put("email", user.getEmail());
//
//			DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
//			customAccessToken.setAdditionalInformation(info);
//
//			return super.enhance(customAccessToken, authentication);
//		}
//	}

	//映射用户角色到权限范围
	class CustomOauth2RequestFactory extends DefaultOAuth2RequestFactory {
		
		@Autowired
		private TokenStore tokenStore;

		public CustomOauth2RequestFactory(ClientDetailsService clientDetailsService) {
			super(clientDetailsService);
		}

		@Override
		public TokenRequest createTokenRequest(Map<String, String> requestParameters,
				ClientDetails authenticatedClient) {
			if (requestParameters.get("grant_type").equals("refresh_token")) {
				OAuth2Authentication authentication = tokenStore.readAuthenticationForRefreshToken(
						tokenStore.readRefreshToken(requestParameters.get("refresh_token")));
				SecurityContextHolder.getContext()
						.setAuthentication(new UsernamePasswordAuthenticationToken(authentication.getName(), null,
								userDetailsService.loadUserByUsername(authentication.getName()).getAuthorities()));
			}
			return super.createTokenRequest(requestParameters, authenticatedClient);
		}
	}

}