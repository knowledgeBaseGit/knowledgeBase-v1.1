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
		url : '/ques/question_getQuesOfPro.action',
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
			hidden : true,
		

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
			width : 100
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
// 监听选择关键词
function selectKey() {

	var aa = "";

	$("#tab2 input:checked").each(function() {
		aa += $(this).val();

		aa += "|";
	});

	$("#selectKeys").val(aa);
	msgBox_close();

}
// 搜索按钮操作
function search() {

	var keys = $("#selectKeys").val();
	if (keys == "") {
		alert("请选择或手动填入关键词");
		return false;
	}

	// 加载数据
	datagrid.datagrid('load', {
		'project.projectId' : projectId,
		'keywords' : keys
	});

}
// 查看问题按钮操作
function seeQues() {
	var rows = $('#tab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var questionTitles = [];
		for ( var i = 0; i < rows.length; i++) {
			questionTitles.push(rows[i].questionTitle);
		}
		$.messager.show({
			msg : '只能选择一个问题查看！您已经选择了【' + questionTitles.join(',') + '】'
					+ rows.length + '个问题',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个问题查看！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = datagrid.datagrid('getSelected');

		// 问题详情
		var createUser = row.usersByWriteUser;
		var quesName = row.questionTitle;
		var quesDesc = row.questionDesc;
		var projectName = row.project;
		var writeDate = row.writeDate;
		var checkUser = row.usersByCheckUser;
		var checkStatus = row.checkStatus;
		var isSolve = row.isSolve;
		$("#seeQues input[name='questionTitle']").val(quesName);
		$("#seeQues input[name='projectName']").val(projectName);
		$("#seeQues input[name='createUser']").val(createUser);
		$("#seeQues input[name='writeDate']").val(writeDate);
		$("#seeQues input[name='questionDesc']").val(quesDesc);
		$("#seeQues input[name='checkUser']").val(checkUser);
		$("#seeQues input[name='checkStatus']").val(checkStatus);
		$("#seeQues input[name='solveStatus']").val(isSolve);

		// 问题对应的解决方案
		// 清空解决方案历史记录
		$("#solution").text("");

		// 清空附件历史记录
		$("#annex").text("");

		var solution = row.solutions;
		if (solution != undefined) {
			for ( var i = 0; i < solution.length; i++) {

				// 解决方案属性
				var soluWriteUser = solution[i].usersByWriteUser;
				var soluWriteDate = solution[i].writeDate;
				var soluContent = solution[i].solutionContent;
				var soluName = solution[i].solutionName;

				// 解决方案填充内容
				var solu_html = "<li><table class='poptab'>";
				solu_html += " <tr><td align='right'>方案名称：</td><td><input type='text' name='soluName' readonly='readonly' value='"
						+ soluName
						+ "' /></td><td align='right'>解答人：</td><td><input type='text' name='soluWriteUser' readonly='readonly' value='"
						+ soluWriteUser + "'/></td></tr>";
				// 附件填充内容
				var annex_html = "";
				var annex = solution[i].annex;
				// 解决方案中的附件
				if (annex != undefined) {
					var aneexName = annex.annexName
					var annexId = annex.annexId;
					var annexUrl = annex.annexUrl;
					var isAppear = annex.isAppear;
					 
					solu_html += "<tr><td align='right'>附件：</td> <td><input type='text' name='annex' readonly='readonly' value='"
							+ aneexName + "' /></td> ";

					annex_html += "<li><table><tr><td align='right'>附件名称：</td><td><input type='text' readonly='readonly' value='"
							+ aneexName + "' /></td>";
					/*annex_html+="<td><input type='button' value='查看' onclick='show("
							+ "'see'" + ");'/></td>";*/

					annex_html += "<td> <a href='/annexDownload/annexDownLoad_getAnnexSource.action?annexId="
							+ annexId
							+ "'> <input type='button' value='下载'></input></a></td></tr></table></li>";
				} else {
					solu_html += "<tr><td align='right'>附件：</td> <td><input type='text' name='annex' readonly='readonly' value='"
							+ "无" + "' /></td> ";
				}

				solu_html += "<td align='right'>回复时间：</td><td><input type='text' readonly='readonly' name='soluCreateDate' value='"
						+ soluWriteDate + "'/></td></tr>";
				solu_html += " <tr><td align='right'>方案内容：</td><td colspan='3'><textarea style='width:417px; height:70px;' name='soluContent' readonly='readonly'>"
						+ soluContent
						+ "</textarea></td></tr></table></li>";

				$("#solution").append(solu_html);
				$("#annex").append(annex_html);

			}

		}

		msgBox('seeQues', '查看');
	}

}

function addQues() {

	/* ----------------------------显示修改下拉列表，页面加载完毕，一次性添加，避免重复添加---------------------------- */
	$.ajax({
		type : "post",
		url : "/project/project_add.action",
		dataType : "json",
		fitColumn:"true",
		success : function(msg) {
			// 将取出的json字符串转换为对象
			var obj = eval(msg);
			// 修改的显示下拉列表

			$("#addQues_project").append("<option value='null'>请选择</option>");

			for ( var i = 0; i < obj.length; i++) {
				$("#addQues_project").append(
						"<option value='" + obj[i].projectId + "'>"
								+ obj[i].projectName + "</option>");
			}

		}
	});

	// 级联关键词下拉列表
	$("#addQues_project").change(function() {
		$("#addQues_keyword").text("<option value='null'>请选择</option>");
		addQues_pro_key($("#addQues_project").val());
	});

	msgBox('addQues', '我有问题');
}

// 提问操作 选择项目类别时 动态添加对应关键词
function addQues_pro_key(id) {

	$.post("/prokey/getPro.action", {
		"project.projectId" : id.trim()
	}, function(returnData, status) {

		var str = eval('(' + returnData + ')');

		for ( var i = 0; i < str.length; i++) {
			$("#addQues_keyword").append("<option value='null'>请选择</option>");
			for ( var i = 0; i < str.length; i++) {
				$("#addQues_keyword").append(
						"<option value='" + str[i].keywordId + "'>"
								+ str[i].keywordName + "</option>");
			}
		}

	});
}

// 确定添加
function sure_addQues() {
	var quesTitle = $("#addQues input[name='questionTitle']").val();
	var project = $("#addQues_project").val();
	var keyword = $("#addQues_keyword").val();
	var createUser = $("#addQues input[name='createUser']").val();
	var questionDesc = $("#addQues textarea[name='questionDesc']").val();

	if (quesTitle == null || quesTitle == "") {
		$.messager.alert('提示', '请填写问题标题，问题标题是必填项!', 'error');
		$("#addQues input[name='questionTitle']").focus();
		return;
	}

	if (project == null || project == "") {
		$.messager.alert('提示', '请选择项目类别，项目类别是必填项！', 'error');
		$("#addQues_project").focus();
		return;
	}

	
	$.post('/ques/question_addQues.action', {
		'project.projectId' : project,
		'ques.questionTitle' : quesTitle,
		'ques.questionDesc' : questionDesc,
		'keywordId' : keyword
	}, function(r) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			// $('#usertab1').datagrid('load');
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

// 问题回复
function replyQues() {

	var rows = $('#tab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var questionTitles = [];
		for ( var i = 0; i < rows.length; i++) {
			questionTitles.push(rows[i].questionTitle);
		}
		$.messager.show({
			msg : '只能选择一个问题解答！您已经选择了【' + questionTitles.join(',') + '】'
					+ rows.length + '个问题',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个问题进行解答！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = datagrid.datagrid('getSelected');

		// 问题详情
		var questionId = row.questionId;
		var quesName = row.questionTitle;
		$("#replyQues input[name='questionTitle']").val(quesName);
		$("#reply_Id").val(questionId);
		msgBox('replyQues', '回复');

	}

}

// 确定回复提交
function sure_replyQues() {

	var questionId = $("#reply_Id").val();
	var soluName = $("#replyQues input[name='soluName']").val();
	var soluContent = $("#replyQues textarea[name='soluContent']").val();
	var annexName = $("#replyQues input[name='annexName']").val();
	var uploadUrl = $("#replyQues input[name='upload']").val();
	// 添加成功后的方案ID

	if (soluName == "" || soluName == false || soluName == undefined) {
		$.messager.alert('错误', '请填写方案名称，该项是必填项！', 'error');
		return;
	}

	var isupload = $("#replyQues input[type='radio']:checked").val();
	if (isupload == 0) {

		if (annexName == "" || annexName == false || annexName == undefined) {
			$.messager.alert('错误', '请填写附件名称，该项是必填项！', 'error');
			return;
		}

		if (uploadUrl == "" || uploadUrl == false || uploadUrl == undefined) {
			$.messager.alert('错误', '请选择上传附件路径，该项是必填项！', 'error');
			return;
		}
	}
	// 添加solu
	$.post('/solu/soluManage_addSolu.action', {
		'solu.solutionName' : soluName,
		'solu.solutionContent' : soluContent
	}, function(r) {

		var json = eval('(' + r + ')');

		if (json && json.success) {

			var soluId = json.obj;
			// 关联问题、解决方案操作

			$.post('/solu/soluManage_addQuesAndSolu.action', {
				'quesId' : questionId,
				'soluId' : soluId
			}, function(r) {
			});
			$.messager.alert('成功', json.msg

			, 'info');

			// 是否上传附件
			var isupload = $("#replyQues input[type='radio']:checked").val();
			if (isupload == 0) {

				if (annexName == "" || annexName == false
						|| annexName == undefined) {
					$.messager.alert('错误', '请填写附件名称，该项是必填项！', 'error');
					return;
				}

				if (uploadUrl == "" || uploadUrl == false
						|| uploadUrl == undefined) {
					$.messager.alert('错误', '请选择上传附件路径，该项是必填项！', 'error');
					return;
				}

				$.post('/load/annxUpload_annexUpload.action', {
					'fileUrl' : uploadUrl,
					'soluId' : soluId,
					'annexName' : annexName
				}, function(r) {
					var json = eval('(' + r + ')');

					if (json && json.success) {

						$.messager.alert('成功', json.msg, 'info');
					} else {

						$.messager.alert('错误', json.msg, 'error');
					}
				});
			}
			msgBox_close();
		} else {

			$.messager.alert('错误', json.msg, 'error');
			return;
		}

	});

	// 判断是否要上传附件

}

// 判断是否上传附件，以显示和隐藏附件上传表单

function chooseAnnex() {
	var select = $("#replyQues input[type='radio']:checked").val();
	if (select == 0) {
		$("#showUpAnnex").slideDown();
	}
	if (select == 1) {
		$("#showUpAnnex").hide();
	}
}

// 附件上传
function uploadAnnex() {
	var upload = $("#replyQues input[name='upload']").val();

	if (upload = "" || upload == undefined || upload == false) {
		$.messager.alert('错误', '请选择要上传的文件！', 'error');
		return;
	} else {
		$.post('/load/annxUpload_annexUpload.action', {
			'fileUrl' : upload
		}, function(r) {
			var json = eval('(' + r + ')');

			if (json && json.success) {

				// $('#usertab1').datagrid('load');
				$.messager.alert('成功', json.msg, 'info');
			} else {

				$.messager.alert('错误', json.msg, 'error');
			}
		});
	}
}

function deleteQues() {
	var rows = $('#tab1').datagrid('getSelections');
	if (rows.length <= 0) {
		$.messager.show({
			msg : '请选择要删除的问题！',
			title : '提示'
		});
	} else {
		var ids = [];
		$.messager.confirm('请确认', '您确定要删除当前所选的问题？', function(b) {
			if (b) {
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i].questionId);
				}

				$.post('/ques/question_deleteQues.action', {
					'quesIds' : ids.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#tab1").datagrid('load');
					$("#tab1").datagrid('unselectAll');
					$.messager.show({
						msg : '删除成功',
						title : '提示'
					});

				});
			}
		});
	}
}