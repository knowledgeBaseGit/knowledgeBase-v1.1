<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/Type.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/projectManager.js"></script>
<script type="text/javascript" src="../js/typeAutho.js"></script>

</head>
<body>
	<div class="all">
		<div class="righttop">
		<!-- 搜索查询  -->
			<s:form action="/project/project_list.action">
				<div class="righttop-left">类别名称： <input name="projectName" /> 
					<input type="submit" value="搜索" class="inputbutton" />
				</div>
			</s:form>
			
			<div class="righttop-right">
				<ul>
					<li id="key_pro"><a href="#" onClick="keyPro();">关键词</a></li>
					<li id="proAdd"><a href="#" onClick="addPro();">新增</a></li>
					<li id="proUpdate"><a href="#" onClick="modiPro();">修改</a></li>
					<li id="proSee"><a href="#" onClick="seePro();">查看</a></li>
					<li id="proDele"><a href="#" onClick="deletePro();" >删除</a></li>
				</ul>
			</div>
			
		</div>
		<div class="rightmiddle">
		    <div class="datagridtop"><img src='../resource/images/datagridtop.jpg'/></div>
		    <div style=" height:535px">
			<table cellspacing="0" id="tab1">
				<tr style=" background: #f8f8f8;">
					<td style="border-left: 1px #e9e9e9 solid" >
					<input id="box1" type="checkbox" onclick="selectProjects(this);" value="no" />
						<label for="box1"></label>
					</td>
					<td onmouseover="this.style.background='#eaf2ff';" onmouseout="this.style.background='#f8f8f8';">类别名称</td>
					<td onmouseover="this.style.background='#eaf2ff';" onmouseout="this.style.background='#f8f8f8';">类别描述</td>
					<td onmouseover="this.style.background='#eaf2ff';" onmouseout="this.style.background='#f8f8f8';">所属父级项目</td>
					<td onmouseover="this.style.background='#eaf2ff';" onmouseout="this.style.background='#f8f8f8';">添加时间</td>
					<td onmouseover="this.style.background='#eaf2ff';" onmouseout="this.style.background='#f8f8f8';">添加人</td>
					
				</tr>
				<!-- 迭代取出的项目 -->
				<s:iterator value="projects" var="project">
					<tr>
						<td style="border-left: 1px #e9e9e9 solid" id="projectSelect">
							<input type="checkbox" value="${projectId }" id="${projectId }"  />
						</td>
						
						<td><s:property value="#project.projectName" /></td>
						<td><s:property value="#project.projectDesc" /></td>
						<td><s:property value="#project.project.projectName" /></td>
						<td><s:property value="#project.createTime" /></td>
						<td><s:property value="#project.creater" /></td>
					</tr>
				</s:iterator>
			</table>
			</div>
			<!--分页-->
		<div id="pages">
			<a href="/project/project_list.action?page=1">首页</a>
			<s:if test="totalPages>1">
				<s:if test="page>1">
					<a href="/project/project_list.action?page=${page-1}">上一页</a>
				</s:if>
				<s:else>
					<a>上一页</a>
				</s:else>
				<s:iterator value="new int[totalPages]" status="i">
					<s:if test="page == #i.count">
						<a href="/project/project_list.action?page=${i.count}"
							class="current_page">${i.count}</a>
					</s:if>
					<s:else>
						<a href="/project/project_list.action?page=${i.count}">${i.count}</a>
					</s:else>
				</s:iterator>
				<s:if test="page<totalPages">
					<a href="/project/project_list.action?page=${page+1}">下一页</a>
				</s:if>
				<s:else>
					<a>下一页</a>
				</s:else>
				<a href="/project/project_list.action?page=${totalPages }">末页</a>
			</s:if>
		</div>
		</div>
		
	</div>
	<!--弹出层-----关键词-----------关键词-----------关键词------关键词-----------------关键词-------关键词------------------>
	<div id="divkey" style="display: none; width: 610px;" >
		<form > 
		<table class="tab2" id="tab2">
			<tr>

				<td id="keywordPro"></td>

			</tr>
			<tr>
				<td colspan="4" align="center"><input type="button" value="确 定" onclick="savekeyPro()" class="inputbutton"/>
				<input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton"/></td>
			</tr>
		</table>
		</form>
	</div>
	<!--弹出层-----查看-----------查看-----------查看------查看-----------------查看--------查看-------------------------->
	<div id="divsee" style="display: none; height: 200px;">
		<form  >
		<table class="tab2" >
			<tr>
				<td align="right">类别名称：</td>
				<td><input id="projectNameSee" type="text" class="inputText" disabled="disabled"   /></td>
				<td align="right">项目描述：</td>
				<td><input id="projectDesc" type="text" class="inputText"  disabled="disabled" /></td>
			</tr>
			<tr>
				<td align="right">所属父级项目：</td>
				<td><input id="parentPro" type="text" class="inputText" disabled="disabled" /></td>
			<td align="right">添加时间：</td>
				<td><input id="createTime" type="text" class="inputText" disabled="disabled"  /></td>
			</tr>
			<tr>
				<td align="right">添加人：</td>
				<td><input id="creater" type="text" readonly="readonly" disabled="disabled" /></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="button" value="返回"
					onClick="msgBox_close();" class="inputbutton"/></td>
			</tr>
		</table>
		</form>
	</div>
	<!--弹出层-----增加-----------增加------------增加------------增加------------------增加---------------增加------------>
	<div id="divadd" style="display: none;height: 200px;">
		<form action="/project/project_save.action"  method="post" id="addForm">
		<table class="tab2" >
			<tr>
				<td align="right">类别名称：</td>
				<td><input id="projectName" type="text" class="inputText"  name="project.projectName"/>
				<div id="divPro"></div>
				</td>
				<td align="right">项目描述：</td>
				<td><input type="text" class="inputText" name="project.projectDesc"  /></td>
			</tr>
			<tr>
				<td align="right">所属父级项目：</td>
				<!-- name="parentId" 用自定义的id代替projectId -->
				 <td><select class="selectstyle" id="parentProAdd" name="parentId"></select></td>
				<td align="right" >添加人：</td>
				<td><input type="text" class="inputText" name="project.creater"/></td>
			</tr>
				
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="保存"  id="save_button" class="inputbutton"/> 
					<input type="button" value="取 消" 
					onClick="msgBox_close();" class="inputbutton"/>
			   </td>
			</tr>
		</table>
		</form>
	</div>
	<!--弹出层---------------修改-------------修改-------------修改-----------修改--------->
	<div id="divmodi" style="display: none;height: 200px;">
		<s:form action="/project/project_modify.action"  method="post" id="modiForm">
		<table class="tab2" >
			<tr>
				<td align="right">类别名称：</td>
				<td><input id="projectName2" type="text" class="inputText"  name="project.projectName"  />
				<td align="right">项目描述：</td>
				<td><input id="projectDesc" type="text" class="inputText"   name="project.projectDesc"/></td>
			</tr>
			<tr>
				<td align="right">所属父级项目：</td>
				<td><select class="selectstyle" id="parentProModi"  name="parentId"></select></td>
			<td align="right">添加时间：</td>
				<td><input id="createTime" type="text" class="inputText"  name="project.createTime"/></td>
			</tr>
			<tr>
				<td align="right">添加人：</td>
				<td><input id="creater" type="text" class="inputText" name="project.creater"/></td>
			</tr>
			<tr>
			<tr>
				<td colspan="4" align="center"><input type="button" value="保存" id="modi_button" class="inputbutton"/> 
				<input type="button" value="取 消" onclick="location.href='/project/project_list.action';" class="inputbutton"/></td>
			</tr>
		</table>
		</s:form>
	</div>
<!--鼠标悬浮式tab列表背景色变为蓝色--------------->
<script type="text/javascript">
 var obj=document.getElementById("tab1");
 for(var i=1;i<obj.rows.length;i++){  
   obj.rows[i].onmouseover=function(){this.style.background="#eaf2ff";}
   obj.rows[i].onmouseout=function(){this.style.background="";}
 }
</script>
</body>
</html>
