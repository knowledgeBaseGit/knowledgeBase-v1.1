package com.loongsoft.knowledgebase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.service.SolutionService;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 附件上传
 * 
 * @author suoyanming 2013-8-31
 * 
 */
public class AnnexUploadAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 上传文件的文件名称
	 */
	private String fileName;

	/**
	 * 附件所属方案Id
	 */
	private String soluId;

	/**
	 * 附件名称
	 */
	private String annexName;

	/**
	 * 上传文件的路径
	 */
	private String fileUrl;

	/**
	 * 注入方案服务类
	 */
	private SolutionService soluService;

	public SolutionService getSoluService() {
		return soluService;
	}

	public void setSoluService(SolutionService soluService) {
		this.soluService = soluService;
	}

	public String getAnnexName() {
		return annexName;
	}

	public void setAnnexName(String annexName) {
		this.annexName = annexName;
	}

	 

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSoluId() {
		return soluId;
	}

	public void setSoluId(String soluId) {
		this.soluId = soluId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	// 附件上传
	public String annexUpload() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(
				"/annex");
		String upFileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);
		System.out.println(path + "*********" + upFileName);
		JsonModel json = new JsonModel();
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = new File(fileUrl);
			File outFile = new File(path, upFileName);
			String outFileUrl = outFile.getPath();
			os = new FileOutputStream(outFile);
			is = new FileInputStream(file);

			// 建立一个1k大小的缓冲区
			byte[] bs = new byte[1024];
			int length = 0;
			while ((length = is.read(bs)) > 0) {
				os.write(bs, 0, length);
			}
			Annex annex = new Annex();
			annex.setAnnexName(annexName);
			annex.setAnnexUrl(outFileUrl);
			annex.setBrowseTimes(0);
			annex.setIsAppear(1);
			annex.setLoadTimes(0);
			this.soluService.addAnnex(annex, Integer.parseInt(soluId));
			json.setSuccess(true);
			json.setMsg("文件【" + upFileName + "】上传成功");
            
		} catch (Exception ex) {
			ex.printStackTrace();

			json.setMsg("文件【" + upFileName + "】上传失败");
		} finally {
			is.close();
			os.close();
		}

		this.writeJson(json);
		return null;
	}

	 

	/**
	 * 回写json字符串
	 * 
	 * @param mapJson
	 * @throws IOException
	 */
	public void writeJson(Object mapJson) throws IOException {
		// 使用google插件gson生成json格式的数据
		GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

		Gson gson = builder.create();
		String strJson = gson.toJson(mapJson);
		System.out.println(strJson);
		HttpServletResponse response = this.getResponse();
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strJson);

		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
	}

	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		return response;

	}

	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		return request;

	}

}
