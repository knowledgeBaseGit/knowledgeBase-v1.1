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
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.KeyManageService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 关键词管理action
 * 
 * @author suoyanming 2013-8-26
 * 
 */
public class KeywordManageAction extends ActionSupport {

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
	 * 关键词实体类
	 */
	private Keyword keyword;

	/**
	 * 注入关键词操作服务类
	 */
	private KeyManageService keyService;

	/**
	 * 模糊搜索关键词
	 */
	private String searchKey;

	/**
	 * 批量删除关键词Ids
	 * 
	 */
	private String keywordIds;
	/**
	 * 
	 * 关键词更新时 的审核人Id
	 */
	private String keywordCheckUserId;

	public String getKeywordCheckUserId() {
		return keywordCheckUserId;
	}

	public void setKeywordCheckUserId(String keywordCheckUserId) {
		this.keywordCheckUserId = keywordCheckUserId;
	}

	public String getKeywordIds() {
		return keywordIds;
	}

	public void setKeywordIds(String keywordIds) {
		this.keywordIds = keywordIds;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public KeyManageService getKeyService() {
		return keyService;
	}

	public void setKeyService(KeyManageService keyService) {
		this.keyService = keyService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 得到所有关键词
	 * 
	 * @return null
	 * @throws Exception
	 */
	public String getAllKeys() throws Exception {
		Map map = null;
		if (searchKey != null) {
			map = this.keyService.getKeywrodByFuzzyKeys(page, rows, sort,
					order, searchKey);
		} else {
			map = this.keyService.getAllKeyword(page, rows, sort, order);
		}
		List<Keyword> list = (List<Keyword>) map.get("listkey");

		int total = (int) map.get("total");
		List<UtilKeywordBean> ul = new ArrayList<UtilKeywordBean>();

		for (Keyword key : list) {
			UtilKeywordBean uk = new UtilKeywordBean();
			TransformBean.keywordTrans(uk, key);
			ul.add(uk);
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
	 * 添加关键词
	 * 
	 * @return null
	 */
	public String addKeyword() throws Exception {
		JsonModel json = new JsonModel();
		try {
			boolean result = this.keyService.addKeyword(keyword);
			if (result) {
				json.setSuccess(true);
				json.setMsg("添加关键词" + "【" + keyword.getKeywordName() + "】成功！");
			} else {
				json.setMsg("添加关键词" + "【" + keyword.getKeywordName()
						+ "】失败！系统中已存在此关键词");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("添加关键词" + "【" + keyword.getKeywordName() + "】失败!");
		}

		this.writeJson(json);
		return null;
	}

	/**
	 * 删除关键词
	 * 
	 * @return null
	 */
	public String deleteKeyword() {
		if (keywordIds != null) {
			this.keyService.deletekeys(keywordIds);
		}
		return null;
	}

	/**
	 * 更新关键词
	 * 
	 * @return null
	 */
	public String updateKeyword() throws Exception {
		JsonModel json = new JsonModel();

		if (keyword != null) {
			if (keywordCheckUserId != "无") {
				Users user = new Users();
				user.setUserId(keywordCheckUserId);
				keyword.setUsers(user);
			}
			try {
				this.keyService.updateKey(keyword);
				json.setSuccess(true);
				json.setMsg("更新成功！");
			} catch (Exception ex) {
				ex.printStackTrace();
				json.setMsg("更新失败！");
			}
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

		Map map = this.keyService.getKeywrodByFuzzyKeys(page, rows, sort,
				order, searchKey);
		List<Keyword> list = (List<Keyword>) map.get("listkey");

		int total = (int) map.get("total");
		List<UtilKeywordBean> ul = new ArrayList<UtilKeywordBean>();

		for (Keyword key : list) {
			UtilKeywordBean uk = new UtilKeywordBean();
			TransformBean.keywordTrans(uk, key);
			ul.add(uk);
		}
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;
	}

	// 审核关键词
	public String checkKeyword() {
		HttpSession session = this.getRequest().getSession();
		Users user = (Users) session.getAttribute("loginUser");
		String checkDate = DateUtil.getCurrentDate();
		this.keyService.checkKeyword(keywordIds, user, checkDate);

		return null;
	}

	// 取消审核关键词
	public String undoCheckKey() {
		HttpSession session = this.getRequest().getSession();
		Users user = (Users) session.getAttribute("loginUser");
		this.keyService.undoCheckKey(keywordIds, user);
		
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
