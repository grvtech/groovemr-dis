package com.grvtech.dis.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.grvtech.dis.model.User;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate jdbcTemplate;

	class UserRowMapper implements RowMapper<User> {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			if (rs.isBeforeFirst()) {
				user.setIduser(rs.getInt("iduser"));
				user.setUsername(rs.getString("username"));
				user.setUuidperson(UUID.fromString(rs.getString("uuidperson")));
				user.setUuiduser(UUID.fromString(rs.getString("uuiduser")));

				user.setPassword(rs.getString("password"));
				user.setPin(rs.getString("pin"));
				user.setLogo(rs.getString("logo"));
				user.setSecurityimage(rs.getString("securityimage"));
			}
			return user;
		}

	}

	public List<User> findAll() {
		return jdbcTemplate.query("select * from user", new UserRowMapper());
	}

	public User findById(long iduser) {
		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query("select * from user where iduser='" + iduser + "'",
				new BeanPropertyRowMapper<User>(User.class));
		// User user = jdbcTemplate.queryForObject("select * from user where iduser=?",
		// new Object[] { iduser }, new BeanPropertyRowMapper<User>(User.class));
		User user = new User();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	/* username and password */
	public User findByUP(String username, String password) {
		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query(
				"select * from user where username='" + username + "' and password = '" + password + "'",
				new BeanPropertyRowMapper<User>(User.class));
		// User user = jdbcTemplate.queryForObject("select * from user where iduser=?",
		// new Object[] { iduser }, new BeanPropertyRowMapper<User>(User.class));
		User user = new User();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	/* username and pin */
	public User findByUPin(String username, String pin) {
		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query(
				"select * from user where username='" + username + "' and pin = '" + pin + "'",
				new BeanPropertyRowMapper<User>(User.class));
		// User user = jdbcTemplate.queryForObject("select * from user where iduser=?",
		// new Object[] { iduser }, new BeanPropertyRowMapper<User>(User.class));
		User user = new User();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	/* username and pin */
	public User findByUI(String username, String securityimage) {
		ArrayList<User> users = (ArrayList<User>) jdbcTemplate.query(
				"select * from user where username='" + username + "' and securityimage = '" + securityimage + "'",
				new BeanPropertyRowMapper<User>(User.class));
		// User user = jdbcTemplate.queryForObject("select * from user where iduser=?",
		// new Object[] { iduser }, new BeanPropertyRowMapper<User>(User.class));
		User user = new User();
		if (users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}

	public int deleteById(long iduser) {
		return jdbcTemplate.update("delete from user where iduser=?", new Object[] { iduser });
	}

	public int insert(User user) {
		return jdbcTemplate.update(
				"insert into user (iduser, uuidperson,uuiduser, username, password, pin, logo, securityimage, email ) "
						+ "values(?, ?, ?, ?, ?, ?, ?, ?)",
				new Object[] { user.getIduser(), user.getUuidperson(), user.getUuiduser(), user.getUsername(),
						user.getPassword(), user.getPin(), user.getLogo(), user.getSecurityimage(), user.getEmail() });
	}

	public int update(User user) {
		return jdbcTemplate.update(
				"update user " + " set username = ?, password = ?, pin = ?, logo= ?, securityimage = ?, email = ? "
						+ " where iduser = ?",
				new Object[] { user.getUsername(), user.getPassword(), user.getPin(), user.getLogo(),
						user.getSecurityimage(), user.getEmail(), user.getIduser() });
	}

}
