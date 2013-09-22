package com.loongsoft.knowledgebase.service;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * @desc 控制层（service） -文件操作服务
 * @author suoyanming 2013-8-22
 * 
 */
public interface FileService {

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
	Map getCheckFilesManager(int projectId, int page, int rows, String sort,
			String order, String keywords);

	/**
	 * 批量删除文件
	 * 
	 * @param ids
	 */
	void delFile(String ids);

	/**
	 * 文件审核
	 */
	void checkFiles(String fileIds, Users user);

	/**
	 * 撤销审核文件
	 * 
	 * @param file
	 * @param user
	 * @throws Exception
	 */
	void undoCheckFiles(String fileIds, Users user);

	/**
	 * 获取所有文件（文件审核）
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param type
	 *            （all:全部; checked:已审核; uncheck:为审核）
	 * @param keywords
	 * @return map(List<Files> ,total)
	 */
	Map getAllFiles(int page, int rows, String sort, String order, String type,
			String keywords);

	/**
	 * 添加新文件
	 * 
	 * @param file
	 * @param createUser
	 * @return jsonModel
	 */
	JsonModel addFile(Files file, Users createUser);

	/**
	 * 添加新的文件版本
	 * 
	 * @param fileVersion
	 */
	void addFileVersion(Fileversion fileVersion);

	/**
	 * 获取文件内容
	 * 
	 * @return
	 */
	JsonModel getFileSource(String fileUrl) throws Exception;

	/**
	 * 开发文件下载权限
	 * 
	 * @param file
	 */
	void grantFileLoad(String fileIds);

	/**
	 * 撤销文件下载权限
	 * 
	 * @param file
	 */
	void undoGrantFileLoad(String fileIds);
     
	/**
	 * 根据文件Id返回该文件的所有文件版本
	 * @return 
	 */
	List<Fileversion> getFileversionByFileId(Integer fileId);
	
	/**
	 * 文件下载量加1
	 * @param fileId
	 */
	void increFileDownLoadTimes(Integer fileId);
}
