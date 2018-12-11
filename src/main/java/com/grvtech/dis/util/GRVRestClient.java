package com.grvtech.dis.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.MessageResponse;

public class GRVRestClient {

	static RestTemplate restTemplate = new RestTemplate();

	@Value("${core.server}")
	private String serverCore;

	@Value("${uuidorganization}")
	private static String uuidorganization;

	public static MessageResponse postRequest(String url, MessageRequest messageRequest) {
		MessageResponse result = new MessageResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.set("organization", uuidorganization);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<MessageResponse> respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, MessageResponse.class);
		result = respEntity.getBody();
		return result;
	}

}
