package com.grvtech.dis.model;

import java.util.Date;
import java.util.UUID;

public class Session {
	private UUID uuidsession;
	private UUID uuiduser;
	private Date startsession;
	private Date endsession;
	public UUID getUuidsession() {
		return uuidsession;
	}
	public void setUuidsession(UUID uuidsession) {
		this.uuidsession = uuidsession;
	}
	public UUID getUuiduser() {
		return uuiduser;
	}
	public void setUuiduser(UUID uuiduser) {
		this.uuiduser = uuiduser;
	}
	public Date getStartsession() {
		return startsession;
	}
	public void setStartsession(Date startsession) {
		this.startsession = startsession;
	}
	public Date getEndsession() {
		return endsession;
	}
	public void setEndsession(Date endsession) {
		this.endsession = endsession;
	}
	public Session(UUID uuidsession, UUID uuiduser, Date startsession, Date endsession) {
		super();
		this.uuidsession = uuidsession;
		this.uuiduser = uuiduser;
		this.startsession = startsession;
		this.endsession = endsession;
	}
	public Session() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
