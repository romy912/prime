package com.romy.prime.api.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romy.prime.api.blog.model.BlogResult;
import com.romy.prime.api.rest.exception.RestApiInvalidException;
import com.romy.prime.biz.config.RestTemplateConfig;
import com.romy.prime.biz.repository.PopularTermsRepository;
import com.romy.prime.biz.repository.ServerInfoRepository;
import com.romy.prime.biz.service.ExternalApiService;
import com.romy.prime.biz.service.PopularTemsService;
import com.romy.prime.biz.service.ServerInfoService;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
@SpringBootTest(classes = { ObjectMapper.class, RestTemplateConfig.class, SearchController.class, SearchService.class,
		ServerInfoService.class, PopularTemsService.class, ServerInfoRepository.class, PopularTermsRepository.class,
		ExternalApiService.class })
class SearchControllerTest {

	@Autowired
	private SearchController searchController;
	
	@MockBean
	private ServerInfoRepository serverInfoRepository;
	
	@MockBean
	private PopularTermsRepository popularTermsRepository;
	
	
	@Test
	void When_ParametersAreValid_Then_NotNull() {
		List<BlogResult> list = this.searchController.searchBlog("kakao", "A", 1, 10);
		assertNotNull(list);
	}
	
	@Test
	void When_QueryIsEmpty_Then_RestApiInvalidException() {
		
		assertThrows(RestApiInvalidException.class, () -> {
			this.searchController.searchBlog(null, "A", 1, 10);
		});
		
	}
	
	@Test
	void When_SortIsNotRange_Then_RestApiInvalidException() {
		
		assertThrows(RestApiInvalidException.class, () -> {
			this.searchController.searchBlog("kakao", "B", 1, 10);
		});
		
	}
	
	@Test
	void When_PageIsNotRange_Then_RestApiInvalidException() {
		
		assertThrows(RestApiInvalidException.class, () -> {
			this.searchController.searchBlog("kakao", "A", 0, 10);
		});
		
	}
	
	@Test
	void When_SizeIsNotRange_Then_RestApiInvalidException() {
	
		assertThrows(RestApiInvalidException.class, () -> {
			this.searchController.searchBlog("kakao", "A", 1, 0);
		});
		
	}
}
