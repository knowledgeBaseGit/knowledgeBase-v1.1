package com.loongsoft.knowledgebase.daoImpl;

import java.util.List;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.FunRole;
import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Module;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.dao.IModelDao;

/**
 * model层模块操作方法实现
 * 
 * @author suoyanming
 * @date 2013-9-13
 */
public class ModelDaoImpl extends BaseDaoImp implements IModelDao {

	/**
	 * 根据Id获取模块
	 */
	@Override
	public Module getModelById(String id) throws Exception {
		return this.getHibernateTemplate().get(Module.class, id);

	}

	/**
	 * 按父子节点参数获取模块
	 */
	@Override
	public List<Module> find(String hql, List param) throws Exception {
		if (!param.isEmpty()) {

			return this.getHibernateTemplate().find(hql, param.get(0));
		} else if (param.isEmpty()) {
			return this.getHibernateTemplate().find(hql);
		}
		return null;
	}

	/**
	 * 获取父亲节点下的子节点个数
	 */
	@Override
	public Integer count(String hql, String pid) throws Exception {
		List list = this.getHibernateTemplate().find(hql, pid);
		return list.size();
	}

	/**
	 * 获取模块下的功能
	 */
	@Override
	public List<Functions> getFunOfModule(String pid) throws Exception {
		String hql = "from Functions fun where fun.module.moduleId=?";
		return this.getHibernateTemplate().find(hql, pid);

	}

	/**
	 * 判断特定角色下是否有特定的功能
	 */
	@Override
	public boolean isFunOfRole(String roleId, String funId) throws Exception {
		String hql = "select fr from FunRole fr where fr.role.roleId=? and fr.functions.functionId=?";
		List<FunRole> list = this.getHibernateTemplate().find(hql, roleId,
				funId);
		if (!list.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 获取某个角色下的所有功能
	 */
	@Override
	public List<String> getAllFunsIdOfRole(String roleId) throws Exception {
		String hql = "select fr.functions.functionId from FunRole fr where fr.role.roleId=?";
		List<String> list = this.getHibernateTemplate().find(hql, roleId);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	// copy
	/**
	 * 新增角色功能
	 */
	@Override
	public void addRoleFuns(String roleId, String funId) throws Exception {
		FunRole fr = new FunRole();
		Role r = new Role();
		r.setRoleId(roleId);
		Functions f = new Functions();
		f.setFunctionId(funId);
		fr.setFunctions(f);
		fr.setRole(r);
		this.getHibernateTemplate().save(fr);
	}

	// copy
	/**
	 * 刪除角色功能
	 */
	@Override
	public void deleteRoleFuns(String roleId, String funId) throws Exception {
		 FunRole fr = this.getFRByRoleIdAndFunId(roleId, funId);
		 this.getHibernateTemplate().delete(fr);
		
	}

	/**
	 * 获取指定的角色功能记录
	 */
	@Override
	public FunRole getFRByRoleIdAndFunId(String roleId, String funId)
			throws Exception {
		String hql = "select fr from FunRole fr where fr.role.roleId=? and fr.functions.functionId=? ";
		@SuppressWarnings({ "unused", "unchecked" })
		List<FunRole> list = this.getHibernateTemplate().find(hql, roleId,
				funId);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 获取所有模块Ids
	 */
	@Override
	public List<String> getAllModuleIds() throws Exception {
		String hql="select m.moduleId from Module m ";
		List<String> list = this.getHibernateTemplate().find(hql);
		return list;
	}

}
