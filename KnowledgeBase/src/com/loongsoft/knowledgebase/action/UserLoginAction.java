package com.loongsoft.knowledgebase.action;

import java.io.PrintWriter;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.UserLoginService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @desc 控制层（action） 用户登陆操作控制
 * 
 * @author suoyanming 2013-8-15
 *
 */
public class UserLoginAction extends ActionSupport {
    
	/**
	 * 注入用户登陆操作服务类
	 */
	private UserLoginService userLoginService;
	
	/**
	 * 用户实体对象（参数接收）
	 */
	private Users user;

	public UserLoginService getUserLoginService() {
		return userLoginService;
	}

	public void setUserLoginService(UserLoginService userLoginService) {
		this.userLoginService = userLoginService;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		 
		return SUCCESS;

	}
	
	/**
	 * 判断用户名是否存在
	 * @return 若存在返回“yes” 否则返回"no"
	 */
	public String isExist(){
		boolean result = this.userLoginService.isLoginNameEixst(user
				.getLoginName());
		System.out.println("判断用户是否存在Action");

		if (result) {
			this.writerJson("yes");
			return null;
		} else {
			this.writerJson("no");
			return null;
		}
	}
	
	/**
	 * 用户登陆
	 * @return 登陆成功返回success跳转到首页、否则返回null
	 */
	public String login(){
		Users loginUser = this.userLoginService.login(user);
		if(loginUser!=null){
			Map session=(Map)ActionContext.getContext().getSession();
			   session.put("loginUser", loginUser);
			   System.out.println("用户登陆成功");
			   return "success";
			 
		}
		else{
			this.writerJson("passwordError");
			return null;
		}
	}

	/**
	 * 用户没有登陆跳转到登陆页面
	 * @return
	 */
	public String noLogin(){
		return SUCCESS;
	}
	/**
	 * 在action 中得到response 对象，并回传数据
	 * 
	 * @param text
	 */
	private void writerJson(String text) {
		ActionContext context = ActionContext.getContext();
		if (context == null) {
			return;
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("application/text;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		PrintWriter print = null;
		try {
			print = response.getWriter();
			print.write(text);
			print.flush();
			print.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
