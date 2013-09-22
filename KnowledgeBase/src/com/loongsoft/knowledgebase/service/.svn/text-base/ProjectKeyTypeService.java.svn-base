package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;

/**
 * @desc 控制层(service) --项目类别关键词管理业务操作服务类
 * 
 * @author suoyanming 2013-8-15
 */
public interface ProjectKeyTypeService {
        
	 /**
	  * 显示所有父级项目名称
	  */
	List<Project> showAllParentProject();
	
	/**
	 * 显示父级项目下的所有子项目
	 */
	List<Project> showSubProjectByParentId(int parentID);
	
	/**
	 * 获取项目类别下的关键词
	 */
	List<Keyword> getAllKeywordOfPro(int proId);
}
