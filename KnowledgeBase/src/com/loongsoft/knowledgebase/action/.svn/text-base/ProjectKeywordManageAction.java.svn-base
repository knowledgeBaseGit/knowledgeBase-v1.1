package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.service.ProjectKeyTypeService;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilKeywordBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @desc 控制层（action）项目关键词管理Action
 * 
 * @author suoyanming 2013-8-15
 * 
 */

public class ProjectKeywordManageAction extends ActionSupport {
	/**
	 * 项目实体对象
	 */
	private Project project;
	/**
	 * 关键词实体对象
	 */
	private Keyword keyword;
	/**
	 * 注入项目关键词业务操作服务类
	 */
	private ProjectKeyTypeService proKeyTypeService;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public ProjectKeyTypeService getProKeyTypeService() {
		return proKeyTypeService;
	}

	public void setProKeyTypeService(ProjectKeyTypeService proKeyTypeService) {
		this.proKeyTypeService = proKeyTypeService;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

	// 获取项目类别
	public String getPro() {

		List<Project> list = this.proKeyTypeService.showAllParentProject();
		// 获得参数
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		request.setAttribute("list", list);
		return SUCCESS;
	}

	// 获取某个项目类别下的关键词（仅仅返回关键词的名称）
	public String getKey() throws IOException {
		StringBuffer str = new StringBuffer();
		System.out.println(project.getProjectId());
		List<Keyword> list = this.proKeyTypeService.getAllKeywordOfPro(project
				.getProjectId());

		List<UtilKeywordBean> ul = new ArrayList<UtilKeywordBean>();
		for (Keyword key : list) {
			UtilKeywordBean uk = new UtilKeywordBean();
		    TransformBean.keywordTrans(uk, key);
		    ul.add(uk);
		}

		// 获得参数
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);

		/* Map map = new HashMap(); */
		List list1 = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			/*
			 * map.put("keyName_"+i,list.get(i).getKeywordName());
			 * map.put("keyId_"+i, list.get(i).getKeywordId());
			 */
			list1.add(list.get(i).getKeywordName());
		}

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String strResult = gson.toJson(ul);

		System.out.println("*********************" + strResult);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}

		return null;

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
			return;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
