package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.QuesKeyword;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.QuestionsService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TargetStrategy;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.Util;
import com.loongsoft.knowledgebase.util.utilBean.UtilQuesBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @desc: 控制层（action）-问题管理
 * @author suoyanming 2013-8-18
 * 
 */
public class QuestionAction extends ActionSupport {

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
	 * 项目实体类
	 */
	private Project project;

	/**
	 * 注入问题操作服务类
	 */
	private QuestionsService quesService;

	/**
	 * 
	 * 请求搜索的关键词
	 */
	private String keywords;

	/**
	 * 问题实体
	 * 
	 */
	private Questions ques;

	

	/**
	 * 批量删除Id、批量审核Id
	 */
	private String quesIds;

	/**
	 * 问题添加时的关键词
	 * 
	 */
	private Integer keywordId;

	/**
	 * 审核问题模块获取问题方式： all:所有问题 ;checked:所有已审核; uncheck:所有未审核
	 */
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public Questions getQues() {
		return ques;
	}

	public void setQues(Questions ques) {
		this.ques = ques;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public QuestionsService getQuesService() {
		return quesService;
	}

	public void setQuesService(QuestionsService quesService) {
		this.quesService = quesService;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 根据项目类别获取问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getQuesOfPro() throws Exception {
		// 初始化默认项目Id（-1为无项目ID变量）
		int projectId = -1;
		// 接收前台传来的项目Id
		if (project != null) {
			projectId = project.getProjectId();
		}

		// 分页查询
		Map map = this.quesService.getAllCheckedQues(projectId, page, rows,
				sort, order, keywords);

		List<Questions> list = (List<Questions>) map.get("listQues");
		int total = (int) map.get("total");
		List<UtilQuesBean> ul = new ArrayList();

		// 问题Bean转换为辅助uitilQuesBean，生成json 返回前台
		for (Questions ques : list) {
			UtilQuesBean utilQues = new UtilQuesBean();
			TransformBean.quesTrans(utilQues, ques);
			ul.add(utilQues);
		}

		// 生成easyui 分页格式的json格式

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

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

		return null;
	}

	/**
	 * 根据关键词搜索问题
	 * 
	 * @return
	 */
	public String getQuesByKey() {
		String[] str = Util.splitStr(keywords, "\\|");

		return null;
	}

	/**
	 * 添加新问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addQues() throws Exception {
		JsonModel json = new JsonModel();
		try {
			HttpSession session = this.getRequest().getSession();
			Users loginUser = (Users) session.getAttribute("loginUser");
			if (ques != null) {
				ques.setCheckDate(DateUtil.getCurrentDate());
				if (loginUser != null) {
					ques.setUsersByWriteUser(loginUser);
				}
				ques.setProject(project);

				json = this.quesService.addQues(ques, keywordId);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("问题【" + ques.getQuestionTitle() + "】添加失败！");
		}
		this.writeJson(json);
		return null;
	}

	/**
	 * 获取所有问题（审核模块：无项目类别搜索）
	 */
	public String getAllQues() throws Exception {

		// 分页查询
		Map map = this.quesService.getAllQues(page, rows, sort, order, type,
				keywords);

		List<Questions> list = (List<Questions>) map.get("listQues");
		int total = (int) map.get("total");
		List<UtilQuesBean> ul = new ArrayList();

		// 问题Bean转换为辅助uitilQuesBean，生成json 返回前台
		for (Questions ques : list) {
			UtilQuesBean utilQues = new UtilQuesBean();
			TransformBean.quesTrans(utilQues, ques);
			ul.add(utilQues);
		}

		// 生成easyui 分页格式的json格式

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		this.writeJson(mapJson);
		return null;
	}

	/**
	 * 审核问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkQues() throws Exception {
		HttpSession session = this.getRequest().getSession();
		Users user = (Users) session.getAttribute("loginUser");
		String checkDate = DateUtil.getCurrentDate();
		this.quesService.checkQues(quesIds, user);
		return null;
	}

	/**
	 * 撤销审核问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String undoCheckQues() throws Exception {
		HttpSession session = this.getRequest().getSession();
		Users user = (Users) session.getAttribute("loginUser");
		String checkDate = DateUtil.getCurrentDate();
		this.quesService.undoCheckQues(quesIds, user);
		return null;
	}

	/**
	 * 批量删除问题
	 * 
	 * @return
	 * @throws Exception
	 */
	public String deleteQues() throws Exception {
		if(quesIds!=null){
			this.quesService.deleteQues(quesIds);
		}
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

	public String getQuesIds() {
		return quesIds;
	}

	public void setQuesIds(String quesIds) {
		this.quesIds = quesIds;
	}

}
