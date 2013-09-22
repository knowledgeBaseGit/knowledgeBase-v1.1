package com.loongsoft.knowledgebase.dao;

import java.util.List;
import java.util.Map;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Users;

/**
 * @desc:model层 关键词管理操作方法
 * 
 * @author suoyanming 2013-8-8
 * 
 */

public interface IKeyWordDao {

	/**
	 * 添加新的、修改 关键词
	 * 
	 * @param keyword
	 */
	void addOrUpadteKeyword(Keyword keyword);

	/**
	 * 根据主键获取关键字
	 * 
	 * @param keywordId
	 */
	Keyword getKeywordById(int keywordId);

	/**
	 * 根据关键词名称获取Id
	 */
	int getIdByKeywordName(String keyName);

	/**
	 * 删除关键词
	 * 
	 * @param keywordId
	 */
	void deleteKeywordById(int keywordId);

	/**
	 * 审核关键词
	 * 
	 * @param keyword
	 */
	void checkKeyword(Integer keywordId, Users checkUser, String checkDate)throws Exception;
	
	/**
	 * 取消审核关键词
	 */
	void undoCheckKey(Integer keywordId, Users checkUser)throws Exception;

	/**
	 * 获得所有未审核的关键字
	 * 
	 * @return Map(List<Keyword>,total)
	 */

	Map getAllUnCheckKeywords();

	/**
	 * 获得已审核的关键词
	 * 
	 * @return map(List<Keyword>,total)
	 */
	Map getCheckedKeywords();

	/**
	 * 获取某个项目类别下的所有未审核关键词
	 * 
	 * @param projectId
	 * @return map(List<keyword>,total)
	 */
	Map getAllKeyOfProUncheck(int projectId);

	/**
	 * 获取某个项目类别下的所有已审核关键词
	 * 
	 * @param projectId
	 * @returnList<keyword>
	 */
	List<Keyword> getAllKeyOfProChecked(int projectId);

	/**
	 * 获取所有关键词 Map(List<Keyword>,total)
	 */
	Map getAllKeyword(int page, int rows, String sort, String order);

	/**
	 * 搜索热度关键词top10
	 * 
	 * @return List<keyword>
	 */
	List<Keyword> getHotSearchKeyword();
	
	/**
	 * 模糊查询关键词
	 * @param searchKey
	 * @return map(List<Keyword>,total)
	 */
	Map getKeywrodByFuzzyKeys(int page, int rows, String sort, String order,String searchKey);

}
