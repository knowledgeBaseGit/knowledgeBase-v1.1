package com.loongsoft.knowledgebase.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.service.KeywordService;
import com.loongsoft.knowledgebase.service.ProjectsService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @描述 关键词控制层
 * @author 张瑞祥 2013-8-27
 *
 */
public class KeywordAction extends ActionSupport {
	/**
	 * 项目类别集合
	 */
	private List<Project> projects;
	/**
	 * 项目服务类
	 */
	private ProjectsService projectsService;
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public ProjectsService getProjectsService() {
		return projectsService;
	}

	public void setProjectsService(ProjectsService projectsService) {
		this.projectsService = projectsService;
	}
	/**
	 * 关键词服务类
	 */
	private KeywordService keywordService;
	/**
	 * 关键词集合
	 */
	private List<Keyword> keywords;

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public KeywordService getKeywordService() {
		return keywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}
	/**
	 * @描述   热搜关键词的action
	 * @return
	 */
	public String searchHot() {
		/**
		 * 得到热搜关键词，放入首页中
		 */
		keywords = this.keywordService.getHotSearchKeyword();
		/**
		 * 查找所有的项目，和热搜关键词一样放到首页中
		 */
		projects  = this.projectsService.findAll();
		return "success";
	}
	
}
