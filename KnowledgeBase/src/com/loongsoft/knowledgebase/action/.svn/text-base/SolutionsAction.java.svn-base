package com.loongsoft.knowledgebase.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.service.SolutionsService;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilSolutionBean;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @描述 解决方案的Action操作
 * @author 张瑞祥 2013-9-12
 * 
 */
public class SolutionsAction extends ActionSupport {

	/**
	 * 解决方案的Service
	 */
	private SolutionsService solutionsService;
	/**
	 * 批量选中的解决方案IDS
	 * 
	 */
	private String solutionIds;
	/**
	 * 解决方案ID
	 */
	private Integer solutionId;
	/**
	 * 解决方案的一个实体对象
	 */
	private Solution solution;

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	public Integer getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}

	public SolutionsService getSolutionsService() {
		return solutionsService;
	}

	public void setSolutionsService(SolutionsService solutionsService) {
		this.solutionsService = solutionsService;
	}

	public String getSolutionIds() {
		return solutionIds;
	}

	public void setSolutionIds(String solutionIds) {
		this.solutionIds = solutionIds;
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */

	public String deleteSolu() {
		System.out.println("solutionIds" + solutionIds);
		solutionsService.deleteSelected(solutionIds);
		return NONE;
	}

	/**
	 * 查看解决方案
	 * 
	 * @return
	 * @throws Exception
	 */
	public String seeSolu() throws Exception {
		solution = solutionsService.seeBysolutionId(solutionId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("appliction/json;charset=utf-8");
		UtilSolutionBean utilSolu = new UtilSolutionBean();
		TransformBean.solutionTrans(utilSolu, solution);
		Gson gson = new Gson();
		String strResult = gson.toJson(utilSolu);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strResult);
		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
		return NONE;
	}

	/**
	 * 保存解决方案
	 * 
	 * @return
	 */
	public String save() {
		System.out.println(solution.getSolutionId()+"solutionId");
		solutionsService.updateSolution(solution);
		
		return SUCCESS;
		
	}

}
