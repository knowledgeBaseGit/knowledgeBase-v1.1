<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>关键词管理</title>
<link href="../resource/css/KeyWord.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css"
	href="../jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">

<script type="text/javascript" src="../jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/TabColor.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>
<script type="text/javascript" src="../js/soluCheck.js"></script>
<script type="text/javascript" src="../js/soluCheckAutho.js"></script>
</head>
<body>
	<div class="all">

		<div class="right">
			<div class="righttop">
				<div class="righttop-left">
					<input type="text" id="select_solu_key" class="inputText" /> <input
						type="button" onclick="solu_search();" value="搜索"
						class="inputbutton" />
				</div>
				<div class="righttop-right">
					<ul>
						<li id="checkSolu"><a href="#" onclick="checkSolu();">审核</a></li>
						<li id="soluUndoCheck"><a href="#" onclick="undoCheck();">取消审核</a></li>
						<li id="annexDownLoad"><a href="#" onclick="checkannex();">附件开放</a></li>
						<li id="annexNoDowLoad"><a href="#" onclick="undoCheckannex();">撤销下载</a></li>
						<!-- reson 2013-9-12 -->
						<li id="soluDel"><a href="#" onclick="deleteSolu();">删除</a></li>
						<li id="soluUpdate"><a href="#" onclick="ModiSolu();">修改</a></li>
					</ul>
				</div>
			</div>
			<div class="rightmiddle">
				<!-- 显示关键词 -->
				<table cellspacing="0" id="solutab1">
					
				</table>
			</div>
		</div>
	</div>
	<!--弹出层---------------方案修改-------------修改-------------修改-----------修改--------->
	<form  id="saveSolutionForm" action="/solution/solution_save.action" >
	<div id="divmodi" style="display: none;height: 180px; width:400px">
		<table width="100%">
			<tr>
				<td align="right" >方案名称：</td>
				<td><input id="solutionName" type="text" class="inputText" style="width:300px;"  name="solution.solutionName"  /></td>
				
			</tr>
			<tr>
				<td align="right">方案内容：</td>
				<td><textarea  id="solutionContent"  style="width:300px;height:100px;"  name="solution.solutionContent"></textarea></td>
				<td><input id="solutionId" type="hidden" class="inputText"   name="solution.solutionId"/></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="submit"  value="保存"  class="inputbutton"/> 
				<input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton"/></td>
			</tr>
		</table>
	</div> 
	</form>
</body>
</html>
