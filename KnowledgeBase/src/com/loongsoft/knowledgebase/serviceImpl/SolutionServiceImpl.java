package com.loongsoft.knowledgebase.serviceImpl;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Annex;
import com.loongsoft.knowledgebase.bean.Questions;
import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.bean.Users;
import com.loongsoft.knowledgebase.dao.ISolutionDao;
import com.loongsoft.knowledgebase.service.SolutionService;
import com.loongsoft.knowledgebase.util.DateUtil;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;
import com.loongsoft.knowledgebase.util.utilBean.UtilSolutionBean;

/**
 * @desc 控制层（serviceImpl） 解决方案实现类
 * @author suoyanming 2013-9-2
 * 
 */
public class SolutionServiceImpl implements SolutionService {

	/**
	 * 注入方案dao 层操作方法
	 */
	private ISolutionDao solutionDao;

	/**
	 * 添加解决方案
	 */
	@Override
	public JsonModel addSolu(Solution solu) {
		JsonModel json = new JsonModel();
		try {
			boolean isExsit = this.solutionDao.isSoluExsit(solu
					.getSolutionName());
			if (isExsit) {
				json.setMsg("添加解决方案【" + solu.getSolutionName()
						+ "】失败，此方案名称已存在！");
				return json;

			}
			solu.setWriteDate(DateUtil.getCurrentDate());
			Solution addSolu = this.solutionDao.addSolu(solu);
			json.setSuccess(true);
			json.setMsg("添加解决方案【" + solu.getSolutionName() + "】成功！");

			json.setObj(addSolu.getSolutionId());

			return json;

		} catch (Exception ex) {
			ex.printStackTrace();
			json.setMsg("添加解决方案【" + solu.getSolutionName() + "】失败！");
		}
		return json;
	}

	public ISolutionDao getSolutionDao() {
		return solutionDao;
	}

	public void setSolutionDao(ISolutionDao solutionDao) {
		this.solutionDao = solutionDao;
	}

	/**
	 * 添加附件
	 */
	@Override
	public void addAnnex(Annex annex, Integer soluId) {
		try {
			this.solutionDao.addAnnex(annex, soluId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 问题、解决方案级联表添加
	 */
	@Override
	public void addSoluAndQues(Questions ques, Solution solu) {
		try {
			this.solutionDao.addSoluAndQues(ques, solu);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 根据附件Id 获取附件
	 */
	@Override
	public Annex getAnnexById(Integer annexId) {
		Annex annex = null;
		try {
			annex = this.solutionDao.getAnnexById(annexId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return annex;
	}

	/**
	 * 附件下载量加1
	 */
	@Override
	public void increAnnexLoadTimes(Integer annexId) {
		try {
			this.solutionDao.increAnnexLoadTimes(annexId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 模糊查询解决方案
	 */
	@Override
	public Map getSolusByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey) {
		Map map = null;
		try {
			map = this.solutionDao.getSolusByFuzzyKeys(page, rows, sort, order,
					searchKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取所有解决方案
	 */
	@Override
	public Map getAllSolus(int page, int rows, String sort, String order) {
		Map map = null;
		try {
			map = this.solutionDao.getAllSolus(page, rows, sort, order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 审核解决方案
	 */
	@Override
	public void checkSolu(String soluIds, Users checkUser) {
		try {
			String[] soluId = Util.splitStr(soluIds, ",");
			for (int i = 0; i < soluId.length; i++) {
				this.solutionDao.checkSolu(Integer.parseInt(soluId[i]),
						checkUser);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 撤销审核
	 */
	@Override
	public void undoCheckSolu(String soluIds, Users checkUser) {
		try {
			String[] soluId = Util.splitStr(soluIds, ",");
			for (int i = 0; i < soluId.length; i++) {
				this.solutionDao.undoCheckSolu(Integer.parseInt(soluId[i]),
						checkUser);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 撤销审核
	 */
	@Override
	public void checkAnnex(String annexIds) {
		try {
			String[] annexId = Util.splitStr(annexIds, ",");
			for (int i = 0; i < annexId.length; i++) {
				this.solutionDao.checkAnnex(Integer.parseInt(annexId[i]));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 撤销附件下载权限
	 */
	@Override
	public void undoCheckAnnex(String annexIds) {
		try {
			String[] annexId = Util.splitStr(annexIds, ",");
			for (int i = 0; i < annexId.length; i++) {
				this.solutionDao.undoCheckAnnex(Integer.parseInt(annexId[i]));

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
