package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Module generated by hbm2java
 */
public class Module implements java.io.Serializable {

	private String moduleId;
	private Module module;
	private String moduleUrl;
	private Integer isDestroy;
	private String destroyDate;
	private String moduleName;
	private Set<Module> modules = new HashSet<Module>(0);
	private Set<Functions> functionses = new HashSet<Functions>(0);

	public Module() {
	}

	public Module(String moduleId) {
		this.moduleId = moduleId;
	}

	public Module(String moduleId, Module module, String moduleUrl,
			Integer isDestroy, String destroyDate, String moduleName,
			Set<Module> modules, Set<Functions> functionses) {
		this.moduleId = moduleId;
		this.module = module;
		this.moduleUrl = moduleUrl;
		this.isDestroy = isDestroy;
		this.destroyDate = destroyDate;
		this.moduleName = moduleName;
		this.modules = modules;
		this.functionses = functionses;
	}

	public String getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public String getModuleUrl() {
		return this.moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Integer getIsDestroy() {
		return this.isDestroy;
	}

	public void setIsDestroy(Integer isDestroy) {
		this.isDestroy = isDestroy;
	}

	public String getDestroyDate() {
		return this.destroyDate;
	}

	public void setDestroyDate(String destroyDate) {
		this.destroyDate = destroyDate;
	}

	public String getModuleName() {
		return this.moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Set<Module> getModules() {
		return this.modules;
	}

	public void setModules(Set<Module> modules) {
		this.modules = modules;
	}

	public Set<Functions> getFunctionses() {
		return this.functionses;
	}

	public void setFunctionses(Set<Functions> functionses) {
		this.functionses = functionses;
	}

}