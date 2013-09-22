package com.loongsoft.knowledgebase.service;

import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
/**
 * @描述   员工的业务层
 * @author 张瑞祥 2013-8-31
 *	
 */
public interface StaffService {
	/**
	 * 
	 * @param staffiD
	 * @return
	 */
	public  Staff findByStaffName(String staffId);
	/**
	 * @描述 添加用户
	 * @param staff
	 */
	public void addUser(Users user);
	/**
	 * 
	 * @param loginName
	 * @return 根据用户登录名称返回用户对象
	 */
	public Users findByLoginName(String loginName);
	/**
	 * 
	 * @param userId
	 * @return 根据用户ID查找用户
	 */
	public Users findByUserId(String userId);
}
