 package com.loongsoft.knowledgebase.service;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * 角色管理服务类
 * 
 * @author suoyanming
 * @date 2013-9-12
 */
public interface RoleManageService {

	/**
	 * 获取所有角色
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return map(List<Role>,total)
	 */
	public Map getAllRoles(int page, int rows, String sort, String order);

	/**
	 * 模糊查询角色
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return
	 */
	public Map getRolesByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey);
	
	/**
	 * 添加新角色
	 * @param role
	 * @return
	 */
	JsonModel addRole(Role role);

	
	/**
	 * 更新角色
	 * @param role
	 * @return
	 */
	JsonModel updateRole(Role role);
	
	/**
	 * 刪除角色
	 * @param roleIds
	 */
	void deleteRole(String roleIds);
}
