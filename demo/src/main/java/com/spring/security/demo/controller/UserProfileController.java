package com.spring.security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.demo.entity.AuthCustomer;
import com.spring.security.demo.service.AuthUserService;

@RestController
@RequestMapping("/api/users/")
public class UserProfileController {
	@Autowired
	private AuthUserService service;

	@GetMapping(value = "/user/{id}")
	public AuthCustomer getUserDetail(@PathVariable("id") Integer id) {
		return service.findById(id);
	}
}
