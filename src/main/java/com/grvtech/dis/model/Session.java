package com.grvtech.dis.model;

import java.util.Date;
import java.util.UUID;

public class Session {
	private UUID uuidsession;
	private UUID uuiduser;
	private Date startsession;

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

	public Session(UUID uuidsession, UUID uuiduser, Date startsession, Date endsession) {
		super();
		this.uuidsession = uuidsession;
		this.uuiduser = uuiduser;
		this.startsession = startsession;

	}
	public Session(UUID uuiduser) {
		this.uuiduser = uuiduser;
		this.uuidsession = UUID.randomUUID();
		this.startsession = new Date();

	}
	public Session() {
		super();
	}

}
