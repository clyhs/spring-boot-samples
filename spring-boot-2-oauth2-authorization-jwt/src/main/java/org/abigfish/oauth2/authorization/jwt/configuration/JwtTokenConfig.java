package org.abigfish.oauth2.authorization.jwt.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;



@Configuration
@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="jwt" ,matchIfMissing=true)
public class JwtTokenConfig {
	
//	@Autowired
//	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public TokenStore tokenStore() {
		//return new RedisTokenStore(redisConnectionFactory);
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new CustomTokenEnhancer();
		//JWT认证，提供了对称加密以及非对称的实现。
		//非对称加密
		converter.setKeyPair(
				new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), "password".toCharArray()).getKeyPair("jwt"));
		
		//对称加密
		//converter.setSigningKey("111111");
		return converter;
	}

}
