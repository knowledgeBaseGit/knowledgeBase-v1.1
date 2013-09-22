package com.loongsoft.knowledgebase.serviceImpl;

import java.util.Map;

import com.loongsoft.knowledgebase.bean.Role;
import com.loongsoft.knowledgebase.dao.IRoleDao;
import com.loongsoft.knowledgebase.service.RoleManageService;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.loongsoft.knowledgebase.util.Util;

/**
 * 
 * @author suoyanming 
 * @date 2013-9-12
 */
public class RoleManageServiceImpl implements RoleManageService {

	/**
	 * 注入角色操作方法
	 */
	private IRoleDao roleDao;
	
	/**
	 * 获取所有角色
	 */
	@Override
	public Map getAllRoles(int page, int rows, String sort, String order) {
		Map map = null;
		try {
			map = this.roleDao.getAllRoles(page, rows, sort, order);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	
    /**
     * 模糊查询角色
     * @throws Exception 
     */
	@Override
	public Map getRolesByFuzzyKeys(int page, int rows, String sort,
			String order, String searchKey){
		Map map = null;
		try {
			map = this.roleDao.getRolesByFuzzyKeys(page, rows, sort, order, searchKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}


	/**
	 * 添加新角色
	 */
	@Override
	public JsonModel addRole(Role role) {
		JsonModel json = new JsonModel();
		try{
		if(role!=null){
			if(this.roleDao.isRoleIdExsit(role.getRoleId())){
				json.setMsg("添加角色【"+role.getRoleName()+"】失败！角色ID:"+role.getRoleId()+"已存在！");
				return json;
			}else if(this.roleDao.isRoleNameExsit(role.getRoleName())){
				json.setMsg("添加角色【"+role.getRoleName()+"】失败！ 角色名称已存在！！");
				return json;
			}
			this.roleDao.addRole(role);
			json.setSuccess(true);
			json.setMsg("添加角色【"+role.getRoleName()+"】成功！" );
			
			
		}else{
			json.setMsg("角色添加失敗！！");
			return json;
		}
		}catch(Exception ex){
			ex.printStackTrace();
			json.setMsg("角色添加失敗！！");
		}
		
		return json;
	}


	/**
	 * 更新角色
	 */
	@Override
	public JsonModel updateRole(Role role) {
		JsonModel json = new JsonModel();
		try{
		if(this.roleDao.isRoleNameOfIdEixsit(role.getRoleName(),role.getRoleId())){
			   json.setMsg("角色名称已经存在！！！");
			   return json;
		}
		this.roleDao.updateRole(role);
		json.setSuccess(true);
		json.setMsg("角色修改成功！！");
		}catch(Exception ex){
			ex.printStackTrace();
			json.setMsg("角色修改失败！！");
		}
		return json;
	}


	/**
	 * 刪除角色
	 */
	@Override
	public void deleteRole(String roleIds) {
		try{
			String [] ids = Util.splitStr(roleIds, ",");
			for(int i =0;i<ids.length;i++){
		 this.roleDao.deleteRole(ids[i]);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	

}
