package com.loongsoft.knowledgebase.service;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * @desc 控制层（servcie）-问题管理业务操作服务类
 * 
 * @author suoyanming 2013-8-17
 *
 */
public interface QuestionsService {
       
	/**
	 *获取某个项目下的所有已审核问题 
	 *@param projectId
	 * @param order 
	 * @param sort 
	 * @param rows 
	 * @param page 
	 *@return Map(List<Questions>,totalQues)
	 */
	Map getAllCheckedQues(int projectId, int page, int rows, String sort, String order,String keywords);

	/**
	 *根据关键词获取问题
	 */
	List<Questions> getAllCheckedQuesByKeyName(String[] keys,int projectId, int page, int rows, String sort, String order);
	
	/**
	 * 添加新问题
	 */
	JsonModel addQues(Questions ques,Integer keywordId);
	
	/**
	 * 审核问题
	 * @param ques
	 * @param checkUser
	 */
	void checkQues(String quesIds,Users checkUser);
	
	/**
	 * 撤销审核
	 * @param ques
	 * @param checkUser
	 */
	void undoCheckQues(String  quesIds,Users checkUser);
	
	/**
	 * 获取所有问题（用于问题审核模块）
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param type （checked：所有已审核;uncheck:所有未审核; all:所有问题）
	 * @return
	 */
	Map getAllQues(int page, int rows, String sort, String order,String type,String keywords);
	
	/**
	 * 批量删除问题
	 * @param quesIds
	 */
	void deleteQues(String quesIds);

}
