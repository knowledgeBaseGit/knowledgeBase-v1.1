package com.loongsoft.knowledgebase.dao;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * @desc modle层 文件管理操作方法
 * 
 * @author suoyanming 2013-8-10
 * 
 */

public interface IFilesDao {

	/**
	 * 保存文件
	 * 
	 * @param file
	 */
	void saveFile(Files file);

	/**
	 * 获取文件
	 * 
	 * @param fileId
	 */
	Files getFileById(int fileId);

	/**
	 * 删除文件
	 * 
	 * @param fileId
	 */
	void deleteFile(int fileId);

	/**
	 * 修改文件
	 * 
	 * @param file
	 */
	void updateFiles(Files file);

	/**
	 * 查看文件名是否存在
	 * 
	 * @param fileName
	 * @return 若存在返回file 、若不存在则返回null
	 */
	Files isFileExist(String fileName);

	/**
	 * 根据关键词Id 获取文件
	 * 
	 * @param keywordId
	 * @return List<Files>
	 */
	List<Files> getFilesByKeyword(int keywordId);

	/**
	 * 热门文件top10 查询
	 * 
	 * @desc 根据浏览次数
	 * @return List<Files>
	 */
	List<Files> getTopBrowseFiles();

	/**
	 * 热门下载top10 文件
	 * 
	 * @desc 根据下载次数
	 * @return List<Files>
	 */
	List<Files> getTopLoadFiles();

	/**
	 * 审核文件
	 * 
	 * @param file
	 *            checkUserId
	 */
	void checkFiles(Integer fileId, Users checkUser);

	/**
	 * 撤销文件审核
	 * 
	 * @param file
	 * @param checkUser
	 * @throws Exception
	 */
	void undoCheckFiles(Integer fileId, Users checkUser) throws Exception;

	/**
	 * 获取项目类别下的已审核文件
	 * 
	 * @param projectId
	 * @return map(List<Files>,total)
	 */
	Map getCheckedFilesByProjectId(int projectId, int page, int rows,
			String sort, String order);

	/**
	 * 根据关键词模糊查询已审核文件
	 * 
	 * @param keyword
	 * @return map(List<Files>,total)
	 */
	Map getChecedFilesByFuzzyKeys(int projectId, int page, int rows,
			String sort, String order, String key);

	/**
	 * 根据项目类别及关键词获取已审核文件
	 * 
	 * @param projectId
	 *            keywordId
	 * @return map(List<Files>,total)
	 */
	Map getCheckedFilesByKeyAndPro(int projectId, int page, int rows,
			String sort, String order, String[] keys);

	/**
	 * 获取所有未审核的文件
	 * 
	 * @return List<Files>
	 */
	Map getAllUncheckFiles(int projectId, int page, int rows, String sort,
			String order);

	/**
	 * 获取所有已审核的文件
	 * 
	 * @return Map(List<Files>,total)
	 */
	Map getAllCheckedFiles(int page, int rows, String sort, String order);

	/**
	 * 获取所有文件（用于审核模块）
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param type
	 * @param keywords
	 * @return map(List<Filse>，total)
	 * @throws Exception
	 */
	Map getAllFiles(int page, int rows, String sort, String order, String type,
			String keywords) throws Exception;

	/**
	 * 获得当前最大文件Id
	 * 
	 * @return
	 * @throws Exception
	 */
	Integer getMaxFileId() throws Exception;

	/**
	 * 添加文件新版本
	 * 
	 * @param fileversion
	 * @throws Exception
	 */
	void addFileversion(Fileversion fileversion) throws Exception;

	/**
	 * 公开文件下载权限
	 * 
	 * @param file
	 * @throws Exception
	 */
	void grantFileLoad(Integer fileId) throws Exception;

	/**
	 * 撤销文件下载
	 * 
	 * @param file
	 * @throws Exception
	 */
	void undoGrantFileLoad(Integer fileId) throws Exception;

	/**
	 * 根据文件Id返回该文件的所有版本
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	List<Fileversion> getFileversionByFileId(Integer fileId) throws Exception;

	/**
	 * 文件下载量1
	 * 
	 * @param fileId
	 * @throws Exception
	 */
	void increFileDownLoadTimes(Integer fileId) throws Exception;

}
