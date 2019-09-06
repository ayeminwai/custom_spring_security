package com.wirecard.sg.sample.controller;

import java.security.NoSuchAlgorithmException;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.sg.sample.dto.User;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.service.impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

	@PostMapping(value = "/")
	public ResponsePayload<User> insert(@RequestBody User user, Authentication authentication) throws NoSuchAlgorithmException {
		return service.insert(user, authentication);
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") @Min(1) Integer id, Authentication authentication) {
		service.delete(id, authentication);
	}

	@GetMapping(value = "/{id}")
	public ResponsePayload<User> get(@PathVariable("id") @Min(1) Integer id) {
		return service.get(id);
	}

	@GetMapping(value = "/")
	public ResponsePayload<User> getAll() {
		return service.getAll();
	}
	
	@PostMapping(value = "/forget-password")
	public ResponsePayload<User> forgetPassword(@RequestBody User user){
		return service.forgetPassword(user);
	}
}
