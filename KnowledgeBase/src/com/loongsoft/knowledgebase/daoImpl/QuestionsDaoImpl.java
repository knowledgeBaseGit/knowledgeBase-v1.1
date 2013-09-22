package com.loongsoft.knowledgebase.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.QuesKeyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IQuestionsDao;
import com.loongsoft.knowledgebase.util.DateUtil;

/**
 * @desc: modle层 问题管理实现类
 * 
 * @author suoyanming 2013-8-19
 * 
 */

public class QuestionsDaoImpl extends BaseDaoImp implements IQuestionsDao {
	/**
	 * 根据projectId查找问题
	 * 
	 * @param projectId
	 * @return List<Questions>
	 */
	@Override
	public List<Questions> findByProjectId(int projectId) {
		String hql = "from Questions ques where ques.project.projectId=?";
		List<Questions> list = this.getHibernateTemplate().find(hql, projectId);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	/**
	 * 根据projectName查找问题
	 * 
	 * @param projectName
	 * @return List<Questions>
	 */
	@Override
	public List<Questions> findByprojectName(String projectName) {
		String hql = "from Questions ques where ques.project.projectName=?";
		List<Questions> list = this.getHibernateTemplate().find(hql,
				projectName);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	/**
	 * 无条件查找查找所有问题
	 * 
	 * @param projectName
	 * @return List<Questions>
	 */
	@Override
	public List<Questions> findAll() {
		String hql = "from Questions";
		List list = getHibernateTemplate().find(hql);
		return list;
	}

	/**
	 * 根据keywordId查找问题
	 * 
	 * @param keywordId
	 * @return List<Questions>
	 */
	@Override
	public List<Questions> findByKeywordId(Integer keywordId, Integer page,
			Integer pageSize) {
		String hql = "select ques from Questions ques,QuesKeyword qk "
				+ "where qk.keyword.keywordId=? and ques.questionId = qk.questions.questionId";
		// List<Questions> list = this.getHibernateTemplate().find(hql,
		// keywordId);
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, keywordId);
		int begin = (page - 1) * pageSize;
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@Override
	public Integer totalPages(Integer keywordId, Integer pageSize) {
		String hql = "select count(ques.questionId) from Questions ques,"
				+ "QuesKeyword qk where qk.keyword.keywordId=? and ques.questionId = qk.questions.questionId ";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, keywordId);

		Object obj = query.uniqueResult();
		Integer totalPages = 1;
		// 获取记录数并计算页数
		int count = Integer.parseInt(obj.toString());
		System.out.println(count);
		if (count % pageSize == 0) {
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}

	/**
	 * 根据keywordId查找问题
	 * 
	 * @param keywordId
	 * @return List<Questions>
	 */
	@Override
	public List<Questions> findBykeywordName(String keywordName) {

		String hql = "select ques from Questions ques,QuesKeyword qk "
				+ "where qk.keyword.keywordName=? and ques.questionId = qk.questions.questionId";

		List<Questions> list = this.getHibernateTemplate().find(hql,
				keywordName);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	/**
	 * 添加问题
	 */
	@Override
	public void addQuestion(Questions question) {
		getHibernateTemplate().save(question);
	}

	/**
	 * 修改问题
	 */
	@Override
	public void updateQuestion(Questions question) {
		getHibernateTemplate().update(question);
	}

	/**
	 * 删除问题
	 */
	@Override
	public void deleteQuestion(int questionId) {
		Questions question = (Questions) getHibernateTemplate().get(
				Questions.class, questionId);
		getHibernateTemplate().delete(question);

	}

	/**
	 * 添加解决方案
	 */
	@Override
	public void addSolution(Solution solution) {
		getHibernateTemplate().save(solution);

	}

	/**
	 * 上传附件
	 */
	@Override
	public void uploadAnnex(Annex annex) {

	}

	/**
	 * 下载附件
	 */
	@Override
	public void downloadAnnex(int annexId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 问题审核
	 */

	@Override
	public void checkQuestion(Integer questionId, Users checkUser,
			String checkDate) throws Exception {
		Questions checkQues = this.getQuesByQuesId(questionId);
		checkQues.setCheckStatus(1);
		checkQues.setCheckDate(DateUtil.getCurrentDate());
		checkQues.setUsersByCheckUser(checkUser);
		this.getHibernateTemplate().update(checkQues);

	}

	/**
	 * 撤销审核问题
	 * 
	 * @param ques
	 * @param checkUser
	 * @throws Exception
	 */
	@Override
	public void undoCheckQues(Integer quesId, Users checkUser) throws Exception {
		Questions checkQues = this.getQuesByQuesId(quesId);
		checkQues.setCheckStatus(0);
		checkQues.setCheckDate(DateUtil.getCurrentDate());
		checkQues.setUsersByCheckUser(checkUser);
		this.getHibernateTemplate().update(checkQues);

	}

	@Override
	public List<Questions> getAllUnCheckQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questions> getCheckedQuestions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Questions> getAllQuestionsOfProUncheck(int projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据项目类别获取相关问题
	 */
	@Override
	public Map getAllQuestionsOfProChecked(int projectId, int page, int rows,
			String sort, String order) {
		/*
		 * String hql =
		 * "select ques from Questions ques where ques.project.projectId=? and checkStatus=1 "
		 * + "order by "+sort+" " +order;
		 */
		String hql = "select ques from Questions ques where checkStatus=1 ";
		if (projectId >= 0) {
			hql += "and ques.project.projectId=" + projectId;
		}
		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			List<Questions> list = query.list();
			map.put("listQues", list);
			map.put("total", totalList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取项目类别，关键词类别条件下的问题
	 * 
	 * @return List<Questions>
	 */
	@Override
	public Map getAllCheckedQuesByProAndKeys(int projectId, int page, int rows,
			String sort, String order, String[] keys) {
		String hql = "select ques from Questions ques where ques.checkStatus=1 ";
		if (projectId >= 0) {
			hql += "and ques.project.projectId=" + projectId;
		}
		String keyName = "";
		if (keys.length > 0) {
			keyName += "(";
			for (int i = 0; i < keys.length - 1; i++) {
				keyName += "'" + keys[i] + "',";
			}
			keyName += "'" + keys[keys.length - 1] + "'";
			keyName += ")";
		}

		if (keyName != "") {
			hql += " and ques.questionId in (select ques_key.questions.questionId from QuesKeyword ques_key "
					+ "where ques_key.keyword.keywordName in " + keyName + ")";
		}

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		List<Questions> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			map.put("listQues", list);
			map.put("total", totalList.size());
		} catch (Exception es) {
			es.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据关键词 标题模糊搜索 问题
	 * 
	 * @return map(List<Question>,total)
	 */
	@Override
	public Map getAllCheckedQuesByFuzzyKeys(int page, int rows, String sort,
			String order, String key) {
		String hql = "select ques from Questions ques where ques.checkStatus=1 and ques.questionTitle like '%"
				+ key + "%'";
		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		List<Questions> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			List totalList = query.list();
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			map.put("listQues", list);
			map.put("total", totalList.size());
		} catch (Exception es) {
			es.printStackTrace();
		}
		return map;
	}

	/**
	 * 判断某个项目类别下的 指定问题标题是否存在
	 */
	@Override
	public boolean isQuesOfProExsit(int projectId, String quesTitle) {
		String hql = "select ques from Questions ques where ques.project.projectId=?"
				+ " and ques.questionTitle=?";
		List list = this.getHibernateTemplate().find(hql, projectId, quesTitle);
		if (!list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据问题标题、项目Id返回quesId
	 */
	@Override
	public int getQuesIdByProIdAndQuestTitle(int projectId, String quesTitle) {
		String hql = "select ques.questionId from Questions ques where ques.project.projectId=? and ques.questionTitle=?";
		List list = this.getHibernateTemplate().find(hql, projectId, quesTitle);
		if (!list.isEmpty()) {
			return (int) list.get(0);
		}
		return -1;
	}

	/**
	 * 关联问题、关键词表操作
	 */
	@Override
	public void cascadeQuesAndKey(int keywordId, int quesId) {
		QuesKeyword qk = new QuesKeyword();
		Questions ques = new Questions();
		Keyword key = new Keyword();
		ques.setQuestionId(quesId);
		key.setKeywordId(keywordId);
		qk.setKeyword(key);
		qk.setQuestions(ques);
		this.getHibernateTemplate().save(qk);

	}

	/**
	 * 获取所有问题
	 * 
	 */

	@Override
	public Map getAllQues(int page, int rows, String sort, String order,
			String type, String keywords) throws Exception {
		String hql = "select ques from Questions ques where 1=1";

		// 按类别查询全部问题
		if (type != null) {
			if (type.equals("checked")) {
				hql += " and ques.checkStatus=1 ";
			} else if (type.equals("uncheck")) {
				hql += " and ques.checkStatus=0 ";
			}
		}

		if (keywords != null) {
			hql += " and ques.questionTitle like '%" + keywords + "%'";
		}

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		List<Questions> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			map.put("listQues", list);
			map.put("total", totalList.size());
		} catch (Exception es) {
			es.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据问题Id获取问题
	 */
	@Override
	public Questions getQuesByQuesId(Integer quesId) throws Exception {

		return this.getHibernateTemplate().get(Questions.class, quesId);

	}

	
	/**
	 * 根据附件Id获取附件
	 */
	@Override
	public Annex getAnnexById(Integer annexId) throws Exception {
		return this.getHibernateTemplate().get(Annex.class,annexId);
		 
	}

}
