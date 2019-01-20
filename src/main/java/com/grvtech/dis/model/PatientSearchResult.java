package com.grvtech.dis.model;

import java.util.List;

public class PatientSearchResult {
	private List<PatientResultItem> patients;

	/**
	 * @return the patients
	 */
	public List<PatientResultItem> getPatients() {
		return patients;
	}

	/**
	 * @param patients
	 *            the patients to set
	 */
	public void setPatients(List<PatientResultItem> patients) {
		this.patients = patients;
	}

	public PatientSearchResult(List<PatientResultItem> patients) {
		super();
		this.patients = patients;
	}

	public PatientSearchResult() {
		super();
		// TODO Auto-generated constructor stub
	}

}
