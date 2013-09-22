function loadimage() {
	document.getElementById("randimage").src = "../jsp/image.jsp?"
			+ Math.random();
}

$(document).ready(function() {

	$("#loginsub").click(function() {
		login();
	});

});

function login() {
	if (check()) {
		go();
	}
}

function check() {

	var userid = $("#userName").val();

	if ("" == userid) {
		$("#div1").text("用户名不能为空！");
		$("#userid").focus();
		return false;
	} else {
		$("#div1").text("");
	}

	var userpwd = $("#loginPassword").val();

	if (userpwd == "" || userpwd == null) {
		$("#div2").text("用户密码不能为空！");
		$("#userpwd").focus();
		return false;
	} else if (userpwd.length < 4 || userpwd.length > 10) {
		$("#div2").text("用户密码长度为4---10！");
		$("#userpwd").focus();
		return false;
	} else {
		$("#div2").text("");
	}

	var code = $("#checkcode").val();

	/**
	 * if(code!= ${sessionScope.rand}){ alert(${sessionScope.rand});
	 * $("#div3").text("验证码输入错误"); $("#checkcode").focus(); return false; }
	 */
	return true;
}

function go() {

	$.post("/users/user_login.action", {
		"user.loginName" : $("#userName").val(),
		"user.loginPassword" : $("#loginPassword").val()
	}, function(returndata, status) {
		if (returndata != "passwordError") {

			$("#biaotou").hide();
			alert("登陆成功");
		} else {
			$("#div2").text("密码错误，请重试！");
		}
	});

}

$(document).ready(function() {

	$("#userName").blur(function() {
		if ($("#userName").val() == null || $("#userName").val() == "") {
			$("#div1").text("用户名不能为空！");
		} else {

			$.post("/users/user_isExist.action", {
				"user.loginName" : $("#userName").val()
			}, function(returnData, status) {

				if (returnData == "yes") {
					$("#div1").text("正确！");
				}
				if (returnData == "no") {
					$("#div1").text("用户不存在！")
					// alert("用户不存在");
				}

			});
		}
	});

});
