package com.romy.prime.api.rest.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;

/**
 * 
 * 성공 결과 Pojo
 *
 * @param <T>
 */
@Data
@Generated
@EqualsAndHashCode(callSuper=false)
public class SuccessResponse<T> extends RestResponse {

	private T data;
	
	public SuccessResponse(String uri, T data) {
		super(uri);
		this.data = data;
	}
}
