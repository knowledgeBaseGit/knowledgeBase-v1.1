package com.loongsoft.knowledgebase.serviceImpl;

import java.util.List;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.dao.IKeyWordDao;
import com.loongsoft.knowledgebase.service.KeywordService;

/**
 * @功能 关键词控制层操作方法
 * @author 张瑞祥 2013-8-15
 * 
 */
public class KeywordServiceImpl implements KeywordService {
	/**
	 * 关键词Dao接口
	 */
	private IKeyWordDao keywordDao;

	public IKeyWordDao getKeywordDao() {
		return keywordDao;
	}

	public void setKeywordDao(IKeyWordDao keywordDao) {
		this.keywordDao = keywordDao;
	}
	/**
	 * 关键词服务层方法
	 */
	@Override
	public List<Keyword> getHotSearchKeyword() {
		List<Keyword> list = keywordDao.getHotSearchKeyword();
		return list;
	}
	/**
	 * 添加或者更新关键词
	 */
	@Override
	public void addOrUpadteKeyword(Keyword keyword) {
		keywordDao.addOrUpadteKeyword(keyword);
		
	}
	/**
	 * 根据关键词ID查找关键词
	 */
	@Override
	public Keyword getKeywordById(int keywordId) {
		Keyword keyword = keywordDao.getKeywordById(keywordId);
		return keyword;
	}

	

}
