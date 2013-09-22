package com.loongsoft.knowledgebase.servlet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.loongsoft.knowledgebase.util.webOfficeUtil.FileOperInter;
import com.loongsoft.knowledgebase.util.webOfficeUtil.SimpleHtml2TxtConverter;

 

public class TxtUpload extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String content = req.getParameter("content");
		String fileName = req.getParameter("fileName");
		
		String fileName_new = new FileOperInter() {
			
			public String rename(String name_old) {
				return "new" + name_old;
			}
		}.rename(fileName);
		
		String docPath_real = this.getServletContext().getRealPath(Constants.docFolderPath);
		File txtFile = new File(docPath_real, fileName_new);
		if(txtFile.exists()){
			txtFile.delete();
		}
		
		//将文本输出到新文件
		boolean result = true;//输出的结果
		Writer writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(txtFile));
			writer.write(SimpleHtml2TxtConverter.html2txt(content));//将转换后的文本输出到 txt 文件
			writer.flush();
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		} finally{
			try {
				if (null != writer) {
					writer.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		//响应客户端
		resp.setContentType("text/plain; charset=utf-8");
		resp.getWriter().print("" + result);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}

