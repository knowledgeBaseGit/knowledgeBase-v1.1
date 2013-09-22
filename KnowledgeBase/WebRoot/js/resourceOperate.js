//加载页面时显示所有已审核问题
jQuery(function($) {

	showUsers();
	// 全局数组，存放角色的初始功能Id
	roleFuns1 = [];

});

// 加载数据
function showUsers() {

	filedatagrid = $('#resourcetab1').treegrid({
		url : '/module/moduleManage_initTree.action',
		// 显示图标
		iconCls : 'icon-search',
		// 标题
		title : '資源管理',
		 
		 
		// 自动适应大小
		fit : true,
		// 列自动适应大小
		fitColumns : true,
		// 只在一行显示数据
		nowarp : true,

		// 边框设置
		border : false,
		// 跨页选择列
		idField : 'id',
		 
		treeField:'text',
		 

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'id',
			title : '角色Id',
			sortable : true,
			hidden:true,
			width : 100

		}, {
			field : 'text',
			title : '资源名称',
			sortable : true,
			width : 100

		} , {
			field : 'url',
			title : '资源路径',
			sortable : true,
			width : 200

		}   ] ]
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

// 增加角色操作
function addrole() {
	var roleId = $("#usertab2 input[name='roleId']").val();
	var roleName = $("#usertab2 input[name='roleName']").val();
	var remark = $("#remark").val();
	 
	if (roleId == null || roleId == "") {
		$.messager.alert('提示', '请填写角色编号，角色编号是必填项!', 'error');
		$("#usertab2 input[name='roleId']").focus();
		return;
	}
	if (roleName == null || roleName == "") {
		$.messager.alert('提示', '请填写角色名称，角色名称是必填项!', 'error');
		$("#usertab2 input[name='roleName']").focus();
		return;
	}
	 
	$.post('/roles/roleManage_addRole.action', {
		'role.roleId' : roleId,
		'role.roleName' : roleName,
		'role.remark' : remark
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

 
 
// 更新角色按钮操作

function beforeupdate() {
	var rows = $('#usertab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var roleNames = [];
		for ( var i = 0; i < rows.length; i++) {
			roleNames.push(rows[i].logingName);
		}
		$.messager.show({
			msg : '只能选择一个角色操作！您已经选择了【' + roleNames.join(',') + '】'
					+ rows.length + '个用户',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个角色更新！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#usertab1").datagrid('getSelected');

		var roleId = row.roleId;
		var roleName = row.roleName;
		var remark = row.remark;
		 

		$("#usertab3 input[name='roleId']").val(roleId);
		$("#usertab3 input[name='roleName']").val(roleName);
		$("#usertab3 textarea[name='remark']").val(remark);
		msgBox('div3', '修改');
	}

}

// 更新关键词

function updateuser() {

	var row = $("#usertab1").datagrid('getSelected');
  
	var roleId = $("#usertab3 input[name='roleId']").val();
	var roleName = $("#usertab3 input[name='roleName']").val();
	var remark = $("#usertab3 textarea[name='remark']").val();

	if (roleId == null || roleId == "") {
		$.messager.alert('提示', '请填写角色编号，角色编号是必填项!', 'error');
		$("#usertab2 input[name='roleId']").focus();
		return;
	}
	if (roleName == null || roleName == "") {
		$.messager.alert('提示', '请填写角色名称，角色名称是必填项!', 'error');
		$("#usertab2 input[name='roleName']").focus();
		return;
	}

	$.post('/roles/roleManage_updateRole.action', {
		'role.roleId' : roleId,
		'role.roleName' : roleName,
		'role.remark' : remark
		

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
// 删除角色
function userdelete() {

	var rows = $("#usertab1").datagrid('getSelections');
	if (rows.length <= 0) {
		$.messager.show({
			msg : '请选择要删除的角色！',
			title : '提示'
		});
	} else {
		var ids = [];
		$.messager.confirm('请确认', '您确定要删除当前所选的角色嗎？', function(b) {
			if (b) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].roleId);
				}

				$.post('/roles/roleManage_deleteRole.action', {
					'roleIds' : ids.join(',')
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

// 角色授权
function grantPower(type) {

	var rows = $('#usertab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var roleNames = [];
		for ( var i = 0; i < rows.length; i++) {
			roleNames.push(rows[i].roleName);
		}
		$.messager.show({
			msg : '只能选择一个角色授权！您已经选择了【' + roleNames.join(',') + '】'
					+ rows.length + '个用户',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个角色授权！',
			title : '提示'
		});
	}

	// 修改后的角色功能Id
	roleFuns2 = [];
	if (rows.length == 1) {
		var row = $("#usertab1").datagrid('getSelected');
		var roleId = row.roleId;

		// 显示角色的原始功能权限
		if (type == 1) {
			$("#moduleTree").tree({
				url : '/module/moduleManage_initTree.action?roleId=' + roleId,
				checkbox : true,
				cascadeCheck : true,
				lines : true,
				onLoadSuccess : function(node, data) {
					var t = $(this);

					if (data) {
						$(data).each(function(index, d) {
							if (this.state == 'closed') {
								t.tree('expandAll');
							}
						});
					}

				}
			});
			msgBox('Permi', '角色授权');

		} else {
			var nodes = $("#moduleTree").tree('getChecked');
			for ( var i = 0; i < nodes.length; i++) {
				roleFuns2.push(nodes[i].id);
			}
			$.post('/module/moduleManage_updateFunOfRole.action', {
				'roleId' : roleId,
				'updateFuns' : roleFuns2.join(",")
			}, function(r) {
				
				var json = eval('(' + r + ')');

				if (json && json.success) {
					msgBox_close('Permi')
					 
					$.messager.show({
						msg : json.msg,
						title : '成功'
					});
				} else {
					msgBox_close('Permi')
					$.messager.alert('错误', json.msg, 'error');
				}
				
			});

		}
	}
}

// 角色授权更新
function updatePower() {
	var nodes = $("#moduleTree").tree('getChecked');
	alert(nodes.length);
}
