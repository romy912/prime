package com.romy.prime.api.popular;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.romy.prime.biz.entity.PopularTerms;
import com.romy.prime.biz.repository.PopularTermsRepository;
import com.romy.prime.biz.service.PopularTemsService;

/**
 * 
 * 인기 검색어 Controller Test
 *
 */
@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
@SpringBootTest(classes = { TermsSearchController.class, PopularTemsService.class, PopularTermsRepository.class })
class TermsSearchControllerTest {

	@Autowired
	private TermsSearchController termsSearchController;
	
	@MockBean
	private PopularTermsRepository popularTermsRepository;
	
	@Test
	void PopularTermsList() {
		
		List<PopularTerms> list = this.termsSearchController.popularTermsList();
		assertNotNull(list);
		
	}

}
