//加载页面时显示所有已审核问题
jQuery(function($) {

	showKeys();

});

// 加载数据
function showKeys() {

	filedatagrid = $('#keytab1').datagrid({
		url : '/manageKey/keywordManage_getAllKeys.action',
		// 显示图标
		iconCls : 'icon-search',
		// 标题
		title : '文件管理',
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
		idField : 'keywordId',
		// 按列排序
		sortName : 'keywordName',
		// 升降序
		sortOrder : 'desc',

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'keywordId',
			title : '文件Id',
			hidden : true

		}, {
			field : 'keywordName',
			title : '关键词名称',
			sortable : true,
			width:100

		},

		{
			field : 'createUser',
			title : '创建人',
			width:100

		},

		{
			field : 'createDate',
			title : '创建时间',
			width:100

		},

		{
			field : 'searchTimes',
			title : '搜索次数',
			width:100

		}, {
			field : 'isDstroy',
			title : '是否销毁',
			width:100
		}, {
			field : 'destroyDate',
			title : '销毁时间',
			width:100
		}, {
			field : 'checkStatus',
			title : '审核状态',
			width:100

		}, {
			field : 'checkDate',
			title : '审核时间',
			width:100
		} ] ]
	});

}
// 搜索按钮操作
function keywrod_search() {

	var keys = $("#select_keyword_key").val();
	if (keys == "") {
		$.messager.alert('提示', "请输入关键词，再搜索！", 'info');
		return;
	}

	// 加载数据
	$("#keytab1").datagrid('load', {
		'searchKey' : keys
	});

}

// 增加按钮操作
function addKey() {
	var keyName = $("#keyadd input[name='keyName']").val();
	var keyDesc = $("#keyadd input[name='keyDesc']").val();
	var keycreateDate = $("#keyadd input[name='keycreateDate']").val();
	var keycreateUser = $("#keyadd input[name='keycreateUser']").val();

	if (keyName == null || keyName == "") {
		$.messager.alert('提示', '请填写关键词名称，关键词名称是必填项!', 'error');
		return;
	}
	if (keycreateDate == null || keycreateDate == "") {
		$.messager.alert('提示', '请填写创建时间，创建时间是必填项!', 'error');
		return;
	}

	$.post('/manageKey/keywordManage_addKeyword.action', {
		'keyword.keywordName' : keyName,
		'keyword.keywordDesc' : keyDesc,
		'keyword.createDate' : keycreateDate,
		'keyword.createUser' : keycreateUser
	}, function(r) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			$('#keytab1').datagrid('load');
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
	var rows = $('#keytab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var keyNames = [];
		for ( var i = 0; i < rows.length; i++) {
			keyNames.push(rows[i].keywordName);
		}
		$.messager.show({
			msg : '只能选择一个关键词更新！您已经选择了【' + keyNames.join(',') + '】'
					+ rows.length + '个关键词',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个关键词更新！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#keytab1").datagrid('getSelected');

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
		$("#updatekey input[name='keyId']").val(keywordId);
		$("#updatekey input[name='keyName']").val(keywordName);
		$("#updatekey input[name='keyDesc']").val(keywordDesc);
		$("#updatekey input[name='createDate']").val(createDate);
		$("#updatekey input[name='createUser']").val(createUser);
		$("#updatekey input[name='searchTimes']").val(searchTimes);
		$("#updatekey input[name='checkStatus']").val(checkStatus);
		$("#updatekey input[name='checkDate']").val(checkDate);
		$("#updatekey input[name='checkUser']").val(checkUser);

		msgBox('div2', '修改');
	}

}

// 更新关键词

function updatekey() {

	var row = $("#keytab1").datagrid('getSelected');

	var keywordId = row.keywordId;
	var keywordName = $("#updatekey input[name='keyName']").val();
	var keywordDesc = $("#updatekey input[name='keyDesc']").val();

	var createUser = $("#updatekey input[name='createUser']").val();

	$.post('/manageKey/keywordManage_updateKeyword.action', {
		'keyword.keywordId' : keywordId,
		'keyword.keywordName' : keywordName,
		'keyword.keywordDesc' : keywordDesc,
		'keyword.createUser' : createUser

	}, function(r, status) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			$('#keytab1').datagrid('load');
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
function keydelete() {

	var rows = $("#keytab1").datagrid('getSelections');
	if (rows.length <= 0) {
		$.messager.show({
			msg : '请选择要删除的文件！',
			title : '提示'
		});
	} else {
		var ids = [];
		$.messager.confirm('请确认', '您确定要删除当前所选的文件？', function(b) {
			if (b) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].keywordId);
				}

				$.post('/manageKey/keywordManage_deleteKeyword.action', {
					'keywordIds' : ids.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#keytab1").datagrid('load');
					$("#keytab1").datagrid('unselectAll');
					$.messager.show({
						msg : '删除成功',
						title : '提示'
					});

				});
			}
		});
	}

}

// 审核关键词
function checkKeyword() {
	var rows = $('#keytab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var keyNames = [];
		for ( var i = 0; i < rows.length; i++) {
			keyNames.push(rows[i].keywordName);
		}
		$.messager.show({
			msg : '只能选择一个关键词审核！您已经选择了【' + keyNames.join(',') + '】'
					+ rows.length + '个关键词',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个关键词审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#keytab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var keywordId = row.keywordId;
			if (checkStatus == '已审核') {
				$.messager.alert('错误', '此关键词已经审核！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要审核通过此关键词吗？', function(b) {

					$.post('/manageKey/keywordManage_checkKeyword.action', {
						'keyword.keywordId' : keywordId
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '审核成功',
							title : '提示'
						});
					});
				});
			}
		}

	}

}
