package org.abigfish.oauth2.resource.jwt.configuration;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	public TokenStore tokenStore;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			    .anyRequest()
			    .permitAll().and()
			    .cors().disable()
			    .csrf().disable()
			    .httpBasic()
			    .disable()
				.exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.accessDeniedHandler(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED));
	}

	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		/**
		 * OAuth2 架构上分为Authorization Server和Resource Server。
		 * 我们可以为每一个Resource Server（一个微服务实例）设置一个resourceid。
		 * 再给client授权的时候，可以设置这个client可以访问哪一些微服务实例，
		 * 如果没设置，就是对所有的resource都有访问权限。
		 */
		resources.resourceId("mw/adminapp").tokenStore(tokenStore);
	}

}
