package com.loongsoft.knowledgebase.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Module;
import com.loongsoft.knowledgebase.dao.IModelDao;
import com.loongsoft.knowledgebase.service.ModelService;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;
import com.loongsoft.knowledgebase.util.utilBean.UtilRoleBean;

/**
 * 控制层 模块操作服务类实现
 * 
 * @author suoyanming
 * @date 2013-9-13
 */
public class ModelServiceImpl implements ModelService {
	/**
	 * 注入模块操作方法
	 */
	private IModelDao modelDao;

	/**
	 * 获取模块树节点
	 * 
	 * @throws Exception
	 */
	@Override
	public List<Module> getTreeNode(String pid) throws Exception {
		String hql = "";
		List param = new ArrayList();
		if (pid == null || pid.trim().equals("")) {
			hql = "from Module m where m.module is null ";
		} else {
			hql = "from Module m where m.module.moduleId=?";
			param.add(pid);
		}
		hql += " order by m.moduleId asc";
		return this.modelDao.find(hql, param);

	}

	public IModelDao getModelDao() {
		return modelDao;
	}

	public void setModelDao(IModelDao modelDao) {
		this.modelDao = modelDao;
	}

	/**
	 * 获取子节点的个数
	 */
	@Override
	public Integer countChildren(String pid) {
		Integer total = 0;
		try {
			total = this.modelDao.count(
					"select count(*) from Module m where m.module.moduleId=?",
					pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return total;
	}

	/**
	 * 获取模块下的功能
	 */
	@Override
	public List<Functions> getFunOfModule(String pid) {
		List<Functions> list = null;
		try {
			list = this.modelDao.getFunOfModule(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 判断角色是否拥有此功能
	 */
	@Override
	public boolean isFunOfRole(String roleId, String functionId) {
		boolean result = false;
		try {
			result = this.modelDao.isFunOfRole(roleId, functionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 角色授权
	 */
	@Override
	public JsonModel updateFunOfRole(String roleId, String updateFuns) {

		JsonModel json = new JsonModel();
		// 原来的角色功能
		List<String> funs1 = null;
		// 新的角色功能
		String[] funs2 = null;
		// 要删除的角色功能
		List<String> delFuns = new ArrayList<String>();
		// 要新增的功能角色
		List<String> addFuns = new ArrayList<String>();
		// 所有modulids
		List<String> moduleIds = null;
		try {
			moduleIds = this.modelDao.getAllModuleIds();
			funs1 = this.modelDao.getAllFunsIdOfRole(roleId);
			if (updateFuns != null && !(updateFuns.trim().equals(""))) {
				funs2 = Util.splitStr(updateFuns, ",");
			}
			// 全部为新授权功能
			if (funs1 == null && funs2 != null) {

				for (int j = 0; j < funs2.length; j++) {
					if (!(moduleIds.contains(funs2[j]))) {
						this.modelDao.addRoleFuns(roleId, funs2[j]);
					}
				}

			} else if (funs1 != null && funs2 == null) {// 删除角色原有的全部功能操作
				for (String fid : funs1) {
					this.modelDao.deleteRoleFuns(roleId, fid);
				}

			} else if (funs1 != null && funs2 != null) {// 功能操作有变化
				// 找出新增加的功能角色
				for (int i = 0; i < funs2.length; i++) {
					if (!(funs1.contains(funs2[i]))) {
						addFuns.add(funs2[i]);
					}
				}

				// 找出去除的角色功能
				List<String> list_funs2 = new ArrayList<String>();
				for (int n = 0; n < funs2.length; n++) {
					list_funs2.add(funs2[n]);
				}

				for (int m = 0; m < funs1.size(); m++) {
					if (!(list_funs2.contains(funs1.get(m)))) {
						delFuns.add(funs1.get(m));
					}
				}
				// 增加
				for (String addId : addFuns) {
					if (!(moduleIds.contains(addId))) {
						this.modelDao.addRoleFuns(roleId, addId);
					}
				}
				// 删除
				for (String delId : delFuns) {
					this.modelDao.deleteRoleFuns(roleId, delId);
				}
			}
			json.setSuccess(true);
			json.setMsg("角色成功!");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			json.setMsg("角色授权失败！");
		}
		return json;
	}

	 
	/**
	 * 获取所有模块Ids
	 */
	@Override
	public List<String> getAllModelIds() {
		List<String> list = null;
		try {
			list = this.modelDao.getAllModuleIds();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
