package com.github.qq275860560.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.requestMatchers().antMatchers("/api/**");
		http.authorizeRequests().antMatchers("/api/**").access("@securityService.decide(request,authentication)");

	}

	@Bean
	public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(
			ApplicationContext applicationContext) {
		OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
		expressionHandler.setApplicationContext(applicationContext);
		return expressionHandler;
	}

	@Autowired
	private OAuth2WebSecurityExpressionHandler expressionHandler;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.expressionHandler(expressionHandler);
	}

}
