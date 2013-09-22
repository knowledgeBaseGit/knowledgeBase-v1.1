//加载页面时显示所有已审核问题
jQuery(function($) {

	showUsers();

});

// 加载数据
function showUsers() {

	filedatagrid = $('#usertab1').datagrid({
		url : '/users/userManage_getAllUsers.action',
		// 显示图标
		iconCls : 'icon-search',
		// 标题
		title : '用戶管理',
		// 显示分页
		pagination : true,
		// 每页显示的rows
		pageSize : 10,
		// 动态改变显示的行数
		pageList : [ 10, 20, 30, 40, 50, 60, 70 ],
		// 自动适应大小
		fit : true,
		// 列自动适应大小
		fitColumns : true,
		// 只在一行显示数据
		nowarp : true,

		// 边框设置
		border : false,
		// 跨页选择列
		idField : 'userId',
		// 按列排序
		sortName : 'loginName',
		// 升降序
		sortOrder : 'desc',

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'userId',
			title : '员工工号',
			sortable : true,
			width : 100

		}, {
			field : 'loginName',
			title : '登录名',
			sortable : true,
			width : 100

		}, {
			field : 'loginPassword',
			title : '登陆密码',
			width : 100
		}, {
			field : 'createDate',
			title : '创建日期',
			width : 100
		}, {
			field : 'staffName',
			title : '员工姓名',
			width : 100
		}

		, {
			field : 'department',
			title : '所属部门',
			width : 100
		}, {
			field : 'tel',
			title : '联系方式',
			width : 100
		}, {
			field : 'entryDate',
			title : '入职日期',
			width : 100
		}, {
			field : 'roleName',
			title : '用户角色',
			width : 100
		} ] ]
	});

}
// 搜索按钮操作
function user_search() {

	var keys = $("#select_user_key").val();
	if (keys == "") {
		$.messager.alert('提示', "请输入关键词，再搜索！", 'info');
		return;
	}

	// 加载数据
	$("#usertab1").datagrid('load', {
		'searchKey' : keys
	});

}

// 增加按钮操作
function adduser() {
	var staffId = $("#usertab2 input[name='staffId']").val();
	var loginName = $("#usertab2 input[name='loginName']").val();
	var loginPassword = $("#usertab2 input[name='loginPassword']").val();
	var rePassword = $("#usertab2 input[name='rePassword']").val();
	var staffName = $("#usertab2 input[name='staffName']").val();
	var staffTel = $("#usertab2 input[name='staffTel']").val();
	var entryDate = $("#usertab2 input[name='entryDate']").val();
	var department = $("#usertab2 input[name='department']").val();

	if (staffId == null || staffId == "") {
		$.messager.alert('提示', '请填写员工工号，工号是必填项!', 'error');
		$("#usertab2 input[name='staffId']").focus();
		return;
	}
	if (loginName == null || loginName == "") {
		$.messager.alert('提示', '请填写登陆名，登录名是必填项!', 'error');
		$("#usertab2 input[name='loginName']").focus();
		return;
	}
	if (loginPassword == null || loginPassword == "") {
		$.messager.alert('提示', '请填写登陆密码，登陆密码是必填项!', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}
	if (loginPassword != rePassword) {
		$.messager.alert('提示', '两次写入的密码有误！', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}
	if (staffName == null || staffName == "") {
		$.messager.alert('提示', '请填写员工姓名！', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}

	$.post('/users/userManage_addUser.action', {
		'staff.staffId' : staffId,
		'user.userId' : staffId,
		'user.loginName' : loginName,
		'user.loginPassword' : loginPassword,
		'staff.staffName' : staffName,
		'staff.staffTel' : staffTel,
		'staff.entryDate' : entryDate,
		'staff.department' : department
	}, function(r) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			$('#usertab1').datagrid('load');
			$.messager.show({
				msg : json.msg,
				title : '成功'
			});
		} else {
			msgBox_close();
			$.messager.alert('错误', json.msg, 'error');
		}

	});

}

// 查看关键词按钮操作
function seekey() {
	var rows = $('#keytab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var keyNames = [];
		for ( var i = 0; i < rows.length; i++) {
			keyNames.push(rows[i].keywordName);
		}
		$.messager.show({
			msg : '只能选择一个关键词查看！您已经选择了【' + keyNames.join(',') + '】'
					+ rows.length + '个关键词',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个关键词查看！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#keytab1").datagrid('getSelected');
		console.info(row);
		var keywordId = row.keywordId;
		var keywordName = row.keywordName;
		var createDate = row.createDate;
		var keywordDesc = row.keywordDesc;
		var searchTimes = row.searchTimes;

		var createUser = row.createUser;

		var checkStatus = row.checkStatus;
		var checkDate = row.checkDate;
		var checkUser = "无";
		if (row.users != undefined || row.users != null) {
			checkUser = row.users.loginName;
		}
		$("#seekey div[name='keyId']").text(keywordId);
		$("#seekey div[name='keyName']").text(keywordName);
		$("#seekey div[name='keyDesc']").text(keywordDesc);
		$("#seekey div[name='createDate']").text(createDate);
		$("#seekey div[name='createUser']").text(createUser);
		$("#seekey div[name='searchTimes']").text(searchTimes);
		$("#seekey div[name='checkStatus']").text(checkStatus);
		$("#seekey div[name='checkDate']").text(checkDate);
		$("#seekey div[name='checkUser']").text(checkUser);

		msgBox('div3', '查看');
	}

}

// 更新关键词按钮操作

function beforeupdate() {
	var rows = $('#usertab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var userNames = [];
		for ( var i = 0; i < rows.length; i++) {
			userNames.push(rows[i].logingName);
		}
		$.messager.show({
			msg : '只能选择一个用户更新！您已经选择了【' + userNames.join(',') + '】'
					+ rows.length + '个用户',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个用户更新！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#usertab1").datagrid('getSelected');

		var userId = row.userId;
		var loginName = row.loginName;
		var createDate = row.createDate;
		var loginPassword = row.loginPassword;
		var staffTel = row.tel;
		var entryDate = row.entryDate;
		var staffName = row.staffName;
		var department = row.department;

		$("#usertab3 input[name='staffId']").val(userId);
		$("#usertab3 input[name='loginName']").val(loginName);
		$("#usertab3 input[name='loginPassword']").val(loginPassword);
		$("#usertab3 input[name='rePassword']").val(loginPassword);
		$("#usertab3 input[name='staffName']").val(staffName);
		$("#usertab3 input[name='staffTel']").val(staffTel);
		$("#usertab3 input[name='entryDate']").val(entryDate);
		$("#usertab3 input[name='department']").val(department);
		msgBox('div3', '修改');
	}

}

// 更新关键词

function updateuser() {

	var row = $("#usertab1").datagrid('getSelected');

	var staffId = $("#usertab3 input[name='staffId']").val();
	var loginName = $("#usertab3 input[name='loginName']").val();
	var loginPassword = $("#usertab3 input[name='loginPassword']").val();
	var rePassword = $("#usertab3 input[name='rePassword']").val();
	var staffName = $("#usertab3 input[name='staffName']").val();
	var staffTel = $("#usertab3 input[name='staffTel']").val();
	var entryDate = $("#usertab3 input[name='entryDate']").val();
	var department = $("#usertab3 input[name='department']").val();

	if (loginName == null || loginName == "") {
		$.messager.alert('提示', '请填写登陆名，登录名是必填项!', 'error');
		$("#usertab2 input[name='loginName']").focus();
		return;
	}
	if (loginPassword == null || loginPassword == "") {
		$.messager.alert('提示', '请填写登陆密码，登陆密码是必填项!', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}
	if (loginPassword != rePassword) {
		$.messager.alert('提示', '两次写入的密码有误！', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}
	if (staffName == null || staffName == "") {
		$.messager.alert('提示', '请填写员工姓名！', 'error');
		$("#usertab2 input[name='loginPassword']").focus();
		return;
	}

	$.post('/users/userManage_updateUser.action', {
		'staff.staffId' : staffId,
		'user.loginName' : loginName,
		'user.loginPassword' : loginPassword,
		'staff.staffName' : staffName,
		'staff.staffTel' : staffTel,
		'staff.entryDate' : entryDate,
		'staff.department' : department

	}, function(r, status) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			$('#usertab1').datagrid('load');
			$.messager.show({
				msg : json.msg,
				title : '成功'
			});
		} else {
			msgBox_close();
			$.messager.alert('错误', json.msg, 'error');
		}

	});
}
// 删除文件
function userdelete() {

	var rows = $("#usertab1").datagrid('getSelections');
	if (rows.length <= 0) {
		$.messager.show({
			msg : '请选择要删除的用户！',
			title : '提示'
		});
	} else {
		var ids = [];
		$.messager.confirm('请确认', '您确定要删除当前所选的用户？', function(b) {
			if (b) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].userId);
				}

				$.post('/users/userManage_deleteUsers.action', {
					'userIds' : ids.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#usertab1").datagrid('load');
					$("#usertab1").datagrid('unselectAll');
					$.messager.show({
						msg : '删除成功',
						title : '提示'
					});

				});
			}
		});
	}

}

//选择上角色的Id
var newRoleId;
// 给用户授权角色
function grantRole(type) {

	var rows = $('#usertab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var userNames = [];
		for ( var i = 0; i < rows.length; i++) {
			userNames.push(rows[i].logingName);
		}
		$.messager.show({
			msg : '只能选择一个用户授权！您已经选择了【' + userNames.join(',') + '】'
					+ rows.length + '个用户',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个用户授权！',
			title : '提示'
		});
	}

	if (rows.length == 1) {

		var row = $("#usertab1").datagrid('getSelected');

		var userId = row.userId;
		var roleName = row.roleName;
		var roleId = row.role.roleId;
		 

		if (type == 1) {
			$("#Permi input[name='staffId']").val(userId);
			$("#role").val(roleName);

			$('#role').combobox({
				url : '/users/userManage_getAllRoles.action',
				valueField : 'roleId',
				textField : 'roleName',
				editable : false,
				onSelect : function(data) {
					newRoleId = data.roleId;
					 
				}
			});
			msgBox('Permi', '角色授权');
		} else if (type == 2) {
			if (roleId != newRoleId) {
				$.post('/users/userManage_grantRole.action', {
					'roleId' : newRoleId,
					'user.userId' : userId
				}, function(r) {
					
					// 重新加载数据
					$("#usertab1").datagrid('load');
					$("#usertab1").datagrid('unselectAll');
					$.messager.show({
						msg : '角色授权成功',
						title : '提示'
					});
				});
			}
			msgBox_close();
		}

	}
}