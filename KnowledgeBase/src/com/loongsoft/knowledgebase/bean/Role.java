package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Role generated by hbm2java
 */
public class Role implements java.io.Serializable {

	private String roleId;
	private String roleName;
	private String remark;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<FunRole> funRoles = new HashSet<FunRole>(0);

	public Role() {
	}

	public Role(String roleId) {
		this.roleId = roleId;
	}

	public Role(String roleId, String roleName, String remark,
			Set<Users> userses, Set<FunRole> funRoles) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.remark = remark;
		this.userses = userses;
		this.funRoles = funRoles;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	public Set<FunRole> getFunRoles() {
		return this.funRoles;
	}

	public void setFunRoles(Set<FunRole> funRoles) {
		this.funRoles = funRoles;
	}

}
