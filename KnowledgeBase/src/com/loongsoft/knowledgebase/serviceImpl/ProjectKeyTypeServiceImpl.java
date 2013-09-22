package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.dao.IKeyWordDao;
import com.loongsoft.knowledgebase.dao.IProjectDao;
import com.loongsoft.knowledgebase.service.ProjectKeyTypeService;

/**
 * @desc 控制层(serviceImpl)--项目类别关键词管理业务操作服务实现类
 * 
 * @author suoyanming 2013-8-15
 * 
 */
public class ProjectKeyTypeServiceImpl implements ProjectKeyTypeService {

	private IProjectDao projectDao;
	private IKeyWordDao keywordDao;

	public IKeyWordDao getKeywordDao() {
		return keywordDao;
	}

	public void setKeywordDao(IKeyWordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

	public IProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	/**
	 * 显示所有父级项目名称
	 */
	@Override
	public List<Project> showAllParentProject() {

		return this.projectDao.getAllParentProject();

	}

	/**
	 * 显示父级项目下的所有子项目
	 */
	@Override
	public List<Project> showSubProjectByParentId(int parentID) {

		return this.projectDao.getProByParentProId(parentID);
	}

	/**
	 * 获取项目下的关键词
	 */
	@Override
	public List<Keyword> getAllKeywordOfPro(int proId) {
		return this.keywordDao.getAllKeyOfProChecked(proId);

	}

}
