package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

/**
 * Staff generated by hbm2java
 */
public class Staff implements java.io.Serializable {

	private String staffId;
	private String staffName;
	private String staffTel;
	private String department;
	private String entryDate;
	private Users users;

	public Staff() {
	}

	public Staff(String staffId) {
		this.staffId = staffId;
	}

	public Staff(String staffId, String staffName, String staffTel,
			String department, String entryDate, Users users) {
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffTel = staffTel;
		this.department = department;
		this.entryDate = entryDate;
		this.users = users;
	}

	public String getStaffId() {
		return this.staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffTel() {
		return this.staffTel;
	}

	public void setStaffTel(String staffTel) {
		this.staffTel = staffTel;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getEntryDate() {
		return this.entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}