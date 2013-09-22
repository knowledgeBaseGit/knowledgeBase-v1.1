package com.loongsoft.knowledgebase.util.utilBean;

import com.loongsoft.knowledgebase.bean.Project;

/**
 * 项目类别辅助bean
 * 
 * @author suoyanming 2013-8-22
 * 
 */
public class UtilProjectBean {
	/**
	 * 项目类别
	 */
	private Integer projectId;
	/**
	 * 父级项目Id
	 */
	private Integer parentProId;

	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目创建时间
	 */
	private String createTime;
	private String parentProName;
	/**
	 * 项目描述
	 */
	private String projectDesc;

	/**
	 * 项目类别创建人
	 */
	private String creater;
	
	

	public String getParentProName() {
		return parentProName;
	}

	public void setParentProName(String parentProName) {
		this.parentProName = parentProName;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	 

	public Integer getParentProId() {
		return parentProId;
	}

	public void setParentProId(Integer parentProId) {
		this.parentProId = parentProId;
	}
    
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

}
