package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.ProjectKeyword;

/**
 * 
 * @author 张瑞祥 2013-9-11
 * @描述 项目类别-关键词的业务层操作 
 */
public interface ProjectKeywordService {
	/**
	 * @描述 根据项目id删除项目-关键词
	 * @param projectId
	 */
	public void deleteProKey(Integer projectId);
	/**
	 * 新增ProjectKeyword
	 * @param proKey
	 */
	public void addProKey(ProjectKeyword proKey);
}
