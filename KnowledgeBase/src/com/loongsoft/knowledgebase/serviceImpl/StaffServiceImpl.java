package com.loongsoft.knowledgebase.serviceImpl;

import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IStaffDao;
import com.loongsoft.knowledgebase.service.StaffService;
/**
 * @描述 员工业务层实现
 * @author 张瑞祥 2013-8-31
 *
 */
public class StaffServiceImpl implements StaffService{
	private IStaffDao staffDao;
	
	public IStaffDao getStaffDao() {
		return staffDao;
	}

	public void setStaffDao(IStaffDao staffDao) {
		this.staffDao = staffDao;
	}
	/**
	 * 根据员工ID查找员工
	 */
	@Override
	public Staff findByStaffName(String staffId) {
		Staff staff = staffDao.findByStaffName(staffId);
		return staff;
	}
	/**
	 * 添加新的用户
	 */
	@Override
	public void addUser(Users user) {
		staffDao.addUser(user);
		
	}
	/**
	 * 根据登陆名称查找用户
	 */
	@Override
	public Users findByLoginName(String loginName) {
		Users user = staffDao.findByLoginName(loginName);
		return user;
	}
	/**
	 * 根据登陆用户ID查找用户
	 */
	@Override
	public Users findByUserId(String userId) {
		Users user = staffDao.findByUserId(userId);
		return user;
	}

}
