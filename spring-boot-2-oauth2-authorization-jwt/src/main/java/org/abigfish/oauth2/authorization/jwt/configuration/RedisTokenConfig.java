package org.abigfish.oauth2.authorization.jwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@ConditionalOnProperty(prefix="security.oauth2.token.store",name="type" ,havingValue="redis" ,matchIfMissing=true)
public class RedisTokenConfig {
	
	@Autowired
	private RedisConnectionFactory redisConnectionFactory;
	
	
	@Bean
	public TokenStore tokenStore() {
		return new RedisTokenStore(redisConnectionFactory);
	}

}
