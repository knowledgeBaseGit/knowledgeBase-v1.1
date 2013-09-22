package com.loongsoft.knowledgebase.dao;

import java.util.List;

import com.loongsoft.knowledgebase.bean.ProjectKeyword;

/**
 * 
 * @author 张瑞祥 2013-9-11
 * @描述 项目类别关键词的Dao
 */
public interface IProjectKeywordDao {
	
	/**
	 * 根据项目类别ID，删除关联表的对应内容
	 * @param projectId
	 */
	public void deleteProKey(Integer projectId);
	/**
	 * 添加一个新的ProjectKeyword
	 * @param proKey
	 */
	public void addProKey(ProjectKeyword proKey);
}
