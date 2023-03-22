package com.romy.prime.biz.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.romy.prime.biz.entity.ServerInfo;
import com.romy.prime.biz.repository.ServerInfoRepository;

/**
 * 
 * 서버 정보 관련 Test
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { ServerInfoRepository.class, ServerInfoService.class })
@TestPropertySource(properties = "spring.config.location=classpath:application-test.yml")
class ServerInfoServiceTest {

	@Autowired
	private ServerInfoService  serverInfoService;
	
	@MockBean
	private ServerInfoRepository serverInfoRepository;
	
	@Test
	void ServerInfoIsNotNull() {
		List<ServerInfo> list = this.serverInfoService.findServerInfoList(null, null);
		assertNotNull(list);
	}

}
