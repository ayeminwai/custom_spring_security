package com.wirecard.sg.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class PasswordException extends RuntimeException {

	private static final long serialVersionUID = 1278676460064138183L;

	public PasswordException(String msg) {
		super(msg);
	}
}
