package com.wirecard.sg.sample.config;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

import com.wirecard.sg.sample.model.AuthUser;

@Service
public class UserSecurity {

	public boolean hasUserName(OAuth2Authentication authentication, Integer id) {
		AuthUser user = (AuthUser) authentication.getPrincipal();
		System.out.println(user.getUserId() + "ayeminwai ayeminwai");
		return user.getUserId() == id;
	}
}
