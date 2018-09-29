package com.grvtech.dis.model;

public class MessageResponse {
	private String status; // success | error
	private String timestamp;
	private String message;
	private String state;
	private String elements;

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
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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
	public String getElements() {
		return elements;
	}

	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(String elements) {
		this.elements = elements;
	}

	public MessageResponse(String status, String timestamp, String message, String state, String elements) {
		super();
		this.status = status;
		this.timestamp = timestamp;
		this.message = message;
		this.state = state;
		this.elements = elements;
	}

	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
