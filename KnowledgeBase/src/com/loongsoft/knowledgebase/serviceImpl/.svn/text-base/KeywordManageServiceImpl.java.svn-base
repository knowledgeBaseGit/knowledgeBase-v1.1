package com.loongsoft.knowledgebase.serviceImpl;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.IKeyWordDao;
import com.loongsoft.knowledgebase.service.KeyManageService;
import com.loongsoft.knowledgebase.util.Util;

/**
 * @desc 控制层（service） 关键词管理操作实现类
 * @author suoyanming 2013-8-26
 * 
 */
public class KeywordManageServiceImpl implements KeyManageService {

	/**
	 * 注入关键词modle操作类
	 */
	private IKeyWordDao keywordDao;

	public IKeyWordDao getKeywordDao() {
		return keywordDao;
	}

	public void setKeywordDao(IKeyWordDao keywordDao) {
		this.keywordDao = keywordDao;
	}

	/**
	 * 获取所有已审核的关键词
	 * 
	 * @return Map(List<Keyword>,total)
	 */

	@Override
	public Map getAllCheckedKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有未审核的关键词
	 * 
	 * @return Map(List<keyword>,total)
	 */
	@Override
	public Map getAllUnCheckedKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 获取所有关键词
	 * 
	 * @return Map(List<keyword>,total)
	 */
	@Override
	public Map getAllKeyword(int page, int rows, String sort, String order) {
		Map map = this.keywordDao.getAllKeyword(page, rows, sort, order);
		return map;
	}

	/**
	 * 添加关键词 return 添加成功：true 添加失败：false
	 */
	@Override
	public boolean addKeyword(Keyword keyword) throws Exception {
		if (keyword != null) {
			// 判断是否已存在相同的关键词名称
			int id = this.keywordDao.getIdByKeywordName(keyword
					.getKeywordName());
			// 没有重名的关键词
			if (id < 0) {
				// 添加默认属性
				keyword.setCheckStatus(0);
				keyword.setIsDstroy(0);
				keyword.setSearchTimes(0);

				this.keywordDao.addOrUpadteKeyword(keyword);
				return true;
			}
		}
		return false;
	}

	/**
	 * 模糊查询关键词
	 */
	@Override
	public Map getKeywrodByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) {
		return this.keywordDao.getKeywrodByFuzzyKeys(page, rows, sort, order,
				searchKey);

	}

	/**
	 * 批量删除
	 */
	@Override
	public void deletekeys(String keywordIds) {
		try {
			String[] fids = Util.splitStr(keywordIds, ",");
			System.out.println(fids.length);
			for (int i = 0; i < fids.length; i++) {
				System.out.println(fids[i]);
				this.keywordDao.deleteKeywordById(Integer.parseInt(fids[i]
						.trim()));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 跟新关键词
	 */
	@Override
	public void updateKey(Keyword keyword) throws Exception {
		// 获取要更新的keyword
		Keyword updatekey = this.keywordDao.getKeywordById(keyword
				.getKeywordId());
		System.out.println(keyword.getKeywordDesc());
		updatekey.setKeywordDesc(keyword.getKeywordDesc());
		updatekey.setKeywordName(keyword.getKeywordName());
		updatekey.setCreateUser(keyword.getCreateUser());
		// 更新
		this.keywordDao.addOrUpadteKeyword(updatekey);

	}

	/**
	 * 审核关键词
	 */
	@Override
	public void checkKeyword(String keywordIds, Users checkUser,
			String checkDate) {
		try {
			String[] keyId = Util.splitStr(keywordIds, ",");
			for (int i = 0; i < keyId.length; i++) {
				this.keywordDao.checkKeyword(Integer.parseInt(keyId[i]), checkUser, checkDate);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * 取消审核关键词
	 */
	@Override
	public void undoCheckKey(String  keywordIds, Users checkUser) {
		try {
			String[] keyId = Util.splitStr(keywordIds, ",");
			for (int i = 0; i < keyId.length; i++) {
			this.keywordDao.undoCheckKey(Integer.parseInt(keyId[i]), checkUser);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
