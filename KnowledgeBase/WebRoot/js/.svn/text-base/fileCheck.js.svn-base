//加载页面时显示所有已审核问题
jQuery(function($) {

	showQues();

	// 初始化已选择项目类别
	projectId = -1;
});

// 加载数据
function showQues() {

	filedatagrid = $('#filetab1').datagrid({
		url : '/files/filesManage_getAllFiles.action',
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
		idField : 'fileId',
		// 按列排序
		sortName : 'fileName',
		// 升降序
		sortOrder : 'desc',

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'fileId',
			title : '文件Id',
			hidden : true

		}, {
			field : 'fileName',
			title : '文件名称',
			sortable : true,
			width:100

		}, {
			field : 'fileDesc',
			title : '文件描述',
			hidden : true,
			width:100

		}, {
			field : 'createUser',
			title : '创建人',
			width:100
		}, {
			field : 'createUserId',
			title : '创建人ID',
			hidden : true,
			width:100
		}, {
			field : 'createDate',
			title : '上传时间',
			width:100
		}

		, {
			field : 'checkStatus',
			title : '是否已审核',
			width:100
		}, {
			field : 'checkDate',
			title : '审核时间',
			width:100
		}, {
			field : 'isAppear',
			title : '下载权限',
			width:100
		},

		{
			field : 'loadTimes',
			title : '下载次数',
			width:100
		}, {
			field : 'browseTimes',
			title : '浏览次数',
			width:100
		}

		] ]
	});

}

// 查看已审核
function getAllChecked() {
	$('#filetab1').datagrid('load', {
		'type' : 'checked'
	});
	$('#filetab1').datagrid('unselectAll');
}

// 查看未审核
function getAllUncheck() {
	$('#filetab1').datagrid('load', {
		'type' : 'uncheck'
	});
	$('#filetab1').datagrid('unselectAll');
}

// 搜索按钮操作
function file_search() {

	var keys = $("#select_file_key").val();
	if (keys == "") {
		$.messager.alert('提示', '请填写关键词！', 'info');
		return false;
	}

	// 加载数据
	$("#filetab1").datagrid('load', {
		'keywords' : keys
	});

}

// 文件审核
function checkFile() {
	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].fileId);
				checkedNames.push(rows[i].fileName);

			} else {
				uncheck.push(rows[i].fileId);
				uncheckNames.push(rows[i].fileName);
			}

		}

		// 批量--全部是未审核
		if (checked.length == 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您确定要审核通过【' + uncheckNames.join(",")
					+ '】文件吗？', function(b) {

				$.post('/files/filesManage_checkFile.action', {
					'fileIds' : uncheck.join(",")
				}, function(returnData, status) {
					// 重新加载数据
					$("#filetab1").datagrid('load');
					$("#filetab1").datagrid('unselectAll');
					$.messager.show({
						msg : '审核成功',
						title : '提示'
					});
				});
			});

		} else if (checked.length >= 0 && uncheck.length == 0) {// 全部是已审核
			$.messager.alert('错误', '您选择的文件已是审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + checkedNames.join(",") + '】已是审核状态!';
			msg += '文件【' + uncheckNames.join(",") + '】可以进行审核操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {

				$.post('/files/filesManage_checkFile.action', {
					'fileIds' : uncheck.join(",")
				}, function(returnData, status) {
					// 重新加载数据
					$("#filetab1").datagrid('load');
					$("#filetab1").datagrid('unselectAll');
					$.messager.show({
						msg : '审核成功',
						title : '提示'
					});
				});
			});

		}
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var fileId = row.fileId;
			if (checkStatus == '已审核') {
				$.messager.alert('错误', '此文件已经审核！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要审核通过此文件吗？', function(b) {

					$.post('/files/filesManage_checkFile.action', {
						'fileIds' : fileId
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
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

// 撤销审核
function undoCheck() {

	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].fileId);
				checkedNames.push(rows[i].fileName);

			} else {
				uncheck.push(rows[i].fileId);
				uncheckNames.push(rows[i].fileName);
			}

		}

		// 批量--全部是已审核
		if (uncheck.length == 0 && checked.length > 0) {

			$.messager.confirm('请确认', '您确定要撤销审核【' + checkedNames.join(",")
					+ '】文件吗？', function(b) {
				if (b) {
					$.post('/files/filesManage_undoCheckFile.action', {
						'fileIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length == 0 && uncheck.length >= 0) {// 全部是未审核
			$.messager.alert('错误', '您选择的文件已是未审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + uncheckNames.join(",") + '】已是未审核状态!';
			msg += '文件【' + checkedNames.join(",") + '】可以进行撤销审核操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/files/filesManage_undoCheckFile.action', {
						'fileIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
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
			msg : '请选择一个文件审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var fileId = row.fileId;
			if (checkStatus != '已审核') {
				$.messager.alert('错误', '此文件已是未审核状态！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要撤销审核此文件吗？', function(b) {

					if (b) {
						$.post('/files/filesManage_undoCheckFile.action', {
							'fileIds' : fileId
						}, function(returnData, status) {
							// 重新加载数据
							$("#filetab1").datagrid('load');
							$("#filetab1").datagrid('unselectAll');
							$.messager.show({
								msg : '撤销审核成功',
								title : '提示'
							});
						});
					}
				});
			}
		}

	}

}

// 文件下载权限
function grantLoad() {

	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].isAppear != '没有权限下载') {
				checked.push(rows[i].fileId);
				checkedNames.push(rows[i].fileName);

			} else {
				uncheck.push(rows[i].fileId);
				uncheckNames.push(rows[i].fileName);
			}

		}

		// 批量--全部是不可下载
		if (checked.length == 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您确定要开放下载【' + uncheckNames.join(",")
					+ '】文件吗？', function(b) {
				if (b) {
					$.post('/files/filesManage_grantFileLoad.action', {
						'fileIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length >= 0 && uncheck.length == 0) {// 全部是可下载
			$.messager.alert('错误', '您选择的文件已是可下载状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + checkedNames.join(",") + '】已是可下载状态!';
			msg += '文件【' + uncheckNames.join(",") + '】可以进行开放下载操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/files/filesManage_grantFileLoad.action', {
						'fileIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '开放下载成功',
							title : '提示'
						});
					});
				}
			});

		}
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');
		if (rows.length == 1) {
			var isAppear = row.isAppear;
			var fileId = row.fileId;
			var fileName = row.fileName;
			if (isAppear != '没有权限下载') {
				$.messager.alert('错误', '此文件已是开放下载状态！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要公开此文件下载权限吗？', function(b) {

					$.post('/files/filesManage_grantFileLoad.action', {
						'fileIds' : fileId
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '文件【' + fileName + '】下载权限已公开',
							title : '提示'
						});
					});
				});
			}
		}

	}
}

// 撤销下载

function undoFileLoad() {
	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].isAppear != '没有权限下载') {
				checked.push(rows[i].fileId);
				checkedNames.push(rows[i].fileName);

			} else {
				uncheck.push(rows[i].fileId);
				uncheckNames.push(rows[i].fileName);
			}

		}

		// 批量--全部是可下载
		if (checked.length > 0 && uncheck.length == 0) {

			$.messager.confirm('请确认', '您确定要撤销下载【' + checkedNames.join(",")
					+ '】文件吗？', function(b) {
				if (b) {
					$.post('/files/filesManage_undoFileLoad.action', {
						'fileIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length == 0 && uncheck.length >= 0) {// 全部是不可下载
			$.messager.alert('错误', '您选择的文件已是不可下载状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + uncheckNames.join(",") + '】已是不可下载状态!';
			msg += '文件【' + checkedNames.join(",") + '】可以进行撤销下载操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/files/filesManage_undoFileLoad.action', {
						'fileIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#filetab1").datagrid('load');
						$("#filetab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销下载成功',
							title : '提示'
						});
					});
				}
			});

		}
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');
		if (rows.length == 1) {
			var isAppear = row.isAppear;
			var fileId = row.fileId;
			var fileName = row.fileName;
			if (isAppear == '没有权限下载') {
				$.messager.alert('错误', '此文件已是撤销下载状态！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要撤销此文件下载权限吗？', function(b) {
					if (b) {
						$.post('/files/filesManage_undoFileLoad.action', {
							'fileIds' : fileId
						}, function(returnData, status) {
							// 重新加载数据
							$("#filetab1").datagrid('load');
							$("#filetab1").datagrid('unselectAll');
							$.messager.show({
								msg : '文件【' + fileName + '】下载权限已撤销',
								title : '提示'
							});
						});
					}
				});
			}
		}

	}

}