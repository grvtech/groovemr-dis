package com.grvtech.dis.controller;

import java.util.Base64;

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
import com.grvtech.dis.util.HttpUtil;

@RestController
public class UtilControler {

	@RequestMapping(value = {"/util/licence"}, method = RequestMethod.POST)
	public ClientMessageResponse getLicence(final HttpServletRequest request) {
		JsonNode jn = HttpUtil.getJSONFromPost(request);
		ClientMessageRequest cmreq = new ClientMessageRequest(jn);
		Organization org = 
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objNode = mapper.createObjectNode();
		ClientMessageResponse cmr = new ClientMessageResponse();
		cmr.setStatus("success");
		cmr.setState("clear");
		String license = Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(sess));
		objNode.put("licence", v)
		cmr.set
		return cmr;
	}

}
