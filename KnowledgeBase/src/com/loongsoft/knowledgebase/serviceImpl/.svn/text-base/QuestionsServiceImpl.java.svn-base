package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IQuestionsDao;
import com.loongsoft.knowledgebase.service.QuestionsService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;

/**
 * @desc 控制层（service）-问题管理业务操作服务实现类
 * 
 * @author suoyanming 2013-8-17
 * 
 */

public class QuestionsServiceImpl implements QuestionsService {

	/**
	 * 注入问题modle类
	 */
	private IQuestionsDao quesDao;

	public IQuestionsDao getQuesDao() {
		return quesDao;
	}

	public void setQuesDao(IQuestionsDao quesDao) {
		this.quesDao = quesDao;
	}

	/**
	 * 获取某个项目下的所有已审核问题
	 * 
	 * @param projectId
	 * @return map(List<Questions>,total)
	 */
	@Override
	public Map getAllCheckedQues(int projectId, int page, int rows,
			String sort, String order, String keywords) {
		if (keywords != null) {
			if (keywords.contains("|")) {
				String[] strs = Util.splitStr(keywords, "\\|");
				System.out.println(keywords);
				System.out.println(strs.length);

				return this.quesDao.getAllCheckedQuesByProAndKeys(projectId,
						page, rows, sort, order, strs);

			} else {
				return this.quesDao.getAllCheckedQuesByFuzzyKeys(page, rows,
						sort, order, keywords);
			}
		} else {
			return this.quesDao.getAllQuestionsOfProChecked(projectId, page,
					rows, sort, order);
		}
	}

	/**
	 * 根据关键词获取问题
	 */
	@Override
	public List<Questions> getAllCheckedQuesByKeyName(String[] keys,
			int projectId, int page, int rows, String sort, String order) {

		return null;
	}

	/**
	 * 添加新问题
	 */
	@Override
	public JsonModel addQues(Questions ques, Integer keywordId) {
		JsonModel json = new JsonModel();
		// 判断该项目类别下是否有相同的问题
		int projectId = ques.getProject().getProjectId();
		String quesTitle = ques.getQuestionTitle();
		try {

			if (this.quesDao.isQuesOfProExsit(projectId, quesTitle)) {
				json.setMsg("该项目类别下已经存在【" + quesTitle + "】问题！");
				return json;
			}

			this.quesDao.addQuestion(ques);
			int quesId = this.quesDao.getQuesIdByProIdAndQuestTitle(projectId,
					quesTitle);
			// 添加 问题 、关键词级联表
			if (keywordId != null) {
				this.quesDao.cascadeQuesAndKey(keywordId, quesId);
			}
			json.setSuccess(true);
			json.setMsg("新问题【" + quesTitle + "】添加成功！");
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("问题【" + quesTitle + "】添加失败！");
		}
		return json;
	}

	/**
	 * 审核问题
	 */
	@Override
	public void checkQues(String quesIds, Users checkUser) {

		try {
			String [] quesId = Util.splitStr(quesIds, ",");
			for(int i=0;i<quesId.length;i++){
				this.quesDao.checkQuestion(Integer.parseInt(quesId[i]), checkUser,
						DateUtil.getCurrentDate());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 撤销审核问题
	 */
	@Override
	public void undoCheckQues(String quesIds, Users checkUser) {
		try {
			
			String [] quesId = Util.splitStr(quesIds, ",");
			for(int i=0;i<quesId.length;i++){
			this.quesDao.undoCheckQues(Integer.parseInt(quesId[i]), checkUser);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取所有问题（问题审核模块使用）
	 */
	@Override
	public Map getAllQues(int page, int rows, String sort, String order,
			String type, String keywords) {
		Map map = null;
		try {
			map = this.quesDao.getAllQues(page, rows, sort, order, type,
					keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 批量删除问题
	 */
	@Override
	public void deleteQues(String quesIds) {
		try {
			String[] fids = Util.splitStr(quesIds, ",");
			System.out.println(fids.length);
			for (int i = 0; i < fids.length; i++) {
				System.out.println(fids[i]);
				this.quesDao.deleteQuestion(Integer.parseInt(fids[i].trim()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
