package com.romy.prime.api.blog;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import com.romy.prime.biz.config.RestTemplateConfig;
import com.romy.prime.biz.repository.PopularTermsRepository;
import com.romy.prime.biz.repository.ServerInfoRepository;
import com.romy.prime.biz.service.ExternalApiService;
import com.romy.prime.biz.service.PopularTemsService;
import com.romy.prime.biz.service.ServerInfoService;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
@SpringBootTest(classes = { ObjectMapper.class, RestTemplateConfig.class, SearchService.class, ServerInfoService.class,
		PopularTemsService.class, ServerInfoRepository.class, PopularTermsRepository.class, ExternalApiService.class })
class SearchServiceTest {

	@Autowired
	private SearchService searchService;
	
	@MockBean
	private ServerInfoRepository serverInfoRepository;
	
	@MockBean
	private PopularTermsRepository popularTermsRepository;
	
	@Test
	void When_TermIsReady_Then_SearchBlog() {
		
		List<BlogResult> list = this.searchService.getBlogSearchList("kakao", "A", 1, 10);
		assertNotNull(list);
	}

}
