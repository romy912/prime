package com.romy.prime.biz.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.Generated;

/**
 * 
 * Error Element
 *
 */
@Data
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorElement implements Serializable {

	private static final long serialVersionUID = 8075077616924199065L;

	private String parameters;

	private String value;

	private String code;

	private String details;

	private String message;

	public ErrorElement(ErrorElement element) {
		this(element.getCode(), element.getDetails(), element.getMessage());
	}

	public ErrorElement(String code, String details, String message) {
		this.code = code;
		this.details = details;
		this.message = message;
	}

	public ErrorElement(HttpStatus status) {
		this.code = status.name();
		this.details = Series.valueOf(status.value()).name();
		this.message = status.getReasonPhrase();
	}
}
