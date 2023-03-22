package com.romy.prime.biz.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * Validation Exception
 *
 */
public class ValidationException extends PrimeException {

	private static final long serialVersionUID = 3462155909105851358L;

	protected static final String DEFAULT_CODE = HttpStatus.BAD_REQUEST.name();
	protected static final String DEFAULT_DETAILS = HttpStatus.BAD_REQUEST.series().name();
	protected static final String DEFAULT_MESSAGE_KEY = "error.invalid.message";

	public ValidationException() {
		this(null, null);
	}

	public ValidationException(String message) {
		this(message, null);
	}

	public ValidationException(String message, Throwable e) {
		super(DEFAULT_CODE, DEFAULT_DETAILS, DEFAULT_MESSAGE_KEY, message, e);
	}

}
