package com.grvtech.dis.model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * MessageRequest Format
 * {
 * 	uuidmessage
 * 	uuidorganization
 *  uuidsession
 *  state enc|clear
 * 	elements
 * 
 * 
 * }
 * 
 * */

public class ClientMessageRequest {
	private String state;
	private String action;
	private UUID uuidsession;
	private Date timestamp;
	private JsonNode elements;

	public ClientMessageRequest(JsonNode jn) throws ParseException, JsonParseException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyMMddhhmmss");
		// JsonNode jn = HttpUtil.getJSONFromPost(request);
		this.state = jn.get("state").toString().replaceAll("\"", "");
		this.action = jn.get("action").toString().replaceAll("\"", "");
		String uuid = jn.get("uuidsession").toString().replaceAll("\"", "");
		if (uuid.equals("")) {
			this.uuidsession = null;
		} else {
			this.uuidsession = UUID.fromString(uuid);
		}
		this.timestamp = sdf.parse(jn.get("timestamp").toString().replaceAll("\"", ""));
		String elems = "";
		if (this.state.equals("enc")) {
			elems = new String(Base64.getDecoder().decode(jn.get("elements").toString().replaceAll("\"", "")));
		} else {
			elems = jn.get("elements").toString().replaceAll("\\\\", "").replaceAll("^\"(.*)\"$", "$1");
		}
		JsonFactory factory = mapper.getFactory();
		JsonParser parser = factory.createParser(elems);
		this.elements = mapper.readTree(parser);
	}

	public ClientMessageRequest(String state, String action, UUID uuidsession, Date timestamp, JsonNode elements) {
		super();
		this.state = state;
		this.action = action;
		this.uuidsession = uuidsession;
		this.timestamp = timestamp;
		this.elements = elements;
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
	 * @return the elements
	 */
	public JsonNode getElements() {
		return elements;
	}

	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(JsonNode elements) {
		this.elements = elements;
	}

}
