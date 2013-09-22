package com.loongsoft.knowledgebase.service;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.util.JsonModel;

/**
 * @desc 控制层（service） 方案操作服务类
 * 
 * @author suoyanming 2013-9-2
 * 
 */
public interface SolutionService {

	/**
	 * 添加解决方案
	 * 
	 * @param solu
	 * @return
	 */
	JsonModel addSolu(Solution solu);

	/**
	 * 添加附件
	 * 
	 * @param annex
	 * @param soluId
	 */
	void addAnnex(Annex annex, Integer soluId);

	/**
	 * 问题、方案关联表操作
	 * 
	 * @param ques
	 * @param solu
	 */
	void addSoluAndQues(Questions ques, Solution solu);

	/**
	 * 获取附件
	 * 
	 * @param annexId
	 * @return
	 */
	Annex getAnnexById(Integer annexId);

	/**
	 * 附件下载量加1
	 * 
	 * @param annexId
	 */
	void increAnnexLoadTimes(Integer annexId);

	/**
	 * 模糊查询解决方案
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return （List<Solutions>,total）
	 */
	public Map getSolusByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey);

	/**
	 * 获取所有解决方案
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return(List<Solutions>,total)
	 */
	public Map getAllSolus(int page, int rows, String sort, String order);
	
	/**
	 * 审核解决方案
	 * @param soluId
	 */
    public void checkSolu(String soluIds,Users checkUser);
    
    /**
     * 撤销审核
     * @param soluIds
     * @param checkUser
     */
    public void undoCheckSolu(String soluIds,Users checkUser);
    
    /**
     * 审核附件
     * @param soluIds
     */
    public void checkAnnex(String soluIds);
    
    /**
     * 撤销下载附件权限
     * @param annexIds
     */
    public void undoCheckAnnex(String annexIds);

}
