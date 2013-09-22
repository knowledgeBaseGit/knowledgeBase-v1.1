 package com.loongsoft.knowledgebase.dao;

import java.util.List;

import com.loongsoft.knowledgebase.bean.FunRole;
import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Module;

/**
 * model层模块操作接口
 * 
 * @author suoyanming
 * @date 2013-9-13
 */
public interface IModelDao {

	/**
	 * 根据Id获取模块
	 * 
	 * @param id
	 * @return
	 */
	Module getModelById(String id) throws Exception;

	/**
	 * 按父子模块参数获取所有模块
	 * 
	 * @param hql
	 * @param param
	 * @return
	 */
	List<Module> find(String hql, List param) throws Exception;

	/**
	 * 获取父亲节点下子节点的个数
	 * 
	 * @param hql
	 * @param pid
	 * @return
	 */
	Integer count(String hql, String pid) throws Exception;

	/**
	 * 获取模块下的功能
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Functions> getFunOfModule(String pid) throws Exception;

	/**
	 * 查询特定角色下是否有特定的功能
	 * 
	 * @param roleId
	 * @param funId
	 * @return
	 * @throws Exception
	 */
	boolean isFunOfRole(String roleId, String funId) throws Exception;

	/**
	 * 获取某个角色下的所有功能
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	List<String> getAllFunsIdOfRole(String roleId) throws Exception;
	
    //copy
	/**
	 * 新增角色功能
	 * @param roleId
	 * @param funId
	 * @throws Exception
	 */
	void addRoleFuns(String roleId,String funId)throws Exception;
	
	
	//copy
	/**
	 * 删除角色功能
	 * @param roleId
	 * @param funId
	 * @throws Exception
	 */
	void deleteRoleFuns(String roleId,String funId)throws Exception;
	
	/**
	 * 获取指定的角色功能记录
	 * @param roleId
	 * @param funId
	 * @return
	 * @throws Exception
	 */
	FunRole getFRByRoleIdAndFunId(String roleId,String funId)throws Exception;
	
	/**
	 * 获取所有模块Ids
	 * @return
	 * @throws Exception
	 */
    List<String> getAllModuleIds()throws Exception;
}
