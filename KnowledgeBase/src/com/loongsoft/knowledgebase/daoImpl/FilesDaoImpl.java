package com.loongsoft.knowledgebase.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Files;
import com.loongsoft.knowledgebase.bean.Fileversion;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IFilesDao;
import com.loongsoft.knowledgebase.util.DateUtil;

/**
 * modle层 文件管理实现类
 * 
 * @author suoyanming 2013-8-12
 * 
 */
public class FilesDaoImpl extends BaseDaoImp implements IFilesDao {

	/**
	 * 保存文件
	 * 
	 * @param file
	 */

	@Override
	public void saveFile(Files file) {

		this.getHibernateTemplate().save(file);

	}

	/**
	 * 根据ID获取文件
	 * 
	 * @param fileId
	 */
	@Override
	public Files getFileById(int fileId) {
		Files file = this.getHibernateTemplate().get(Files.class, fileId);
		if (file != null) {
			return file;
		}
		return null;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileId
	 */
	@Override
	public void deleteFile(int fileId) {
		try {
			System.out.println("^^^^^^^^^^^^^^^^"
					+ this.getFileById(fileId).getFileName());

			this.getHibernateTemplate().delete(this.getFileById(fileId));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 修改文件
	 * 
	 * @param file
	 */
	@Override
	public void updateFiles(Files file) {
		this.getHibernateTemplate().update(file);

	}

	/**
	 * 根据关键词Id 获取文件
	 * 
	 * @param keywordId
	 * @return List<Files>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Files> getFilesByKeyword(int keywordId) {
		String hql = "select file from Files file,FileKeyword file_key where file_key.keyword.keywordId=? "
				+ "and file.fileId=file_key.files.fileId";
		List<Files> list = this.getHibernateTemplate().find(hql, keywordId);
		return list;
	}

	/**
	 * 热门文件top10 查询
	 * 
	 * @desc 根据浏览次数
	 * @return List<Files>
	 */
	@Override
	public List<Files> getTopBrowseFiles() {
		String hql = "select file from Files order by browseTimes desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Files> list = query.list();
		return list;
	}

	/**
	 * 热门下载top10 文件
	 * 
	 * @desc 根据下载次数
	 * @return List<Files>
	 */

	@Override
	public List<Files> getTopLoadFiles() {
		String hql = "select file from Files order by loadTimes desc";
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Files> list = query.list();
		return list;
	}

	/**
	 * 审核文件
	 * 
	 * @param file
	 */

	@Override
	public void checkFiles(Integer fileId, Users checkUser) {
		Files checkFile = this.getFileById(fileId);
		checkFile.setCheckStatus(1);
		checkFile.setUsersByCheckUser(checkUser);
		checkFile.setCheckDate(DateUtil.getCurrentDate());
		this.getHibernateTemplate().update(checkFile);
	}

	/**
	 * 撤销文件审核
	 */
	@Override
	public void undoCheckFiles(Integer fileId, Users checkUser) throws Exception {
		Files checkFile = this.getFileById(fileId);
		checkFile.setCheckStatus(0);
		checkFile.setCheckDate(DateUtil.getCurrentDate());
		checkFile.setUsersByCheckUser(checkUser);
		this.getHibernateTemplate().update(checkFile);

	}

	/**
	 * 查看文件名是否存在
	 * 
	 * @param fileName
	 * @return 若存在返回file 、若不存在则返回null
	 */
	@Override
	public Files isFileExist(String fileName) {
		String hql = "select file from Files file where file.fileName=? ";
		List<Files> list = this.getHibernateTemplate().find(hql, fileName);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取项目类别下的已审核文件
	 * 
	 * @param projectId
	 * @return map(List<Files>,total)
	 */
	@Override
	public Map getCheckedFilesByProjectId(int projectId, int page, int rows,
			String sort, String order) {
		String hql = "select file from Files file where file.checkStatus=1 and file.project.projectId="
				+ projectId;

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			List<Questions> list = query.list();
			map.put("listFiles", list);
			map.put("total", totalList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;

	}

	/**
	 * 根据关键词模糊查询已审核文件
	 * 
	 * @param keyword
	 * @return map(List<Files>,total)
	 */
	@Override
	public Map getChecedFilesByFuzzyKeys(int projectId, int page, int rows,
			String sort, String order, String key) {
		String hql = "select file from Files file where file.checkStatus=1 ";

		if (projectId >= 0) {
			hql += "and file.project.projectId=" + projectId;
		}
		if (key != null && (!"".equals(key))) {
			hql += " and file.fileName like '%" + key + "%'";
		}

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			List<Files> list = query.list();
			map.put("listFiles", list);
			map.put("total", totalList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;

	}

	/**
	 * 根据项目类别及关键词获取已审核文件
	 * 
	 * @param projectId
	 *            keywordId
	 * @return map(List<Files>,total)
	 */
	@Override
	public Map getCheckedFilesByKeyAndPro(int projectId, int page, int rows,
			String sort, String order, String[] keys) {

		String hql = "select file from Files file where file.checkStatus=1 ";
		if (projectId >= 0) {
			hql += "and file.project.projectId=" + projectId;
		}
		String keyName = "";

		if (keys.length > 0) {
			keyName += "(";
			for (int i = 0; i < keys.length - 1; i++) {
				keyName += "'" + keys[i] + "',";
			}
			keyName += "'" + keys[keys.length - 1] + "'";
			keyName += ")";
		}

		if (keyName != "") {
			hql += "  and file.fileId in (select file_key.files.fileId from FileKeyword file_key "
					+ "where file_key.keyword.keywordName in " + keyName + ")";
		}

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		List<Files> list = null;
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			list = query.list();
			map.put("listFiles", list);
			map.put("total", totalList.size());
		} catch (Exception es) {
			es.printStackTrace();
		}
		return map;

	}

	@Override
	public Map getAllUncheckFiles(int projectId, int page, int rows,
			String sort, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有已审核的文件
	 * 
	 * @return Map(List<Files>,total)
	 */
	@Override
	public Map getAllCheckedFiles(int page, int rows, String sort, String order) {
		String hql = "select file from Files file where file.checkStatus=1 ";

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			List<Files> list = query.list();
			map.put("listFiles", list);
			map.put("total", totalList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;

	}

	/**
	 * 获取所有文件（审核模块展示）
	 */
	@Override
	public Map getAllFiles(int page, int rows, String sort, String order,
			String type, String keywords) throws Exception {
		String hql = "select file from Files file where 1=1 ";

		// 按类别查询全部问题
		if (type != null) {
			if (type.equals("checked")) {
				hql += " and file.checkStatus=1 ";
			} else if (type.equals("uncheck")) {
				hql += " and file.checkStatus=0 ";
			}
		}

		if (keywords != null) {
			hql += " and file.fileName like '%" + keywords + "%'";
		}

		if (sort != null) {
			hql += " order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}

		Map map = new HashMap();
		try {
			Query query = this.getSession().createQuery(hql);
			// 查询总条数
			List totalList = query.list();
			// 分页查询
			query.setFirstResult((page - 1) * rows);
			query.setMaxResults(rows);
			List<Files> list = query.list();
			map.put("listFiles", list);
			map.put("total", totalList.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取当前最大文件Id
	 */
	@Override
	public Integer getMaxFileId() throws Exception {
		String hql = "select max(fileId) from Files ";
		return (Integer) this.getHibernateTemplate().find(hql).get(0);

	}

	/**
	 * 添加文件新版本
	 */
	@Override
	public void addFileversion(Fileversion fileversion) throws Exception {
		this.getHibernateTemplate().saveOrUpdate(fileversion);

	}

	/**
	 * 公开文件下载权限
	 */
	@Override
	public void grantFileLoad(Integer fileId) throws Exception {
		Files grantFile = this.getFileById(fileId);
		grantFile.setIsAppear(1);
		this.getHibernateTemplate().update(grantFile);

	}

	/**
	 * 撤销文件下载
	 */
	@Override
	public void undoGrantFileLoad(Integer fileId) throws Exception {
		Files undoFile = this.getFileById(fileId);
		undoFile.setIsAppear(0);
		this.getHibernateTemplate().update(undoFile);

	}

	/**
	 * 根据文件Id返回该文件的所有版本
	 */
	@Override
	public List<Fileversion> getFileversionByFileId(Integer fileId)
			throws Exception {

		String hql = "select version from Fileversion version where version.files.fileId=?";
		List<Fileversion> list = this.getHibernateTemplate().find(hql, fileId);
		return list;
	}

	/**
	 * 文件下载量加1
	 */
	@Override
	public void increFileDownLoadTimes(Integer fileId) throws Exception {
		Files file = this.getFileById(fileId);
		Integer loadTimes = file.getLoadTimes();
		if (loadTimes == null) {
			file.setLoadTimes(0);
		} else {
			file.setLoadTimes(file.getLoadTimes() + 1);
		}
		this.getHibernateTemplate().update(file);
	}

}
