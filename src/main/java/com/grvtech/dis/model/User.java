package com.grvtech.dis.model;

import java.util.UUID;

public class User {
	private int iduser;
	private UUID uuiduser;
	private UUID uuidperson;
	private String username;
	private String password;
	private String email;
	private String pin;
	private String logo;
	private String securityimage;

	public User(int iduser, UUID uuiduser, UUID uuidperson, String username, String password, String email, String pin,
			String logo, String securityimage) {
		super();
		this.iduser = iduser;
		this.uuiduser = uuiduser;
		this.uuidperson = uuidperson;
		this.username = username;
		this.password = password;
		this.email = email;
		this.pin = pin;
		this.logo = logo;
		this.securityimage = securityimage;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the iduser
	 */
	public int getIduser() {
		return iduser;
	}

	/**
	 * @param iduser
	 *            the iduser to set
	 */
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	/**
	 * @return the uuiduser
	 */
	public UUID getUuiduser() {
		return uuiduser;
	}

	/**
	 * @param uuiduser
	 *            the uuiduser to set
	 */
	public void setUuiduser(UUID uuiduser) {
		this.uuiduser = uuiduser;
	}

	/**
	 * @return the uuidperson
	 */
	public UUID getUuidperson() {
		return uuidperson;
	}

	/**
	 * @param uuidperson
	 *            the uuidperson to set
	 */
	public void setUuidperson(UUID uuidperson) {
		this.uuidperson = uuidperson;
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}

	/**
	 * @param pin
	 *            the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}

	/**
	 * @return the logo
	 */
	public String getLogo() {
		return logo;
	}

	/**
	 * @param logo
	 *            the logo to set
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * @return the securityimage
	 */
	public String getSecurityimage() {
		return securityimage;
	}

	/**
	 * @param securityimage
	 *            the securityimage to set
	 */
	public void setSecurityimage(String securityimage) {
		this.securityimage = securityimage;
	}

	@Override
	public String toString() {
		return String.format("User [username=%s, password=%s]", username, password);
	}

	public boolean isEmpty() {
		boolean result = true;
		if (this.username != null && this.password != null) {
			result = false;
		}
		return result;
	}

}
