package com.grvtech.dis.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grvtech.dis.model.Session;
import com.grvtech.dis.repository.SessionRepository;

@Service
public class SessionService implements ISessionService {

	@Autowired
	SessionRepository repository;
	
	@Override
	public List<Session> getAllActiveSessions() {
		return repository.findAllActive();
	}

	@Override
	public Session getSessionById(UUID sessionId) {
		return repository.findById(sessionId);
	}

	@Override
	public boolean addSession(Session session) {
		boolean result = false;
		if(repository.insert(session) > 0) result = true;
		return result;
	}

	@Override
	public void updateSession(Session session) {
		repository.update(session);

	}

	@Override
	public void deleteSession(UUID idSession) {


	}

	@Override
	public boolean expireSession(Session Session) {
		boolean result = false;
		Session.setEndsession(new Date());
		if(repository.update(Session) > 0 )result = true;
		return result;
	}

}
