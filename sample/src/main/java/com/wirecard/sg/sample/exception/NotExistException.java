package com.wirecard.sg.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotExistException extends RuntimeException {

	private static final long serialVersionUID = 5483509655123824343L;

	public NotExistException(String msg) {
		super(msg + " is not Exist");
	}

}
