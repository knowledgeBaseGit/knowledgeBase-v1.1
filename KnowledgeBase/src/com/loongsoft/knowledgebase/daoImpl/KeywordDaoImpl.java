package com.loongsoft.knowledgebase.daoImpl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IKeyWordDao;
import com.loongsoft.knowledgebase.util.DateUtil;

/**
 * model层 关键词管理实现类
 * 
 * @author suoyanming 2013-8-9
 * 
 */

public class KeywordDaoImpl extends BaseDaoImp implements IKeyWordDao {

	/**
	 * 添加新的、修改 关键词
	 * 
	 * @param keyword
	 */
	@Override
	public void addOrUpadteKeyword(Keyword keyword) {

		this.getHibernateTemplate().saveOrUpdate(keyword);

	}

	/**
	 * 根据主键获取关键字
	 * 
	 * @param keywordId
	 */
	public Keyword getKeywordById(int keywordId) {
		Keyword keyword = this.getHibernateTemplate().get(Keyword.class,
				keywordId);
		return keyword;
	}

	/**
	 * 删除关键词
	 * 
	 * @param keywordId
	 */

	@Override
	public void deleteKeywordById(int keywordId) {
		this.getHibernateTemplate().delete(this.getKeywordById(keywordId));

	}

	/**
	 * 审核关键词
	 * 
	 * @param keywordId
	 */
	@Override
	public void checkKeyword(Integer keywordId, Users checkUser,
			String checkDate) throws Exception {
		Keyword upKey = this.getKeywordById(keywordId);
		upKey.setCheckStatus(1);
		upKey.setCheckDate(checkDate);
		upKey.setUsers(checkUser);

		this.getHibernateTemplate().update(upKey);
	}

	/**
	 * 获得所有未审核的关键字
	 * 
	 * @return List<Keyword>
	 */

	@Override
	public Map getAllUnCheckKeywords() {
		/*
		 * String hql = "select key from Keyword key where key.checkStatus=0";
		 * List<Keyword> list = this.getHibernateTemplate().find(hql); return
		 * list;
		 */
		return null;
	}

	/**
	 * 获得已审核的关键词
	 * 
	 * @return List<Keyword>
	 */

	@Override
	public Map getCheckedKeywords() {
		/*
		 * String hql = "select key from Keyword key where key.checkStatus=1";
		 * List<Keyword> list = this.getHibernateTemplate().find(hql); return
		 * list;
		 */
		return null;
	}

	/**
	 * 获取某个项目类别下的所有未审核关键词
	 * 
	 * @param projectId
	 * @return List<keyword>
	 */

	@Override
	public Map getAllKeyOfProUncheck(int projectId) {
		/*
		 * String hql =
		 * "select key from Keyword key,ProjectKeyword pro_key where " +
		 * " pro_key.project.projectId=? and key.keywordId=pro_key.keyword.keywordId and key.checkStatus=0)"
		 * ; List<Keyword> list = this.getHibernateTemplate().find(hql,
		 * projectId);
		 * 
		 * return list;
		 */
		return null;
	}

	/**
	 * 获取某个项目类别下的所有已审核关键词
	 * 
	 * @param projectId
	 * @return List<keyword>
	 */

	@Override
	public List<Keyword> getAllKeyOfProChecked(int projectId) {
		String hql = "select key from Keyword key,ProjectKeyword pro_key where "
				+ " pro_key.project.projectId=? and key.keywordId=pro_key.keyword.keywordId and key.checkStatus=1)";
		List<Keyword> list = this.getHibernateTemplate().find(hql, projectId);

		return list;
	}

	/**
	 * 搜索热度关键词top10
	 * 
	 * @return List<keyword>
	 */
	@Override
	public List<Keyword> getHotSearchKeyword() {

		String sql = "select k from Keyword k where checkStatus = 1 order by k.searchTimes desc";
		Query query = this.getSession().createQuery(sql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Keyword> list = query.list();
		return list;
	}

	/**
	 * 根据关键词名称获取关键词Id return 关键词存在返回该关键词的Id 若不存在，则返回-1
	 */
	@Override
	public int getIdByKeywordName(String keyName) {
		System.out.println(keyName);
		String hql = "select k.keywordId from Keyword k where k.keywordName=?";
		List list = this.getHibernateTemplate().find(hql, keyName);
		if (!list.isEmpty())
			return (int) list.get(0);
		else
			return -1;
	}

	/**
	 * 获取所有关键词
	 * 
	 * @return map(List<Keyword>,total)
	 */
	@Override
	public Map getAllKeyword(int page, int rows, String sort, String order) {
		String hql = "select key from Keyword key ";

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

		map.put("listkey", list);
		map.put("total", totalList.size());
		return map;
	}

	/**
	 * 模糊查询关键词
	 */
	@Override
	public Map getKeywrodByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) {
		String hql = "select key from Keyword key ";

		if (searchKey != null) {
			hql += "where key.keywordName like '%" + searchKey + "%' ";
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

		map.put("listkey", list);
		map.put("total", totalList.size());
		return map;

	}

	/**
	 * 取消审核关键词
	 */
	@Override
	public void undoCheckKey(Integer keywordId, Users checkUser)
			throws Exception {
		Keyword checkKey = this.getKeywordById(keywordId);
		checkKey.setCheckStatus(0);
		checkKey.setUsers(checkUser);
		checkKey.setCheckDate(DateUtil.getCurrentDate());
		this.getHibernateTemplate().update(checkKey);

	}

}
