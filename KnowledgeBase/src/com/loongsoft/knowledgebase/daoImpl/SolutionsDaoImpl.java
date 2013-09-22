package com.loongsoft.knowledgebase.daoImpl;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.dao.ISolutionsDao;

public class SolutionsDaoImpl extends BaseDaoImp implements ISolutionsDao {
	/**
	 * 删除解决方案
	 */
	@Override
	public void deleteBysolutionId(Integer solutionId) {
		String hql = "delete Solution where solutionId = ?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, solutionId);
		query.executeUpdate();
	}
	/**
	 * @描述 查看选中的某个解决方案的具体信息
	 */
	@Override
	public Solution seeBysolutionId(Integer solutionId) {
		String hql = "select Solution from Solution solution where solution.solutionId = ?";
		Solution solution = this.getHibernateTemplate().get(Solution.class, solutionId);
		return solution;
	}
	/**
	 * @描述 修改解决方案
	 */
	@Override
	public void updateSolution(Solution solution) {
		this.getHibernateTemplate().update(solution);
	}

}
