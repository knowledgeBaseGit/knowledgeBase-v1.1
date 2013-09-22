package com.loongsoft.knowledgebase.serviceImpl;

import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IUserDao;
import com.loongsoft.knowledgebase.service.UserLoginService;

/**
 * 控制层 （service）--用户登录业务操作服务类实现
 * 
 * @author suoyanming 2013-8-15
 * 
 */
public class UserLoginServiceImpl implements UserLoginService {

	/**
     *注入userDao 
     */
	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 检查用户名是否存在
	 */
	@Override
	public boolean isLoginNameEixst(String loginName) {

		return userDao.isUserExist(loginName);

	}

	/**
	 * 用户登录
	 */
	@Override
	public Users login(Users user) {

		return this.userDao.login(user);

	}
}
