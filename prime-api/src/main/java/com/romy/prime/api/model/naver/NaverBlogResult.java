package com.romy.prime.api.model.naver;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * 
 * Naver 블로그 검색 결과 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
public class NaverBlogResult {
	
	private Timestamp lastBuildDate;
	
	private long total;
	
	private int start;
	
	private int display;
	
	private List<NaverItem> items;
}
