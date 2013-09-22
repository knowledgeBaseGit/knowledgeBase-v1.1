package com.loongsoft.knowledgebase.dao;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
/**
 * @ 描述 项目类别基本操作
 * @author 张瑞祥 2013-8-20
 *
 */
public interface IProjectsDao {
	/**
	 * @ 描述 显示所有项目列表
	 * @return 项目列表
	 */
	public List<Project> findAll(); 
	/**
	 * 
	 * @param projectName
	 * @return 项目列表
	 */
	public List<Project> findByProjectName(String projectName);
	/**
	 * 
	 * @param projectId
	 * @return 实体Project类
	 */
	public Project seeByprojectId(Integer projectId);
	/**
	 * @描述 添加新的项目
	 * @param project
	 */
	public void addProject(Project project);
	/**
	 * @ 描述 根据项目Id删除已有项目
	 * @param project
	 */
	public void deleteByProjectId(Integer projectId);
	/**
	 * 
	 * @return 返回所有的父级项目
	 */
	public List<Project> findAllParent();
	/**
	 * 
	 * @param projectName
	 * @return 根据项目名称精确查找项目
	 */
	public Project findBySingleProjectName(String projectName);
	/**
	 * @ 描述    项目修改
	 * @param project
	 */
	public void update(Project project);
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
	 * 
	 * @return 获得除所选项目所包含的的关键词以外的所有关键词
	 * @描述 在类别管理中的关键词功能中使用
	 */
	public List<Keyword> findAllKeywords(Integer projectId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
