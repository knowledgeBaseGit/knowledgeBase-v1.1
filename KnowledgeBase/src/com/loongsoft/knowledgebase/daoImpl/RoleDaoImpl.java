package com.loongsoft.knowledgebase.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.loongsoft.knowledgebase.basis.impl.BaseDaoImp;
import com.loongsoft.knowledgebase.bean.Keyword;
import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.dao.IRoleDao;
/**
 * 角色操作实现类
 * @author suoyanming 
 * @date 2013-9-12
 */
public class RoleDaoImpl extends BaseDaoImp implements IRoleDao {

	/**
	 * 获取所有角色
	 */
	@Override
	public Map getAllRoles(int page, int rows, String sort, String order)
			throws Exception {
		String hql = "select role from Role role ";

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);
		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Keyword> list = query.list();

		map.put("listrole", list);
		map.put("total", totalList.size());
		return map;
	 
	}

	/**
	 * 模糊查询角色
	 */
	@Override
	public Map getRolesByFuzzyKeys(int page, int rows, String sort, String order,String searchKey)
			throws Exception {
		String hql = "select role from Role role ";

		if (searchKey != null) {
			hql += "where role.roleName like '%" + searchKey + "%' ";
		}

		if (sort != null) {
			hql += "order by " + sort;
		}
		if (order != null) {
			hql += " " + order;
		}
		Query query = this.getSession().createQuery(hql);

		Map map = new HashMap();

		// 查询总条数
		List totalList = query.list();
		// 分页查询
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		List<Keyword> list = query.list();

		map.put("listrole", list);
		map.put("total", totalList.size());
		return map;
	}

	/**
	 *判断角色Id是否存在
	 */
	@Override
	public boolean isRoleIdExsit(String roleId) throws Exception {
		 
		Role r = this.getHibernateTemplate().get(Role.class,roleId);
		if(r!=null){
			return true;
		}
		return false;
	}

	/**
	 * 判断角色名称是否已经存在
	 */
	@Override
	public boolean isRoleNameExsit(String roleName) throws Exception {
		 String hql = "select r from Role r where r.roleName=?";
		List<Role> list =  this.getHibernateTemplate().find(hql,roleName);
		if(!list.isEmpty()){
			return true;
		}
		return false;
	}

	/**
	 * 添加新角色
	 */
	@Override
	public void addRole(Role role) throws Exception {
		this.getHibernateTemplate().save(role);
		
	}

	/**
	 * 更新角色
	 */
	@Override
	public void updateRole(Role role) throws Exception {
		this.getHibernateTemplate().update(role);
		
	}

	/**
	 * 判断指定Id的名称除该角色是否还存在
	 */
	@Override
	public boolean isRoleNameOfIdEixsit(String roleName,String roleId) throws Exception {
		 String hql = "select r from Role r where r.roleName=? and r.roleId!=?";
			List<Role> list =  this.getHibernateTemplate().find(hql,roleName,roleId);
			if(!list.isEmpty()){
				return true;
			}
			return false;
	}
	
	
    /**
     * 刪除角色
     */
	@Override
	public void deleteRole(String roleId) throws Exception {
		 
		this.getHibernateTemplate().delete(this.getRoleById(roleId));
		
	}

	/**
	 * 根据角色Id得到角色
	 */
	@Override
	public Role getRoleById(String roleId) throws Exception {
		return this.getHibernateTemplate().get(Role.class, roleId);
	
	}
	
	
    
}
