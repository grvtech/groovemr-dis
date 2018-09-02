package com.grvtech.dis.service;

import java.util.List;
import java.util.UUID;

import com.grvtech.dis.model.Session;



public interface ISessionService {
	List<Session> getAllActiveSessions();

	Session getSessionById(UUID SessionId);

	boolean addSession(Session Session);
	
	boolean expireSession(Session Session);

	void updateSession(Session Session);

	void deleteSession(UUID idSession);
}
