package com.grvtech.dis.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private int iduser;
	private UUID uuiduser;
	private UUID uuidperson;
	private UUID uuidrole;
	private UUID uuidgroup;
	private String username;
	private String password;
	private String email;
	private String pin;
	private String logo; // can be an url or a name of a local image
	private String securityimage;
	private String authmethod;
	private String language;
	private Date created;
	private Date modified;
	private UUID createdBy;
	private UUID modifiedBy;

	public User() {
		super();
		this.iduser = 0;
	}

	public User(int iduser, UUID uuiduser, UUID uuidperson, UUID uuidrole, UUID uuidgroup, String username, String password, String email, String pin, String logo, String securityimage,
			String authmethod, String language, Date created, Date modified, UUID createdBy, UUID modifiedBy) {
		super();
		this.iduser = iduser;
		this.uuiduser = uuiduser;
		this.uuidperson = uuidperson;
		this.uuidrole = uuidrole;
		this.uuidgroup = uuidgroup;
		this.username = username;
		this.password = password;
		this.email = email;
		this.pin = pin;
		this.logo = logo;
		this.securityimage = securityimage;
		this.authmethod = authmethod;
		this.language = language;
		this.created = created;
		this.modified = modified;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	@JsonIgnore
	public boolean isEmpty() {
		return (this.iduser == 0);
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
	 * @return the uuidrole
	 */
	public UUID getUuidrole() {
		return uuidrole;
	}

	/**
	 * @param uuidrole
	 *            the uuidrole to set
	 */
	public void setUuidrole(UUID uuidrole) {
		this.uuidrole = uuidrole;
	}

	/**
	 * @return the uuidgroup
	 */
	public UUID getUuidgroup() {
		return uuidgroup;
	}

	/**
	 * @param uuidgroup
	 *            the uuidgroup to set
	 */
	public void setUuidgroup(UUID uuidgroup) {
		this.uuidgroup = uuidgroup;
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

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the createdBy
	 */
	public UUID getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(UUID createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the modifiedBy
	 */
	public UUID getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy
	 *            the modifiedBy to set
	 */
	public void setModifiedBy(UUID modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

}
