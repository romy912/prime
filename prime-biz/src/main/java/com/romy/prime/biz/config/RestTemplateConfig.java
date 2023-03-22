package com.romy.prime.biz.config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RestTemplate Config
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.rest-template", value = "enable", havingValue = "true")
public class RestTemplateConfig implements DisposableBean {

	@Value("${application.rest.conn.timeout:5000}")
	private int connTimeout;

	@Value("${application.rest.conn.request.timeout:5000}")
	private int connRequestTimeout;

	@Value("${application.rest.conn.live.time:10}")
	private long connTimeToLive;

	@Value("${application.rest.socket.timeout:5000}")
	private int socketTimeout;

	private PoolingHttpClientConnectionManager connectionManager;

	/**
	 * Default Constructor
	 */
	public RestTemplateConfig(@Value("${application.rest.pool.conn.total:100}") int connTotal,
			@Value("${application.rest.pool.conn.route:50}") int connRoute) {
		this.connectionManager = new PoolingHttpClientConnectionManager();

		this.connectionManager.setMaxTotal(connTotal);
		this.connectionManager.setDefaultMaxPerRoute(connRoute);
	}

	@Bean
	public RestTemplate restTemplate() {

		RestTemplate restTemplate = new RestTemplate();

		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(this.connTimeout)
				.setConnectionRequestTimeout(this.connRequestTimeout).setSocketTimeout(this.socketTimeout).build();

		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(this.connectionManager)
				.setConnectionTimeToLive(this.connTimeToLive, TimeUnit.SECONDS).setDefaultRequestConfig(requestConfig)
				.setSSLHostnameVerifier((hostname, session) -> hostname.equalsIgnoreCase(session.getPeerHost()))
				.build();

		restTemplate.setRequestFactory(
				new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient)));

		List<HttpMessageConverter<?>> converters = new ArrayList<>();

		converters.add(this.getMappingJackson2HttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
		converters.add(new FormHttpMessageConverter());

		restTemplate.setMessageConverters(converters);

		return restTemplate;
	}

	private MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
		ObjectMapper mapper = new ObjectMapper();

		return new MappingJackson2HttpMessageConverter(mapper);
	}

	/**
	 * {@link DisposableBean#destroy()}
	 */
	@Override
	public void destroy() throws Exception {
		if (this.connectionManager != null) {
			this.connectionManager.close();
			this.connectionManager = null;
		}
	}

}
