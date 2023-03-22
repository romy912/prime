package com.romy.prime.api.rest.handler;

import java.sql.SQLTransientException;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.romy.prime.api.rest.exception.RestApiInvalidException;
import com.romy.prime.api.rest.response.ErrorResponse;
import com.romy.prime.biz.exception.ErrorElement;
import com.romy.prime.biz.exception.PrimeException;
import com.romy.prime.biz.exception.ValidationException;

/**
 * Error Handler
 */
@RestControllerAdvice
public class RestExceptionControllerAdvice {

	@ExceptionHandler({ServletRequestBindingException.class, ValidationException.class, RestApiInvalidException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<ErrorResponse> handleBadRequest(HttpServletRequest request, Exception e) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	@ExceptionHandler({NoHandlerFoundException.class})
	public ResponseEntity<ErrorResponse> handleNoHandlerFound(HttpServletRequest request, NoHandlerFoundException e) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ErrorResponse> handleMethodNotAllowed(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
		
		HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	@ExceptionHandler({HttpMediaTypeException.class})
	public ResponseEntity<ErrorResponse> handleMediaTypeNotSupported(HttpServletRequest request, HttpMediaTypeException e) {
		
		HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	@ExceptionHandler({ResponseStatusException.class})
	public ResponseEntity<ErrorResponse> handleResponseStatusException(HttpServletRequest request, ResponseStatusException e) {
		
		HttpStatus status = e.getStatus();
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	@ExceptionHandler({PrimeException.class})
	public ResponseEntity<ErrorResponse> handlePrimeException(HttpServletRequest request, PrimeException e) {
		
		int status = HttpStatus.INTERNAL_SERVER_ERROR.value();
		
		return new ResponseEntity<>(this.errorResponse(request, e), null, status);
	}
	
	@ExceptionHandler({RestClientResponseException.class})
	public ResponseEntity<ErrorResponse> handleRestClientException(HttpServletRequest request, RestClientResponseException e) {
		
		ErrorResponse errorRes = this.errorResponse(request);
		ErrorElement element = new ErrorElement(String.valueOf(e.getRawStatusCode()), Series.CLIENT_ERROR.name(), "");
		element.setMessage(e.getMessage());
		
		errorRes.getErrors().add(element);
		
		return new ResponseEntity<>(errorRes, null, e.getRawStatusCode());
	}
	
	@ExceptionHandler({PersistenceException.class, SQLTransientException.class, DataAccessException.class})
	public ResponseEntity<ErrorResponse> handlePersistenceException(HttpServletRequest request, Exception e) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ErrorResponse errorRes = this.errorResponse(request);
		ErrorElement element = new ErrorElement(status);
		element.setMessage("Database is error");
		
		errorRes.getErrors().add(element);
		
		return new ResponseEntity<>(errorRes, status);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception e) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return new ResponseEntity<>(this.errorResponse(request, e, status), status);
	}
	
	public ErrorResponse errorResponse(HttpServletRequest request, Exception e, HttpStatus status) {
		
		ErrorResponse errorRes = new ErrorResponse(request.getRequestURI());
		
		if( e instanceof PrimeException) {
			errorRes.getErrors().addAll( ((PrimeException) e).getErorrs() ); 
		} else {
			ErrorElement element = new ErrorElement(status);
			element.setMessage(e.getMessage());
			errorRes.getErrors().add(element);
		}
		
		return errorRes;
	}
	
	public ErrorResponse errorResponse(HttpServletRequest request, PrimeException e) {
		
		ErrorResponse errorRes = new ErrorResponse(request.getRequestURI());
		errorRes.getErrors().addAll(e.getErorrs()); 
		
		return errorRes;
	}
	
	public ErrorResponse errorResponse(HttpServletRequest request) {
		
		ErrorResponse errorRes = new ErrorResponse(request.getRequestURI());
		
		return errorRes;
	}
}
