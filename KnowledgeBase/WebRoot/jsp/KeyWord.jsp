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
<script type="text/javascript" src="../js/keywordOperate.js"></script>
<script type="text/javascript" src="../js/keywordAutho.js"></script>
</head>
<body>
	<div class="all">

		<div class="right">
			<div class="righttop">
				<div class="righttop-left">
					关键词：<input type="text" id="select_keyword_key" class="inputText" /> <input
						type="button" onclick="keywrod_search();" value="搜索"
						class="inputbutton" />
				</div>
				<div class="righttop-right">
					<ul>
						<li id="keyadd"><a href="#" onClick="msgBox('div1', '新增');">新增</a></li>
						<li id="keyUpdate"><a href="#" onClick="beforeupdate();">修改</a></li>
						<li id="keySee"><a href="#" onClick="seekey();">查看</a></li>
						<li id="keyDel"><a href="#" onclick="keydelete();">删除</a></li>

					</ul>
				</div>
			</div>
			<div class="rightmiddle">
				<!-- 显示关键词 -->
				<table cellspacing="0" id="keytab1">

				</table>
			</div>
		</div>
	</div>
	<!-- 添加 弹出层-->
	<div id="div1" style="display: none; width: 650px;">
		<table class="keyadd" id="keyadd">
			<tr>
				<td align="right">关键词名称：</td>
				<td><input type="text" name="keyName" class="inputText" /></td>
				<td align="right">关键词描述：</td>
				<td><input type="text" name="keyDesc" class="inputText" /></td>
			</tr>
			<tr>
				<td align="right">添加时间：</td>
				<td><input type="text" name="keycreateDate"
					class="easyui-datebox" editable="false" /></td>
				<td align="right">添加人：</td>
				<td><input type="text" name="keycreateUser" class="inputText" /></td>
			</tr>

			<tr>
				<td colspan="2"><input type="button" onclick="addKey();" value="确 定" class="inputbutton" /> </td>
				<td colspan="2"><input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
		</table>
	</div>


	<!--修改按钮 弹出层-->
	<div id="div2" style="display: none; width: 700px;">
		<table id="updatekey">
			<tr>
				<td>关键词ID：</td>
				<td><input name="keyId" class="inputText" readonly="readonly" /></td>
				<td>关键词名称：</td>
				<td><input name="keyName" class="inputText" /></td>
				<td>添加时间：</td>
				<td><input name="createDate" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>关键词描述：</td>
				<td><input name="keyDesc" class="inputText" /></td>
				<td>创建人：</td>
				<td><input name="createUser" readonly="readonly" /></td>
				<td>搜索次数：</td>
				<td><input name="searchTimes" class="inputText"
					readonly="readonly" /></td>
			</tr>

			<tr>
				<td>审核状态：</td>
				<td><input name="checkStatus" class="inputText"
					readonly="readonly" /></td>
				<td>审核时间：</td>
				<td><input name="checkDate" class="easyui-datebox"
					editable="false" readonly="readonly" /></td>
				<td>审核人：</td>
				<td><input name="checkUser" class="inputText"
					readonly="readonly" /></td>
			</tr>

			<tr>
				<td colspan="3" align="center"><input type="button" onclick="updatekey();"	value="更新" class="inputbutton"/></td>
				<td colspan="3" align="center"><input type="button" value="关闭"	onClick="msgBox_close();" class="inputbutton"/></td>
			</tr>
		</table>
	</div>



	<!--查看按钮 弹出层-->
	<div id="div3" style="display: none; width: 700px;">
		<table id="seekey">
			<tr>
				<td>关键词ID：</td>
				<td><div name="keyId" class="inputText" ></div></td>
				<td>关键词名称：</td>
				<td><div name="keyName" class="inputText" ></div></td>
				<td>添加时间：</td>
				<td><div name="createDate" class="easyui-datebox"
						editable="false"></div></td>
			</tr>
			<tr>
				<td>关键词描述：</td>
				<td><div name="keyDesc" class="inputText" /></td>
				<td>创建人：</td>
				<td><div name="createUser" class="easyui-datebox" editable="false"></div></td>
				<td>搜索次数：</td>
				<td><div name="searchTimes" class="inputText"></div></td>
			</tr>

			<tr>
				<td>审核状态：</td>
				<td><div name="checkStatus" class="inputText"></div></td>
				<td>审核时间：</td>
				<td><div name="checkDate" class="easyui-datebox" editable="false"></div></td>
				<td>审核人：</td>
				<td><div name="checkUser" class="inputText"></div></td>
			</tr>

			<tr>
				<td colspan="6" align="center"><input type="button" value="关闭"
					onClick="msgBox_close();" class="inputbutton"/></td>
			</tr>
		</table>
	</div>
</body>
</html>
