package com.loongsoft.knowledgebase.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.QuesSolu;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.ISolutionDao;
import com.loongsoft.knowledgebase.util.DateUtil;

/**
 * @desc model层 解决方案实现类
 * @author suoyanming 2013-9-2
 * 
 */
public class SolutionDaoImpl extends BaseDaoImp implements ISolutionDao {

	/**
	 * 添加解决方案
	 */
	@Override
	public Solution addSolu(Solution solu) throws Exception {
		this.getHibernateTemplate().save(solu);
		return this.getSoluBySoluName(solu.getSolutionName());
	}

	/**
	 * 通过解决方案名称得到解决方案实体
	 */
	@Override
	public Solution getSoluBySoluName(String soluName) throws Exception {
		String hql = "select solu from Solution solu where solu.solutionName=?";
		List<Solution> list = this.getHibernateTemplate().find(hql, soluName);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 判断解决方案的名称是否已存在
	 */
	@Override
	public boolean isSoluExsit(String soluName) throws Exception {
		String hql = "select solu from Solution solu where solu.solutionName=?";
		List<Solution> list = this.getHibernateTemplate().find(hql, soluName);
		if (!list.isEmpty()) {
			return true;
		}

		return false;
	}

	/**
	 * 添加附件
	 */
	@Override
	public Annex addAnnex(Annex annex, Integer soluId) throws Exception {
		this.getHibernateTemplate().save(annex);
		// 当前Id
		int id = this.getCurrentAnnexId();
		// 级联保存
		this.saveAnnexOfSolu(soluId, id);
		return null;
	}

	/**
	 * 返回当前附件Id
	 */
	@Override
	public Integer getCurrentAnnexId() throws Exception {
		String hql = "select max(annexId) from Annex";
		List list = this.getHibernateTemplate().find(hql);
		return (Integer) list.get(0);
	}

	/**
	 * 方案对应附件级联保存
	 */
	@Override
	public void saveAnnexOfSolu(Integer soluId, Integer annexId)
			throws Exception {
		Solution solu = this.getSoluBySoluId(soluId);
		Annex annex = new Annex();

		annex.setAnnexId(annexId);
		solu.setAnnex(annex);
		this.getHibernateTemplate().saveOrUpdate(solu);

	}

	/**
	 * 根据方案Id返回方案实体
	 */
	@Override
	public Solution getSoluBySoluId(Integer soluId) throws Exception {
		return this.getHibernateTemplate().get(Solution.class, (int) soluId);

	}

	/**
	 * 方案、问题关联表记录添加
	 */
	@Override
	public void addSoluAndQues(Questions ques, Solution solu) throws Exception {
		QuesSolu qs = new QuesSolu();
		qs.setQuestions(ques);
		qs.setSolution(solu);
		this.getHibernateTemplate().save(qs);

	}

	/**
	 * 根据附件Id获取附件
	 */
	@Override
	public Annex getAnnexById(Integer annexId) throws Exception {
		return this.getHibernateTemplate().get(Annex.class, annexId);

	}

	/**
	 * 附件下载量加1
	 */
	@Override
	public void increAnnexLoadTimes(Integer annexId) throws Exception {
		Annex annex = this.getAnnexById(annexId);
		Integer loadTimes = annex.getLoadTimes();
		if (loadTimes == null) {
			annex.setLoadTimes(0);
		} else {
			annex.setLoadTimes(annex.getLoadTimes() + 1);
		}
		this.getHibernateTemplate().update(annex);

	}

	/**
	 * 模糊查询解决方案
	 */

	@Override
	public Map getSolusByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) throws Exception {
		String hql = "select solu from Solution solu ";

		if (searchKey != null) {
			hql += "where solu.solutionName like '%" + searchKey + "%' ";
		}

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);

		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Keyword> list = query.list();

		map.put("listsolu", list);
		map.put("total", totalList.size());
		return map;

	}

	/**
	 * 获取所有解决方案
	 */
	@Override
	public Map getAllSolus(int page, int rows, String sort, String order)
			throws Exception {

		String hql = "select solu from Solution solu ";

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);
		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Keyword> list = query.list();

		map.put("listsolu", list);
		map.put("total", totalList.size());
		return map;
	}

	/*
	 * 审核解决方案
	 */
	@Override
	public void checkSolu(Integer soluId, Users checkUser) throws Exception {
		Solution checkSolu = this.getSoluBySoluId(soluId);
		checkSolu.setCheckStatus(1);
		checkSolu.setCheckDate(DateUtil.getCurrentDate());
		checkSolu.setUsersByCheckUser(checkUser);
		this.getHibernateTemplate().update(checkSolu);

	}

	/**
	 * 撤销解决方案
	 */
	@Override
	public void undoCheckSolu(Integer soluId, Users checkUser) throws Exception {
		Solution checkSolu = this.getSoluBySoluId(soluId);
		checkSolu.setCheckStatus(0);
		checkSolu.setCheckDate(DateUtil.getCurrentDate());
		checkSolu.setUsersByCheckUser(checkUser);
		this.getHibernateTemplate().update(checkSolu);

	}

	/**
	 * 开放附件下载
	 */
	@Override
	public void checkAnnex(Integer annexId) throws Exception {
		Annex checkAnnex = this.getAnnexById(annexId);
		checkAnnex.setIsAppear(1);
		this.getHibernateTemplate().update(checkAnnex);

	}

	/**
	 * 撤销附件下载权限
	 */
	@Override
	public void undoCheckAnnex(Integer annexId) throws Exception {
		Annex checkAnnex = this.getAnnexById(annexId);
		checkAnnex.setIsAppear(0);
		this.getHibernateTemplate().update(checkAnnex);
	}
	
	

}
