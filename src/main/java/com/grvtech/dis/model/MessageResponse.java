package com.grvtech.dis.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.util.CryptoUtil;

/*
{
	"uuidsession":"aaaaabbbb33333",
	"uuidevent":"addu",
	"state":"enc",
	"status":"success",
	"elements":[
	            "aaaaasdaedaecsecsecse",
	            "asdasdasdasdasdasdasdasd"
	]  
}


an event 

	anonymous events = everybody can do that
	event = login
		
	event = subscribe

	not anonymous 
	event  = search 
		controler resuests ......
		 
	event = view clical data vcd
			add clinical data
			edit clinical data
			delete clinical data
	
	event = view patient record
			add patient record
			edit patient record
			delete patient record
			
	event = view search patient
	
	
	authentification of event for user done on dis 
	only authentification of organisation should be done on core
	
*/

public class MessageResponse {

	private UUID uuidsession;
	private String status; // success|error
	private String action;
	private ObjectNode elements; // on error is empty

	@Autowired
	ApplicationContext context;

	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MessageResponse(boolean status, MessageRequest mr, HashMap<String, String> map) {
		ObjectMapper mapper = new ObjectMapper();
		if (status) {
			this.status = "success";
			this.uuidsession = mr.getUuidsession();
			this.action = mr.getAction();
			this.elements = mapper.createObjectNode();

			System.out.println("--------------------------------------");
			System.out.println("licence : " + context.getBean("licence").toString());
			System.out.println("--------------------------------------");

			Set<String> fields = map.keySet();
			for (String field : fields) {
				try {
					this.elements.put(field, CryptoUtil.decrypt(context.getBean("licence").toString(), map.get(field)));
				} catch (InvalidKeyException | BeansException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidAlgorithmParameterException
						| IllegalBlockSizeException | BadPaddingException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					this.status = "error";
					this.elements.put("error", "error message");
				}
			}
		} else {
			this.status = "error";
			Set<String> fields = map.keySet();
			for (String field : fields) {
				this.elements.put(field, map.get(field));
			}
		}
	}

	public void clear() throws InvalidKeyException, BeansException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException,
			UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, IOException {
		Iterator<String> fieldNames = this.elements.fieldNames();
		while (fieldNames.hasNext()) {
			String fieldName = fieldNames.next();
			String fieldValueScramble = this.elements.get(fieldName).asText();
			String fieldValue = CryptoUtil.decrypt(context.getBean("licence").toString(), fieldValueScramble);
			this.elements.put(fieldName, fieldValue);
		}
	}

	public MessageResponse(String uuidsession, String action, String status, ObjectNode elements) {
		super();
		this.uuidsession = UUID.fromString(uuidsession);
		this.status = status;
		this.elements = elements;
	}
	/**
	 * @return the uuidsession
	 */
	public UUID getUuidsession() {
		return uuidsession;
	}
	/**
	 * @param uuisession
	 *            the uuisession to set
	 */
	public void setUuidsession(UUID uuidsession) {
		this.uuidsession = uuidsession;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the elements
	 */
	public ObjectNode getElements() {
		return elements;
	}
	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(ObjectNode elements) {
		this.elements = elements;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}

}
