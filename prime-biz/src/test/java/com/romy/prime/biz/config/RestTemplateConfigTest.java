package com.romy.prime.biz.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * RestTemplate Config Test
 *
 */
@ExtendWith(SpringExtension.class)
class RestTemplateConfigTest {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

	@Test
	void Given_Property_When_ConfigIsLoaded_Then_BeanIsCreated() {

		this.contextRunner
				/* Given Property */
				.withPropertyValues("spring.rest-template.enable=true")
				/* When Configuration Loaded */
				.withUserConfiguration(RestTemplateConfig.class)
				.run(context -> {
					assertThat(context).hasSingleBean(RestTemplate.class);
				});
	}

}
