package com.grvtech.dis.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.grvtech.dis.model.Session;
import com.grvtech.dis.model.User;

@Repository
public class SessionRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class SessionRowMapper implements RowMapper<Session> {
		@Override
		public Session mapRow(ResultSet rs, int rowNum) throws SQLException {
			Session session = new Session();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			if (rs.isBeforeFirst()) {
				session.setUuidsession((UUID)rs.getObject("uuidsession"));
				session.setUuiduser(UUID.fromString(rs.getString("uuiduser")));
				session.setStartsession(rs.getDate("startsession"));
				session.setEndsession(rs.getDate("endsession"));
			}
			return session;
		}
	}

	public List<Session> findAll() {
		return jdbcTemplate.query("select * from session", new SessionRowMapper());
	}

	public List<Session> findAllActive() {
		return jdbcTemplate.query("select * from session where endsession >= CURRENT_TIMESTAMP()", new SessionRowMapper());
	}
	
	public Session findById(UUID sessionid) {
		ArrayList<Session> sessions = (ArrayList<Session>) jdbcTemplate.query("select * from session where uuidsession='" + sessionid.toString() + "'",
				new BeanPropertyRowMapper<Session>(Session.class));
		Session session = new Session();
		if (sessions.size() > 0) {
			session = sessions.get(0);
		}
		return session;
	}


	/* username and pin */
	public List<Session> findByUser(UUID uuiduser) {
		ArrayList<Session> sessions = (ArrayList<Session>) jdbcTemplate.query("select * from session where uuiduser='" + uuiduser.toString() + "' and endsession >= CURRENT_TIMESTAMP()",
				new BeanPropertyRowMapper<Session>(Session.class));
		return sessions;
	}


	public int deleteById(UUID uuidsession) {
		return jdbcTemplate.update("delete from session where uuidsession=?", new Object[] { uuidsession.toString() });
	}
	
	

	public int insert(Session session) {
		return jdbcTemplate.update(
				"insert into session (uuidsession,uuiduser,startsession,endsession) "
						+ "values(?, ?, ?, ?)",
				new Object[] { session.getUuidsession(), session.getUuiduser(), session.getStartsession(), session.getEndsession()});
	}

	public int update(Session session) {
		return jdbcTemplate.update(
				"update session " + " set endsession = ?",
				new Object[] {session.getEndsession() });
	}

}
