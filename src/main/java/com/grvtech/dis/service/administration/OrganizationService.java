package com.grvtech.dis.service.administration;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.User;
import com.grvtech.dis.model.administration.Organization;

public class OrganizationService implements IOrganizationService {

	@Value("${core.server}")
	private String serverCore;

	@Value("${app.id}")
	private String appid;

	@Override
	public Organization getOrganizationByUUID(UUID uuidorganization) {
		Organization result = new Organization();
		RestTemplate restTemplate = new RestTemplate();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uuidorganization", username);
		map.put("password", password);

		MessageRequest mr = new MessageRequest(new Date(), "action", map);
		new HttpEntity<MessageRequest>(mr, headers);
		ResponseEntity<User> response;
		result = restTemplate.postForEntity("http://" + serverCore + "/util/gubup", mapper.writeValueAsString(mr), User.class);
		user = response.getBody();

		if (!user.isEmpty()) {
			addUser(user);
		}
		return result;
	}

}
