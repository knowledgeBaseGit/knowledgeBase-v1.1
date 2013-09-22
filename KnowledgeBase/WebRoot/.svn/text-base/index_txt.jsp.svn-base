<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.ckeditor.*"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="js/jquery-1.10.2.js"></script>
		<script src="ckeditor/ckeditor.js"></script>
		<script src="ckeditor/plugins/ajax/plugin.js"></script>
	</head>
	<body>
		<%
			CKEditorConfig editorConfig = new CKEditorConfig();
			//添加配置项
			editorConfig.addConfigValue("width","80%");//set the outer width of the entire editor UI
			editorConfig.addConfigValue("height","600");//set the height of the editing area, Percent units are not supported
			
			/*
				要求：CKEditor 的功能应该限制为和记事本相同
				 剪切、复制、粘贴、撤销、重做、字体（Bold/Italic、字号、字体），缩进
			*/
			
			//自定义工具栏，功能和记事本一致
			//editorConfig.addConfigValue("toolbar", "[['Cut','Copy','Paste','PasteText'],['Bold', '-','Italic'],['Find','Replace','SelectAll'],['Font','FontSize'],['Outdent','Indent'],['Maximize']]");//'-' 表示 toolbar strip 间的分隔符， strip: 某类功能按钮的集合
			editorConfig.addConfigValue("toolbar", "[['Cut','Copy','Paste','PasteText'],['Find','Replace','SelectAll'],['Outdent','Indent'],['Maximize']]");
		%>
		<%--使用 ckeditor 提供的 taglib 创建其实例，会动态引入 ckeditor.js, 无需手动引入--%>
		<%-- <ckeditor:editor editor="editor1" basePath="ckeditor" config="<%=editorConfig %>"></ckeditor:editor> --%>
		
		<%--使用 ckeditor 提供的 taglib 创建其实例，会动态引入 ckeditor.js, 无需手动引入--%>
		<textarea id="editor1"></textarea>
		<ckeditor:replace replace="editor1" basePath="ckeditor" config="<%=editorConfig %>"></ckeditor:replace>
		<button id="btn_1">保存更改</button>
		
		<script type="text/javascript">
			$(function(){
				$.post("getTxt", function(data){
					//代码方式创建 ckeditor 实例，需要手动引入 ckeditor.js
					//var editor = CKEDITOR.replace('editor1', {"height":"600","toolbar":[['Cut','Copy','Paste','PasteText'],['Bold', '-','Italic'],['Find','Replace','SelectAll'],['Font','FontSize'],['Outdent','Indent'],['Maximize']],"width":"80%"});
					
					//CKEDITOR.instances.editor1.insertText(data);//Insert text content into the currently selected position in the editor in WYSIWYG mode.
					//insertText(data) 的前提是：编辑器内有被选择的内容，否则无法加载 data, insertHtml() 同理
					CKEDITOR.instances.editor1.setData(data);//Sets the editor data.
				});	
				
				$("#btn_1").click(function(){
					$.post("saveTxt",{
							fileName: "demo.txt",
							content:CKEDITOR.instances.editor1.getData()//html 字符串，提取其中的文本有两种方式：1. Html Parser 递归实现  2.regex 删除标签，留下文本 
						},function(data){
						if(data == 'true'){
							alert("保存成功！");
						}else{
							alert("保存失败！");
						}
					});
				});
			});
		 	/*
			// 使用 ckeditor 自己的 ajax API， 需要手动引入 ckeditor.js 和 /plugins/ajax/plugin.js
			CKEDITOR.ajax.load("getTxt", function(data){
				alert(data);
				CKEDITOR.instances.editor1.setData(data);
			});  
			*/
		</script>
	</body>	
</html>
