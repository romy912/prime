package com.romy.prime.api.model.kakao;

import java.sql.Timestamp;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * 
 * 블로그 문서 정보 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
public class KakaoDocument {

	private String blogname;
	
	private String contents;
	
	private Timestamp datetime;
	
	private String thumbnail;
	
	private String title;
	
	private String url;
}
