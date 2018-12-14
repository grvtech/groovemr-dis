package com.grvtech.dis.service.administration;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.grvtech.dis.model.MessageRequest;
import com.grvtech.dis.model.MessageResponse;
import com.grvtech.dis.util.GRVRestClient;

@Component
public class OrganizationService implements IOrganizationService {

	@Value("${core.server}")
	private String serverCore;

	@Value("${uuidorganization}")
	private String uuidorganization;

	@Value("${emptysession}")
	private String emptysession;

	@Autowired
	GRVRestClient grvrc;

	@Override
	public String getOrganizationLicence() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		/*
		 * this function goes directly to core to fetch organisation info There
		 * is no organisation info stored on the client app
		 * 
		 * the flow : user - client - core - client - user
		 * 
		 */
		String result = "";
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("uuidorganization", uuidorganization.toString());
		MessageRequest mreq = new MessageRequest(uuidorganization, emptysession, "gl", map);
		String url = "http://" + serverCore + "/util/gl";
		MessageResponse mres = grvrc.postRequest(url, mreq);

		result = mres.getElements().get("licence").asText();
		return result;
	}

}
