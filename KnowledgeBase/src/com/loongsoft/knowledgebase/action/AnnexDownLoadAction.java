package com.loongsoft.knowledgebase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.service.SolutionService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 附件下载
 * 
 * @author suoyanming 2013-9-5
 * 
 */
public class AnnexDownLoadAction extends ActionSupport {

	/**
	 * 附件下载ID
	 */
	private String annexId;

	/**
	 * 注入问题操作服务类
	 */
	private SolutionService soluService;

	/**
	 * 输处流
	 */
	private InputStream inputStream;

	/**
	 * 附件Url
	 */
	private String annexUrl;

	/**
	 * 获取文件名称
	 * 
	 * @return
	 */
	public String getDownLoadFileName() {

		String upAnnexName = annexUrl.substring(annexUrl.lastIndexOf("\\") + 1);
		String reStr = null;
		try {
			reStr = new String(upAnnexName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reStr;
	}

	/**
	 * 获取附件url
	 * 
	 * @return
	 */
	private String getDownFileUrl() {
		Annex annex = null;

		if (annexId != null) {
			annex = this.soluService.getAnnexById(Integer.parseInt(annexId
					.trim()));
		}
		String downAnnexUrl = annex.getAnnexUrl();
		return downAnnexUrl;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAnnexSource() throws Exception {
		this.annexUrl = this.getDownFileUrl();

		try {

			File file = new File(annexUrl);
			inputStream = new FileInputStream(file);

			if (inputStream == null) {

				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		this.soluService.increAnnexLoadTimes(Integer.parseInt(annexId));
		return "success";
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public String getAnnexId() {
		return annexId;
	}

	public void setAnnexId(String annexId) {
		this.annexId = annexId;
	}

	public SolutionService getSoluService() {
		return soluService;
	}

	public void setSoluService(SolutionService soluService) {
		this.soluService = soluService;
	}

	public String getAnnexUrl() {
		return annexUrl;
	}

	public void setAnnexUrl(String annexUrl) {
		this.annexUrl = annexUrl;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
