package com.romy.prime.api.rest.exception;

import com.romy.prime.biz.exception.ValidationException;

/**
 * 
 * API Invalid Exception
 *
 */
public class RestApiInvalidException extends ValidationException {

	private static final long serialVersionUID = 906154577597198968L;
	
	protected static final int DEFAULT_ERROR_STATUS = 400;
	
	public RestApiInvalidException() {
		this(null, null);
	}
	
	public RestApiInvalidException(Throwable e) {
		this(e.getMessage(), e);
	}
	
	public RestApiInvalidException(String message) {
		this(message, (Throwable) null);
	}
	
	public RestApiInvalidException(String message, Throwable e) {
		super(message, e);
	}
	
	public int getStatus() {
		return DEFAULT_ERROR_STATUS;
	}
	
}
