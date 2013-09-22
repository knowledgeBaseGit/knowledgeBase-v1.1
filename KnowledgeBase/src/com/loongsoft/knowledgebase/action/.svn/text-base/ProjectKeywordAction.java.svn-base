package com.loongsoft.knowledgebase.action;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.service.ProjectKeywordService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @描述 项目关键词的操作
 * @author 张瑞祥 2013-9-11
 */
public class ProjectKeywordAction extends ActionSupport{
	/**
	 * 项目id
	 */
	private Integer projectId;
	/**
	 * ProjectKeyword的业务实现
	 */
	private ProjectKeywordService projectKeywordService;
	/**
	 * ProjectKeyword的实体类
	 */
	private ProjectKeyword proKey;
	
	private String keywordIds;
	
	public String getKeywordIds() {
		return keywordIds;
	}



	public void setKeywordIds(String keywordIds) {
		this.keywordIds = keywordIds;
	}



	public ProjectKeyword getProKey() {
		return proKey;
	}



	public void setProKey(ProjectKeyword proKey) {
		this.proKey = proKey;
	}



	public Integer getProjectId() {
		return projectId;
	}



	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}



	public ProjectKeywordService getProjectKeywordService() {
		return projectKeywordService;
	}



	public void setProjectKeywordService(ProjectKeywordService projectKeywordService) {
		this.projectKeywordService = projectKeywordService;
	}



	/**
	 * 上传动态修改后的关键词
	 * @return
	 */
	public String saveKeyPro() {
		
		//根据项目id删除项目以及其对应的关键词
		projectKeywordService.deleteProKey(projectId);
		
		String[] arrKeywordIds = keywordIds.split(",");
		
		for (int i = 0; i < arrKeywordIds.length; i++) {
			Project pro = new Project();
			Keyword key = new Keyword();
			ProjectKeyword proKey = new ProjectKeyword();
			pro.setProjectId(projectId);
			key.setKeywordId(Integer.parseInt(arrKeywordIds[i]));
			proKey.setProject(pro);
			proKey.setKeyword(key);
			projectKeywordService.addProKey(proKey);
		}
		
		return SUCCESS;
	}
}
