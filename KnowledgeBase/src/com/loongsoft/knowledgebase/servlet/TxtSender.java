package com.loongsoft.knowledgebase.servlet;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.loongsoft.knowledgebase.util.webOfficeUtil.SimpleHtml2TxtConverter;

/**
 * 读取服务端的文本文件，将内容发送到客户端
 * @author suoyanming 
 * @date 2013-9-17
 */
public class TxtSender extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String docPath_relative = "/demo.txt";//相对于文档所在目录
		String docPath = Constants.docFolderPath +  docPath_relative;
		String docPath_real = this.getServletContext().getRealPath(docPath);
		
		resp.setContentType("text/html; charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(SimpleHtml2TxtConverter.txt2html(new File(docPath_real)));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
}

