//加载页面时显示所有已审核问题
jQuery(function($) {

	showQues();

	// 初始化已选择项目类别
	projectId = -1;

});

// 监听项目类别选择
function cascadeKey(id) {

	projectId = id.trim();

	// 异步获取所有某个项目类别下的关键词
	$
			.post(
					"/prokey/getPro.action",
					{
						"project.projectId" : id.trim()
					},
					function(returnData, status) {

						var str = eval('(' + returnData + ')');
						/*
						 * var ss=str.keyName_0;
						 */

						var aaa = " <tr>";
						for ( var i = 0; i < str.length; i++) {
							aaa += "<td><input type='checkbox' value='"
									+ str[i].keywordName + "'/>";
							aaa += str[i].keywordName;
							aaa += "</td>";
						}
						aaa += "</tr><tr><td colspan='4'><input onClick='selectKey();' type='button' value='确 定' /> <input type='button' value='取 消' onClick='msgBox_close();' /></td></tr> ";

						$("#tab2").html("");
						$("#tab2").append(aaa);

					});

	// 按项目Id重新加载数据
	datagrid.datagrid('load', {
		'project.projectId' : id.trim()
	});
}
// 加载数据
function showQues() {

	datagrid = $('#tab1').datagrid({
		url : '/ques/question_getAllQues.action',
		// 显示图标
		iconCls : 'icon-search',
		// 标题
		title : '问题查看',
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
		nowarp : false,
		// 边框设置
		border : false,
		// 跨页选择列
		idField : 'questionId',
		// 按列排序
		sortName : 'questionTitle',
		// 升降序
		sortOrder : 'desc',

		checkOnSelect : true,
		selectOnCheck : true,
		singleSelect : false,
		columns : [ [ {
			field : 'ck',
			checkbox : true
		}, {
			field : 'questionId',
			title : '问题Id',
			hidden : true

		}, {
			field : 'solutions',
			title : '解决方案',
			hidden : true

		}, {
			field : 'questionDesc',
			title : '问题描述',
			hidden : true

		}, {
			field : 'questionTitle',
			title : '问题标题',
			sortable : true,
			width:100
		}, {
			field : 'usersByWriteUser',
			title : '提问人',
			width:100
		}, {
			field : 'writeDate',
			title : '提问时间',
			width:100
		}, {
			field : 'checkStatus',
			title : '审核状态',
			width:100
		}, {
			field : 'usersByCheckUser',
			title : '审核人',
			width:100
		}, {
			field : 'checkDate',
			title : '审核时间',
			width:100
		}, {
			field : 'isSolve',
			title : '是否解决',
			width:100
		}

		] ]
	});

}

// 搜索按钮操作
function search() {

	var keys = $("#selectKeys").val();
	if (keys == "") {
		$.messager.alert('提示', '请填写关键词！', 'info');
		return false;
	}

	// 加载数据
	datagrid.datagrid('load', {
		'keywords' : keys
	});

}

// 获取全部已审核问题
function getChecked() {
	datagrid.datagrid('load', {
		'type' : 'checked'
	});
}

// 获取全部未审核问题
function getUnCheck() {
	datagrid.datagrid('load', {
		'type' : 'uncheck'
	});
}
// 审核问题
function checkQues() {
	var rows = $('#tab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].questionId);
				checkedNames.push(rows[i].questionTitle);

			} else {
				uncheck.push(rows[i].questionId);
				uncheckNames.push(rows[i].questionTitle);
			}

		}

		// 批量--全部是未审核
		if (checked.length == 0 && uncheck.length > 0) {

			$.messager.confirm('请确认', '您确定要审核通过【' + uncheckNames.join(",")
					+ '】问题吗？', function(b) {
				if (b) {
					$.post('/ques/question_checkQues.action', {
						'quesIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#tab1").datagrid('load');
						$("#tab1").datagrid('unselectAll');
						$.messager.show({
							msg : '审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length >= 0 && uncheck.length == 0) {// 全部是已审核
			$.messager.alert('错误', '您选择的问题已是审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + checkedNames.join(",") + '】是已审核状态!';
			msg += '问题【' + uncheckNames.join(",") + '】可以进行审核通过操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/ques/question_checkQues.action', {
						'quesIds' : uncheck.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#tab1").datagrid('load');
						$("#tab1").datagrid('unselectAll');
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
			msg : '请选择一个问题审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = datagrid.datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var quesId = row.questionId;
			if (checkStatus == '已审核') {
				$.messager.alert('错误', '此问题已经审核！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要审核通过此问题吗？', function(b) {

					if (b) {
						$.post('/ques/question_checkQues.action', {
							'quesIds' : quesId
						}, function(returnData, status) {
							// 重新加载数据
							datagrid.datagrid('load');
							datagrid.datagrid('unselectAll');
							$.messager.show({
								msg : '审核成功',
								title : '提示'
							});
						});
					}
				});
			}
		}

	}

}
// 取消审核关键词
function undoCheckQues() {

	var rows = $('#tab1').datagrid('getSelections');

	if (rows.length > 1) {

		var checkedNames = [];
		var uncheckNames = [];
		var checked = [];
		var uncheck = [];

		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].checkStatus == '已审核') {
				checked.push(rows[i].questionId);
				checkedNames.push(rows[i].questionTitle);

			} else {
				uncheck.push(rows[i].questionId);
				uncheckNames.push(rows[i].questionTitle);
			}

		}

		// 批量--全部是已审核
		if (checked.length > 0 && uncheck.length == 0) {

			$.messager.confirm('请确认', '您确定要撤销审核【' + checkedNames.join(",")
					+ '】问题吗？', function(b) {
				if (b) {
					$.post('/ques/question_undoCheckQues.action', {
						'quesIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#tab1").datagrid('load');
						$("#tab1").datagrid('unselectAll');
						$.messager.show({
							msg : '撤销审核成功',
							title : '提示'
						});
					});
				}
			});

		} else if (checked.length ==0 && uncheck.length >= 0) {// 全部是未审核
			$.messager.alert('错误', '您选择的问题已是未审核状态！', 'error');
			return;
		} else if (checked.length > 0 && uncheck.length > 0) {// 既有已审核又有未审核
			var msg = '您选择的【' + uncheckNames.join(",") + '】已是未审核状态!';
			msg += '问题【' + checkedNames.join(",") + '】可以进行撤销审核操作，是否继续？';

			$.messager.confirm('请确认', msg, function(b) {
				if (b) {
					$.post('/ques/question_undoCheckQues.action', {
						'quesIds' : checked.join(",")
					}, function(returnData, status) {
						// 重新加载数据
						$("#tab1").datagrid('load');
						$("#tab1").datagrid('unselectAll');
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
			msg : '请选择一个文件审核！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = datagrid.datagrid('getSelected');
		if (rows.length == 1) {
			var checkStatus = row.checkStatus;
			var quesId = row.questionId;
			if (checkStatus != '已审核') {
				$.messager.alert('错误', '此问题已是未审核状态！', 'error');
			} else {
				$.messager.confirm('请确认', '您确定要撤销审核此问题吗？', function(b) {
                
				if(b){	 
					$.post('/ques/question_undoCheckQues.action', {
						'quesIds' : quesId
					}, function(returnData, status) {
						// 重新加载数据
						datagrid.datagrid('load');
						datagrid.datagrid('unselectAll');
						$.messager.show({
							msg : '取消审核成功',
							title : '提示'
						});
					});
				}
				});
			}
		}

	}
}