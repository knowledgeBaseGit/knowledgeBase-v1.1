package com.loongsoft.knowledgebase.action;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.service.KeywordService;
import com.loongsoft.knowledgebase.service.Questions1Service;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @描述 根据热搜关键词进行问题查看
 * @author 张瑞祥 2013-8-27
 * 
 */
public class Question1Action extends ActionSupport {
	
	/**
	 *关键词服务类
	 */
	private KeywordService keywordService;
	/**
	 * 关键词对象
	 */
	private Keyword keyword;
	
	/**
	 * 问题服务类
	 */
	private Questions1Service questionsService;
	/**
	 * 问题集合
	 */
	private List<Questions> questions;
	/**
	 * 关键词id
	 */
	private int keywordId;
	/**
	 * 查询记录的总页数
	 */
	private int totalPages;
	/**
	 * 当前页，首次登陆默认为1
	 */
	private int page = 1;
	/**
	 * 每页显示的记录数
	 */
	private int pageSize;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Questions1Service getQuestionsService() {
		return questionsService;
	}

	public void setQuestionsService(Questions1Service questionsService) {
		this.questionsService = questionsService;
	}

	public List<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}

	public int getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(int keywordId) {
		this.keywordId = keywordId;
	}
	
	public KeywordService getKeywordService() {
		return keywordService;
	}

	public void setKeywordService(KeywordService keywordService) {
		this.keywordService = keywordService;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	/**
	 * @描述 通过热搜关键词查找问题
	 * @return
	 */
	public String findByHotKeyword() {
		
		questions = questionsService.findByKeywordId(keywordId, page, pageSize);
		
		totalPages = questionsService.totalPages(keywordId, pageSize);
		
		/**
		 * 根据关键词ID找到对应的关键词对象，通过set，将其的SearchTimes加1
		 */
		
		keyword = keywordService.getKeywordById(keywordId);
		int i = keyword.getSearchTimes();
		int j = ++i;
		/**
		 * 每次点击热搜关键词的关键词，将数据库的searchTime自动加1.
		 */
		keyword.setSearchTimes(j);
		keywordService.addOrUpadteKeyword(keyword);
		return "success";
	}
	
	
}
