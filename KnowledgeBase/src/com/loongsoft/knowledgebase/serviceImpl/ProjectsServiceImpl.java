package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.dao.IProjectsDao;
import com.loongsoft.knowledgebase.service.ProjectsService;
import com.loongsoft.knowledgebase.util.Util;

/**
 * @描述 项目类别业务操作实现类
 * @author 张瑞祥 2013-8-20
 * 
 */
public class ProjectsServiceImpl implements ProjectsService {
	/**
	 * 项目类别的方法实现
	 */
	private IProjectsDao projectsDao;

	public IProjectsDao getProjectsDao() {
		return projectsDao;
	}

	public void setProjectsDao(IProjectsDao projectsDao) {
		this.projectsDao = projectsDao;
	}
	/**
	 * @描述 查询所有项目
	 */
	@Override
	public List<Project> findAll() {
		List<Project> list = projectsDao.findAll();
		return list;
	}
	/**
	 * @描述 根据所填入的项目名称，进行模糊搜索
	 */
	@Override
	public List<Project> findByProjectName(String projectName) {
		List<Project> list = projectsDao.findByProjectName(projectName);
		return list;
	}
	/**
	 * @描述 选中要查找的项目，点击查找，显示项目具体信息
	 */
	@Override
	public Project seeByprojectId(Integer projectId) {
		Project project = projectsDao.seeByprojectId(projectId);
		return project;
	}
	/**
	 * @描述 添加新的项目
	 */
	@Override
	public void addProject(Project project) {
		projectsDao.addProject(project);
		
	}

	/**
	 * @描述 根据项目id删除相应的项目
	 */
	@Override
	public void deleteByProjectId(Integer projectId) {
		projectsDao.deleteByProjectId(projectId);
		
	}
	/**
	 * @描述 根据项目名称精确查找项目
	 */
	@Override
	public Project findBySingleProjectName(String projectName) {
		Project project = projectsDao.findBySingleProjectName(projectName);
		return project;
	}
	/**
	 * @描述   项目修改
	 */
	@Override
	public void update(Project project) {
		projectsDao.update(project);
		
	}
	/**
	 * 批量删除项目
	 */
	@Override
	public void deleteAll(String projectIds) {
		String[] arrProjectIds = projectIds.split(",");
		for (int i = 0; i < arrProjectIds.length; i++) {
			this.projectsDao.deleteByProjectId(Integer.parseInt(arrProjectIds[i].trim()));
		}
	}
	/**
	 * 项目名称分页显示列表
	 */
	@Override
	public List<Project> findPage(String projectName, Integer page,
			Integer pageSize) {
		List<Project> list =  projectsDao.findPage(projectName, page, pageSize);
		return list;
	}
	/**
	 * 总页数
	 */
	@Override
	public Integer totalPages(String projectName, Integer pageSize) {
	    Integer totalPages = projectsDao.totalPages(projectName, pageSize);
		return totalPages;
	}
	/**
	 * 根据项目id查找对应的关键词列表
	 */
	@Override
	public List<Keyword> findByProjectId(Integer projectId) {
		List list = projectsDao.findByProjectId(projectId);
		return list;
	}
	/**
	 * 除选择项目包含的关键词以外的所有关键词
	 */
	@Override
	public List<Keyword> findAllKeywords(Integer projectId) {
		List<Keyword> list = projectsDao.findAllKeywords(projectId);
		return list;
	}



}
