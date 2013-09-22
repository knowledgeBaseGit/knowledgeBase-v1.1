//加载页面时显示所有已审核问题
jQuery(function($) {

	showKeys();

});

// 加载数据
function showKeys() {

	filedatagrid = $('#solutab1').datagrid({
		url : '/solu/soluManage_getAllSolus.action',
		// 显示图标
		iconCls : 'icon-search',
		// 标题
		title : '方案管理',
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
		idField : 'solutionId',
		// 按列排序
		sortName : 'solutionName',
		// 升降序
		sortOrder : 'desc',

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'solutionId',
			title : '方案Id',
			hidden : true

		}, {
			field : 'solutionName',
			title : '方案名称',
			sortable : true,
			width : 115

		}, {
			field : 'usersByWriteUser',
			title : '创建人',
			width : 115

		}, {
			field : 'writeDate',
			title : '回复时间',
			width : 115

		}, {
			field : 'checkStatus',
			title : '审核状态',
			width : 115

		}, {
			field : 'checkDate',
			title : '审核时间',
			width : 115
		}, {
			field : 'usersByCheckUser',
			title : '审核人',
			width : 115
		},

		{
			field : 'annexName',
			title : '附件名称',
			width : 115

		},

		{
			field : 'isAppear',
			title : '附件下载权限',
			width : 115

		} ] ]
	});

}
// 搜索按钮操作
function solu_search() {

	var keys = $("#select_solu_key").val();
	if (keys == "") {
		$.messager.alert('提示', "请输入关键词，再搜索！", 'info');
		return;
	}

	// 加载数据
	$("#solutab1").datagrid('load', {
		'searchKey' : keys
	});

}

//修改解决方案 张瑞祥 --2013-9-13
function ModiSolu(){
	var rows = $('#solutab1').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.show({
			msg : '选择一个解决方案进行修改',
			title : '提示'
		});
	}
	else if (rows.length > 1) {
		$.messager.show({
			msg : '只能选择一个解决方案进行修改',
			title : '提示'
		});
	}else{
		var row = $('#solutab1').datagrid('getSelected');
		
		$.post("/solution/solution_seeSolu.action", {"solutionId" : row.solutionId},function(data) {
				
				$('#solutionName').val(data.solutionName);
				$('#solutionContent').val(data.solutionContent);
				$('#solutionId').val(data.solutionId);
				
		});
		msgBox('divmodi', '方案修改');
	}
}
//删除解决方案  张瑞祥 -- 2013-9-12

// 删除解决方案 张瑞祥 -- 2013-9-12

function deleteSolu() {
	var rows = $('#solutab1').datagrid('getSelections');
	if (rows.length < 1) {
		$.messager.show({
			msg : '至少选择一个解决方案进行删除',
			title : '提示'
		});
	} else {
		var soluIds = [];
		$.messager.confirm("请确认", "确定要删除所选解决方案吗", function(b) {
			if (b) {
				for ( var i = 0; i < rows.length; i++) {
					soluIds.push(rows[i].solutionId);
				}
				$.post('/solution/solution_deleteSolu.action', {
					'solutionIds' : soluIds.join(",")
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
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
function checkSolu() {
	var rows = $('#solutab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].solutionId);
				checkedNames.push(rows[i].solutionName);

			} else {
				uncheck.push(rows[i].solutionId);
				uncheckNames.push(rows[i].solutionName);
			}

		}
		if (checkedNames.length == 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您选择的【' + uncheckNames.join(',')
					+ '】进行审核操作！ 是否继续操作？', function(b) {

				$.post('/solu/soluManage_checkSolu.action', {
					'soluIds' : uncheck.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '审核成功',
						title : '提示'
					});
				});
			});

		} else if (checkedNames.length != 0 && uncheck.length == 0) {
			$.messager.alert('错误', '您选择的方案【' + checkedNames.join(',')
					+ '】已是审核通过状态！', 'error');
			return;

		} else if (checkedNames.length > 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您选择的【' + checkedNames.join(',')
					+ '】已是审核状态！可对【' + uncheckNames.join(',')
					+ '】进行审核操作！ 是否继续操作？', function(b) {

				$.post('/solu/soluManage_checkSolu.action', {
					'soluIds' : uncheck.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '审核成功',
						title : '提示'
					});
				});
			});

		}

	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个解决方案审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#solutab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var soluId = row.solutionId;
			if (checkStatus == '已审核') {
				$.messager.alert('错误', '此方案已经审核！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要审核通过此方案吗？', function(b) {

					$.post('/solu/soluManage_checkSolu.action', {
						'soluIds' : soluId
					}, function(returnData, status) {
						// 重新加载数据
						$("#solutab1").datagrid('load');
						$("#solutab1").datagrid('unselectAll');
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

	var rows = $('#solutab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].solutionId);
				checkedNames.push(rows[i].solutionName);

			} else {
				uncheck.push(rows[i].solutionId);
				uncheckNames.push(rows[i].solutionName);
			}

		}
		if (uncheckNames.length == 0 && checkedNames.length > 0) {

			$.messager.confirm('请确认', '您选择的【' + checkedNames.join(',')
					+ '】进行撤销审核操作！ 是否继续操作？', function(b) {

				$.post('/solu/soluManage_undoCheckSolu.action', {
					'soluIds' : checked.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '撤销审核成功',
						title : '提示'
					});
				});
			});

		} else if (uncheck.length != 0 && checkedNames.length == 0) {
			$.messager.alert('错误', '您选择的方案【' + uncheckNames.join(',')
					+ '】已是撤销审核状态！', 'error');
			return;

		} else if (uncheck.length > 0 && checkedNames.length > 0) {

			$.messager.confirm('请确认', '您选择的【' + uncheckNames.join(',')
					+ '】已是未审核状态！可对【' + checkedNames.join(',')
					+ '】进行撤销审核操作！ 是否继续操作？', function(b) {

				$.post('/solu/soluManage_undoCheckSolu.action', {
					'soluIds' : checked.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '撤销审核成功',
						title : '提示'
					});
				});
			});

		}

	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个解决方案审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#solutab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var soluId = row.solutionId;
			if (checkStatus == '未审核') {
				$.messager.alert('错误', '此方案已是未审核状态！', 'error');
				return;
			} else {
				$.messager.confirm('请确认', '您确定要撤销审核此方案吗？', function(b) {

					$.post('/solu/soluManage_undoCheckSolu.action', {
						'soluIds' : soluId
					}, function(returnData, status) {
						// 重新加载数据
						$("#solutab1").datagrid('load');
						$("#solutab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				});
			}
		}

	}

}

// 附件权限
function checkannex() {

	var rows = $('#solutab1').datagrid('getSelections');

	if (rows.length > 1) {
		// 已审核的附件名称
		var checkedNames = [];
		// 未审核的附件名称
		var uncheckNames = [];
		// 无附件的方案名称
		var noneNames = [];

		// 已审核的附件Id
		var checked = [];

		// 未审核的附件Id
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].isAppear != '!' && rows[i].isAppear == '可下载') {
				checked.push(rows[i].annexId);
				checkedNames.push(rows[i].annexName);

			} else if (rows[i].isAppear != '!' && rows[i].isAppear == '不可下载') {
				uncheck.push(rows[i].annexId);
				uncheckNames.push(rows[i].annexName);
			} else {
				noneNames.push(rows[i].solutionName);
			}

		}
		alert(uncheckNames.join(','));
		alert(checkedNames.join(','));
		if (uncheck.length == 0 && noneNames.length == 0) {
			$.messager.alert('错误', '附件已是可下载状态！', 'error');
			return;
		} else if (checkedNames.length >= 0 && uncheck.length > 0) {

			var msg = '您选择的';
			if (checkedNames.length > 0) {
				msg = '【' + checkedNames.join(',') + '】已是开放状态！';
			}

			if (noneNames.length > 0) {
				msg += '【' + noneNames.join(",") + '】方案中无附件！'
			}

			msg += '【' + uncheckNames.join(',') + '】可进行权限开放操作！确定要继续操作吗？';
			$.messager.confirm('请确认', msg, function(b) {

				$.post('/solu/soluManage_checkAnnex.action', {
					'annexIds' : uncheck.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '开放下载权限成功',
						title : '提示'
					});
				});
			});

		}

	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个解决方案审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#solutab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var soluId = row.solutionId;
			var annexId;
			var isAppear = row.isAppear;
			if (isAppear != '!' && isAppear == '可下载') {
				$.messager.alert('错误', '此附件已是开放下载状态！', 'error');
				return;
			} else if (isAppear == '!') {
				$.messager.alert('错误', '此方案无插件！', 'error');
				return;
			} else {
				annexId = row.annexId;
				$.messager.confirm('请确认', '您确定要开放此方案附件的下载权限吗？', function(b) {

					$.post('/solu/soluManage_checkAnnex.action', {
						'annexIds' : annexId
					}, function(returnData, status) {
						// 重新加载数据
						$("#solutab1").datagrid('load');
						$("#solutab1").datagrid('unselectAll');
						$.messager.show({
							msg : '附件下载权限设置成功',
							title : '提示'
						});
					});
				});
			}
		}

	}

}

// 撤销附件权限
function undoCheckannex() {

	var rows = $('#solutab1').datagrid('getSelections');

	if (rows.length > 1) {
		// 已审核的附件名称
		var checkedNames = [];
		// 未审核的附件名称
		var uncheckNames = [];
		// 无附件的方案名称
		var noneNames = [];

		// 已审核的附件Id
		var checked = [];

		// 未审核的附件Id
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].isAppear != '!' && rows[i].isAppear == '可下载') {
				checked.push(rows[i].annexId);
				checkedNames.push(rows[i].annexName);

			} else if (rows[i].isAppear != '!' && rows[i].isAppear == '不可下载') {
				uncheck.push(rows[i].annexId);
				uncheckNames.push(rows[i].annexName);
			} else {
				noneNames.push(rows[i].solutionName);
			}

		}

		if (checked.length == 0 && noneNames.length == 0) {
			$.messager.alert('错误', '附件已是不可下载状态！', 'error');
			return;
		} else if (uncheck.length >= 0 && checkedNames.length > 0) {

			var msg = '您选择的';
			if (uncheckNames.length > 0) {
				msg = '【' + uncheckNames.join(',') + '】已是不可下载状态！';
			}

			if (noneNames.length > 0) {
				msg += '【' + noneNames.join(",") + '】方案中无附件！'
			}

			msg += '【' + checkedNames.join(',') + '】可进行撤销下载操作！确定要继续操作吗？';
			$.messager.confirm('请确认', msg, function(b) {

				$.post('/solu/soluManage_undoCheckAnnex.action', {
					'annexIds' : checked.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#solutab1").datagrid('load');
					$("#solutab1").datagrid('unselectAll');
					$.messager.show({
						msg : '撤销下载权限设置成功',
						title : '提示'
					});
				});
			});
		}

	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个解决方案审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#solutab1").datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var soluId = row.solutionId;
			var annexId;
			var isAppear = row.isAppear;
			if (isAppear != '!' && isAppear == '不可下载') {
				$.messager.alert('错误', '此附件已是撤销下载状态！', 'error');
				return;
			} else if (isAppear == '!') {
				$.messager.alert('错误', '此方案无插件！', 'error');
				return;
			} else {
				annexId = row.annexId;
				$.messager.confirm('请确认', '您确定要撤销此方案附件的下载权限吗？', function(b) {

					$.post('/solu/soluManage_undoCheckAnnex.action', {
						'annexIds' : annexId
					}, function(returnData, status) {
						// 重新加载数据
						$("#solutab1").datagrid('load');
						$("#solutab1").datagrid('unselectAll');
						$.messager.show({
							msg : '附件撤销下载设置成功',
							title : '提示'
						});
					});
				});
			}
		}

	}

}