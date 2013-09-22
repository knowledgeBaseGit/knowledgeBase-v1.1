package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.dao.IProjectKeywordDao;

public class ProjectKeywordDaoImpl extends BaseDaoImp implements IProjectKeywordDao{
	/**
	 * 根据项目id删除
	 */
	@Override
	public void deleteProKey(Integer projectId) {
		String hql = "delete ProjectKeyword where project.projectId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, projectId);
		query.executeUpdate();
		
	}
	/**
	 * 添加新的ProjectKeyword
	 */
	@Override
	public void addProKey(ProjectKeyword proKey) {
		this.getHibernateTemplate().save(proKey);
	}
}
