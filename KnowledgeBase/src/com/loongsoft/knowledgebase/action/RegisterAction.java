package com.loongsoft.knowledgebase.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.junit.Test.None;

import com.google.gson.Gson;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.StaffService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilStaffBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 员工注册页面的后台实现
 * 
 * @author 张瑞祥 2013-8-31
 * 
 */
public class RegisterAction extends BaseAction {
	/**
	 * 员工类
	 */
	private Staff staff;
	/**
	 * 员工的业务实现层
	 */
	private StaffService staffService;
	/**
	 * 员工的ID，是Users的外键
	 */
	private String staffId;
	/**
	 * 用户类
	 */
	private Users user;
	/**
	 * struts2 的JSON传递的参数
	 */
	private boolean ok = false;
	/**
	 * 从表单传递来的用户ID
	 * 
	 */
	private String userId;
	/**
	 * 从表单传递来的员工姓名
	 */
	private String loginName;
	/**
	 * 从表单传递来的员工密码
	 */
	private String loginPassword;
	/**
	 * 从表单传递来的验证码
	 */
	private String checkCode;

	/**
	 * 查看员工ID是否存在，若存在，自动添加其他员工信息，不存在，提示用户
	 * 
	 * @return
	 * @throws IOException
	 */
	public String staff() throws IOException {
		staff = staffService.findByStaffName(staffId);
		System.out.println(staffId + "staffId");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");
		UtilStaffBean utiStaff = new UtilStaffBean();
		// staffID不存在，取出的staff对象为空
		if (staff == null) {
			ok = true;
			return "success";

		}
		String userId = staffId;
		user = staffService.findByUserId(userId);
		if (user != null) {
			ok = false;
			return "success";
		}

		// 将取出的对象转为JSON字符串传回ajax的回调函数
		TransformBean.staffTrans(utiStaff, staff);
		Gson gson = new Gson();
		String strResult = gson.toJson(utiStaff);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return "success";
	}

	// 注册的实现
	public String regist() {
		// staffId是User的外键。虽然DB中已经存在，但是在保存staffId时，需要先声明
		Staff staff = new Staff();
		staff.setStaffId(user.getUserId());
		user.setStaff(staff);
		// 设置用户的默认角色
		Role role = new Role();
		role.setRoleId("0001");
		user.setRole(role);
		// 添加的用户自动设置登录时间
		String createDate = DateUtil.getCurrentDate();
		user.setCreateDate(createDate);
		staffService.addUser(user);
		return "success";
	}

	// 登录名称是否存在的验证
	public String checkLoginName() {
		Users user = staffService.findByLoginName(loginName);
		if (user == null) {
			ok = false;
			return "success";
		}
		return null;
	}

	// 登陆用户名和密码正确性验证
	public String check() {
		Users user = staffService.findByLoginName(loginName);
		// 根据loginName查找到的用户，取出其数据库中的正确密码，和前台传递来的密码进行比对
		if (!user.getLoginPassword().equals(loginPassword)) {
			ok = false;
			return "success";
		} else {
            HttpSession session = this.getRequest().getSession();
            session.setAttribute("loginUser", user);
			ok = true;
			return "success";
		}
	}

	// 登陆
	public String login() {
		return "success";
	}

	// 验证码验证
	public String checkCode() {
		String realCode = (String) session.get("rand");

		if (realCode.equals(checkCode)) {
			ok = true;
			return "success";
		} else {
			ok = false;
		}
		return "success";
	}

	/**
	 * 获取reponse 对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		return response;

	}

	/**
	 * 获取request 对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		return request;

	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

}
