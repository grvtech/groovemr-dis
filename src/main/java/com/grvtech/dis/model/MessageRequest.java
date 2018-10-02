package com.grvtech.dis.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.util.CryptoUtil;

public class MessageRequest {
	private Date timestamp;
	private String action; // for logging and tracing
	private ObjectNode elements;

	public MessageRequest(Date timestamp, String action, HashMap<String, String> map) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		super();
		ObjectMapper mapper = new ObjectMapper();
		this.timestamp = timestamp;
		this.action = action;
		this.elements = mapper.createObjectNode();
		if (!map.isEmpty()) {
			Iterator<String> it = map.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = map.get(key);
				String enc = CryptoUtil.encrypt("test", value);
				System.out.println("Encrypted value:" + enc + "    key:" + key);
				this.elements.put(key, enc);
			}

		}
	}

	public MessageRequest() {
		super();
	}
	public MessageRequest(Date timestamp, String action, ObjectNode elements) {
		super();
		this.timestamp = timestamp;
		this.action = action;
		this.elements = elements;
	}
	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
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

}
