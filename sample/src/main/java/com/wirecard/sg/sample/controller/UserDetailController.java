package com.wirecard.sg.sample.controller;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wirecard.sg.sample.dto.UserDetail;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.service.impl.UserDetailService;

@RestController
@RequestMapping("/user-detail")
public class UserDetailController {
	@Autowired
	UserDetailService service;
	
	@PostMapping(value = "/")
	public ResponsePayload<UserDetail> insert(@RequestBody UserDetail userDetail, Authentication authentication) throws NoSuchAlgorithmException, JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return service.insert(userDetail, authentication);
	}

	@GetMapping(value = "/{id}")
	public ResponsePayload<UserDetail> get(@PathVariable("id") Integer id) {
		return service.get(id);
	}
	
	@GetMapping(value = "/")
	public ResponsePayload<UserDetail> getAll(){
		return service.getAll();
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Integer id, Authentication authentication) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		service.delete(id, authentication);
	}
}
