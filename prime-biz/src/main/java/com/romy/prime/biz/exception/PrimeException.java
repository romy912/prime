package com.romy.prime.biz.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

/**
 * 
 * Prime Application Exception
 *
 */
@Data
@Generated
@EqualsAndHashCode(callSuper = false)
public abstract class PrimeException extends NestedRuntimeException {

	private static final long serialVersionUID = 1983920013954565148L;

	protected transient ArrayList<ErrorElement> erorrs = new ArrayList<>();

	@JsonIgnore
	ErrorElement defaultElement;

	protected PrimeException(String code, String details, String defaultMessageKey, String message, Throwable e) {
		super(message, e);
		String internalMsg = message;

		if (e != null && StringUtils.hasText(internalMsg)) {
			internalMsg = e.getMessage();
		}

		this.defaultElement = new ErrorElement(code, details, defaultMessageKey);

		this.addErrorElement(internalMsg);
	}

	public void addErrorElement(String message) {
		ErrorElement element = new ErrorElement(this.defaultElement);
		element.setMessage(message);

		this.erorrs.add(element);
	}

	public List<ErrorElement> getErrors() {
		return this.erorrs;
	}

}
