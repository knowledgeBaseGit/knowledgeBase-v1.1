package com.loongsoft.knowledgebase.serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IFilesDao;
import com.loongsoft.knowledgebase.service.FileService;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;
import com.loongsoft.knowledgebase.util.Word2Html;

/**
 * 
 * 
 * @author suoyanming 2013-8-22
 * 
 */
public class FileServiceImpl implements FileService {

	/**
	 * 注入文件modle层操作类
	 */
	private IFilesDao fileDao;

	public IFilesDao getFileDao() {
		return fileDao;
	}

	public void setFileDao(IFilesDao fileDao) {
		this.fileDao = fileDao;
	}

	/**
	 * 获取已审核文件
	 * 
	 * @desc:1、获取所有已审核文件 2、获取某个项目类别下的已审核文件 3、获取项目及关键词条件下的审核文件 4、获取关键词模糊查询的已审核文件
	 * @param projectId
	 *            项目类别
	 * @param page
	 *            当前页
	 * @param rows
	 *            每页显示的行数
	 * @param sort
	 *            结果排序字段
	 * @param order
	 *            升序或降序
	 * @param keywords
	 *            关键词
	 * @return map(List<Files>,total)
	 */
	@Override
	public Map getCheckFilesManager(int projectId, int page, int rows,
			String sort, String order, String keywords) {
		if (projectId < 0 && (keywords == null || "".equals(keywords))) {
			// System.out.println("*********查询所有已审核问题*********");
			return fileDao.getAllCheckedFiles(page, rows, sort, order);

		} else if (projectId >= 0 && (keywords == null || "".equals(keywords))) {
			// System.out.println("*********查询项目类别下的已审核问题*************");
			return fileDao.getCheckedFilesByProjectId(projectId, page, rows,
					sort, order);

		} else if (keywords != null && (!keywords.equals(""))) {
			if (keywords.contains("|")) {
				String[] strs = Util.splitStr(keywords, "\\|");

				// System.out.println("************項目類別、關鍵字綜合搜索****************");
				return this.fileDao.getCheckedFilesByKeyAndPro(projectId, page,
						rows, sort, order, strs);

			} else {
				// System.out.println("************关键词模糊搜索*****************");
				return fileDao.getChecedFilesByFuzzyKeys(projectId, page, rows,
						sort, order, keywords);
			}
		}
		return null;
	}

	/**
	 * 批量删除文件
	 */
	@Override
	public void delFile(String ids) {
		try {
			String[] fids = Util.splitStr(ids, ",");
			System.out.println(fids.length);
			for (int i = 0; i < fids.length; i++) {
				System.out.println(fids[i]);
				this.fileDao.deleteFile(Integer.parseInt(fids[i].trim()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 文件审核
	 */
	@Override
	public void checkFiles(String fileIds, Users checkUser) {
		try {
			String[] fileId = Util.splitStr(fileIds, ",");
			for (int i = 0; i < fileId.length; i++) {
				this.fileDao.checkFiles(Integer.parseInt(fileId[i]), checkUser);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 撤销审核文件
	 */
	@Override
	public void undoCheckFiles(String fileIds, Users user) {
		try {
			String[] fileId = Util.splitStr(fileIds, ",");
			for (int i = 0; i < fileId.length; i++) {
				this.fileDao.undoCheckFiles(Integer.parseInt(fileId[i]), user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取所有文件（用于审核模块）
	 */
	@Override
	public Map getAllFiles(int page, int rows, String sort, String order,
			String type, String keywords) {
		Map map = null;
		try {
			map = this.fileDao.getAllFiles(page, rows, sort, order, type,
					keywords);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 添加新文件
	 */
	@Override
	public JsonModel addFile(Files file, Users createUser) {
		JsonModel json = new JsonModel();
		try {
			Files existFile = this.fileDao.isFileExist(file.getFileName());
			if (existFile != null) {
				json.setMsg("文件【" + file.getFileName() + "】已经存在！");
				return json;
			}

			this.fileDao.saveFile(file);
			json.setSuccess(true);
			json.setMsg("文件【" + file.getFileName() + "】添加成功！");
			int fileId = this.fileDao.getMaxFileId();
			json.setObj(fileId);
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("文件【" + file.getFileName() + "】添加失败！");
		}
		return json;
	}

	/**
	 * 添加新的文件版本
	 */
	@Override
	public void addFileVersion(Fileversion fileVersion) {
		try {
			this.fileDao.addFileversion(fileVersion);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 获取文件内容
	 */
	@Override
	public JsonModel getFileSource(String fileUrl) throws Exception {
		JsonModel json = new JsonModel();

		InputStream is = null;
		BufferedReader bufferReader = null;
		StringBuffer stringBuffer = new StringBuffer();

		try {
			File file = new File(fileUrl);
			is = new FileInputStream(file);
			bufferReader = new BufferedReader(new InputStreamReader(is));
			// 读取一行
			String line = null;
			while ((line = bufferReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			Word2Html word2html = new Word2Html();
			String fileSource = word2html.word2html(file);
			System.out.println("***********" + fileSource);
			json.setSuccess(true);
			json.setMsg("加载文件成功");
			json.setObj(fileSource);
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("加载文件出错，请重试！");
		} finally {
			if (is != null) {
				is.close();
			}
			if (bufferReader != null) {
				bufferReader.close();
			}

		}
		return json;
	}

	/**
	 * 开放文件下载权限
	 */
	@Override
	public void grantFileLoad(String fileIds) {
		try {
			String[] fileId = Util.splitStr(fileIds, ",");
			for (int i = 0; i < fileId.length; i++) {
				this.fileDao.grantFileLoad(Integer.parseInt(fileId[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 撤销文件下载
	 */
	@Override
	public void undoGrantFileLoad(String fileIds) {

		try {
			String[] fileId = Util.splitStr(fileIds, ",");
			for (int i = 0; i < fileId.length; i++) {
			this.fileDao.undoGrantFileLoad(Integer.parseInt(fileId[i]));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据文件Id返回该文件的所有版本
	 */
	@Override
	public List<Fileversion> getFileversionByFileId(Integer fileId) {

		List<Fileversion> list = null;
		try {

			list = this.fileDao.getFileversionByFileId(fileId);
			System.out.println(list.get(0).getFileUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 文件下载量加1
	 */
	@Override
	public void increFileDownLoadTimes(Integer fileId) {
		try {
			this.fileDao.increFileDownLoadTimes(fileId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
