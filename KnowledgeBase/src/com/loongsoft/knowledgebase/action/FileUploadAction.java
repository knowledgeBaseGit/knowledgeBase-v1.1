package com.loongsoft.knowledgebase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.FileService;
import com.loongsoft.knowledgebase.service.SolutionService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件上传
 * 
 * @author suoyanming 2013-9-3
 * 
 */
public class FileUploadAction extends ActionSupport {

	/**
	 * 上传文件的文件名称
	 */
	private String fileName;

	/**
	 * 文件Id
	 */
	private String fileId;
	/**
	 * 上传文件的路径
	 */
	private String fileUrl;

	/**
	 * 注入方案服务类
	 */
	private FileService fileService;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
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

	// 文件上传
	public String fileUpload() throws Exception {
		String path =ServletActionContext.getServletContext().getRealPath(Constants.docFolderPath);
		String upFileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);
		String fileType = fileUrl.substring(fileUrl.lastIndexOf("."+1));
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
			Fileversion fileVersion = new Fileversion();
			fileVersion.setFileUrl(outFileUrl);
			fileVersion.setUploadDate(DateUtil.getCurrentDate()); 
			// 文件版本号
		    fileVersion.setVersionId(1);
			// 获取当前登陆用户
			HttpSession session = this.getRequest().getSession();
			Users user = (Users) session.getAttribute("loginUser");
			if (user != null) {
				fileVersion.setUpAuthor(user.getLoginName());
			}
			if (fileId != null) {
				Files ff = new Files();
				System.out.println("!!!!!!!!!!!!!!!!!!" + fileId);
				ff.setFileId(Integer.parseInt(fileId));
				fileVersion.setFiles(ff);
			}
			this.fileService.addFileVersion(fileVersion);
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
