package com.loongsoft.knowledgebase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.service.FileService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件下载
 * 
 * @author suoyanming 2013-9-4
 * 
 */
public class FileDownLoadAction extends ActionSupport {
	/**
	 * 下载文件的路径
	 */
	private String fileUrl;

	/**
	 * 下载文件的Id
	 */
	private String fileId;

	/**
	 * 注入文件操作服务类
	 */
	private FileService fileService;

	public InputStream inputStream;

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	/**
	 * 获取文件名称
	 * 
	 * @return
	 */
	public String getDownLoadFileName() {

		String upFileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);
		String reStr = null;
		try {
			reStr = new String(upFileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 获取文件版本
	 * 
	 * @return
	 */
	private String getDownFileUrl() {
		List<Fileversion> list = null;

		if (fileId != null) {
			list = this.fileService.getFileversionByFileId(Integer
					.parseInt(fileId.trim()));
		}

		String downFileUrl = list.get(0).getFileUrl();
		return downFileUrl;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getFileSource() throws Exception {
		this.fileUrl = this.getDownFileUrl();

		try {

			File file = new File(fileUrl);
			inputStream = new FileInputStream(file);

			if (inputStream == null) {

				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		this.fileService.increFileDownLoadTimes(Integer.parseInt(fileId));
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

}
