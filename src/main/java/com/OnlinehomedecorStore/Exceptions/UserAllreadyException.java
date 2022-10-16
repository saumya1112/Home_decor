package com.OnlinehomedecorStore.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserAllreadyException extends RuntimeException {
	public UserAllreadyException(String msg) {
		super(msg);
	}
}
