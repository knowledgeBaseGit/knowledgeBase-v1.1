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

// 审核关键词
function checkKeyword() {
	var rows = $('#keytab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].keywordId);
				checkedNames.push(rows[i].keywordName);

			} else {
				uncheck.push(rows[i].keywordId);
				uncheckNames.push(rows[i].keywordName);
			}

		}

		// 批量--全部是未审核
		if (checked.length == 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您确定要审核通过【' + uncheckNames.join(",")
					+ '】关键词吗？', function(b) {
				if (b) {
					$.post('/manageKey/keywordManage_checkKeyword.action', {
						'keywordIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length >= 0 && uncheck.length == 0) {// 全部是已审核
			$.messager.alert('错误', '您选择的关键词已是审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + checkedNames.join(",") + '】是已审核状态!';
			msg += '关键词【' + uncheckNames.join(",") + '】可以进行审核通过操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/manageKey/keywordManage_checkKeyword.action', {
						'keywordIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '审核成功',
							title : '提示'
						});
					});
				}
			});

		}
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
						'keywordIds' : keywordId
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

// 取消审核
function undoCheck() {
	var rows = $('#keytab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].keywordId);
				checkedNames.push(rows[i].keywordName);

			} else {
				uncheck.push(rows[i].keywordId);
				uncheckNames.push(rows[i].keywordName);
			}

		}

		// 批量--全部是已审核
		if (checked.length > 0 && uncheck.length == 0) {

			$.messager.confirm('请确认', '您确定要撤销审核【' + checkedNames.join(",")
					+ '】关键词吗？', function(b) {
				if (b) {
					$.post('/manageKey/keywordManage_undoCheckKey.action', {
						'keywordIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length == 0 && uncheck.length >= 0) {// 全部是未审核
			$.messager.alert('错误', '您选择的关键词已是未审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + uncheckNames.join(",") + '】已是未审核状态!';
			msg += '关键词【' + checkedNames.join(",") + '】可以进行撤销审核操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/manageKey/keywordManage_undoCheckKey.action', {
						'keywordIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				}
			});

		}
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个关键词审核操作！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#keytab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var keywordId = row.keywordId;
			if (checkStatus != '已审核') {
				$.messager.alert('错误', '此关键词已是未审核状态！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定撤销审核此关键词吗？', function(b) {

					$.post('/manageKey/keywordManage_undoCheckKey.action', {
						'keywordIds' : keywordId
					}, function(returnData, status) {
						// 重新加载数据
						$("#keytab1").datagrid('load');
						$("#keytab1").datagrid('unselectAll');
						$.messager.show({
							msg : '取消审核成功',
							title : '提示'
						});
					});
				});
			}
		}

	}

}