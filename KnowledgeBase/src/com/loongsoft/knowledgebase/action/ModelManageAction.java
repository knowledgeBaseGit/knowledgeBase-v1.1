 package com.loongsoft.knowledgebase.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loongsoft.knowledgebase.bean.Functions;
import com.loongsoft.knowledgebase.bean.Module;
import com.loongsoft.knowledgebase.service.ModelService;
import com.loongsoft.knowledgebase.util.EasyUiTreeNode;
import com.loongsoft.knowledgebase.util.HibernateProxyTypeAdapter;
import com.loongsoft.knowledgebase.util.JsonModel;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 控制层 模块管理action
 * 
 * @author suoyanming
 * @date 2013-9-13
 */
public class ModelManageAction extends ActionSupport {

	/**
	 * 父亲节点Id
	 */
	private String id;
	/**
	 * 注入模块服务类
	 */
	private ModelService modelService;

	/**
	 * 角色Id
	 */
	private String roleId;

	/**
	 * 要更新的角色功能
	 */
	private String updateFuns;

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 返回Tree的json格式数据
	 * 
	 * @return
	 * @throws IOException
	 */
	public String initTree() throws IOException {
		List<EasyUiTreeNode> tree = new ArrayList<EasyUiTreeNode>();
		try {
			List<Module> modules = this.modelService.getTreeNode(id);
			if (!modules.isEmpty()) {
				for (Module module : modules) {
					EasyUiTreeNode n = new EasyUiTreeNode();
					n.setId(module.getModuleId());
					n.setText(module.getModuleName());
					Map attributes = new HashMap();
					attributes.put("url", module.getModuleUrl());
					n.setAttributes(attributes);
					if (modelService.countChildren(id) > 0) {
						n.setState("closed");
					}
					tree.add(n);
				}
			} else {
				List<Functions> function = this.modelService.getFunOfModule(id);
				for (Functions fun : function) {
					EasyUiTreeNode n = new EasyUiTreeNode();
					n.setId(fun.getFunctionId());
					n.setText(fun.getFunctionName());
					if (this.modelService.isFunOfRole(roleId,
							fun.getFunctionId())) {
						n.setChecked(true);
					}
					Map attributes = new HashMap();
					attributes.put("controlName", fun.getControlName());
					n.setAttributes(attributes);
					tree.add(n);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.writeJson(tree);

		return null;
	}

	/**
	 * 授权角色权限
	 * @return
	 * @throws IOException
	 */
	public String updateFunOfRole() throws IOException {
		JsonModel json = this.modelService.updateFunOfRole(roleId,updateFuns);
		this.writeJson(json);
		return null;
	}

	/**
	 * 回写json字符串
	 * 
	 * @param mapJson
	 * @throws IOException
	 */
	public void writeJson(Object mapJson) throws IOException {
		// 使用google插件gson生成json格式的数据
		GsonBuilder builder = new GsonBuilder()
				.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);

		Gson gson = builder.create();
		String strJson = gson.toJson(mapJson);
		System.out.println(strJson);
		HttpServletResponse response = this.getResponse();
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(strJson);

		} catch (Exception e) {
			response.getWriter().write("");
		} finally {
			response.flushBuffer();
			response.getWriter().close();
		}
	}

	/**
	 * 获取response 对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		return response;

	}

	/**
	 * 获取request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ActionContext context = ActionContext.getContext();
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		return request;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ModelService getModelService() {
		return modelService;
	}

	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUpdateFuns() {
		return updateFuns;
	}

	public void setUpdateFuns(String updateFuns) {
		this.updateFuns = updateFuns;
	}

}
