package com.romy.prime.api.blog.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

/**
 * 
 * 블로그 목록 Pojo
 *
 */
@Data
@Generated
@NoArgsConstructor
public class BlogInfo {

	private String title;
	
	private String blogname;
	
	private String contents;
	
	private String url;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate regDate;
}
