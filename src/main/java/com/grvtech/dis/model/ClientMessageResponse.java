package com.grvtech.dis.model;

import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.grvtech.dis.util.xxtea.XXTEA;

public class ClientMessageResponse {
	private String status; // success | error
	private String state;
	private String action;
	private ObjectNode elements;

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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
		// this.elements = elements;
		ObjectMapper mapper = new ObjectMapper();
		this.elements = mapper.createObjectNode();
		if (this.state.equals("enc")) {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, XXTEA.encryptToBase64String(elements.get(fieldName).asText(), this.action));
			}
		} else {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, elements.get(fieldName).asText());
			}
		}

	}

	public ClientMessageResponse(String status, String state, String action, ObjectNode elements) {
		super();
		ObjectMapper mapper = new ObjectMapper();
		this.status = status;
		this.state = state;
		this.action = action;
		// this.elements = elements;

		this.elements = mapper.createObjectNode();
		if (this.state.equals("enc")) {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, XXTEA.encryptToBase64String(elements.get(fieldName).asText(), this.action));
			}
		} else {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, elements.get(fieldName).asText());
			}
		}
	}

	public ClientMessageResponse(boolean status, ClientMessageRequest request, HashMap<String, String> messages) {
		super();
		ObjectMapper mapper = new ObjectMapper();
		if (status) {
			this.status = "success";
		} else {
			this.status = "error";
		}
		this.state = request.getState();
		this.action = request.getAction();
		JsonNode elements = request.getElements();
		this.elements = mapper.createObjectNode();
		if (this.state.equals("enc")) {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				// this.elements.put(fieldName,
				// XXTEA.encryptToBase64String(elements.get(fieldName).asText(),
				// this.action));
				this.elements.put(fieldName, Base64.getEncoder().encodeToString(elements.get(fieldName).asText().getBytes()));
			}
		} else {
			Iterator<String> fieldNames = elements.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, elements.get(fieldName).asText());
			}
		}
		if (messages != null && !messages.isEmpty()) {
			Set<String> keys = messages.keySet();
			for (String key : keys) {
				if (this.state.equals("enc")) {
					this.elements.put(key, Base64.getEncoder().encodeToString(elements.get(key).asText().getBytes()));
				} else {
					this.elements.put(key, messages.get(key));
				}
			}
		}

	}

	public ClientMessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
