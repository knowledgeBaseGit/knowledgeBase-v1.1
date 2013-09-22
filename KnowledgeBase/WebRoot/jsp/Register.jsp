<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/Register.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function() {
		$('#staffId').blur(function() {
			var staffId = $('#staffId').val();
			$.ajax({
				type : "post",
				url : "/register/register_staff.action",
				dataType : "json",
				data : {
					"staffId" : staffId
				},
				success : function(data) {
					if (data == true) {
						$('#div1').text("员工ID不存在,请联系管理员");
						return;
						//alert("员工ID不存在,请联系管理员");
					} else if(data == false){
						$('#div1').text("员工ID已经注册，请联系管理员");
					}else{
					$('#div1').text("");	
					$('#staffName').val(data.staffName);
					$('#department').val(data.department);
					$('#staffTel').val(data.staffTel);
					$('#entryDate').val(data.entryDate);
					}
				}
			});
		});
		
		$('#register').click(function() {
			if($('#loginName').val()==null||$('#password').val()==null||$('#loginName').val()==""||$('#password').val()==""){
				alert("请填写完整注册信息！");
			}else{
				if($('#password').val() == $('#repassword').val()){
					$('#registerForm').submit();
				}else{
					alert("两次输入的密码不一致");
				}
			}
			
		});
	});
</script>
</head>

<body>
	<div class="all">
		<div class="top"><span>注册</span><span class="top-right"><a href="../jsp/Login.jsp">已有账号？去登录</a></span></div>
		<div class="center">
			<form action="/register/register_regist.action" method="post" id="registerForm">
				<table class="tab">
					<tr>
						<td align="right">员工工号</td>
						<td>
							<input type="text" class="input" id="staffId" name="user.userId" />
							<div id="div1"></div>
						</td>
						
					</tr>
					<tr>
						<td align="right">员工姓名</td>
						<td><input type="text" class="input" id="staffName" /></td>
					</tr>
					<tr>
						<td align="right">登录名</td>
						<td><input type="text" class="input" name="user.loginName" id="loginName"/></td>
					</tr>
					<tr>
						<td align="right">登录密码</td>
						<td><input type="password" class="input"
							name="user.loginPassword" id="password" /></td>
					</tr>

					<tr>
						<td align="right">重复登录密码</td>
						<td><input type="password" class="input"
							id="repassword"/></td>
					</tr>

					<tr>
						<td align="right">所属部门</td>
						<td><input type="text" class="input" id="department" /></td>
					</tr>
					<tr>
						<td align="right">联系电话</td>
						<td><input type="text" class="input" id="staffTel" /></td>
					</tr>
					<tr>
						<td align="right">入职日期</td>
						<td><input type="text" class="input" id="entryDate" /></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><input type="button"
							class="input1" value="注 册" id="register" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
