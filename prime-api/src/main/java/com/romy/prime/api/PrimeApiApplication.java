package com.romy.prime.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.romy.prime")
@EntityScan(basePackages = {"com.romy.prime.biz.entity"})
@EnableJpaRepositories(basePackages = {"com.romy.prime.biz.repository"})
public class PrimeApiApplication {

	public static void main(String[] args) {
		// prime-biz module application yml read
		System.setProperty("spring.config.name", "application,application-biz");
		SpringApplication.run(PrimeApiApplication.class, args);
	}

}
