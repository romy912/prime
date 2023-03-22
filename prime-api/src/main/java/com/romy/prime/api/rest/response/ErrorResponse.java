package com.romy.prime.api.rest.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.romy.prime.biz.exception.ErrorElement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

/**
 * 
 * 에러 결과 Pojo
 *
 */
@Data
@Generated
@EqualsAndHashCode(callSuper=false)
public class ErrorResponse extends RestResponse {

	private List<ErrorElement> errors =  new ArrayList<>();
	
	public ErrorResponse(String uri) {
		super(uri);
	}
	
	public ErrorResponse(String uri, ErrorElement... errors) {
		super(uri);
		CollectionUtils.addAll(this.errors, errors);
	}
	
	
}
