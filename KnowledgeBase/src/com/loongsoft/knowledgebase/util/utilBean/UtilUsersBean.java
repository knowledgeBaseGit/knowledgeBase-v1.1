package com.loongsoft.knowledgebase.util.utilBean;

import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;

/**
 * 用户辅助bean
 * 
 * @author suoyanming 2013-8-22
 * 
 */

public class UtilUsersBean {
	/**
	 * 用户Id
	 */
	private String userId;

	/**
	 * 员工信息
	 */
	private UtilStaffBean staff;

	/**
	 * 员工所属部门
	 */
	private String department;
	/**
	 * 联系方式
	 */
	private String tel;
	/**
	 * 用户角色
	 */
	private UtilRoleBean role;
	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 用户名称
	 */
	private String loginName;
	/**
	 * 用户登录密码
	 */
	private String loginPassword;
	/**
	 * 用户创建时间
	 */
	private String createDate;

	/**
	 * 入职日期
	 * 
	 */
	private String entryDate;
	/**
	 * 员工姓名
	 * 
	 */
	private String staffName;

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UtilStaffBean getStaff() {
		return staff;
	}

	public void setStaff(UtilStaffBean staff) {
		this.staff = staff;
	}

	public UtilRoleBean getRole() {
		return role;
	}

	public void setRole(UtilRoleBean role) {
		this.role = role;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
