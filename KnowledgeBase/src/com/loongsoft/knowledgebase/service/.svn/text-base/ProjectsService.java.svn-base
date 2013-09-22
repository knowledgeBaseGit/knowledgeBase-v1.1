package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
/**
 * @ 描述 控制层service -- 对项目类别的业务操作
 * @author 张瑞祥 2013-8-20
 *
 */
public interface ProjectsService {
	
	 /**
	  * 
	  * @return 全部项目列表
	  */
	public List<Project> findAll();
	/**
	 * 
	 * @return 根据项目名称查到的项目列表
	 */
	public List<Project> findByProjectName(String projectName);
	/**
	 * 
	 * @param projectId
	 * @return 选中项目，点击查看，弹出该项目的详细信息
 	 */
	public Project seeByprojectId(Integer projectId);
	/**
	 * @ 描述 添加项目
	 * @param project
	 */
	public void addProject(Project project);
	/**
	 * @ 删除项目
	 * @param projectId
	 */
	public void deleteByProjectId(Integer projectId);
	/**
	 * @描述 根据项目名称精确查找
	 * @param projectName
	 * @return
	 */
	public Project findBySingleProjectName(String projectName);
	/**
	 * @ 描述 
	 * @param project
	 */
	public void update(Project project);
	/**
	 * @描述 批量删除项目
	 * @param projectId
	 */
	public void deleteAll(String projectIds);
	/**
	 * @描述 根据项目名称，页数，和每页的行数，进行分页查询
	 * @param projectName
	 * @param page
	 * @param pageSize
	 * @return Project的集合
	 */
	public List<Project> findPage(String projectName,Integer page,Integer pageSize);
	/**
	 * @描述 根据项目名称和每页的行数，计算总页数
	 * @param projectName
	 * @param pageSize
	 * @return 总页数
	 */
	public Integer totalPages(String projectName,Integer pageSize);
	
	/**
	 * 
	 * @param projectId
	 * @return 根据项目ID查找关键词列表
	 */
	public List<Keyword> findByProjectId(Integer projectId);
	
	/**
	 * 在类别管理中查找除所选项目包含的关键词以外的所有关键词
	 * @return
	 */
	public List<Keyword> findAllKeywords(Integer projectId);
	
	
}
