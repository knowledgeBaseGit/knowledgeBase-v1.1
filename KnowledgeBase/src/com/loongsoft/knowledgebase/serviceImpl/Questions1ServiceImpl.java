package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.dao.IQuestionsDao;
import com.loongsoft.knowledgebase.service.Questions1Service;

/**
 * 
 * @author 张瑞祥 2013-8-19
 *
 */
public class Questions1ServiceImpl implements Questions1Service {
	/**
	 * 问题的方法实现
	 */
	private IQuestionsDao questionsDao;
	
		
	public IQuestionsDao getQuestionsDao() {
		return questionsDao;
	}


	public void setQuestionsDao(IQuestionsDao questionsDao) {
		this.questionsDao = questionsDao;
	}

	/**
	 * 根据关键词查找问题列表，分页显示
	 * @ 用途：在首页的热搜关键词中，点击后进入包含该关键词的问题列表。
	 */
	@Override
	public List<Questions> findByKeywordId(Integer keywordId, Integer page,
			Integer pageSize) {
		
		List<Questions> list = questionsDao.findByKeywordId(keywordId, page, pageSize);
		
		return list;
	}

	/**
	 * 总页数
	 */
	@Override
	public Integer totalPages(Integer keywordId, Integer pageSize) {
		Integer totalPages = questionsDao.totalPages(keywordId, pageSize);
	    System.out.println(totalPages);
		return totalPages;
	}

  

}
