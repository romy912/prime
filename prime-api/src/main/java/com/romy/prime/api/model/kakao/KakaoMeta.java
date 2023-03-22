package com.romy.prime.api.model.kakao;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;


/**
 * 
 * Meta 정보 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoMeta {

	private boolean isEnd;
	
	private int pageableCount;
	
	private long totalCount;
	
}
