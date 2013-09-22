package com.loongsoft.knowledgebase.util.utilBean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.loongsoft.knowledgebase.bean.FileKeyword;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * 文件辅助bean
 * 
 * @author suoyanming 2013-8-22
 * 
 */
public class UtilFilesBean {
	/**
	 * 文件Id
	 */
	private Integer fileId;
	/**
	 * 文件作者
	 */
	private UtilUsersBean usersByCreateUser;
	/**
	 * 所属项目
	 */
	private UtilProjectBean project;
	/**
	 * 审核人
	 */
	private UtilUsersBean usersByCheckUser;

	/**
	 * 创建人
	 * 
	 */
	private String createUser;

	/**
	 * 创建人Id
	 */
	private String creatUserId;
	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件描述
	 */
	private String fileDesc;
	/**
	 * 创建时间
	 */
	private String createDate;
	/**
	 * 审核状态
	 */
	private String checkStatus;
	/**
	 * 审核时间
	 */
	private String checkDate;
	/**
	 * 是否可下载
	 */
	private String isAppear;

	/**
	 * 下载次数
	 */
	private Integer loadTimes;
	/**
	 * 浏览次数
	 */
	private Integer browseTimes;
	/**
	 * 文件版本
	 */

	private List<UtilFileversionBean> fileversions;
	/**
	 * 文件类型
	 */
	private String fileType;

	/**
	 * 文件是否可浏览
	 */
	private String isBrowse;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreatUserId() {
		return creatUserId;
	}

	public void setCreatUserId(String creatUserId) {
		this.creatUserId = creatUserId;
	}

	public List<UtilFileversionBean> getFileversions() {
		return fileversions;
	}

	public void setFileversions(List<UtilFileversionBean> fileversions) {
		this.fileversions = fileversions;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public UtilUsersBean getUsersByCreateUser() {
		return usersByCreateUser;
	}

	public void setUsersByCreateUser(UtilUsersBean usersByCreateUser) {
		this.usersByCreateUser = usersByCreateUser;
	}

	public UtilProjectBean getProject() {
		return project;
	}

	public void setProject(UtilProjectBean project) {
		this.project = project;
	}

	public UtilUsersBean getUsersByCheckUser() {
		return usersByCheckUser;
	}

	public void setUsersByCheckUser(UtilUsersBean usersByCheckUser) {
		this.usersByCheckUser = usersByCheckUser;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileDesc() {
		return fileDesc;
	}

	public void setFileDesc(String fileDesc) {
		this.fileDesc = fileDesc;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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

	public String getIsAppear() {
		return isAppear;
	}

	public void setIsAppear(String isAppear) {
		this.isAppear = isAppear;
	}

	public Integer getLoadTimes() {
		return loadTimes;
	}

	public void setLoadTimes(Integer loadTimes) {
		this.loadTimes = loadTimes;
	}

	public Integer getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getIsBrowse() {
		return isBrowse;
	}

	public void setIsBrowse(String isBrowse) {
		this.isBrowse = isBrowse;
	}

}
