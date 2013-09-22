<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.loongsoft.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
</head>

<%
	Map map = (Map) request.getAttribute("fileInfo");
    String fileName = (String)map.get("fileName");
    fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8"); 
    System.out.println("wenjian ___"+fileName);
%>


<body onload="domReady();">
	<input type="hidden" id="fileType" value="<%=map.get("fileType")%>"></input>
	<input type="hidden" id="filePath" value="<%=map.get("filePath")%>"></input>
	<input type="hidden" id="fileName" value="<%=map.get("fileName")%>"></input>

	<OBJECT id=WebOffice1 height=520 width="100%"
		style="LEFT: 0px; TOP: 0px"
		classid="clsid:E77E049B-23FC-4DB8-B756-60529A35FAD5"
		codebase=WebOffice/WebOffice.ocx#Version=3,0,0,0>
		<PARAM NAME="_Version" VALUE="65536">
		<PARAM NAME="_ExtentX" VALUE="2646">
		<PARAM NAME="_ExtentY" VALUE="1323">
		<PARAM NAME="_StockProps" VALUE="0">
	</OBJECT>

	<button onclick="SaveDoc();">保存到服务端</button>

	<script language="javascript" type="text/javascript">
		var status;//文件的状态：修改/保存

		//WebOffice1_NotifyCtrlReady();    
		function openDoc() {
			//long LoadOriginalFile(BSTR pcFileNameOrUrl, BSTR pcType)
			//第一个参数：文档地址，可以是文档路径，也可是获取文档的 url, 如果制定为 空字符串，表示新建文档
			//第二个参数: 文档的格式，可以是： doc/xls/ppt/wps/vsd
			//返回值：如果文档加载失败，return 0  
			//var result = document.all.WebOffice1.LoadOriginalFile("C:\\win7.doc", "doc");

			var fileType = $("#fileType").val().trim();
			var filePath = $("#filePath").val();
			var fileName = $("#fileName").val();
		 
		 

			if (fileType == 'docx') {
				fileType = 'doc';
			} else if (fileType == 'pptx') {
				fileType = 'ppt';
			} else if (fileType == 'xlsx') {
				fileType = 'xls';
			} else if (fileType == 'vsdx') {
				fileType = 'vsd';
			}
			var result = document.all.WebOffice1.LoadOriginalFile(
					"/getDoc?filePath=" + filePath, fileType);//第一个参数的 / 表示的是当前 app 路径， 而不是而不是常用的 localhost，比较别扭 
			if (result == 0) {
				alert("文件加载失败！");
			} else {
				//初始加载，文件修改状态为 1（保存状态）
				//alert(document.all.WebOffice1.IsSaved());//0， 初始加载后状态为 0(修改状态)
				//alert("文件加载成功！");
			}
			/*
				隐藏不需要的按钮 ， 2.1.23 HideMenuItem
					vNew = 0x01,	//新建 
					vOpen = 0x02,   //打开
					vSaveAs = 0x04,	//保存 
					vPrint = 0x10,	//打印 
					vPrintView = 0x20,	//打印预览 
					vReturn = 0x1000,	//全屏 
					vFullScrean = 0x2000	//返回 
			 */

			//document.all.WebOffice1.HideMenuItem(0x01 + 0x02 + 0x04 + 0x10 + 0x20);  
			//隐藏WebOffice自带工具栏     
			//document.all.WebOffice1.ShowToolBar = 0; // 0 隐藏 1 显示
			return result;
		}
		function domReady() {
			obj = document.all.item("WebOffice1");
			//alert(obj == null);
			if (obj != null)//如果成功获取到集合内的某个元素
			{
				openDoc();
			}
		}

		function SaveDoc() {
			var returnValue; // 保存页面的返回值
			document.all.WebOffice1.HttpInit(); // 初始化Http引擎
			// 添加相应的Post元素
			// document.all.WebOffice1.HttpAddPostString("username", "allbutone"); 
			// 添加上传文件
			document.all.WebOffice1.HttpAddPostCurrFile("aaa.doc", "");//第二个参数为空字符串，表示由控件自动指定  
			// 提交上传文件(url)
			returnValue = document.all.WebOffice1.HttpPost("/uploadDoc");
			if ("true" == returnValue) {
				alert("文件上传成功");
				//无需重新加载
			} else {
				alert("文件上传失败");
			}
		}
	</script>
</body>
