package com.romy.prime.api.popular;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.romy.prime.biz.entity.PopularTerms;
import com.romy.prime.biz.service.PopularTemsService;

/**
 * 
 * 인기 검색어 관련 Controller
 *
 */
@RestController
public class TermsSearchController {

	@Autowired
	private PopularTemsService popularTemsService;
	
	/**
	 * 인기 검색어 조회
	 * @return
	 */
	@GetMapping("/v1/popular/terms/list")
	public List<PopularTerms> popularTermsList() {
		
		return this.popularTemsService.getPopularTermsList();
	}
	
}
