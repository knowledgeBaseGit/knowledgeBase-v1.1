package com.loongsoft.knowledgebase.bean;

// Generated 2013-9-17 16:59:46 by Hibernate Tools 3.4.0.CR1

/**
 * FileKeyword generated by hbm2java
 */
public class FileKeyword implements java.io.Serializable {

	private Integer id;
	private Files files;
	private Keyword keyword;

	public FileKeyword() {
	}

	public FileKeyword(Files files, Keyword keyword) {
		this.files = files;
		this.keyword = keyword;
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

	public Keyword getKeyword() {
		return this.keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

}