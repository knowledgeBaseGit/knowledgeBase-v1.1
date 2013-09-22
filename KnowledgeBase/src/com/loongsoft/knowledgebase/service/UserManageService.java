 package com.loongsoft.knowledgebase.service;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * @desc 控制层（service） 用户管理操作
 * @author suoyanming 2013-8-28
 * 
 */
public interface UserManageService {
       

	/**
	 * 获取所有用户
	 * 
	 * @return Map(List<keyword>,total)
	 */
	public Map getAllUsers(int page, int rows, String sort, String order);
	
	/**
	 * 添加用户
	 */
	public JsonModel addUser(Users user,Staff staff)throws Exception;
	
	/**
	 * 模糊查询用户
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return Map
	 */
	public Map getUserByFuzzyKeys(int page, int rows, String sort, String order,String searchKey);
	
	/**
	 * 批量删除用户
	 */
	public void deleteUsers(String keywordIds);
	
	/**
	 * 更新用户
	 */
	public void updateUser(Users user,Staff staff)throws Exception;
	
	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> getAllRoles();
	 
	/**
	 * 用戶角色授权
	 * @param userId
	 * @param roleId
	 */
	void grantRole(String userId,String roleId);
	
	/**
	 * 获取用户功能权限
	 * @param user
	 * @return
	 */
	JsonModel getFunsOfUser(Users user);
	
	
}
