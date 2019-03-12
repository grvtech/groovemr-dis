package com.grvtech.dis.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.configuration.DisConfig;
import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.MessageResponse;

@Component
public class GRVRestClient {

	@Value("${uuidorganization}")
	private String uuidorganization;

	@Autowired
	private DisConfig config;

	public MessageResponse postRequest(String url, MessageRequest messageRequest) throws InvalidKeyException, BeansException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
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

		System.out.println("--------------------------------------------");
		System.out.println("----response : " + result.getElements());
		System.out.println("----response : " + result.getAction());
		System.out.println("----response : " + result.getStatus());
		System.out.println("----response : " + result.getUuidsession());
		System.out.println("--------------------------------------------");

		return result;
	}

	public MessageResponse clear(MessageResponse mres) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
		MessageResponse result = mres;

		ObjectNode ob = mres.getElements();

		Iterator<String> fields = ob.fieldNames();
		while (fields.hasNext()) {
			String field = fields.next();
			String scramble = ob.get(field).asText();
			System.out.println("-----------------------------------");
			System.out.println("-field :" + field + "   - scramble : " + scramble + "    - action :" + mres.getAction());
			System.out.println("-----------------------------------");
			if (mres.getUuidsession().toString().equals("00000000-0000-0000-0000-000000000000")) {
				ob.put(field, CryptoUtil.decrypt(mres.getAction(), scramble));
			} else {
				ob.put(field, CryptoUtil.decrypt(config.getLicence(), scramble));
			}
		}
		result.setElements(ob);
		return result;
	}

}
