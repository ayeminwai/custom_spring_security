package com.spring.security.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.demo.service.AuthUserService;

@RestController
@RequestMapping("/")
public class TokenController {
	@Autowired
	private AuthUserService service;

	@PostMapping(value = "/token")
	public String getToken(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		String token = service.login(username, password);
		if(StringUtils.isEmpty(token))
			return "no token found";
		return token;
	}
}
