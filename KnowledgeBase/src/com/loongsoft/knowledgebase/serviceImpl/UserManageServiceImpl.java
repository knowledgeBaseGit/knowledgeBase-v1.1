package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IModelDao;
import com.loongsoft.knowledgebase.dao.IUserDao;
import com.loongsoft.knowledgebase.service.UserManageService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;

/**
 * @desc 控制层（service） 用户管理操作服务类
 * @author suoyanming 2013-8-28
 * 
 */
public class UserManageServiceImpl implements UserManageService {

	/**
	 * 注入用户操作Dao
	 */
	private IUserDao userDao;

	/**
	 * 注入模块操作Dao
	 */
	private IModelDao modelDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IModelDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IModelDao modelDao) {
		this.modelDao = modelDao;
	}

	/**
	 * 获取所有用户
	 * 
	 * @return Map(List<keyword>,total)
	 */
	@Override
	public Map getAllUsers(int page, int rows, String sort, String order) {
		Map map = null;
		try {
			map = this.userDao.getAllUsers(page, rows, sort, order);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return map;
	}

	/**
	 * 添加用户
	 */
	@Override
	public JsonModel addUser(Users user, Staff staff) throws Exception {
		String userId = user.getUserId();
		JsonModel json = new JsonModel();
		boolean result = this.userDao.isUserIdExist(userId);
		// 判断用户Id是否已经注册过了
		if (result) {
			json.setSuccess(false);
			json.setMsg("添加用户" + "【" + user.getLoginName()
					+ "】失败！系统中已存在相同的用户Id！");
			return json;

		} else if (this.userDao.isUserExist(user.getLoginName())) {
			json.setSuccess(false);
			json.setMsg("添加用户" + "【" + user.getLoginName()
					+ "】失败！系统中已存在相同的用户名称！");
			return json;
		}

		else {// 不存在
				// 判断员工表中是否有该员工
			boolean isStaff = this.userDao.isStaffIdExist(staff.getStaffId());
			if (!isStaff) {
				this.userDao.saveOrUpdateStaff(staff);
				/*
				 * json.setSuccess(false); json.setMsg("添加用户" + "【" +
				 * user.getLoginName() + "】失败！员工表中无此工号！"); return json;
				 */
			}
			user.setStaff(staff);
			user.setCreateDate(DateUtil.getCurrentDate());
			this.userDao.addUser(user);
			json.setSuccess(true);
			json.setMsg("添加用户" + "【" + user.getLoginName() + "】成功！");
			return json;
		}

	}

	/**
	 * 模糊查询用户
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return
	 */
	@Override
	public Map getUserByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) {
		return this.userDao.getUserByFuzzyKeys(page, rows, sort, order,
				searchKey);

	}

	/**
	 * 批量删除用户
	 */
	@Override
	public void deleteUsers(String userIds) {
		try {
			String[] fids = Util.splitStr(userIds, ",");

			for (int i = 0; i < fids.length; i++) {

				this.userDao.deleteUsers(fids[i]);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 更新用户
	 */
	@Override
	public void updateUser(Users user, Staff staff) throws Exception {
		Users upUser = this.userDao.getUserByUserId(staff.getStaffId());
		upUser.setLoginName(user.getLoginName());
		upUser.setLoginPassword(user.getLoginPassword());
		this.userDao.updateUser(upUser);
		this.userDao.updateStaff(staff);
	}

	/**
	 * 获取所有角色
	 */
	@Override
	public List<Role> getAllRoles() {
		List<Role> list = null;
		try {
			list = this.userDao.getAllRoles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 用户角色授权
	 */
	@Override
	public void grantRole(String userId, String roleId) {
		try {
			this.userDao.grantRole(userId, roleId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取用户功能权限
	 */
	@Override
	public JsonModel getFunsOfUser(Users user) {
		JsonModel json = new JsonModel();
		try {
			if (user != null) {
				List<Role> list = this.userDao
						.getRoleByUserId(user.getUserId());
				if (list.get(0).getRoleId() == null) {
					json.setMsg("您没有角色权限，请联系管理员！");
					return json;
				}
				List<String> funsIds = this.modelDao.getAllFunsIdOfRole(list
						.get(0).getRoleId());
				json.setSuccess(true);
				json.setMsg("获取功能权限成功！");
				json.setObj(funsIds);
				return json;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("用户权限存在异常！请联系管理员");
		}
		return json;
	}

}
