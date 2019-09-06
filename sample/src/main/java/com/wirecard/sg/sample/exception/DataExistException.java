package com.wirecard.sg.sample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class DataExistException extends RuntimeException {

	private static final long serialVersionUID = 8882223323100210506L;

	public static final String USER_DETAIL_EXIST = "User information already exist";

	public DataExistException(String msg) {
		super(msg);
	}

}
