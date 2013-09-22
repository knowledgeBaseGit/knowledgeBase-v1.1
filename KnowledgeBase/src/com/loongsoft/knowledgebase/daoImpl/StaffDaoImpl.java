package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IStaffDao;
/**
 * @描述 员工操作的实现层
 * @author 张瑞祥 2013-8-31
 *
 */
public class StaffDaoImpl extends BaseDaoImp implements IStaffDao{
	/**
	 * 通过员工ID查找员工
	 */
	@Override
	public Staff findByStaffName(String staffId) {
		String hql = "select staff from Staff staff where staff.staffId = ?";
		List list = this.getHibernateTemplate().find(hql,staffId);
		if(!list.isEmpty()){
			Staff staff = (Staff)list.get(0);
			return staff;
		}
		return null;
	}
	/**
	 * 添加新的员工
	 */
	@Override
	public void addUser(Users user) {
		this.getHibernateTemplate().save(user);
		
	}
	/**
	 * 根据用户登陆名称查找用户
	 */
	@Override
	public Users findByLoginName(String loginName) {
		String hql = "select user from Users user where user.loginName = ?";
		List list = this.getHibernateTemplate().find(hql, loginName);
		if(!list.isEmpty()){
			Users user = (Users)list.get(0);
			return user;
		}
		return null;
	}
	@Override
	public Users findByUserId(String userId) {
		String hql = "select user from Users user where user.userId = ?";
		List list = this.getHibernateTemplate().find(hql, userId);
		if(!list.isEmpty()){
			Users user = (Users)list.get(0);
			return user;
		}
		return null;
	}

}
