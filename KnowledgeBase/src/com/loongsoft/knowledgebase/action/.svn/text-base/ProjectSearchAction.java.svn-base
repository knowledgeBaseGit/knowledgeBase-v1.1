package com.loongsoft.knowledgebase.action;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.dao.IProjectDao;
import com.loongsoft.knowledgebase.dao.IProjectsDao;
import com.loongsoft.knowledgebase.service.ProjectsService;
import com.opensymphony.xwork2.ActionSupport;

public class ProjectSearchAction extends ActionSupport {
	/**
	 * 项目集合
	 */
	private List<Project> projects;
	/**
	 * 查询记录的总页数
	 */
	private int totalPages;
	/**
	 * 项目名称查询条件
	 */
	private String projectName;
	/**
	 * 当前页，首次登陆默认显示第一页
	 */
	private int page = 1;
	/**
	 * 每页显示的记录数
	 */
	private int pageSize;
	/**
	 * 项目DAO注入
	 */
	private IProjectsDao projectDao;

	private ProjectsService projectService;

	/**
	 * 列出项目列表并且实现分页展示
	 * 
	 * @return
	 */
	public String list() {
		try {
			projects = projectService.findPage(projectName, page, pageSize);
			totalPages = projectService.totalPages(projectName, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.SUCCESS;

	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public IProjectsDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectsDao projectDao) {
		this.projectDao = projectDao;
	}

	public ProjectsService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectsService projectService) {
		this.projectService = projectService;
	}

}
