 package com.loongsoft.knowledgebase.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IUserDao;

/**
 * @desc：model层 用户管理实现类
 * 
 * @author suoyanming 2013-8-8
 * 
 */

public class UserDaoImpl extends BaseDaoImp implements IUserDao {

	/**
	 * 用户登陆
	 * 
	 * @param userName
	 *            , password 根据用户名，密码 判断实体是否存在
	 * 
	 * @return 若登陆成功 返回当前登陆成功用户对象 否则返回null;
	 */

	@Override
	public Users login(Users user) {

		String hql = "from Users u where u.loginName=? and u.loginPassword=?";
		List<Users> list = this.getHibernateTemplate().find(hql,
				user.getLoginName(), user.getLoginPassword());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据用户Id 返回实体
	 * 
	 * @param userId
	 * @return 若实体存在放回实体,否则返回null
	 * 
	 */

	@Override
	public Users findUserById(String userId) {

		String hql = "from Users where userId = ?";
		Users user = this.getHibernateTemplate().get(Users.class, userId);
		if (user != null) {
			return user;
		}

		return null;
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user实体
	 * 
	 */
	@Override
	public void updateUser(Users user) {

		this.getHibernateTemplate().update(user);

	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            实体
	 * @return 返回用户实体则注册成功
	 */

	@Override
	public Users createUser(Users user) {
		this.getHibernateTemplate().save(user);
		return user;
	}

	/**
	 * 判断注册用户是否是公司员工
	 * 
	 * @param userId
	 * @return 返回true 则是本公司员工，允许注册
	 */
	@Override
	public boolean isStaff(String userId) {
		Staff staff = this.getHibernateTemplate().get(Staff.class, userId);
		if (staff != null) {
			return true;
		}
		return false;
	}

	/**
	 * 查看用户拥有角色
	 * 
	 * @param userid
	 * @return 返回角色实体集合
	 */

	@Override
	public List<Role> getRoleByUserId(String userId) {
		String hql = "select role from Users user where " + " user.userId=?";
		List<Role> list = this.getHibernateTemplate().find(hql, userId);
		return list;
	}

	/**
	 * 查看角色拥有的功能权限
	 * 
	 * @param roleId
	 * @return list<functions>
	 */
	@Override
	public List<Functions> getFunByRoleId(String roleId) {
		String hql = "select func from Functions func,FunRole fun_role where "
				+ "fun_role.role.roleId=? and func.functionId=fun_role.functions.functionId";
		List<Functions> list = this.getHibernateTemplate().find(hql, roleId);
		return list;
	}

	/**
	 * 判断登陆名是否为已注册用户
	 * 
	 * @param loginName
	 * @return boolean
	 */
	@Override
	public boolean isUserExist(String loginName) {
		String hql = "select user from Users user where user.loginName=?";
		List<Users> list = this.getHibernateTemplate().find(hql, loginName);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 获取全部用户信息
	 */
	@Override
	public Map getAllUsers(int page, int rows, String sort, String order) {
		String hql = "select user from Users user ";

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);
		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Users> list = query.list();

		map.put("listuser", list);
		map.put("total", totalList.size());
		return map;
	}

	/**
	 * 模糊查询用户
	 */
	@Override
	public Map getUserByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) {
		String hql = "select user from Users user ";

		if (searchKey != null) {
			hql += "where user.loginName like '%" + searchKey + "%' ";
		}

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);

		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Users> list = query.list();

		map.put("listuser", list);
		map.put("total", totalList.size());
		return map;
	}

	/**
	 * 添加用户
	 */
	@Override
	public void addUser(Users user) {
		this.getHibernateTemplate().save(user);

	}

	/**
	 * 判断用户id是否已经存在
	 */
	@Override
	public boolean isUserIdExist(String id) {
		boolean isexist = false;
		try {
			String hql = "select user from Users user where user.userId=?";
			List list = this.getHibernateTemplate().find(hql, id);
			if (!list.isEmpty()) {
				isexist = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isexist;
	}

	/**
	 * 判断员工表中是否存在指定的staffId
	 */
	@Override
	public boolean isStaffIdExist(String id) {
		boolean isexist = false;
		try {
			String hql = "select staff from Staff staff where staff.staffId=?";
			List list = this.getHibernateTemplate().find(hql, id);
			if (!list.isEmpty()) {
				isexist = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isexist;

	}

	/**
	 * 保存或更新staff
	 */
	@Override
	public void saveOrUpdateStaff(Staff staff) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(staff);

	}

	/**
	 * 根据用户Id 得到用户
	 */
	@Override
	public Users getUserByUserId(String userId) {
		return this.getHibernateTemplate().get(Users.class, userId);

	}

	/**
	 * 删除用户
	 */
	@Override
	public void deleteUsers(String userId) {
		this.getHibernateTemplate().delete(this.getUserByUserId(userId));
		
	}
	
	/**
	 * 更新员工表
	 */
	@Override
	public void updateStaff(Staff staff) throws Exception {
		this.getHibernateTemplate().update(staff);
		
	}

	/**
	 * 获取所有角色
	 */
	@Override
	public List<Role> getAllRoles() throws Exception {
	 String hql = "select r from Role r";
	 return this.getHibernateTemplate().find(hql);
		 
	}

	/**
	 * 用户角色授权
	 */
	@Override
	public void grantRole(String userId, String roleId) throws Exception {
		Users grantUser = this.getUserByUserId(userId);
		Role role = new Role();
		role.setRoleId(roleId);
		grantUser.setRole(role);
		this.getHibernateTemplate().update(grantUser);
		
	}
	
	
}
