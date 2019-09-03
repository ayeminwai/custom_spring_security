package com.spring.security.demo.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.spring.security.demo.entity.AuthCustomer;

public interface AuthUserService {
	String login(String username, String password);

	Optional<User> findByToken(String token);

	AuthCustomer findById(Integer id);
}
