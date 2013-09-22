 package com.loongsoft.knowledgebase.dao;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Role;

/**
 * 角色操作类
 * @author suoyanming 
 * @date 2013-9-12
 */
public interface IRoleDao {

	/**
	 * 获取所有角色
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 * @throws Exception
	 */
	Map getAllRoles(int page, int rows, String sort, String order)throws Exception;
	
	/**
	 * 模糊查询角色
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 * @throws Exception
	 */
	Map getRolesByFuzzyKeys(int page, int rows, String sort, String order,String searchKey)throws Exception;
    
	/**
	 * 判斷角色Id是否已存在
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	boolean isRoleIdExsit(String roleId)throws Exception;
	
	/**
	 * 判断角色名称是否已经存在
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	boolean isRoleNameExsit(String roleName)throws Exception;
	
	/**
	 * 添加新角色
	 * @param role
	 * @throws Exception
	 */
	void addRole(Role role)throws Exception;
	
	/**
	 * 更新角色
	 * @param role
	 * @throws Exception
	 */
	void updateRole(Role role)throws Exception;
	
	/**
	 * 判断某个角色Id下的名称是否已经存在
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	boolean isRoleNameOfIdEixsit(String roleName,String roleId)throws Exception;
	
	/**
	 * 刪除角色
	 * @param roleId
	 * @throws Exception
	 */
	void deleteRole(String roleId)throws Exception;
	
	/**
	 * 根据角色Id得到角色
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	Role getRoleById(String roleId)throws Exception;
}
