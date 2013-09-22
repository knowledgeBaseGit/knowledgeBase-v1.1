package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;

public interface KeywordService {
	/**
	 * 
	 * @return 查找所有已审核的关键词
	 */
	List<Keyword> getHotSearchKeyword();
	/**
	 * 更新关键词
	 * @param keyword
	 */
	void addOrUpadteKeyword(Keyword keyword);
	/**
	 * 
	 * @param keywordId
	 * @return 根据关键词ID 查找关键词。
	 */
	Keyword getKeywordById(int keywordId);
}
