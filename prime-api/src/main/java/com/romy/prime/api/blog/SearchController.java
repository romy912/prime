package com.romy.prime.api.blog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romy.prime.api.blog.model.BlogResult;
import com.romy.prime.api.rest.exception.RestApiInvalidException;

/**
 * 
 * 블로그 검색 Controller
 *
 */
@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	/**
	 * 블로그 검색
	 * @param query
	 * @param sort
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/v1/blog/search/list")
	public List<BlogResult> searchBlog(@RequestParam(required = true, name = "query") String query,
			@RequestParam(required = false, name = "sort", defaultValue = "A") String sort,
			@RequestParam(required = false, name = "page", defaultValue = "1") int page,
			@RequestParam(required = false, name = "size", defaultValue = "10") int size) {
		
		if(StringUtils.isBlank(query)) {
			throw new RestApiInvalidException("검색어[query]는 필수 입력 항목입니다.");
		}
		
		ArrayList<String> range = new ArrayList<>(Arrays.asList("A", "R"));
		if(range.indexOf(sort) == -1) {
			throw new RestApiInvalidException("정렬방식[sort]은 정확도[A], 최신순[R]만 허용 됩니다.");
		} else if(page < 1 || page > 50) {
			throw new RestApiInvalidException("페이지 번호[page]는 1~50까지만 허용 됩니다.");
		} else if(size < 1 || size > 50) {
			throw new RestApiInvalidException("문서 수[size]는 1~50까지만 허용 됩니다.");
		}
		
		return this.searchService.getBlogSearchList(query, sort, page, size);
	}
	
	
}
