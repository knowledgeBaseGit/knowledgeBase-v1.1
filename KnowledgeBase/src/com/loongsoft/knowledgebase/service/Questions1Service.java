package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Questions;

/**
 * 
 * @author 张瑞祥 2013-8-19
 *
 */
public interface Questions1Service {
       /**
        * 
        * @param keywordId
        * @param page
        * @param pageSize
        * @return 根据热搜关键词查找包含该关键词的问题列表，分页展示
        */
	List<Questions> findByKeywordId(Integer keywordId,Integer page,Integer pageSize);
	/**
	 * 
	 * @param keywordId
	 * @param pageSize
	 * @return 总页数
	 */
	public Integer totalPages(Integer keywordId,Integer pageSize);
	 
	
}
