package com.loongsoft.knowledgebase.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.service.FileService;
import com.loongsoft.knowledgebase.service.QuestionsService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.TransformBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilFilesBean;
import com.loongsoft.knowledgebase.util.utilBean.UtilQuesBean;
import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @desc 控制层（action）文件管理操作action实现类
 * @author suoyanming 2013-8-22
 * 
 */
public class FileManageAction extends ActionSupport {
	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 每页显示的行数
	 */
	private int rows;

	/**
	 * 排序列名
	 */
	private String sort;

	/**
	 * 升降序
	 */
	private String order;

	/**
	 * 项目实体类
	 */
	private Project project;

	/**
	 * 文件实体
	 */
	private Files file;

	/**
	 * 所上传文件的路径
	 */
	private String fileUrl;

	/**
	 * 文件所属项目
	 */
	private String projectId;

	/**
	 * 注入问题操作服务类
	 */
	private FileService fileService;

	/**
	 * 
	 * 请求搜索的关键词
	 */
	private String keywords;

	/**
	 * 接收要批量删除的文件Id
	 */
	private String fileIds;

	/**
	 * 获取所有文件方式 all:全部; checked:全部已审核;uncheck:全部未审核 （审核模块使用）
	 */
	private String type;

	/**
	 * 文件所属项目名称
	 * 
	 */
	private String projectName;

	/**
	 * 文件类型
	 */
	private String fileType;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Files getFile() {
		return file;
	}

	public void setFile(Files file) {
		this.file = file;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public FileService getFileService() {
		return fileService;
	}

	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 文件搜索显示
	 * 
	 * @return json
	 * @throws Exception
	 */
	public String showFiles() throws Exception {
		// 初始化默认项目Id（-1为无项目ID变量）
		int projectId = -1;
		// 接收前台传来的项目Id
		if (project != null) {
			projectId = project.getProjectId();
		}

		// 分页查询
		Map map = this.fileService.getCheckFilesManager(projectId, page, rows,
				sort, order, keywords);

		List<Files> list = (List<Files>) map.get("listFiles");

		int total = (int) map.get("total");
		List<UtilFilesBean> ul = new ArrayList<UtilFilesBean>();
		try {
			// 文件bean转换为utilFileBean ,生成json格式数据返回
			for (int i = 0; i < list.size(); i++) {
				UtilFilesBean uf = new UtilFilesBean();
				TransformBean.filesTrans(uf, list.get(i));
				ul.add(uf);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;
	}

	/**
	 * 添加新文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addFiles() throws Exception {
		JsonModel json = null;
		try {
			// 上传文件,返回文件路徑
			String outFileUrl = this.fileUpload();
			// 取得文件类型
			// String fileType = fileUrl.substring(fileUrl.lastIndexOf("."+1));
			String fileType = this.getExtensionName(outFileUrl);
			if (outFileUrl != null) {

				if (file != null) {
					HttpSession session = this.getRequest().getSession();
					// 获取已登陆用户的信息
					Users user = (Users) session.getAttribute("loginUser");
					file.setUsersByCreateUser(user);
					file.setCreateDate(DateUtil.getCurrentDate());
					file.setBrowseTimes(0);
					file.setFileType(fileType);
					file.setLoadTimes(0);
					if (projectId != null) {
						Project project = new Project();
						project.setProjectId(Integer.parseInt(projectId));
						file.setProject(project);
					}
					json = this.fileService.addFile(file, user);

					Integer fileId = (Integer) json.getObj();

					// 添加fileVersion 表

					Fileversion fileVersion = new Fileversion();
					fileVersion.setFileUrl(outFileUrl);
					fileVersion.setUploadDate(DateUtil.getCurrentDate());
					// 文件版本号
					fileVersion.setVersionId(1);

					if (user != null) {
						fileVersion.setUpAuthor(user.getLoginName());
					}
					if (fileId != null) {
						Files ff = new Files();
						System.out.println("!!!!!!!!!!!!!!!!!!" + fileId);
						ff.setFileId(fileId);
						fileVersion.setFiles(ff);
					}
					this.fileService.addFileVersion(fileVersion);
					json.setSuccess(true);
					json.setMsg("文件【" + file.getFileName() + "】上传成功");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		this.writeJson(json);
		return null;
	}

	// 获取文件的扩展名
	public String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	// 文件上传
	public String fileUpload() throws Exception {
		String path = ServletActionContext.getServletContext().getRealPath(
				Constants.docFolderPath);
		String upFileName = fileUrl.substring(fileUrl.lastIndexOf("\\") + 1);

		System.out.println(path + "*********" + upFileName);
		JsonModel json = new JsonModel();
		InputStream is = null;
		OutputStream os = null;
		String outFileUrl = null;
		try {
			File file = new File(fileUrl);
			File outFile = new File(path, upFileName);

			os = new FileOutputStream(outFile);
			is = new FileInputStream(file);

			// 建立一个1k大小的缓冲区
			byte[] bs = new byte[1024];
			int length = 0;
			while ((length = is.read(bs)) > 0) {
				os.write(bs, 0, length);
			}
			outFileUrl = outFile.getPath();
			return outFileUrl;

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {
			is.close();
			os.close();
		}
		return outFileUrl;
	}

	/**
	 * 文件删除
	 * 
	 * @return json
	 */

	public String delFile() {
		this.fileService.delFile(fileIds);

		return null;
	}

	/**
	 * 获取所有文件（用于审核模块显示）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAllFiles() throws Exception {

		// 分页查询
		Map map = this.fileService.getAllFiles(page, rows, sort, order, type,
				keywords);

		List<Files> list = (List<Files>) map.get("listFiles");

		int total = (int) map.get("total");
		List<UtilFilesBean> ul = new ArrayList<UtilFilesBean>();
		try {
			// 文件bean转换为utilFileBean ,生成json格式数据返回
			for (int i = 0; i < list.size(); i++) {
				UtilFilesBean uf = new UtilFilesBean();
				TransformBean.filesTrans(uf, list.get(i));
				ul.add(uf);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("total", total);
		mapJson.put("rows", ul);

		writeJson(mapJson);

		return null;
	}

	/**
	 * 文件审核
	 * 
	 */
	public String checkFile() {
		try {
			if (fileIds != null) {
				HttpSession session = this.getRequest().getSession();
				// 获取已登陆用户的信息
				Users user = (Users) session.getAttribute("loginUser");
				this.fileService.checkFiles(fileIds, user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 撤销审核文件
	 * 
	 * @return
	 */
	public String undoCheckFile() {

		try {
			if (fileIds != null) {
				HttpSession session = this.getRequest().getSession();
				// 获取已登陆用户的信息
				Users user = (Users) session.getAttribute("loginUser");
				this.fileService.undoCheckFiles(fileIds, user);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取文件内容
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getFileSource() throws Exception {
		List<Fileversion> getFile = this.fileService
				.getFileversionByFileId(file.getFileId());

		JsonModel json = this.fileService.getFileSource(getFile.get(0)
				.getFileUrl());
		this.writeJson(json);
		return null;
	}

	/**
	 * 在线查看页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String onlineSee() throws Exception {
		List<Fileversion> getFile = this.fileService
				.getFileversionByFileId(file.getFileId());
		String filePath = getFile.get(0).getFileUrl();
		// String path = new String(filePath.getBytes(),"GBK");

		Map map = new HashMap();
		map.put("fileId", file.getFileId());
		map.put("fileName", file.getFileName());
		// map.put("projectName", projectName);
		map.put("fileType", fileType);
		map.put("filePath", filePath);
		this.getRequest().setAttribute("fileInfo", map);

		return "success";
	}

	/**
	 * 在线查看关闭页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	public String onlineClose() throws Exception {

		return "success";
	}

	/**
	 * 开放文件可下载权限
	 * 
	 * @return
	 * @throws Exception
	 */
	public String grantFileLoad() throws Exception {
		try {
			if (fileIds != null) {
				this.fileService.grantFileLoad(fileIds);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * 撤销文件下载
	 * 
	 * @return
	 * @throws Exception
	 */
	public String undoFileLoad() throws Exception {

		try {
			if (fileIds != null) {
				this.fileService.undoGrantFileLoad(fileIds);
			}
		} catch (Exception ex) {
		}
		return null;
	}

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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
