package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;

import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.dao.IProjectKeywordDao;
import com.loongsoft.knowledgebase.service.ProjectKeywordService;

/**
 * 
 * @author 张瑞祥 2013-9-11
 * @描述 项目类别-关键词 业务层实现
 */
public class ProjectKeywordServiceImpl implements ProjectKeywordService {
	/**
	 * 项目类别-关键词Dao
	 */
	private IProjectKeywordDao projectKeywordDao;


	public IProjectKeywordDao getProjectKeywordDao() {
		return projectKeywordDao;
	}

	public void setProjectKeywordDao(IProjectKeywordDao projectKeywordDao) {
		this.projectKeywordDao = projectKeywordDao;
	}

	/**
	 * 根据项目id进行删除项目-关键词
	 */
	@Override
	public void deleteProKey(Integer projectId) {
		projectKeywordDao.deleteProKey(projectId);

	}
	/**
	 * 新增ProjectKeyword
	 */
	@Override
	public void addProKey(ProjectKeyword proKey) {
		projectKeywordDao.addProKey(proKey);
		
	}

}
