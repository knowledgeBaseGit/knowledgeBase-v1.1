package com.loongsoft.knowledgebase.service;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * @desc 控制层（service） 文件管理操作
 * @author suoyanming 2013-8-26
 * 
 */
public interface KeyManageService {
       
	/**
	 * 获取所有已审核的关键词
	 * @return Map(List<Keyword>,total)
	 */
	public Map getAllCheckedKeys();
	
	/**
	 * 获取所有未审核的关键词
	 * @return Map(List<keyword>,total)
	 */
	public Map getAllUnCheckedKeys();
	
	
	/**
	 * 获取所有关键词
	 * 
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return(List<Keyword>,total)
	 */
	public Map getAllKeyword(int page, int rows, String sort, String order);
	
	/**
	 * 添加关键词
	 */
	public boolean addKeyword(Keyword keyword)throws Exception;
	
	/**
	 * 模糊查询关键词
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @param searchKey
	 * @return
	 */
	public Map getKeywrodByFuzzyKeys(int page, int rows, String sort, String order,String searchKey);
	
	/**
	 * 批量删除关键词
	 */
	public void deletekeys(String keywordIds);
	
	/**
	 * 更新关键词
	 */
	public void updateKey(Keyword keyword)throws Exception;
	
	/**
	 * 审核关键词
	 */
	void checkKeyword(String keywordIds,Users checkUser,String CheckDate);
	/**
	 * 取消审核关键词
	 */
	void undoCheckKey(String keywordIds,Users checkUser);
}
