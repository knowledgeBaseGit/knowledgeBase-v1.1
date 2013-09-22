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
<script type="text/javascript" src="../js/keywordCheck.js"></script>
<script type="text/javascript" src="../js/keywordCheckAutho.js"></script>
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
						<li id="cheKey"><a href="#" onclick="checkKeyword();">审核</a></li>
						<li id="keyUndoCheck"><a href="#" onclick="undoCheck();">取消审核</a></li>
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
	 
</body>
</html>
