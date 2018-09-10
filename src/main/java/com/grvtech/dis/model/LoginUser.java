package com.grvtech.dis.model;

public class LoginUser {
	private String uuiduser;
	private String lastname;
	private String firstname;
	private String username;
	private String password;
	private String matchingPassword;
	private String authmethod;

	/**
	 * @return the uuiduser
	 */
	public String getUuiduser() {
		return uuiduser;
	}

	/**
	 * @param uuiduser
	 *            the uuiduser to set
	 */
	public void setUuiduser(String uuiduser) {
		this.uuiduser = uuiduser;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the matchingPassword
	 */
	public String getMatchingPassword() {
		return matchingPassword;
	}

	/**
	 * @param matchingPassword
	 *            the matchingPassword to set
	 */
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	/**
	 * @return the authmethod
	 */
	public String getAuthmethod() {
		return authmethod;
	}

	/**
	 * @param authmethod
	 *            the authmethod to set
	 */
	public void setAuthmethod(String authmethod) {
		this.authmethod = authmethod;
	}

	public LoginUser(String uuiduser, String lastname, String firstname, String username, String password,
			String matchingPassword, String authmethod) {
		super();
		this.uuiduser = uuiduser;
		this.lastname = lastname;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.authmethod = authmethod;
	}

	public LoginUser() {
		super();

	}

}
