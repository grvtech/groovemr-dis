package com.grvtech.dis.model;

public class PatientResultItem {
	private String firstname;
	private String lastname;
	private String ramq;
	private String chart;
	private String community;
	private String mid;
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
	 * @return the ramq
	 */
	public String getRamq() {
		return ramq;
	}
	/**
	 * @param ramq
	 *            the ramq to set
	 */
	public void setRamq(String ramq) {
		this.ramq = ramq;
	}
	/**
	 * @return the chart
	 */
	public String getChart() {
		return chart;
	}
	/**
	 * @param chart
	 *            the chart to set
	 */
	public void setChart(String chart) {
		this.chart = chart;
	}
	/**
	 * @return the community
	 */
	public String getCommunity() {
		return community;
	}
	/**
	 * @param community
	 *            the community to set
	 */
	public void setCommunity(String community) {
		this.community = community;
	}
	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}
	/**
	 * @param mid
	 *            the mid to set
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}
	public PatientResultItem(String firstname, String lastname, String ramq, String chart, String community, String mid) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.ramq = ramq;
		this.chart = chart;
		this.community = community;
		this.mid = mid;
	}
	public PatientResultItem() {
		super();
		// TODO Auto-generated constructor stub
	}

}
