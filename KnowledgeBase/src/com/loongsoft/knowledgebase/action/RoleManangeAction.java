 package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.service.KeyManageService;
import com.loongsoft.knowledgebase.service.RoleManageService;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilRoleBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 角色管理
 * 
 * @author suoyanming 2013-9-12
 * 
 */
public class RoleManangeAction extends ActionSupport {

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
	 * 角色实体类
	 */
	private Role role;

	/**
	 * 注入关键词操作服务类
	 */
	private RoleManageService roleService;

	/**
	 * 模糊搜索关键词
	 */
	private String searchKey;

	/**
	 * 批量删除关键词Ids
	 * 
	 */
	private String roleIds;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 获取所有角色
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAllRoles() throws Exception {
		Map map = null;
		if (searchKey != null) {
			map = this.roleService.getRolesByFuzzyKeys(page, rows, sort,
					order, searchKey);
		} else {
			map = this.roleService.getAllRoles(page, rows, sort, order);
		}
		List<Role> list = (List<Role>) map.get("listrole");

		int total = (Integer) map.get("total");
		List<UtilRoleBean> ul = new ArrayList<UtilRoleBean>();

		for (Role role : list) {
			UtilRoleBean ur = new UtilRoleBean();
			TransformBean.roleTrans(ur, role);
			ul.add(ur);
		}
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;
		 
	}
	
	/**
	 * 添加新角色
	 * @return
	 * @throws IOException
	 */
	public String addRole()throws IOException{
		JsonModel json = this.roleService.addRole(role);
		this.writeJson(json);
		return null;
	}
	
	/**
	 * 修改新角色
	 * @return
	 * @throws IOException
	 */
	public String updateRole()throws IOException{
		JsonModel json = this.roleService.updateRole(role);
		this.writeJson(json);
		return null;
	}
	
	/**
	 * 刪除角色
	 * @return
	 * @throws IOException
	 */
	public String deleteRole()throws IOException{
		if(roleIds!=null&&!(roleIds.trim().equals(""))){
		this.roleService.deleteRole(roleIds);
		}
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
	 * 获取response 对象
	 * @return
	 */
	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		return response;

	}

	/**
	 * 获取request对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		return request;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public RoleManageService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleManageService roleService) {
		this.roleService = roleService;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

}
