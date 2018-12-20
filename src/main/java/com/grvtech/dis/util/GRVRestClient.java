package com.grvtech.dis.util;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.MessageResponse;

@Component
public class GRVRestClient {

	@Value("${uuidorganization}")
	private String uuidorganization;

	public MessageResponse postRequest(String url, MessageRequest messageRequest) {
		MessageResponse result = new MessageResponse();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		System.out.println("Organization from grv rest client :" + uuidorganization);

		headers.set("organization", uuidorganization);
		headers.set("radu", "gabor");
		headers.set("user-agent", "grv");
		HttpEntity<MessageRequest> entity = new HttpEntity<MessageRequest>(messageRequest, headers);

		System.out.println("url : " + url);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MessageResponse> respEntity = restTemplate.exchange(url, HttpMethod.POST, entity, MessageResponse.class);
		result = respEntity.getBody();
		return result;
	}

}
