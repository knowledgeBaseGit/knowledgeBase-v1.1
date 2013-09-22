<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理</title>
<link href="../resource/css/Person.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css"
	href="../jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">

<script type="text/javascript" src="../jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../jquery/locale/easyui-lang-zh_CN.js"></script>


<script type="text/javascript" src="../js/TabColor.js"></script>
<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>
<script type="text/javascript" src="../js/userOperate.js"></script>
<script type="text/javascript" src="../js/userAutho.js"></script>
</head>
<body>
	<div class="all">

		<div class="right">
			<div class="righttop">
				<div class="righttop-left">
					人员姓名： <input type="text" class="inputText" id="select_user_key" />
					<input type="button" onclick="user_search();" value="搜索"
						class="inputbutton" />
				</div>
				<div class="righttop-right">
					<ul>
						<li id="userAdd"><a href="#" onClick="msgBox('div1', '新增');">新增</a></li>
						<li id="userUpdate"><a href="#" onClick="beforeupdate();">修改</a></li>
						<li id="userDele"><a href="#" onclick="userdelete();">删除</a></li>
						<li id="userAuth"><a href="#" onClick="grantRole(1);">角色授权</a></li>
					</ul>
				</div>
			</div>

			<!-- 数据显示 -->
			<div class="rightmiddle">
				<table cellspacing="0" id="usertab1">

				</table>
			</div>
		</div>
	</div>
	<!--弹出层-->
	<div id="div1" style="display: none; width: 600px; height: 200px;">
		<table class="tab2" id="usertab2">
			<tr>
			    <td align="right">员工工号:</td>
				<td><input type="text" name="staffId" /></td>
				<td align="right">登录名：</td>
				<td><input name="loginName" type="text" /></td>
			</tr>
			<tr>
				<td align="right">登录密码：</td>
				<td><input type="password" name="loginPassword" /></td>
				<td align="right">重复密码：</td>
				<td><input type="password" name="rePassword" /></td>
			</tr>
			<tr>
				<td align="right">员工姓名：</td>
				<td><input type="text" name="staffName" /></td>
				<td align="right">登录人电话：</td>
				<td><input type="text" name="staffTel" /></td>

			</tr>
			<tr>
				<td align="right">入职时间：</td>
				<td><input type="text" name="entryDate" class="easyui-datebox" editable="false"/></td>
				<td align="right">登录人所属部门：</td>
				<td><input type="text" name="department" /></td>

			</tr>
			<tr>
				<td colspan="2"><input type="button" onclick="adduser();" value="确 定" class="inputbutton" /> </td>
				<td colspan="2"><input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
		</table>
		
	</div>
	
	<!--修改用户 弹出层-->
	<div id="div3" style="display: none; width: 600px; height: 200px;">
		<table class="tab2" id="usertab3">
			<tr>
			    <td align="right">员工工号:</td>
				<td><input type="text" name="staffId" readonly="readonly" /></td>
				<td align="right">登录名：</td>
				<td><input name="loginName" type="text" /></td>
			</tr>
			<tr>
				<td align="right">登录密码：</td>
				<td><input type="password" name="loginPassword" /></td>
				 <td align="right">重复密码：</td>
				<td><input type="password" name="rePassword" /></td>
			</tr>
			<tr>
				<td align="right">员工姓名：</td>
				<td><input type="text" name="staffName" /></td>
				<td align="right">用户联系方式：</td>
				<td><input type="text" name="staffTel" /></td>

			</tr>
			<tr>
				<td align="right">入职时间：</td>
				<td><input type="text" name="entryDate" class="easyui-datebox" editable="false"/></td>
				<td align="right">用户所属部门：</td>
				<td><input type="text" name="department" /></td>

			</tr>
			<tr>
				<td colspan="2"><input type="button" onclick="updateuser();" value="确 定" class="inputbutton" /></td>
				 <td colspan="2"><input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
		</table>
	</div>
	
	
	
	<div id="div2" style="display: none; width: 600px; height: 200px;">
		<div id="tag">
			<div id="Tab0">
				<ul class="menu0" id="menu0">
					<li class="hover" onClick="setTab(0,0)">问题描述</li>
					<li onClick="setTab(0,1)">解决方案</li>
					<li onClick="setTab(0,2)">相关附件</li>
				</ul>
			</div>
			<div id="tagContent0">
				<ul class="block">
					<li>
						<table class="tab3">
							<tr>
								<td><textarea class="textareastyle"></textarea></td>
							</tr>
							<tr>
								<td align="right"><span>提问人：小A <input type="button"
										value="提交" class="inputbutton" /> <input type="button" value="审核" class="inputbutton" />
								</span></td>
							</tr>
						</table>
					</li>
				</ul>
				<ul>
					<li>选项卡二内容</li>
				</ul>
				<ul>
					<li>选项卡三内容</li>
				</ul>
			</div>
		</div>
	</div>
	<div id="Permi" style="display: none; width: 500px; height: 350px;">
          <table class="tab2" id="usertab3">
			<tr>
			    <td align="right">员工工号:</td>
				<td><input type="text" name="staffId" readonly="readonly" /></td>
				 <td align="right">角色授权：</td>
				<td><input id="role"  /></td>
			</tr>
		 
			 
			<tr>
				<td colspan="2"><input type="button" onclick="grantRole(2);" value="确 定" class="inputbutton" /></td>
				 <td colspan="2"><input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
		</table>
	</div>
</body>
</html>