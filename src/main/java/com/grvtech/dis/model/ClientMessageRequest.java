package com.grvtech.dis.model;

import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;
import java.util.Iterator;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ClientMessageRequest {
	private UUID uuidsession;
	private String action;
	private String state; // should always be encrypted ??? enc|clear
	private ObjectNode elements;

	public ClientMessageRequest(JsonNode jn) throws ParseException, JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		if (!jn.get("state").isMissingNode()) {
			this.state = jn.get("state").asText();
		} else {
			this.state = "enc"; // always enc by default
		}
		this.state = jn.get("state").asText();
		this.action = jn.get("action").asText();
		this.uuidsession = UUID.fromString(jn.get("uuidsession").asText());
		JsonNode elems = jn.get("elements");
		this.elements = mapper.createObjectNode();
		if (this.state.equals("enc")) {
			Iterator<String> fieldNames = elems.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				// this.elements.put(fieldName,
				// XXTEA.decryptBase64StringToString(elems.get(fieldName).asText(),
				// this.action));
				String scramble = elems.get(fieldName).asText();
				// this.elements.put(fieldName,
				// XXTEA.decryptToString(scramble.getBytes(), this.action));
				this.elements.put(fieldName, new String(Base64.getDecoder().decode(scramble.getBytes())));

				System.out.println(fieldName + "   action : " + this.action + "   " + scramble + "  decrypt : " + new String(Base64.getDecoder().decode(scramble.getBytes())));
			}
		} else {
			Iterator<String> fieldNames = elems.fieldNames();
			while (fieldNames.hasNext()) {
				String fieldName = fieldNames.next();
				this.elements.put(fieldName, elems.get(fieldName).asText());
			}
		}
	}

	public ClientMessageRequest(String state, String action, ObjectNode elements, UUID uuidsession) {
		super();
		this.state = state;
		this.action = action;
		this.elements = elements;
		this.uuidsession = uuidsession;
	}

	public ClientMessageRequest() {
		super();
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
	 * @return the elements
	 */
	public JsonNode getElements() {
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
