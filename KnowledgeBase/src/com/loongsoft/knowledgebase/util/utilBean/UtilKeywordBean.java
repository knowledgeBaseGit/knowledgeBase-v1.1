package com.loongsoft.knowledgebase.util.utilBean;

import java.util.HashSet;
import java.util.Set;

import com.loongsoft.knowledgebase.bean.FileKeyword;
import com.loongsoft.knowledgebase.bean.ProjectKeyword;
import com.loongsoft.knowledgebase.bean.QuesKeyword;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * 关键词辅助bean
 * 
 * @author suoyanming 2013-8-26
 * 
 */
public class UtilKeywordBean {
	/**
	 * 关键词Id
	 */
	private Integer keywordId;

	/**
	 * 审核人
	 */
	private UtilUsersBean users;
	/**
	 * 关键词名称
	 */
	private String keywordName;
	/**
	 * 是否注销（1：是；0：否）
	 */
	private String isDstroy;

	/**
	 * 销毁时间
	 */
	private String destroyDate;
	/**
	 * 审核状态
	 */
	private String checkStatus;

	/**
	 * 审核时间
	 */
	private String checkDate;
	/**
	 * 搜索次数
	 */
	private Integer searchTimes;
	/**
	 * 创建人
	 * 
	 */
	private String createUser;
	
	/**
	 * 创建时间
	 * 
	 */
	private String createDate;
	/**
	 * 关键词描述
	 */
	private String keywordDesc;
	
	

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getKeywordDesc() {
		return keywordDesc;
	}

	public void setKeywordDesc(String keywordDesc) {
		this.keywordDesc = keywordDesc;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(Integer keywordId) {
		this.keywordId = keywordId;
	}

	public UtilUsersBean getUsers() {
		return users;
	}

	public void setUsers(UtilUsersBean users) {
		this.users = users;
	}

	public String getKeywordName() {
		return keywordName;
	}

	public void setKeywordName(String keywordName) {
		this.keywordName = keywordName;
	}

	public String getIsDstroy() {
		return isDstroy;
	}

	public void setIsDstroy(String isDstroy) {
		this.isDstroy = isDstroy;
	}

	public String getDestroyDate() {
		return destroyDate;
	}

	public void setDestroyDate(String destroyDate) {
		this.destroyDate = destroyDate;
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

	public Integer getSearchTimes() {
		return searchTimes;
	}

	public void setSearchTimes(Integer searchTimes) {
		this.searchTimes = searchTimes;
	}

}
