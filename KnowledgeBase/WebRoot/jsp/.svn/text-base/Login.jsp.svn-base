<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link rel="stylesheet" type="text/css"
	href="../resource/css/loginstyle.css" />
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>

<script type="text/javascript">
	//更换验证码图片的jsp
	function loadimage() {
		document.getElementById("randimage").src = "../jsp/image.jsp?"
				+ Math.random();
	}
	
	$(document).ready(function() {
		//登录名称是否存在的验证
		$('#loginName').blur(function(){
			var loginName = $('#loginName').val();
			$.ajax({
				type:"POST",
				url : "/register/register_checkLoginName.action",
				dataType : "json",
				data : {
					"loginName" : loginName,
				},
				success : function(data) {
					if(!data){
						alert("用户名不存在，请先注册.");
					}
				}
			});
		});
		//登陆名和密码正确性验证
		
			//当用户名或者密码正确时候，标记为true，在点击登录的时候使用。
		
		$('#loginPassword').blur(function() {
			var loginName = $('#loginName').val();
			var password = $('#loginPassword').val();
			$.ajax({
				type:"POST",
				url : "/register/register_check.action",
				dataType : "json",
				data : {
					"loginName" : loginName,
					"loginPassword" : password
				},
				success : function(data) {
					//用户存在的情况下密码错误，才提示.
					if(!data){
						alert("用户名或密码错误.");
					}
				}
			});
		});
		
		
		//登录验证。
		$('#loginSub').click(function() {
			$.ajax({
				type : "post",
				url : "/register/register_checkCode.action",
				data : {
					"checkCode" : $('#checkCode').val()
				},
				success : function(data) {
					if (!data) {
						alert("验证码不正确");
					}else{
						$.ajax({
							type:"POST",
							url : "/register/register_check.action",
							dataType : "json",
							data : {
								"loginName" :  $('#loginName').val(),
								"loginPassword" : $('#loginPassword').val()
							},
							success : function(data) {
								if(!data){
									alert("用户名或密码错误.");
								}else{
									$('#loginForm').submit();
								}
							}
						});
					}
				}
			});
			
			
		});
	});
</script>
</head>
<body>
	<div class="Lonall">
		<div class="Lontop"><span class="Lontop-left">知识库</span><span class="Lontop-right"><a href="../jsp/Register.jsp">没有账号？去注册</a></span></div>
		<div class="Loncenter">
			<form action="/register_login.action" method="post"
				id="loginForm">
				<table class="Lontab">
					<tr>
						<td align="right">用户名</td>
						<td colspan="2"><input type="text" name="user.loginName"
							id="loginName" class="Loninput" /></td>
					</tr>
					<tr>
						<td align="right">密&nbsp;&nbsp;码</td>
						<td colspan="2"><input type="password" class="Loninput"
							name="user.loginPassword" id="loginPassword" /></td>
					</tr>
					<tr>
						<td align="right">验证码</td>
						<td><input type="text" class="Loninputyan" name="checkCode"
							id="checkCode" /></td>
					<td><img src="../jsp/image.jsp"
							id="randimage" name="randimage"><a
							href="javascript:loadimage();"><font size="2s" style="color: black;">点我换一张</font></a>
					</tr>
					<tr>
						<td colspan="3" align="right"><input type="button"
							class="Loninput1" id="loginSub" value="登   录" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="Lonbottom">石家庄恒运网络科技有限公司</div>
	</div>
</body>
</html>