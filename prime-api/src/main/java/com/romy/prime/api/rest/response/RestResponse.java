package com.romy.prime.api.rest.response;

import lombok.Data;
import lombok.Generated;

/**
 * 
 * API Pojo
 *
 */
@Data
@Generated
public abstract class RestResponse {

	protected String uri;
	
	protected RestResponse(String uri) {
		this.uri = uri;
	}
	
}
