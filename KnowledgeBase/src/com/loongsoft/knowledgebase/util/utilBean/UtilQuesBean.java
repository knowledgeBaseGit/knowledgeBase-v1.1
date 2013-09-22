package com.loongsoft.knowledgebase.util.utilBean;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Solution;

/**
 * 问题辅助bean文件
 * 
 * @author suoyanming 2013-8-19
 * 
 */
public class UtilQuesBean {
	/**
	 * 问题Id
	 */
	private Integer questionId;
	/**
	 * 提问人
	 */
	private String usersByWriteUser;

	/**
	 * 所属项目
	 */
	private String project;

	/**
	 * 问题审核人
	 */
	private String usersByCheckUser;

	/**
	 * 问题描述
	 */
	private String questionDesc;

	/**
	 * 提问时间
	 */
	private String writeDate;

	/**
	 * 审核状态
	 */
	private String checkStatus;

	/**
	 * 审核时间
	 */
	private String checkDate;

	/**
	 * 浏览次数
	 */
	private Integer browseTimes;

	/**
	 * 问题标题
	 */
	private String questionTitle;

	/**
	 * 是否已解决
	 */
	private String isSolve;

	/**
	 * 解决方案
	 * 
	 */
	private List<UtilSolutionBean> solutions;

	public List<UtilSolutionBean> getSolutions() {
		return solutions;
	}

	public void setSolutions(List<UtilSolutionBean> solutions) {
		this.solutions = solutions;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getUsersByWriteUser() {
		return usersByWriteUser;
	}

	public void setUsersByWriteUser(String usersByWriteUser) {
		this.usersByWriteUser = usersByWriteUser;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getUsersByCheckUser() {
		return usersByCheckUser;
	}

	public void setUsersByCheckUser(String usersByCheckUser) {
		this.usersByCheckUser = usersByCheckUser;
	}

	public String getQuestionDesc() {
		return questionDesc;
	}

	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getIsSolve() {
		return isSolve;
	}

	public void setIsSolve(String isSolve) {
		this.isSolve = isSolve;
	}

}
