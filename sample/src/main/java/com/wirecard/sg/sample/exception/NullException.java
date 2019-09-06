package com.wirecard.sg.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class NullException extends RuntimeException {

	private static final long serialVersionUID = -2513129352483178181L;

	public NullException(String msg) {
		super(msg + " cannot be empty.");
	}
}
