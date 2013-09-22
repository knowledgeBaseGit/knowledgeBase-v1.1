package com.loongsoft.knowledgebase.dao;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * 
 * @author suoyanming 2013-8-8
 * @描述 问题基本操作
 */
public interface IQuestionsDao {
	/**
	 * 
	 * @param projectId
	 * @return 根据projectId查找问题
	 */
	public List<Questions> findByProjectId(int projectId);

	/**
	 * 
	 * @param projectName
	 * @return 根据项目名称查找问题
	 */
	public List<Questions> findByprojectName(String projectName);

	/**
	 * 
	 * @return 无条件查询
	 */
	public List<Questions> findAll();

	/**
	 * 
	 * @param keywordId
	 *            根据项目中关键词的id查找问题
	 * @return 若存在返回实体类 若查找不到返回Null
	 * @描述 添加分页 ---2013-8-30
	 */
	public List<Questions> findByKeywordId(Integer keywordId, Integer page,
			Integer pageSize);

	/**
	 * @ 描述 计算总页数
	 * 
	 * @param keywordId
	 * @param pageSize
	 * @return 总页数
	 */
	public Integer totalPages(Integer keywordId, Integer pageSize);

	/**
	 * 
	 * @param keywordName
	 * @return 根据关键词的名字查找问题
	 */
	public List<Questions> findBykeywordName(String keywordName);

	/**
	 * 
	 * @param question
	 *            添加新的问题
	 */
	public void addQuestion(Questions question);

	/**
	 * 
	 * @param question
	 *            修改问题
	 */
	public void updateQuestion(Questions question);

	/**
	 * 
	 * @param questionId
	 *            删除问题
	 */
	public void deleteQuestion(int questionId);

	/**
	 * 
	 * @param solution
	 *            添加解决方案
	 */
	public void addSolution(Solution solution);

	/**
	 * 
	 * @param annex
	 *            上传附件
	 */
	public void uploadAnnex(Annex annex);

	/**
	 * 
	 * @param annexId
	 *            下载附件
	 */
	public void downloadAnnex(int annexId);

	/**
	 * 审核问题
	 * 
	 * @param keyword
	 */
	void checkQuestion(Integer quesId, Users checkUser, String checkDate)
			throws Exception;

	/**
	 * 撤销审核
	 * 
	 * @param ques
	 * @param checkUser
	 * @throws Exception
	 */
	void undoCheckQues(Integer quesId, Users checkUser) throws Exception;

	/**
	 * 获取所有问题
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param type
	 *            (all:全部;checked:全部已审核的;uncheck:所有未审核)
	 * @return map(List<Questions>,total)
	 * @throws Exception
	 */
	Map getAllQues(int page, int rows, String sort, String order, String type,String keywords)
			throws Exception;

	/**
	 * 获得所有未审核的问题
	 * 
	 * @return List<Question>
	 */

	List<Questions> getAllUnCheckQuestions();

	/**
	 * 获得已审核的问题
	 * 
	 * @return List<Questions>
	 */
	List<Questions> getCheckedQuestions();

	/**
	 * 获取某个项目类别下的所有未审核问题
	 * 
	 * @param projectId
	 * @return List<Questions>
	 */
	List<Questions> getAllQuestionsOfProUncheck(int projectId);

	/**
	 * 获取某个项目类别下的所有已审核问题
	 * 
	 * @param projectId
	 * @return map(List<Questions>,total)
	 */
	Map getAllQuestionsOfProChecked(int projectId, int page, int rows,
			String sort, String order);

	/**
	 * 获取项目类别，关键词类别条件下的问题
	 * 
	 * @return map(List<Questions>,total)
	 */
	Map getAllCheckedQuesByProAndKeys(int projectId, int page, int rows,
			String sort, String order, String[] keys);

	/**
	 * 根据关键词 标题模糊搜索 问题
	 * 
	 * @return map(List<Questions>,total)
	 */
	Map getAllCheckedQuesByFuzzyKeys(int page, int rows, String sort,
			String order, String key);

	/**
	 * 判断某个项目类别下的 指定问题标题是否存在
	 */
	boolean isQuesOfProExsit(int projectId, String quesTitle);

	/**
	 * 根据问题标题、项目Id返回quesId
	 */
	int getQuesIdByProIdAndQuestTitle(int projectId, String quesTitle);

	/**
	 * 关联问题、关键词表操作
	 */
	void cascadeQuesAndKey(int keywordId, int quesId);

	/**
	 * 根据问题获取问题
	 * 
	 * @param quesId
	 * @return
	 * @throws Exception
	 */
	Questions getQuesByQuesId(Integer quesId) throws Exception;
	
	/**
	 * 根据附件id获取附件
	 * @param annexId
	 * @return
	 * @throws Exception
	 */
	Annex getAnnexById(Integer annexId)throws Exception;
}
