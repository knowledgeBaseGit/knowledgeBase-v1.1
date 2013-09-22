package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.bean.Staff;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.KeyManageService;
import com.loongsoft.knowledgebase.service.UserManageService;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilRoleBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilUsersBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 角色管理action
 * 
 * @author suoyanming 2013-8-28
 * 
 */
public class UserManageAction extends ActionSupport {

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 每页显示的行数
	 */
	private int rows;

	/**
	 * 排序列名
	 */
	private String sort;

	/**
	 * 升降序
	 */
	private String order;

	/**
	 * 用户实体类
	 */
	private Users user;

	/**
	 * 员工实体类
	 */
	private Staff staff;
	/**
	 * 注入关键词操作服务类
	 */
	private UserManageService userService;

	/**
	 * 模糊搜索关键词
	 */
	private String searchKey;

	/**
	 * 批量删除用户Ids
	 * 
	 */
	private String userIds;

	/**
	 * 用户角色Id
	 */
	private String roleId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public UserManageService getUserService() {
		return userService;
	}

	public void setUserService(UserManageService userService) {
		this.userService = userService;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 得到所有用户
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String getAllUsers() throws Exception {
		Map map = null;
		if (searchKey != null) {
			map = this.userService.getUserByFuzzyKeys(page, rows, sort, order,
					searchKey);
		} else {
			map = this.userService.getAllUsers(page, rows, sort, order);
		}
		List<Users> list = (List<Users>) map.get("listuser");

		int total = (Integer) map.get("total");
		List<UtilUsersBean> ul = new ArrayList<UtilUsersBean>();

		for (Users returnUser : list) {
			UtilUsersBean uu = new UtilUsersBean();
			TransformBean.userTrans(uu, returnUser);
			ul.add(uu);
		}
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;

	}

	/**
	 * 回写json字符串
	 * 
	 * @param mapJson
	 * @throws IOException
	 */
	public void writeJson(Object mapJson) throws IOException {
		// 使用google插件gson生成json格式的数据
		GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

		Gson gson = builder.create();
		String strJson = gson.toJson(mapJson);
		System.out.println(strJson);
		HttpServletResponse response = this.getResponse();
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strJson);

		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
	}

	/**
	 * 新增用户
	 * 
	 * @return null
	 */
	public String addUser() throws Exception {

		JsonModel json = null;
		try {

			json = this.userService.addUser(user, staff);

		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("添加关键词" + "【" + user.getLoginName() + "】失败!");
		}

		this.writeJson(json);

		return null;
	}

	/**
	 * 删除用户
	 * 
	 * @return null
	 */
	public String deleteUsers() {
		try {
			if (userIds != null) {
				this.userService.deleteUsers(userIds);
			}
		} catch (Exception ex) {
		}

		return null;
	}

	/**
	 * 更新用户
	 * 
	 * @return null
	 */
	public String updateUser() throws Exception {

		JsonModel json = new JsonModel();

		if (user != null && staff != null) {
		}
		try {
			this.userService.updateUser(user, staff);
			json.setSuccess(true);
			json.setMsg("更新成功！");
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("更新失败！");
		}
		this.writeJson(json);

		return null;
	}

	/**
	 * 搜索关键词
	 * 
	 * @return
	 */
	public String searchKey() throws Exception {

		/*
		 * Map map = this.keyService.getKeywrodByFuzzyKeys(page, rows, sort,
		 * order, searchKey); List<Keyword> list = (List<Keyword>)
		 * map.get("listkey");
		 * 
		 * int total = (int) map.get("total"); List<UtilKeywordBean> ul = new
		 * ArrayList<UtilKeywordBean>();
		 * 
		 * for (Keyword key : list) { UtilKeywordBean uk = new
		 * UtilKeywordBean(); TransformBean.keywordTrans(uk, key); ul.add(uk); }
		 * Map<String, Object> mapJson = new HashMap<String, Object>();
		 * mapJson.put("total", total); mapJson.put("rows", ul);
		 * 
		 * writeJson(mapJson);
		 */
		return null;
	}

	/**
	 * 得到所有角色
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getAllRoles() throws IOException {

		List<Role> list = this.userService.getAllRoles();
		List<UtilRoleBean> ul = new ArrayList<UtilRoleBean>();
		for (Role role : list) {
			UtilRoleBean ur = new UtilRoleBean();
			TransformBean.roleTrans(ur, role);
			ul.add(ur);
		}
		this.writeJson(ul);
		return null;
	}

	/**
	 * 用户角色授权
	 * 
	 * @return
	 */
	public String grantRole() {
		this.userService.grantRole(user.getUserId(), roleId);
		return null;
	}

	/**
	 * 获取用户所有功能权限
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getFunsOfUser() throws IOException {
		JsonModel json = null;
		HttpSession session = this.getRequest().getSession();
		Users loginUser = (Users) session.getAttribute("loginUser");
		//System.out.println(loginUser);
		json = this.userService.getFunsOfUser(loginUser);
		this.writeJson(json);
		return null;
	}

	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		return response;

	}

	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		return request;

	}

}
