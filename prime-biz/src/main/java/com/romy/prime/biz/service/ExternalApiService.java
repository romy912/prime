package com.romy.prime.biz.service;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 
 * 외부 서비스 관련
 *
 */
@Service
@SuppressWarnings("unchecked")
public class ExternalApiService {

	@Autowired
	private RestTemplate restTemplate;
	
	private static final char QP_SEP_A = '&';
    private static final String NAME_VALUE_SEPARATOR = "=";

	
	/**
	 * 헤더 셋팅
	 * @param headers
	 * @return
	 */
	private HttpHeaders getHttpHeaders(JSONObject headers) {

		HttpHeaders reqHeaders = new HttpHeaders();

		if (!JSONUtils.isNull(headers)) {
			Set<String> keys = headers.keySet();
			for (String key : keys) {
				reqHeaders.add(key, headers.getString(key));
			}
		}

		return reqHeaders;
	}
	
	public Object callExternalGetApi(@NonNull String url, JSONObject headers, Map<String, Object> params) {

		HttpHeaders reqHeaders = this.getHttpHeaders(headers);
		
		if(params != null) {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (sb.length() > 0) {
					sb.append(QP_SEP_A);
				}
				sb.append(entry.getKey());
				sb.append(NAME_VALUE_SEPARATOR);
				sb.append(String.valueOf(entry.getValue()));
			}
			url += "?" + sb.toString();
		}

		ResponseEntity<Object> response = this.restTemplate.exchange(url, HttpMethod.GET,
				new HttpEntity<>(reqHeaders), new ParameterizedTypeReference<Object>() {
				});
		
		if (response != null && response.getBody() != null) {
			return response.getBody();
		}
		
		return null;
	}

}
