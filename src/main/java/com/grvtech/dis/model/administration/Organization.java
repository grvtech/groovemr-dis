package com.grvtech.dis.model.administration;

import java.util.Date;
import java.util.UUID;

import org.springframework.web.client.RestTemplate;

import com.grvtech.dis.model.User;

public class Organization {
	private int idorganization;
	private String nameOrganization;
	private UUID uuidorganization;
	private String codeorganization;
	private String licence;
	private Date lws; /* licensing window start */
	private Date lwe; /* licensing window end */
	public Organization(int idorganization, String nameOrganization, UUID uuidorganization, String codeorganization, String licence, Date lws, Date lwe) {
		super();
		this.idorganization = idorganization;
		this.nameOrganization = nameOrganization;
		this.uuidorganization = uuidorganization;
		this.codeorganization = codeorganization;
		this.licence = licence;
		this.lws = lws;
		this.lwe = lwe;
	}
	public Organization() {
		super();
		this.idorganization = 0;
	}

	public boolean isEmpty() {
		boolean result = true;
		if (this.idorganization != 0)
			result = false;
		return result;
	}

	public Organization(UUID organization) {
		RestTemplate restTemplate = new RestTemplate();
		this = restTemplate.postForEntity("http://" + serverCore + "user/gubup", mapper.writeValueAsString(mr), Organization.class);
		user = response.getBody();

	}

	/**
	 * @return the idorganization
	 */
	public int getIdorganization() {
		return idorganization;
	}
	/**
	 * @param idorganization
	 *            the idorganization to set
	 */
	public void setIdorganization(int idorganization) {
		this.idorganization = idorganization;
	}
	/**
	 * @return the nameOrganization
	 */
	public String getNameOrganization() {
		return nameOrganization;
	}
	/**
	 * @param nameOrganization
	 *            the nameOrganization to set
	 */
	public void setNameOrganization(String nameOrganization) {
		this.nameOrganization = nameOrganization;
	}
	/**
	 * @return the uuidorganization
	 */
	public UUID getUuidorganization() {
		return uuidorganization;
	}
	/**
	 * @param uuidorganization
	 *            the uuidorganization to set
	 */
	public void setUuidorganization(UUID uuidorganization) {
		this.uuidorganization = uuidorganization;
	}
	/**
	 * @return the codeorganization
	 */
	public String getCodeorganization() {
		return codeorganization;
	}
	/**
	 * @param codeorganization
	 *            the codeorganization to set
	 */
	public void setCodeorganization(String codeorganization) {
		this.codeorganization = codeorganization;
	}
	/**
	 * @return the licence
	 */
	public String getLicence() {
		return licence;
	}
	/**
	 * @param licence
	 *            the licence to set
	 */
	public void setLicence(String licence) {
		this.licence = licence;
	}
	/**
	 * @return the lws
	 */
	public Date getLws() {
		return lws;
	}
	/**
	 * @param lws
	 *            the lws to set
	 */
	public void setLws(Date lws) {
		this.lws = lws;
	}
	/**
	 * @return the lwe
	 */
	public Date getLwe() {
		return lwe;
	}
	/**
	 * @param lwe
	 *            the lwe to set
	 */
	public void setLwe(Date lwe) {
		this.lwe = lwe;
	}

}
