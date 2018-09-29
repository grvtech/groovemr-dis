package com.grvtech.dis.model;

import javax.servlet.http.HttpServletRequestWrapper;

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

public class MessageRequest {
	private String state;
	private String timestamp;
	private String elements;

	public MessageRequest(HttpServletRequestWrapper request) {
		this.state = request.getParameter("state");
		this.timestamp = request.getParameter("timestamp");

	}
}
