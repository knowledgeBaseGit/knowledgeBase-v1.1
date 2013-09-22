<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/Home.css" type="text/css" rel="stylesheet" />
 
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
</head>
<body>
	<div class="all">
		<div class="keystyle">
			<table class="tab">
				<tr>
					<td >热搜关键词 ：</td>
					<!-- 使用struts标签对生成的关键词集合进行遍历 -->
					<s:iterator value="keywords" var="keyword">
					<!-- 为每个热搜关键词添加链接，链接到       QueryList.jsp   -->
						<td>| <a href="/question/question_findByHotKeyword.action?keywordId=${keyword.keywordId }"><s:property value="#keyword.keywordName" /></a></td>
					</s:iterator>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
				</tr>
			</table>
		</div>
		<div class="waterfull">
			<ul id="box">
			<!-- 在首页显示所有的项目 -->
			<% int i = 0; %>
				<s:iterator value="projects" var="project">
				<li><div class="boxpic">
						<a href="/project/project_list.action"><img src="../resource/images/<%=i++ %>.jpg" /></a>
					</div>
					<div class="boxtext">
						<s:property value="#project.projectName" />
					</div></li>
				</s:iterator>
			</ul>
		</div>
	</div>
	<script type="text/javascript" src="../js/waterfull.js"></script>
</body>
</html>
