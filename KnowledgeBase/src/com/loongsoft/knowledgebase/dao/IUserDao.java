 package com.loongsoft.knowledgebase.dao;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * @描述：modle层 用户管理操作方法
 * 
 * @author suoyanming 2013-8-7
 * 
 */

public interface IUserDao {

	/**
	 * 用户登陆
	 * 
	 * @param user
	 *            根据用户名，密码 判断实体是否存在
	 * 
	 * @return 若登陆成功 返回当前登陆成功用户对象 否则返回null;
	 */
	Users login(Users user);

	/**
	 * 根据用户Id 返回实体
	 * 
	 * @param userId
	 * @return 若实体存在放回实体 否则返回null
	 * 
	 */
	Users findUserById(String userId);

	/**
	 * 添加或更员工实体
	 */
	void saveOrUpdateStaff(Staff staff) throws Exception;

	/**
	 * 添加新用户
	 */
	void addUser(Users user);

	/**
	 * 删除用户
	 * 
	 * @param userId
	 */
	void deleteUsers(String userId);
	
	/**
	 * 根据用户Id 得到用户
	 * @param userId
	 * @return Users
	 */
	Users getUserByUserId(String UserId);

	/**
	 * 获得全部用户信息
	 * 
	 * @return Map(List<Users>,total)
	 */
	Map getAllUsers(int page, int rows, String sort, String order);

	/**
	 * 更新用户信息
	 * 
	 * @param user实体
	 * 
	 */
	void updateUser(Users user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            实体
	 * @return 返回用户实体则注册成功
	 */
	Users createUser(Users user);

	/**
	 * 判断注册用户是否是公司员工
	 * 
	 * @param userId
	 * @return 返回true 则是本公司员工，允许注册
	 */
	boolean isStaff(String userId);

	/**
	 * 判断登陆名是否为已注册用户
	 * 
	 * @param loginName
	 * @return boolean
	 */
	boolean isUserExist(String LoginName);

	/**
	 * 判断用户Id 是否已经注册
	 */
	boolean isUserIdExist(String id);

	/**
	 * 判断员工表是否存在staffId
	 */
	boolean isStaffIdExist(String id);
	
	/**
	 * 更新员工表
	 */
	void updateStaff(Staff staff)throws Exception;

	/**
	 * 查看用户拥有角色
	 * 
	 * @param userid
	 * @return 返回角色实体集合
	 */
	List<Role> getRoleByUserId(String userId);

	/**
	 * 查看角色拥有的功能权限
	 * 
	 * @param roleId
	 * @return list<functions>
	 */
	List<Functions> getFunByRoleId(String roleId);

	/**
	 * 模糊查询用户
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return Map(List<Users>,total)
	 */
	public Map getUserByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey);
    /**
     * 获取所有角色
     * @return
     * @throws Exception
     */
	List<Role> getAllRoles()throws Exception;
	
	/**
	 * 用户角色授权
	 * @param userId
	 * @param roleId
	 * @throws Exception
	 */
	void grantRole(String userId,String roleId)throws Exception;
	
	
}
