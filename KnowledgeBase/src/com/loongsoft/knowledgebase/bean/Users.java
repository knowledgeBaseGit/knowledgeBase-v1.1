package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private String userId;
	private Staff staff;
	private Role role;
	private String loginName;
	private String loginPassword;
	private String createDate;
	private Set<Solution> solutionsForCheckUser = new HashSet<Solution>(0);
	private Set<Files> filesesForCheckUser = new HashSet<Files>(0);
	private Set<Keyword> keywords = new HashSet<Keyword>(0);
	private Set<Questions> questionsesForCheckUser = new HashSet<Questions>(0);
	private Set<Questions> questionsesForWriteUser = new HashSet<Questions>(0);
	private Set<Solution> solutionsForWriteUser = new HashSet<Solution>(0);
	private Set<Files> filesesForCreateUser = new HashSet<Files>(0);

	public Users() {
	}

	public Users(Staff staff) {
		this.staff = staff;
	}

	public Users(Staff staff, Role role, String loginName,
			String loginPassword, String createDate,
			Set<Solution> solutionsForCheckUser,
			Set<Files> filesesForCheckUser, Set<Keyword> keywords,
			Set<Questions> questionsesForCheckUser,
			Set<Questions> questionsesForWriteUser,
			Set<Solution> solutionsForWriteUser, Set<Files> filesesForCreateUser) {
		this.staff = staff;
		this.role = role;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.createDate = createDate;
		this.solutionsForCheckUser = solutionsForCheckUser;
		this.filesesForCheckUser = filesesForCheckUser;
		this.keywords = keywords;
		this.questionsesForCheckUser = questionsesForCheckUser;
		this.questionsesForWriteUser = questionsesForWriteUser;
		this.solutionsForWriteUser = solutionsForWriteUser;
		this.filesesForCreateUser = filesesForCreateUser;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Set<Solution> getSolutionsForCheckUser() {
		return this.solutionsForCheckUser;
	}

	public void setSolutionsForCheckUser(Set<Solution> solutionsForCheckUser) {
		this.solutionsForCheckUser = solutionsForCheckUser;
	}

	public Set<Files> getFilesesForCheckUser() {
		return this.filesesForCheckUser;
	}

	public void setFilesesForCheckUser(Set<Files> filesesForCheckUser) {
		this.filesesForCheckUser = filesesForCheckUser;
	}

	public Set<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public Set<Questions> getQuestionsesForCheckUser() {
		return this.questionsesForCheckUser;
	}

	public void setQuestionsesForCheckUser(
			Set<Questions> questionsesForCheckUser) {
		this.questionsesForCheckUser = questionsesForCheckUser;
	}

	public Set<Questions> getQuestionsesForWriteUser() {
		return this.questionsesForWriteUser;
	}

	public void setQuestionsesForWriteUser(
			Set<Questions> questionsesForWriteUser) {
		this.questionsesForWriteUser = questionsesForWriteUser;
	}

	public Set<Solution> getSolutionsForWriteUser() {
		return this.solutionsForWriteUser;
	}

	public void setSolutionsForWriteUser(Set<Solution> solutionsForWriteUser) {
		this.solutionsForWriteUser = solutionsForWriteUser;
	}

	public Set<Files> getFilesesForCreateUser() {
		return this.filesesForCreateUser;
	}

	public void setFilesesForCreateUser(Set<Files> filesesForCreateUser) {
		this.filesesForCreateUser = filesesForCreateUser;
	}

}
