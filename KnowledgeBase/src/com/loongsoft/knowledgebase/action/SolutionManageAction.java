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
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.SolutionService;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilSolutionBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @desc 解决方案Action
 * @author suoyanming 2013-9-2
 * 
 */
public class SolutionManageAction extends ActionSupport {

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
	 * 模糊搜索关键词
	 */
	private String searchKey;

	/**
	 * 注入解决方案服务类
	 */
	private SolutionService soluService;

	/**
	 * 解决方案实体
	 */
	private Solution solu;
	/**
	 * 问题实体
	 */
	private Questions ques;

	/**
	 * 方案Id
	 */
	private String soluId;
	/**
	 * 问题Id
	 */
	private String quesId;

	/**
	 * 批量操作解决方案Ids
	 */
	private String soluIds;

	/**
	 * 批量操作附件Ids
	 */
	private String annexIds;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 添加解决方案
	 * 
	 * @return
	 */
	public String addSolu() throws Exception {
		JsonModel json = null;
		if (solu != null) {
			HttpSession session = this.getRequest().getSession();
			// 获取已登陆用户的信息
			Users user = (Users) session.getAttribute("loginUser");
			solu.setUsersByWriteUser(user);

			json = this.soluService.addSolu(solu);
		}
		this.writeJson(json);
		return null;
	};

	/**
	 * 问题和方案关联操作
	 * 
	 * @return
	 */
	public String addQuesAndSolu() {

		if (quesId != null && soluId != null) {
			Questions q = new Questions();
			Solution s = new Solution();

			q.setQuestionId(Integer.parseInt(quesId));
			s.setSolutionId(Integer.parseInt(soluId));
			this.soluService.addSoluAndQues(q, s);
		}
		return null;
	}

	/**
	 * 得到所有解决方案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAllSolus() throws Exception {

		Map map = null;
		if (searchKey != null) {
			map = this.soluService.getSolusByFuzzyKeys(page, rows, sort, order,
					searchKey);
		} else {
			map = this.soluService.getAllSolus(page, rows, sort, order);
		}
		List<Solution> list = (List<Solution>) map.get("listsolu");

		int total = (int) map.get("total");
		List<UtilSolutionBean> ul = new ArrayList<UtilSolutionBean>();

		for (Solution key : list) {
			UtilSolutionBean us = new UtilSolutionBean();
			TransformBean.solutionTrans(us, key);
			ul.add(us);
		}
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;

	}

	/**
	 * 审核解决方案
	 * 
	 * @return
	 */
	public String checkSolu() {
		if (soluIds != null) {
			HttpSession session = this.getRequest().getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			this.soluService.checkSolu(soluIds, loginUser);
		}
		return null;
	}

	/**
	 * 撤销审核
	 * 
	 * @return
	 */
	public String undoCheckSolu() {
		if (soluIds != null) {
			HttpSession session = this.getRequest().getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			this.soluService.undoCheckSolu(soluIds, loginUser);
		}

		return null;
	}

	/**
	 * 开放附件下载权限
	 * 
	 * @return
	 */
	public String checkAnnex() {
		if (annexIds != null) {
			this.soluService.checkAnnex(annexIds);
		}
		return null;
	}

	/**
	 * 撤销附件下载权限
	 * 
	 * @return
	 */
	public String undoCheckAnnex(){
		if(annexIds!=null){
			this.soluService.undoCheckAnnex(annexIds);
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

	public SolutionService getSoluService() {
		return soluService;
	}

	public void setSoluService(SolutionService soluService) {
		this.soluService = soluService;
	}

	public Solution getSolu() {
		return solu;
	}

	public void setSolu(Solution solu) {
		this.solu = solu;
	}

	public Questions getQues() {
		return ques;
	}

	public void setQues(Questions ques) {
		this.ques = ques;
	}

	public String getSoluId() {
		return soluId;
	}

	public void setSoluId(String soluId) {
		this.soluId = soluId;
	}

	public String getQuesId() {
		return quesId;
	}

	public void setQuesId(String quesId) {
		this.quesId = quesId;
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

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSoluIds() {
		return soluIds;
	}

	public void setSoluIds(String soluIds) {
		this.soluIds = soluIds;
	}

	public String getAnnexIds() {
		return annexIds;
	}

	public void setAnnexIds(String annexIds) {
		this.annexIds = annexIds;
	}

}
