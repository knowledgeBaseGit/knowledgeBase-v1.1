package com.loongsoft.knowledgebase.util.utilBean;

import com.loongsoft.knowledgebase.bean.Files;

/**
 * 文件版本辅助bean
 * 
 * @author suoyanming 2013-8-22
 * 
 */
public class UtilFileversionBean {
	/**
	 * 版本Id
	 */
	private Integer id;
	/**
	 * 文件Id
	 */
	private Integer fileId;
	/**
	 * 版本号
	 */
	private Integer versionId;

	/**
	 * 文件路径
	 */
	private String fileUrl;
	/**
	 * 文件作者
	 */
	private String upAuthor;
	/**
	 * 文件上传时间
	 */
	private String uploadDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getUpAuthor() {
		return upAuthor;
	}

	public void setUpAuthor(String upAuthor) {
		this.upAuthor = upAuthor;
	}

	public String getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

}
