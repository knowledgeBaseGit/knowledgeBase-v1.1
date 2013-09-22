 package com.loongsoft.knowledgebase.service;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Module;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * 控制层 模块管理服务类
 * 
 * @author suoyanming
 * @date 2013-9-13
 */
public interface ModelService {

	/*
	 * 获取模块树节点
	 */
	List<Module> getTreeNode(String pid) throws Exception;

	/**
	 * 获取子节点的个数
	 * 
	 * @param pid
	 * @return
	 */
	Integer countChildren(String pid);

	/**
	 * 获取模块下的功能
	 * 
	 * @param pid
	 * @return
	 */
	List<Functions> getFunOfModule(String pid);

	/**
	 * 判断角色是否拥有此功能
	 * 
	 * @param roleId
	 * @param functionId
	 * @return
	 */
	boolean isFunOfRole(String roleId, String functionId);
	
	/**
	 * 角色授权
	 * @param roleId
	 * @param updateFuns
	 * @return
	 */
	JsonModel updateFunOfRole(String roleId,String updateFuns);
	/**
	 * 获取所有模块Id
	 * @return
	 */
	List<String> getAllModelIds();
}
