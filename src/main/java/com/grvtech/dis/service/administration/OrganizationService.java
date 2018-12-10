package com.grvtech.dis.service.administration;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.administration.Organization;

public class OrganizationService implements IOrganizationService {

	@Value("${core.server}")
	private String serverCore;

	@Value("${app.id}")
	private String appid;

	@Value("${uuidorganization}")
	private String uuidorganization;

	@Override
	public Organization getOrganization() {
		/*
		 * this function goes directly to core to fetch organisation info There
		 * is no organisation info stored on the client app
		 * 
		 * the flow : user - client - core - client - user
		 * 
		 */

		Organization result = new Organization();
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uuidorganization", uuidorganization.toString());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("ApplicationID", appid);

		MessageRequest mr = new MessageRequest("0", "action", map);
		HttpEntity<MessageRequest> entity = new HttpEntity<MessageRequest>(mr, headers);
		ResponseEntity<Organization> response = restTemplate.postForEntity("http://" + serverCore + "/util/gl", mapper.writeValueAsString(mr), Organization.class);
		user = response.getBody();

		if (!user.isEmpty()) {
			addUser(user);
		}
		return result;
	}

}
