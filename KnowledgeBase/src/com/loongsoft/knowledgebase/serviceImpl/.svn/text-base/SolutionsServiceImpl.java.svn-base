package com.loongsoft.knowledgebase.serviceImpl;

import com.loongsoft.knowledgebase.bean.Solution;
import com.loongsoft.knowledgebase.dao.ISolutionsDao;
import com.loongsoft.knowledgebase.service.SolutionsService;
/**
 * 解决方案的业务层操作方法实现
 * @author 张瑞祥 2013-9-12
 *
 */
public class SolutionsServiceImpl implements SolutionsService {
	/**
	 * 解决方案Dao层操作方法
	 */
	private ISolutionsDao solutionsDao;
	/**
	 * 解决方案ID
	 */
	private Integer solutionId;
	
	public Integer getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Integer solutionId) {
		this.solutionId = solutionId;
	}

	public ISolutionsDao getSolutionsDao() {
		return solutionsDao;
	}

	public void setSolutionsDao(ISolutionsDao solutionsDao) {
		this.solutionsDao = solutionsDao;
	}

	/**
	 * 解决方案的批量删除
	 */
	@Override
	public void deleteSelected(String solutionIds) {
		String[] arrSolutionIds = solutionIds.split(",");
		for (int i = 0; i < arrSolutionIds.length; i++) {
			this.solutionsDao.deleteBysolutionId(Integer.parseInt(arrSolutionIds[i]));
		}
	}
	/**
	 * 查看解决方案
	 */
	@Override
	public Solution seeBysolutionId(Integer solutionId) {
		Solution solution = solutionsDao.seeBysolutionId(solutionId);
		return solution;
	}

	@Override
	public void updateSolution(Solution solution) {
		solutionsDao.updateSolution(solution);
	}

}
