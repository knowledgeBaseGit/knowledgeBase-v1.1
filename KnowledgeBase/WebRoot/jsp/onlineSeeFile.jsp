<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,com.ckeditor.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/onlineSeeFile.css" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">

<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jquery/jquery-1.8.0.min.js"></script>

<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/TabColor.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>



<%-- <link rel="stylesheet" href="../kindediter/themes/default/default.css" />
<script charset="utf-8" src="../kindediter/kindeditor.js"></script>
<script charset="utf-8" src="../kindediter/lang/zh_CN.js"></script>
--%>
<script type="text/javascript" src="../js/onlineSeefileOperate.js"></script>


</head>
<body>


	<%
		CKEditorConfig editorConfig = new CKEditorConfig();
		//添加配置项
		editorConfig.addConfigValue("width", "80%");//set the outer width of the entire editor UI
		editorConfig.addConfigValue("height", "600");//set the height of the editing area, Percent units are not supported

		//使用 toolbar 的预定义配置："Full"（默认）
		//editorConfig.addConfigValue("toolbar", "Full");//The toolbox (alias toolbar) definition. It is a toolbar name or an array of toolbars (strips), each one being also an array, containing a list of UI items. 

		//使用 toolbar 的预定义配置：
		editorConfig.addConfigValue("toolbar", "Basic");

		//使用 toolbar 自定义配置： Defines a toolbar with only one strip containing the "Source" button, a separator and the "Bold" and "Italic" buttons.
		//editorConfig.addConfigValue("toolbar", "[['Source', '-','Bold','Italic']]");//'-' 表示 toolbar strip 间的分隔符， strip: 某类功能按钮

		//使用 toolbar 自定义配置: 和上面等价
		/*
		List<List<String>> toolbarConfig = new ArrayList<List<String>>();
		List<String> stripConfig = new ArrayList<String>();
		stripConfig.add("Source");
		stripConfig.add("Bold");
		stripConfig.add("Italic");
		toolbarConfig.add(stripConfig);
		editorConfig.addConfigValue("toolbar", toolbarConfig);
		 */
	%>

	<%
		Map map = (Map) request.getAttribute("fileInfo");
	%>
	<!--弹出层-->
	<div class="all">
		<!-- 查看文件 -->
		<div id="see" class="pop_see">
			<table width="100%">
				<tr>
					<td align="right">所属项目：</td>
					<td><input type="text" name="projectName"
						value="<%=map.get("projectName")%>" readonly="readonly" /></td>
					<td align="right">文件名称：</td>
					<td><input type="text" name="fileName"
						value="<%=map.get("fileName")%>" readonly="readonly" /> <input
						type="hidden" id="seeFileId" value="<%=map.get("fileId")%>"></input>
					</td>
				</tr>
				<tr>
					<td align="right">文件内容：</td>
					<td colspan="3"><textarea id="fileSource" name="content"
							style="width: 100%; height: 100%;"> </textarea></td>
				</tr>

			</table>
			<ckeditor:replace replace="fileSource" basePath="../ckeditor" />


			<table width="100%">
				<tr>

					<td colspan="2" align="center"><a
						href="/prokey/fileprokey_getPro.action"> <input
							type="button" value="关闭" class="inputbutton" /></a></td>
				</tr>
			</table>
		</div>


	</div>
</body>
</html>