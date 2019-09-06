package com.wirecard.sg.sample.service;

import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.Authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wirecard.sg.sample.model.ResponsePayload;

public abstract interface GeneralServices<T extends Object> {
	public ResponsePayload<T> get(Integer id);

	public ResponsePayload<T> getAll();

	public ResponsePayload<T> insert(T obj, Authentication authentication) throws NoSuchAlgorithmException, JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;

	public void delete(Integer id, Authentication authentication) throws JsonProcessingException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}
