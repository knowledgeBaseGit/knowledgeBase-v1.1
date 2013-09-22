package com.loongsoft.knowledgebase.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
/**
 * @描述  创建接口Map的session，request，application
 * @author 张瑞祥 2013-9-9
 *
 */
public class BaseAction implements SessionAware, RequestAware, ApplicationAware {
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	protected Map<String, Object> application;

	public void setApplication(Map<String, Object> arg0) {
		application = arg0;
	}

	public void setRequest(Map<String, Object> arg0) {
		request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		session = arg0;
	}
}
