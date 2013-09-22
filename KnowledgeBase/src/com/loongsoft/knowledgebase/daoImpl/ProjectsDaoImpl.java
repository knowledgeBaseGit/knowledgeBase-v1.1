package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.dao.IProjectsDao;
/**
 * @ 描述 项目管理实现类
 * @author 张瑞祥 2013-8-20
 *
 */
public class ProjectsDaoImpl extends BaseDaoImp implements IProjectsDao{

	/**
	 * @描述 查找所有的项目
	 */
	@Override
	public List<Project> findAll() {
		String hql = "from Project";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * @描述 根据项目名称查找项目 --模糊查找
	 */
	@Override
	public List<Project> findByProjectName(String projectName) {
		String hql = "select pro from Project pro where pro.projectName like:projectName";
		Query query = this.getSession().createQuery(hql);
		query.setString("projectName", "%" + projectName + "%");
		return query.list();
	}
	
	/**
	 * @描述 查看选中的某个项目的具体信息
	 */
	
	@Override
	public Project seeByprojectId(Integer projectId) {
		String hql = "select project from Project project where project.projectId = ?";
		Project project = this.getHibernateTemplate().get(Project.class, projectId);
		return project;
	}
	/**
	 * @描述 添加新的项目
	 */
	@Override
	public void addProject(Project project) {
		this.getHibernateTemplate().save(project);
		
	}
	/**
	 * @ 描述 根据项目id删除项目
	 */
	@Override
	public void deleteByProjectId(Integer projectId) {
		//参数的合法性判断
		
		String hql = "delete Project where projectId = :id";
		Query query = this.getSession().createQuery(hql);
		query.setInteger("id", projectId);
		query.executeUpdate();
		
			
		}
	
	/**
	 * @描述 返回所有的父级项目
	 */
	@Override
	public List<Project> findAllParent() {
		String hql = "select distinct pro1 from Project pro1,Project pro2 where pro1.projectId = pro2.project.projectId";
		List list = this.getHibernateTemplate().find(hql);
		return list;
	}
	/**
	 * @描述 根据项目名称精确查找项目
	 */
	@Override
	public Project findBySingleProjectName(String projectName) {
		String hql = "select project from Project project where project.projectName = ?";
		List list = this.getHibernateTemplate().find(hql, projectName);
		if(!list.isEmpty()){
			return (Project)list.get(0);
					
		}
		return null;
	}
	
	/**
	 * 项目修改
	 */
	@Override
	public void update(Project project) {
		this.getHibernateTemplate().update(project);
		
	}
	/**
	 * 根据projectName进行分页查询
	 */
	@Override
	public List<Project> findPage(String projectName,Integer page,Integer pageSize) {
		StringBuilder hql = new StringBuilder("from Project ");
		if(projectName != null && !"".equals(projectName)){
			hql.append(" where projectName like '%" + projectName +"%'");
			System.out.println(projectName+"1");
		}
		Query query = this.getSession().createQuery(hql.toString());
		//设置分页
		int begin = (page - 1)*pageSize;
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		return query.list();
		
		
	}
	/**
	 * 统计页数
	 */
	@Override
	public Integer totalPages(String projectName,Integer pageSize) {
		StringBuilder hql = new StringBuilder("select count(projectId) from Project  ");
		
		if(projectName != null && !"".equals(projectName)){
			hql.append(" where projectName like '%" + projectName +"%'");
		}
		Query query = this.getSession().createQuery(hql.toString());

		Object obj = query.uniqueResult();
		Integer totalPages = 1;
		//获取记录数并计算页数
		int count = Integer.parseInt(obj.toString());
		System.out.println(count);
		if(count % pageSize == 0){
			totalPages = count / pageSize;
		}else{
			totalPages = count / pageSize+1;
		}
		return totalPages;
	}
	/**
	 * 根据项目ID查找对应的关键词列表
	 */
	@Override
	public List<Keyword> findByProjectId(Integer projectId) {
		String hql = "select key from Keyword key,ProjectKeyword pk" +
				" where key.keywordId = pk.keyword.keywordId and pk.project.projectId = ?";
		List list = this.getHibernateTemplate().find(hql, projectId);
		return list;
	}
	/**
	 * 选择除所选项目包含的关键词以外的所有关键词。
	 */
	@Override
	public List<Keyword> findAllKeywords(Integer projectId) {
		String hql = "select key from Keyword key where key.keywordId not in (select key.keywordId from Keyword key,ProjectKeyword pk" +
				" where key.keywordId = pk.keyword.keywordId and pk.project.projectId = ?) ";
		List list = this.getHibernateTemplate().find(hql,projectId);
		return list;
	}
	
	
}


