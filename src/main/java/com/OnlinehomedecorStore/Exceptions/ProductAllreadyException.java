package com.OnlinehomedecorStore.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductAllreadyException extends RuntimeException {
	public ProductAllreadyException(String message) {
		super(message);
	}
}
