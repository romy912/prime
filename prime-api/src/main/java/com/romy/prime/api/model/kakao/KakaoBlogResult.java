package com.romy.prime.api.model.kakao;

import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * 
 * Kakao 검색 결과 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
public class KakaoBlogResult {

	private List<KakaoDocument> documents;
	
	private KakaoMeta meta;
	
}
