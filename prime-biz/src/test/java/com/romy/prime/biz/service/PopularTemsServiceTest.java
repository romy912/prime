package com.romy.prime.biz.service;

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

import com.romy.prime.biz.entity.PopularTerms;
import com.romy.prime.biz.exception.ValidationException;
import com.romy.prime.biz.repository.PopularTermsRepository;

/**
 * 
 * 인기 검색어 관련 Test
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { PopularTermsRepository.class, PopularTemsService.class })
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class PopularTemsServiceTest {

	@Autowired
	private PopularTemsService popularTemsService;
	
	@MockBean
	private PopularTermsRepository popularTermsRepository;
	
	@Test
	void Given_PopularTerm_Then_Save() {
		String terms = "kakao";
		this.popularTemsService.savePopularTerms(terms);
		
		List<PopularTerms> list = this.popularTemsService.getPopularTermsList();
		assertNotNull(list);
	}
	
	@Test
	void When_TermIsNull_Then_ValidationException() {
		
		assertThrows(ValidationException.class, () -> {
			this.popularTemsService.savePopularTerms(null);
		});
	}

}
