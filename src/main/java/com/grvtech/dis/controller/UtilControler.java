package com.grvtech.dis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.grvtech.dis.service.administration.IOrganizationService;

@RestController
public class UtilControler {

	@Autowired
	IOrganizationService orgservice;

	/*
	 * @RequestMapping(value = {"/util/licence"}, method = RequestMethod.POST)
	 * public ClientMessageResponse getLicence(final HttpServletRequest request)
	 * { ClientMessageResponse cmr = new ClientMessageResponse();
	 * ClientMessageRequest cmreq = new ClientMessageRequest(); try { JsonNode
	 * jn = HttpUtil.getJSONFromPost(request); cmreq = new
	 * ClientMessageRequest(jn); Organization organization =
	 * orgservice.getOrganizationLicence(); ObjectMapper mapper = new
	 * ObjectMapper(); ObjectNode objNode = mapper.createObjectNode();
	 * cmr.setStatus("success"); cmr.setState("enc");
	 * cmr.setAction(cmreq.getAction()); // String license = //
	 * Base64.getEncoder().encodeToString(mapper.writeValueAsBytes(sess));
	 * objNode.put("licence", organization.getLicence());
	 * cmr.setElements(objNode);
	 * 
	 * } catch (ParseException | IOException e) { HashMap<String, String>
	 * messages = new HashMap<>(); messages.put("error-" + cmr.getAction(),
	 * "error-" + cmr.getAction()); return new ClientMessageResponse(false,
	 * cmreq, messages); // e.printStackTrace(); } return cmr; }
	 */

}
