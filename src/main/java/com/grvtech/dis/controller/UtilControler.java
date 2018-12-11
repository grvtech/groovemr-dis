package com.grvtech.dis.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.model.ClientMessageRequest;
import com.grvtech.dis.model.ClientMessageResponse;
import com.grvtech.dis.model.administration.Organization;
import com.grvtech.dis.service.administration.IOrganizationService;
import com.grvtech.dis.util.HttpUtil;

@RestController
public class UtilControler {

	@Autowired
	IOrganizationService orgservice;

	@RequestMapping(value = {"/util/licence"}, method = RequestMethod.POST)
	public ClientMessageResponse getLicence(final HttpServletRequest request) {
		ClientMessageResponse cmr = new ClientMessageResponse();
		ClientMessageRequest cmreq = new ClientMessageRequest();
		try {
			JsonNode jn = HttpUtil.getJSONFromPost(request);
			cmreq = new ClientMessageRequest(jn);
			Organization organization = orgservice.getOrganization();
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objNode = mapper.createObjectNode();
			cmr.setStatus("success");
			cmr.setState("enc");
			cmr.setAction(cmreq.getAction());
			// String license =
			// Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(sess));
			objNode.put("licence", organization.getLicence());
			cmr.setElements(objNode);

		} catch (ParseException | IOException e) {
			HashMap<String, String> messages = new HashMap<>();
			messages.put("error-" + cmr.getAction(), "error-" + cmr.getAction());
			return new ClientMessageResponse(false, cmreq, messages);
			// e.printStackTrace();
		}
		return cmr;
	}

}
