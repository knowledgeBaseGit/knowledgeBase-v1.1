<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/File.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">

<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/TabColor.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>



<script type="text/javascript" src="../js/fileOperate.js"></script>
<script type="text/javascript" src="../js/fileAutho.js"></script>
<link rel="stylesheet" href="../kindediter/themes/default/default.css" />
<script charset="utf-8" src="../kindediter/kindeditor.js"></script>
<script charset="utf-8" src="../kindediter/lang/zh_CN.js"></script>

<script type="text/javascript">
	var editor;
	KindEditor.ready(function(k) {
		editor = k.create('#fileSource');
	});
</script>
</head>
<body>
	<div class="all">
		<div class="left">
			<div class="lefttop">&nbsp;</div>
			<div class="leftmiddle">
				<ul id="nav">
					<s:iterator value="#request.list" var="project">
						<li
							onclick="cascadeKey('<s:property value="#project.projectId"/> ');"><a
							href="#"><img src="../resource/images/Icontree.png"
								align="absmiddle" /> <s:property value="#project.projectName" />
						</a> <s:if test="#project.projects.size>0">

								<ul>
									<s:iterator value="#project.projects" var="subpro">
										<li
											onclick="cascadeKey('<s:property value="#subpro.projectId"/> ');"><a
											href="#"><img src="../resource/images/Icontree.png"
												align="absmiddle" /> <s:property
													value="#subpro.projectName" /> </a></li>
									</s:iterator>
								</ul>

							</s:if></li>

					</s:iterator>
				</ul>
			</div>
			<div class="leftbottom">&nbsp;</div>
		</div>
		<div class="right">
			<div class="righttop">
				<div class="righttop-left">
					关键词： <input type="text" id="select_file_key" class="inputText" />
					<input type="button" value="选择" class="inputbutton"
						onClick="msgBox('div1', '关键词选择');" /> <input type="button"
						onclick="file_search();" value="搜索" class="inputbutton" />
				</div>
				<div class="righttop-right">
					<ul>
						<li id="fileUpload"><a href="#" onClick="addFile();">上传</a></li>
						<li id="fileUpdate"><a id="file_update" href="#" onClick="seeFile();">修改</a></li>
						<li id="fileSee"><a id="a_see" href="#" onClick="seeFile();">查看</a></li>
						<li id="fileDele"><a href="#" onClick="filedelete();">删除</a></li>
						<li id="fileDown"><a id="a_download" href="#" onClick="fileDownload();">下载</a></li>
					</ul>
				</div>
			</div>
			<div class="rightmiddle">
				<table cellspacing="0" id="filetab1">

				</table>
			</div>
		</div>
	</div>
	<!--弹出层-->
	<div id="div1" style="display: none; width: 300px">
		<!-- 关键词弹出 -->
		<table class="filetab2" id="filetab2">

		</table>
	</div>

	<%-- 	<div id="div2" style="display: none;">
		<div id="tag">
			<div id="Tab0">
				<ul class="menu0" id="menu0">
					<li class="hover" onClick="setTab(0,0)">文件详细信息</li>
					<li onClick="setTab(0,1)">文件内容</li>
					<li onClick="setTab(0,2)">文件权限</li>
				</ul>
			</div>
			<div id="tagContent0">
				<ul class="block">
					<li>
						<table class="tab3">
							<tr>
								<td><textarea class="textareastyle" id="fileDesc"></textarea></td>
							</tr>
							<tr>
								<td align="right"><span>提问人：小A <input type="button"
										value="提交" /> <input type="button" value="审核" />
								</span></td>
							</tr>
						</table>
					</li>
				</ul>
				<ul>
					<li>选项卡二内容</li>
				</ul>
				<ul>
					<li><textarea class="textareastyle" id="fileOath"></textarea>
						</td></li>
				</ul>
			</div>
		</div>
	</div> --%>

	<!--弹出层-->

	<!-- 查看文件 -->
	<div id="see" class="pop_see">
		<table width="100%">
			<tr>
				<td align="right">所属项目：</td>
				<td><input type="text" name="projectName" readonly="readonly" /></td>
				<td align="right">文件名称：</td>
				<td><input type="text" name="fileName" readonly="readonly" /></td>
			</tr>
			<tr>
				<td align="right">文件内容：</td>
				<td colspan="3"><textarea id="fileSource" name="content"
						style="width: 600px; height: 320px;"> </textarea></td>


			</tr>
		</table>


		<table width="100%">
			<tr>

				<td colspan="2" align="center"><input type="button" value="关闭"
					class="inputbutton" onClick="msgBox_close();" /></td>
			</tr>
		</table>
	</div>


	<!-- 修改文件 -->
	<div id="update" class="pop_see">
		<table width="100%">
			<tr>
				<td align="right">所属项目：</td>
				<td><input type="text" name="projectName" readonly="readonly" /></td>
				<td align="right">文件名称：</td>
				<td><input type="text" name="fileName" readonly="readonly" /></td>
			</tr>
			<tr>
				<td align="right">文件内容：</td>
				<td colspan="3"><textarea id="updateSource" name="content"
						style="width: 600px; height: 320px;"> </textarea></td>


			</tr>
		</table>


		<table width="100%">
			<tr>

				<td colspan="2" align="center"><input type="button" value="关闭"
					class="inputbutton" onClick="msgBox_close();" /></td>
			</tr>
		</table>
	</div>
	<!--弹出层-->
	<div id="add" class="pop_add">
		<table width="100%" height="100%">
			<tr>
				<td align="right">项目类别：</td>
				<td><select style="width: 150px" id="add_project">

				</select></td>
				<td align="right">文件名称：</td>
				<td><input type="text" name="fileName" /></td>
			</tr>
			<tr>
				<td align="right">文件描述：</td>
				<td colspan="3"><textarea style="width: 400px;"
						id="tx_filedesc"></textarea></td>
			</tr>

			<tr>
				<td align="right">上传文件：</td>
				<td colspan="3"><input type="file" name="upFile" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					class="inputbutton" onclick="sure_addFile();" value="确定" /></td>
				<td colspan="2" align="center"><input type="button"
					class="inputbutton" value="取 消" onClick="msgBox_close();" /></td>
			</tr>
		</table>
	</div>
	<!--弹出层-->
	<div id="update" class="pop_update">
		<table>
			<tr>
				<td align="right">项目类别：</td>
				<td><select style="width: 150px">
						<option>一站式</option>
						<option>发改委</option>
				</select></td>
				<td align="right">文件名称：</td>
				<td><input type="text" /></td>
			</tr>
			<tr>
				<td align="right">文件内容：</td>
				<td colspan="3"><textarea style="width: 365px; height: 50px"></textarea></td>
			</tr>
			<tr>
				<td align="right">修改人：</td>
				<td colspan="3"><input type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="button"
					class="inputbutton" value="修改" /></td>
				<td colspan="2" align="center"><input type="button"
					class="inputbutton" value="取 消" onClick="msgBox_close();" /></td>
			</tr>
		</table>
	</div>
</body>
</html>