package com.romy.prime.api.blog.model;

import java.util.List;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * 
 * 블로그 검색결과 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
public class BlogResult {

	private String serverNm;
	
	private long totalCount;
	
	private int pageCount;
	
	private List<BlogInfo> list;
}
