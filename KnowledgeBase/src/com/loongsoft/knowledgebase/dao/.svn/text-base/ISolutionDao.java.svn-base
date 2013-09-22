package com.loongsoft.knowledgebase.dao;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * model层 解決方案操作方法
 * 
 * @author suoyanming 2013-9-2
 * 
 */
public interface ISolutionDao {

	/**
	 * 添加解决方案
	 * 
	 * @param solu
	 * @return
	 * @throws Exception
	 */
	Solution addSolu(Solution solu) throws Exception;

	/**
	 * 通过方案名称得到方案实体
	 * 
	 * @param soluName
	 * @return
	 * @throws Exception
	 */
	Solution getSoluBySoluName(String soluName) throws Exception;

	/**
	 * 更加Id返回方案实体
	 * 
	 * @param soluId
	 * @return
	 * @throws Exception
	 */
	Solution getSoluBySoluId(Integer soluId) throws Exception;

	/**
	 * 判断解决方案的名称是否已存在
	 * 
	 * @param soluName
	 * @return true:已存在;false 不存在
	 * @throws Exception
	 */
	boolean isSoluExsit(String soluName) throws Exception;

	/**
	 * 添加附件
	 * 
	 * @param annex
	 * @return
	 */
	Annex addAnnex(Annex annex, Integer soluId) throws Exception;

	/**
	 * 返回当前附件中最大的ID
	 * 
	 * @return
	 */
	Integer getCurrentAnnexId() throws Exception;

	/**
	 * 方案对应附件级联保存
	 */
	void saveAnnexOfSolu(Integer soluId, Integer annexId) throws Exception;

	/**
	 * 方案、问题关联表记录添加
	 * 
	 * @param ques
	 * @param solu
	 */
	void addSoluAndQues(Questions ques, Solution solu) throws Exception;

	/**
	 * 根据附件id获取附件
	 * 
	 * @param annexId
	 * @return
	 * @throws Exception
	 */
	Annex getAnnexById(Integer annexId) throws Exception;

	/**
	 * 附件下载量加1
	 * 
	 * @param annexId
	 */
	void increAnnexLoadTimes(Integer annexId) throws Exception;

	/**
	 * 模糊查询解决方案
	 * 
	 * @param searchKey
	 * @return map(List<Keyword>,total)
	 */
	Map getSolusByFuzzyKeys(int page, int rows, String sort, String order,
			String searchKey) throws Exception;

	/**
	 * 获取所有解决方案
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return map(list<Solutions>,total)
	 */
	Map getAllSolus(int page, int rows, String sort, String order)
			throws Exception;

	/**
	 * 审核解决方案
	 * 
	 * @param soluId
	 * @throws Exception
	 */
	void checkSolu(Integer soluId, Users checkUser) throws Exception;

	/**
	 * 撤销解决方案
	 * 
	 * @param soluId
	 * @param checkUser
	 * @throws Exception
	 */
	void undoCheckSolu(Integer soluId, Users checkUser) throws Exception;

	/**
	 * 开放附件下载
	 * 
	 * @param annexId
	 * @throws Exception
	 */
	void checkAnnex(Integer annexId) throws Exception;

	/**
	 * 撤销附件附件下载权限
	 * 
	 * @param annexId
	 * @throws Exception
	 */
	void undoCheckAnnex(Integer annexId) throws Exception;
}
