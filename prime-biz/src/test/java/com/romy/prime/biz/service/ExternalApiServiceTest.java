package com.romy.prime.biz.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.romy.prime.biz.config.RestTemplateConfig;

import net.sf.json.JSONObject;

/**
 * 
 * 외부 API 관련 Test
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RestTemplateConfig.class, ExternalApiService.class })
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class ExternalApiServiceTest {
	
	@Autowired
	private ExternalApiService externalApiService; 
	
	@Test
	void Given_RestTemplateConfig_When_ApiInfomationIsReady_Then_ApiIsCalled() {
		
		String serverHost = "https://dapi.kakao.com";
		String apiUri = "/v2/search/blog";

		JSONObject jsonHeaders = new JSONObject();
		jsonHeaders.put("Authorization", "KakaoAK ac53943962e89a7e05375d0d263fa1c5");

		Map<String, Object> params = new HashMap<>();
		params.put("query", "kakaobank");

		Object result = this.externalApiService.callExternalGetApi(serverHost + apiUri, jsonHeaders, params);
		assertNotNull(result);
	}

}
