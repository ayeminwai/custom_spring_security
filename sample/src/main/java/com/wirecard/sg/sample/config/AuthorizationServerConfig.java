package com.wirecard.sg.sample.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	static final String CLIEN_ID = "client";
	static final String CLIENT_SECRET = "$2a$04$9m1hunhvnz.1LE7x9S079OBT07knHQoGV4MrX60nJabvibY3TLS8u";	//secret encrypted by bcrypt round 4
	static final String GRANT_TYPE_PASSWORD = "password";
	static final String AUTHORIZATION_CODE = "authorization_code";
	static final String REFRESH_TOKEN = "refresh_token";
	static final String IMPLICIT = "implicit";
	static final String READ = "read";
	static final String WRITE = "write";
	static final String TRUST = "trust";
	static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
	static final int REFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;
	static final String SIGNING_KEY = "as466gf";
	static final String BAD_CREDENTIAL="Bad credentials";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		
		clients
			.inMemory()
			.withClient(CLIEN_ID)
			.secret(CLIENT_SECRET)
			.authorizedGrantTypes(GRANT_TYPE_PASSWORD,REFRESH_TOKEN,AUTHORIZATION_CODE,IMPLICIT)
			.scopes(READ,WRITE,TRUST)
			.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
			.refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY_SECONDS);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints
			.userDetailsService(userService)	//need to add userDetailService for refreshToken
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
			.tokenEnhancer(tokenEnhancerChain());	//.exceptionTranslator(this::exceptionHandler);
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public TokenEnhancerChain tokenEnhancerChain() {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(),jwtAccessTokenConverter()));
		return tokenEnhancerChain;
	}
	
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(SIGNING_KEY);
		((DefaultAccessTokenConverter) converter.getAccessTokenConverter())
        .setUserTokenConverter(userAuthenticationConverter());		//added the userAuthenticationConverter() to get custom user info
		return converter;
	}
	
	@Bean
	public UserAuthenticationConverter userAuthenticationConverter() {
	    DefaultUserAuthenticationConverter defaultUserAuthenticationConverter = new DefaultUserAuthenticationConverter();
	    defaultUserAuthenticationConverter.setUserDetailsService(userService);
	    return defaultUserAuthenticationConverter;
	}
}