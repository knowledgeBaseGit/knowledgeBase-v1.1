package com.loongsoft.knowledgebase.servlet;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.loongsoft.knowledgebase.util.webOfficeUtil.Constants;
import com.loongsoft.knowledgebase.util.webOfficeUtil.FileOperInter;
import com.loongsoft.knowledgebase.util.webOfficeUtil.FileUtil;

/**
 * 处理文档上传
 * @author suoyanming 
 * @date 2013-9-17
 */
public class DocUpload extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!进入上传servelet");
		InputStream is = null;
		OutputStream os = null;
		
		boolean uploadResult = true;//文件上传的结果，需要反馈给客户端
		String docFolderRealPath = this.getServletContext().getRealPath(Constants.docFolderPath);
		
		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Set factory constraints
		factory.setSizeThreshold(Constants.sizeThreshold);
		
		// Configure a repository (to ensure a secure temp location is used)
		// temporary files are deleted automatically by a reaper thread silently
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute(Constants.tmpDir);
		factory.setRepository(repository);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(Constants.requestSize);

		
		// Parse the request
		List<FileItem> items;
		try {
			items = upload.parseRequest(req);
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();//DiskFileItem：The default implementation of the FileItem interface. DiskFileItem 对应的临时文件会被自动删除
			
			// 一个页面只对于一个文档
			if(iter.hasNext()){
				FileItem item = iter.next();
				
			/*
				 判断是常规表单域还是文件域
				if (item.isFormField()) {
					//processFormField(item);
				} else {
					//processUploadedFile(item);
				}
			*/
				
				String docName_old = item.getFieldName();//Returns the name of the field in the multipart form corresponding to this file item.  
//				String docPath_client = item.getName();//Returns the original filename in the client's filesystem.
				
				String docName_new = new FileOperInter() {
					public String rename(String name_old) {
						return "new_" + name_old;
					}
				}.rename(docName_old);
				
				 
				File doc_new = new File(docFolderRealPath,docName_new);
				
				//删除同名文件
				if(doc_new.exists()){
					doc_new.delete();
				}
				
				is = item.getInputStream();
				os = new BufferedOutputStream(new FileOutputStream(doc_new));
				
				FileUtil.flushInputToOutput(is, os);
				
				//自动清理不及时，因此需要执行如下操作：
				for(File file: repository.listFiles()){
					if(file.getName().endsWith(".tmp")){
						file.delete();
					}
				}
			}else{
				
				uploadResult = false;
				throw new RuntimeException("请求中未发现上传文件！");
			}
		} catch (FileUploadException e) {
			uploadResult = false;
			e.printStackTrace();
		} catch (IOException e){
			uploadResult = false;
			e.printStackTrace();
		} finally{
			os.close();
			is.close();
		}
			
		//send upload result to client
		PrintWriter out = resp.getWriter();
		out.print(uploadResult + "");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}

