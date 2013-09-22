package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.TransformBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.service.ProjectsService;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilProjectBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @描述 项目类别的所有action
 * @author 张瑞祥 2013-8-27
 * 
 */
public class ProjectsAction extends ActionSupport {
	
	
	private Integer keywordId;
	/**
	 * 根据项目ID查找出的关键词集合
	 */
	private List<Keyword> keywords;
	/**
	 * 关键词集合中的一个对象
	 */
	private Keyword keyword;
	
	/**
	 * 项目服务类
	 */
	private ProjectsService projectsService;
	/**
	 * 项目集合
	 */
	private List<Project> projects;
	/**
	 * 项目名称，用来根据名称找项目，包括精确查找和模糊查找
	 */
	private String projectName;
	/**
	 * 定义一个实体类的Project
	 */
	private Project project;
	/**
	 * 定义一个Project的id
	 */
	private Integer projectId;
	/**
	 * 定义一个boolean属性的变量，struts的json需求
	 */
	private boolean ok = false;
	/**
	 * 父级项目Id
	 */
	private String parentId;
	/**
	 * 项目ids
	 */
	private String projectIds;
	
	private String keywords1;
	
	
	public Integer getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public String getKeywords1() {
		return keywords1;
	}

	public void setKeywords1(String keywords1) {
		this.keywords1 = keywords1;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public String getProjectIds() {
		return projectIds;
	}

	public void setProjectIds(String projectIds) {
		this.projectIds = projectIds;
	}

	public String getParentId() {
		return parentId;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public ProjectsService getProjectsService() {
		return projectsService;
	}

	public void setProjectsService(ProjectsService projectsService) {
		this.projectsService = projectsService;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String list() {
		projects = projectsService.findAll();
		return "success";

	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * @描述 根据用户输入的项目名称，模糊查询匹配的项目
	 * @return
	 */
	public String search() {
		projects = projectsService.findByProjectName(projectName);
		return "success";
	}

	/**
	 * @描述 根据选定的项目名称，绑定id，进行项目信息查看
	 * @return
	 * @throws IOException
	 */
	public String see() throws IOException {

		project = projectsService.seeByprojectId(projectId);
		// 得到response，并设置其类型
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");
		// 辅助bean
		UtilProjectBean utilPro = new UtilProjectBean();
		// 将hibernate映射生成的bean和自定义的辅助bean进行转换
		TransformBean.projectTrans(utilPro, project);
		// 创建一个Gson对象，或者使用
		// GsonBuilder gson = new GsonBuilder();
		Gson gson = new Gson();
		// json序列化
		String strResult = gson.toJson(utilPro);

		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return SUCCESS;
	}

	/**
	 * @描述 保存添加的项目
	 * @return
	 */
	public String save() {
		try {
			
			if (project != null) {

				if (parentId != null && (!"null".equals(parentId))) {
					Project parentPro = new Project();
					//初始化父类项目ID
					parentPro.setProjectId(Integer.parseInt(parentId.trim()));
					project.setProject(parentPro);
				}
				project.setCreateTime(DateUtil.getCurrentDate());
				projectsService.addProject(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";

	}

	/**
	 * 点击新增，获取对应的对象，从数据库取出"所属父级项目"
	 * 
	 * @throws IOException
	 */
	public String add() throws IOException {

		List<Project> projects = this.projectsService.findAll();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");

		// 新建Project辅助类的List
		List<UtilProjectBean> list = new ArrayList();

		for (Project project : projects) {
			UtilProjectBean utilPro = new UtilProjectBean();
			TransformBean.projectTrans(utilPro, project);
			list.add(utilPro);
		}

		Gson gson = new Gson();
		String strResult = gson.toJson(list);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return this.NONE;
	}

	/**
	 * 
	 * @return 新增项目时，判断项目名称是否和后台原有项目名称重复
	 * 
	 */
	public String nameCheck() {
		Project project = this.projectsService
				.findBySingleProjectName(projectName);
		try {
			//说明用户名不存在，可以新增
			if (project == null) {
				ok = true;
			}else{
				ok = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "success";
		}
		return "success";

	}

	/**
	 * @描述 项目修改
	 * @return
	 */
	public String modify() {
		try {
			if (project != null) {

				if (parentId != null && (!"null".equals(parentId))) 
				{
					Project parentPro = new Project();
					parentPro.setProjectId(Integer.parseInt(parentId.trim()));
					project.setProject(parentPro);
				}
				this.projectsService.update(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * @描述 批量删除选中的项目
	 * @return
	 */
	public String deleteAll(){
		
		projectsService.deleteAll(projectIds);
		return this.NONE;
		
	}
	
	/**
	 * 
	 * @return 根据项目id获得对应的关键词列表集合。
	 * @throws IOException 
	 */
	public String keyProject() throws IOException{
		List<Keyword> keywords = projectsService.findByProjectId(projectId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");
		List<UtilKeywordBean> list = new ArrayList<>();
		for(Keyword keyword : keywords){
			UtilKeywordBean utilKey = new UtilKeywordBean();
			TransformBean.keywordTrans(utilKey, keyword);
			list.add(utilKey);
		}
		Gson gson = new Gson();
		String strResult = gson.toJson(list);
		
		System.out.println(strResult+"strResult");
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @return 选取除选择项目所包含的的关键词以外的全部关键词
	 * @throws IOException
	 */
	public String aLLKeyProject() throws IOException{
		List<Keyword> keywords = projectsService.findAllKeywords(projectId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");
		List<UtilKeywordBean> list = new ArrayList<>();
		for(Keyword keyword : keywords){
			UtilKeywordBean utilKey = new UtilKeywordBean();
			TransformBean.keywordTrans(utilKey, keyword);
			list.add(utilKey);
		}
		Gson gson = new Gson();
		String strResult = gson.toJson(list);
		
		System.out.println(strResult+"strResult");
		
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return SUCCESS;
	}
}
