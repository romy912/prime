package com.romy.prime.api.model.naver;

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
public class NaverItem {
	
	private String title;
	
	private String link;
	
	private String description;
	
	private String bloggername;
	
	private String bloggerlink;
	
	private String postdate;
}
