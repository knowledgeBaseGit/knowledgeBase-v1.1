package com.loongsoft.knowledgebase.basis;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 单元测试基类
 * 
 * 获得spring上下文的方法是：this.getContext()
 * @author mahongliang
 * @createdate 20130402
 * @modifieddate 20130410
 */
public class TestBase extends TestCase{

	@Override
	/**
	 * 执行单元测试的前置方法，主要为获得spring上下文
	 */
	protected void setUp() throws Exception{
		super.setUp();
		context = new FileSystemXmlApplicationContext("WEB-INF/config/spring/applicationContext*.xml");
	}
	
	@Override
	/**
	 * 执行单元测试的后置方法，目前没有任何方法
	 */
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		((AbstractApplicationContext)context).close();
	}
	
	/**
	 * 获得spring上下文
	 * @return 
	 */
	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	/**
	 * spring上下文
	 */
	private ApplicationContext context = null;
	
}