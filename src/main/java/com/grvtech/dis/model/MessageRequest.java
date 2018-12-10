package com.grvtech.dis.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.util.CryptoUtil;

public class MessageRequest {
	private UUID uuidsession;
	private UUID uuidorganization;
	private String action; // for logging and tracing
	private ObjectNode elements;

	@Autowired
	ApplicationContext context;

	public MessageRequest(String uuidorganization, String uuidsession, String action, HashMap<String, String> map) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
			NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		super();
		ObjectMapper mapper = new ObjectMapper();
		this.uuidsession = UUID.fromString(uuidsession);
		this.uuidorganization = UUID.fromString(uuidorganization);
		this.action = action;
		this.elements = mapper.createObjectNode();
		String cryptKey = "";
		if (uuidsession.equals("0")) {
			// this arrives only when we are making the firts requests
			// we are crypting with the action name
			cryptKey = this.action;
		} else {
			cryptKey = (String) context.getBean("license");
		}
		if (!map.isEmpty()) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key);
				String enc = CryptoUtil.encrypt(cryptKey, value);
				System.out.println("Encrypted value:" + enc + "    key:" + key);
				this.elements.put(key, enc);
			}
		}
	}

	public MessageRequest() {
		super();
	}
	public MessageRequest(String uuidorganization, String uuidsession, String action, ObjectNode elements) {
		super();
		this.uuidorganization = UUID.fromString(uuidorganization);
		this.uuidsession = UUID.fromString(uuidsession);
		this.action = action;
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
	 * @return the uuidsession
	 */
	public UUID getUuidsession() {
		return uuidsession;
	}

	/**
	 * @param uuidsession
	 *            the uuidsession to set
	 */
	public void setUuidsession(UUID uuidsession) {
		this.uuidsession = uuidsession;
	}

	/**
	 * @return the uuidorganization
	 */
	public UUID getUuidorganization() {
		return uuidorganization;
	}

	/**
	 * @param uuidorganization
	 *            the uuidorganization to set
	 */
	public void setUuidorganization(UUID uuidorganization) {
		this.uuidorganization = uuidorganization;
	}

}
