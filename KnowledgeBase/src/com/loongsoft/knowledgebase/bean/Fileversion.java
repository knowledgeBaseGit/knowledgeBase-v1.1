package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

/**
 * Fileversion generated by hbm2java
 */
public class Fileversion implements java.io.Serializable {

	private Integer id;
	private Files files;
	private Integer versionId;
	private String fileUrl;
	private String upAuthor;
	private String uploadDate;

	public Fileversion() {
	}

	public Fileversion(Files files, Integer versionId, String fileUrl,
			String upAuthor, String uploadDate) {
		this.files = files;
		this.versionId = versionId;
		this.fileUrl = fileUrl;
		this.upAuthor = upAuthor;
		this.uploadDate = uploadDate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Files getFiles() {
		return this.files;
	}

	public void setFiles(Files files) {
		this.files = files;
	}

	public Integer getVersionId() {
		return this.versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	public String getFileUrl() {
		return this.fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getUpAuthor() {
		return this.upAuthor;
	}

	public void setUpAuthor(String upAuthor) {
		this.upAuthor = upAuthor;
	}

	public String getUploadDate() {
		return this.uploadDate;
	}

	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}

}
