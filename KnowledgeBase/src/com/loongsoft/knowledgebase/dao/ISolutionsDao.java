package com.loongsoft.knowledgebase.dao;

import com.loongsoft.knowledgebase.bean.Project;
import com.loongsoft.knowledgebase.bean.Solution;

/**
 * 
 * @author 张瑞祥 2013-9-12
 * @ 描述 解决方法的相关操作
 */
public interface ISolutionsDao {
	/**
	 * @描述 根据解决方案id删除解决方案
	 * @param solutionId
	 */
	public void deleteBysolutionId(Integer solutionId);
	/**
	 * @描述 根据解决方案ID查找该解决方案
	 * @param solutionId
	 * @return 该解决方案的一个实体
	 */
	public Solution seeBysolutionId(Integer solutionId);
	/**
	 * 修改解决方案
	 * @param solution
	 * @return
	 */
	public void updateSolution(Solution solution);
}
