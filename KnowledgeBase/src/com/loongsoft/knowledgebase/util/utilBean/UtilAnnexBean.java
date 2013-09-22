package com.loongsoft.knowledgebase.util.utilBean;

import java.util.HashSet;
import java.util.Set;

import com.loongsoft.knowledgebase.bean.Solution;

/**
 * @desc 解决方案 附件bean 辅助类
 * 
 * @author suoyanming 2013-8-30
 * 
 */
public class UtilAnnexBean {
	/**
	 * 附件Id
	 */
	private Integer annexId;

	/**
	 * 附件名称
	 */
	private String annexName;
	/**
	 * 附件URL
	 */
	private String annexUrl;
	/**
	 * 附件是否可见
	 */
	private Integer isAppear;
	/**
	 * 附件浏览次数
	 */
	private Integer browseTimes;
	/**
	 * 附件下载次数
	 */
	private Integer loadTimes;

	public Integer getAnnexId() {
		return annexId;
	}

	public void setAnnexId(Integer annexId) {
		this.annexId = annexId;
	}

	public String getAnnexName() {
		return annexName;
	}

	public void setAnnexName(String annexName) {
		this.annexName = annexName;
	}

	public String getAnnexUrl() {
		return annexUrl;
	}

	public void setAnnexUrl(String annexUrl) {
		this.annexUrl = annexUrl;
	}

	public Integer getIsAppear() {
		return isAppear;
	}

	public void setIsAppear(Integer isAppear) {
		this.isAppear = isAppear;
	}

	public Integer getBrowseTimes() {
		return browseTimes;
	}

	public void setBrowseTimes(Integer browseTimes) {
		this.browseTimes = browseTimes;
	}

	public Integer getLoadTimes() {
		return loadTimes;
	}

	public void setLoadTimes(Integer loadTimes) {
		this.loadTimes = loadTimes;
	}

}
