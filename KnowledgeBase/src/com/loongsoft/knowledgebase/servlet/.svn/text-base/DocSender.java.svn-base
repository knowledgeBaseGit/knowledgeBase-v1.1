package com.loongsoft.knowledgebase.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.loongsoft.knowledgebase.util.webOfficeUtil.FileUtil;

/**
 * 向客户端发送文档流
 * 
 * @author suoyanming
 * @date 2013-9-17
 */
public class DocSender extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		InputStream is = null;
		OutputStream os = null;

		String filePath = (String) req.getParameter("filePath");
		filePath = new String(filePath.getBytes("ISO-8859-1"), "UTF-8");   

		//String path = new String(filePath.getBytes(), "GBK");
		String fileName = (String) req.getParameter("fileName");
          
		String docPath_relative = "/" + fileName;// 相对于文档存储路径, 最前面的 / 表示文档存储目录
		String docPath = Constants.docFolderPath + docPath_relative;// 目标文档的路径

	 
		try {
			// is = this.getServletContext().getResourceAsStream(docPath);
			File file = new File(filePath);
			is = new FileInputStream(file);
			os = resp.getOutputStream();

			if (null == is) {
				throw new RuntimeException(docPath + "不存在！");
			}

			FileUtil.flushInputToOutput(is, os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			is.close();
			os.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
