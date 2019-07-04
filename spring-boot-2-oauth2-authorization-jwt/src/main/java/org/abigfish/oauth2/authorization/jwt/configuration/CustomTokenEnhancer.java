package org.abigfish.oauth2.authorization.jwt.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.abigfish.oauth2.authorization.jwt.entity.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
/*
 * Add custom user principal information to the JWT token
 * JWT中，需要在token中携带额外的信息，这样可以在服务之间共享部分用户信息,比如共享用户email
 */
public class CustomTokenEnhancer extends JwtAccessTokenConverter {
	
		@Override
		public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			User user = (User) authentication.getPrincipal();

			Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());

			info.put("email", user.getEmail());

			DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
			customAccessToken.setAdditionalInformation(info);

			return super.enhance(customAccessToken, authentication);
		}
	}