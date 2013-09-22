<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.ckeditor.*"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CKEditor 功能演示</title>
	</head>
	<body>
		<%-- 为了测试方便： 这里直接使用 scriptlet --%>
		<% 
			//textarea 的属性映射
			Map attrMap = new HashMap<String, String>();
			attrMap.put("rows", "10");
			attrMap.put("cols", "50");
		%>
		
		<%-- 第一种方式
				直接创建内置 textarea 元素的 ckeditor 实例
				textareaAttributes：HashMap<textarea 的属性, textarea 的属性值>
				
		<ckeditor:editor editor="editor2" basePath="ckeditor" value="请输入内容" textareaAttributes="<%=attrMap %>"></ckeditor:editor>
		 --%>
	
		<%-- 第二种方式：
				创建 textarea 元素，然后使用 ckeditor:replace 或 ckeditor:replaceAll 替换目标 textarea 元素
		 --%>
		<textarea cols="80" id="editor1" name="editor1" rows="10"></textarea>
		<button id="btn_2">提交</button>
		<button id="btn_3">开始编辑</button>
		
		</form>
		
		<%
			CKEditorConfig editorConfig = new CKEditorConfig();
			//添加配置项
			editorConfig.addConfigValue("width","80%");//set the outer width of the entire editor UI
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
		<%-- 
			replace: replace – points to the name or ID of the HTML textarea element that is to be replaced with a CKEditor instance. 
			basePath: basePath – contains the path to the main CKEditor directory. 
			config: editor config, specified by CKEditorConfig Object
		 --%>
		<ckeditor:replace replace="editor1" basePath="ckeditor" config="<%=editorConfig %>"/>
		<button id="btn_1">显示 word 内容</button>
		<script src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript">
			$(function(){
				//alert("hello world!");
				
				$("#btn_1").click(function(){
						//alert("hello world!");
						
						//ckeditor 编辑器的内容区域对应： div#cke_第几个编辑器_contents
						//var htmlStr = $("#cke_1_contents iframe:eq(0)").contents().find('html').html();//<head>...</body>
						//console.log(htmlStr);
						
						//jQuery.post( url [, data ] [, success(data, textStatus, jqXHR) ] [, dataType ] )
						
						$.post("getHtml",{},function(data){
							//console.log(data);
							console.log(data);
							
							//The .contents() method can also be used to get the content document of an 
							//iframe, if the iframe is on the same domain as the main page.
							//$("#cke_1_contents iframe:eq(0)").contents().find('html').html(data);
							
							//contenteditable="true" 控制着编辑器是否可以编辑
							
							//只有下面这种方法不会损失 <html><head><body> 标签
							//var mydoc = document.getElementsByTagName('iframe')[0].contentWindow.document;
							//mydoc.write(data); 
							//mydoc.close();
							
							//CKEDITOR.instances.editor1.setData($("#cke_1_contents iframe:eq(0)").contents().find('html').html());
							
							CKEDITOR.instances.editor1.insertHtml(data);//清除了 data 内的 css
							
							//CKEDITOR.instances.editor1.setData(data);
							//CKEDITOR.instances.editor1.setReadOnly(false);//默认为 false
						});
					}
				);
				
				$("#btn_2").click(function(){
					alert("开始提交");
					$.post('makeWord',{
						content:CKEDITOR.instances.editor1.getData()
					},function(data){
						alert("提交成功");
					});
				});
				
				$("#btn_3").click(function(){
					CKEDITOR.instances.editor1.replace("editor1"); 
				});
				
			});
		</script>
	</body>	
</html>
