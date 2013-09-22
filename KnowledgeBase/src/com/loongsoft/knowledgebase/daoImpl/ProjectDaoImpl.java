package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.dao.IProjectDao;

public class ProjectDaoImpl extends BaseDaoImp implements IProjectDao {

	/**
     * 获取所有父级项目
     * @return List<Project>
     */
	@Override
	public List<Project> getAllParentProject() {
		String hql = "select pro from Project pro where pro.project.projectId=null";
		List<Project> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	/**
     * 根据父级项目Id获取所有所属子项目
     * @param parentProId
     * @return List<Project>
     * 
     */
	@Override
	public List<Project> getProByParentProId(int parentProId) {
		String hql = "select pro from Project pro where pro.project.projectId=?";
		List<Project> list = this.getHibernateTemplate().find(hql,parentProId);
		return list;
	}

}
