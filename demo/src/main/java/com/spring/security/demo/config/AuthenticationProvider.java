package com.spring.security.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.spring.security.demo.service.AuthUserService;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	AuthUserService authUserService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		Object token = authentication.getCredentials();
		return Optional
				.ofNullable(token)
				.map(String::valueOf)
				.flatMap(authUserService::findByToken)
				.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
	}

}
