package com.grvtech.dis.model;

import java.util.ArrayList;
import java.util.Date;

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
	private String uuisession;
	private String uuidevent;
	private String state; // clear|enc
	private String status; // success|error
	private Date timestamp;
	private ArrayList<Object> elements; // on error is empty

	public MessageResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageResponse(String uuisession, String uuidevent, String state, String status, Date timestamp, ArrayList<Object> elements) {
		super();
		this.uuisession = uuisession;
		this.uuidevent = uuidevent;
		this.state = state;
		this.status = status;
		this.timestamp = timestamp;
		this.elements = elements;
	}
	/**
	 * @return the uuisession
	 */
	public String getUuisession() {
		return uuisession;
	}
	/**
	 * @param uuisession
	 *            the uuisession to set
	 */
	public void setUuisession(String uuisession) {
		this.uuisession = uuisession;
	}
	/**
	 * @return the uuidevent
	 */
	public String getUuidevent() {
		return uuidevent;
	}
	/**
	 * @param uuidevent
	 *            the uuidevent to set
	 */
	public void setUuidevent(String uuidevent) {
		this.uuidevent = uuidevent;
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
	public ArrayList<Object> getElements() {
		return elements;
	}
	/**
	 * @param elements
	 *            the elements to set
	 */
	public void setElements(ArrayList<Object> elements) {
		this.elements = elements;
	}

}
