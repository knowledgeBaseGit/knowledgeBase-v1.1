//加载页面时显示所有已审核问题
jQuery(function($) {

	showQues();

	KindEditor.ready(function(k) {
		editor = k.create('#fileSource');
		updateEditor = k.create('#updateSource');

	});
	// 初始化已选择项目类别
	projectId = -1;
});

// 监听项目类别选择
function cascadeKey(id) {

	projectId = id.trim();
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
						for (i = 0; i < str.length; i++) {
							aaa += "<td><input type='checkbox' value='"
									+ str[i] + "'/>";
							aaa += str[i];
							aaa += "</td>";
						}
						aaa += "</tr><tr><td colspan='4'><input onClick='selectKey();' type='button' value='确 定' /> <input type='button' value='取 消' onClick='msgBox_close();' /></td></tr> ";

						$("#filetab2").html("");
						$("#filetab2").append(aaa);

					});

	// 按项目Id重新加载数据
	$("#filetab1").datagrid('load', {
		'project.projectId' : id.trim()
	});
}
// 加载数据
function showQues() {

	filedatagrid = $('#filetab1')
			.datagrid(
					{
						url : '/files/filesManage_showFiles.action',
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
							sortable : true

						}, {
							field : 'fileDesc',
							title : '文件描述',
							hidden : true

						}, {
							field : 'createUser',
							title : '创建人'
						}, {
							field : 'createUserId',
							title : '创建人ID',
							hidden : true
						}, {
							field : 'createDate',
							title : '上传时间'
						}, {
							field : 'checkDate',
							title : '审核时间'
						}, {
							field : 'loadTimes',
							title : '下载次数'
						}, {
							field : 'browseTimes',
							title : '浏览次数'
						}, {
							field : 'isAppear',
							title : '下载权限'
						}, {
							field : 'isBrowse',
							title : '浏览权限'
						}, {
							field : 'fileType',
							title : '文件类型'
						}

						] ],

						onClickRow : function(index, rowdata) {

							// 监控下载按钮
							var isAppear = rowdata.isAppear;
							var fileId = rowdata.fileId;
							var fileName = rowdata.fileName;
							var fileType = rowdata.fileType;
							var isBrowse = rowdata.isBrowse;
							if (fileType == null || fileType == ""
									|| fileType == undefined) {
								fileType == 'doc';
							}
							var projectName;
							if (rowdata.project != undefined
									|| rowdata.project != null) {
								projectName = rowdata.project.projectName;
							}

							if (isAppear != '没有权限下载') {
								$("#a_download").attr(
										"href",
										"/fileDownload/fileDownLoad_getFileSource.action?fileId="
												+ fileId);
								$("#a_download").attr('onClick', "");
							}

							if (isBrowse == "可浏览") {
								// 监控查看按钮
								$("#a_see").attr(
										"href",
										"/files/onlineSeeFile_onlineSee.action?file.fileId="
												+ fileId + "&file.fileName="
												+ fileName + "&fileType="
												+ fileType);
								$("#a_see").attr('onClick', "");

								// 跟新按钮操作
								$("#file_update").attr(
										"href",
										"/files/onlineSeeFile_onlineSee.action?file.fileId="
												+ fileId + "&file.fileName="
												+ fileName + "&fileType="
												+ fileType);
								$("#file_update").attr('onClick', "");

							} else {
								$.messager.show({
									msg : '该文件不可浏览！',
									title : '提示'
								});
							}

						}
					});

}

// 增加文件
function addFile() {

	/* ----------------------------显示修改下拉列表，页面加载完毕，一次性添加，避免重复添加---------------------------- */
	$.ajax({
		type : "post",
		url : "/project/project_add.action",
		dataType : "json",
		success : function(msg) {
			// 将取出的json字符串转换为对象
			var obj = eval(msg);
			// 修改的显示下拉列表
			$("#add_project").append("<option value='null'>请选择</option>");
			for ( var i = 0; i < obj.length; i++) {
				$("#add_project").append(
						"<option value='" + obj[i].projectId + "'>"
								+ obj[i].projectName + "</option>");
			}

		}
	});

	msgBox('add', '新增');
}

// 添加文件提交
function sure_addFile() {

	var projectId = $("#add_project").val();
	var fileName = $("#add input[name='fileName']").val();
	var fileDesc = $("#tx_filedesc").val();
	var fileUrl = $("#add input[name='upFile']").val();

	if (projectId == null || projectId == undefined || projectId == false) {
		$.messager.alert('提示', '请选择项目类别,该项是必填项！', 'info');
		return;
	}
	if (fileName == null || fileName == undefined || fileName == false) {
		$.messager.alert('提示', '请填写文件名称,该项是必填项！', 'info');
		return;
	}
	if (fileUrl == null || fileUrl == undefined || fileUrl == false) {
		$.messager.alert('提示', '请选择要上传的文件！', 'info');
		return;
	}

	$.post('/files/filesManage_addFiles.action', {
		'file.fileName' : fileName,
		'file.fileDesc' : fileDesc,
		'projectId' : projectId,
		'fileUrl' : fileUrl
	}, function(r) {
		var json = eval('(' + r + ')');

		if (json && json.success) {
			msgBox_close();
			$('#filetab1').datagrid('load');
			$.messager.show({
				msg : json.msg,
				title : '提示'
			});

		} else {
			msgBox_close();
			$.messager.alert('错误', '文件上传失败！', 'error');
		}
	});
}

// 监听选择关键词
function selectKey() {

	var aa = "";

	$("#filetab2 input:checked").each(function() {
		aa += $(this).val();

		aa += "|";
	});

	$("#select_file_key").val(aa);
	msgBox_close();

}
// 搜索按钮操作
function file_search() {

	var keys = $("#select_file_key").val();
	if (keys == "") {
		$.messager.alert('提示', '请选择或填入关键词！', 'info');
		return false;
	}

	// 加载数据
	$("#filetab1").datagrid('load', {
		'project.projectId' : projectId,
		'keywords' : keys
	});

}

// 修改文件

function updateFile() {

	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var fileTitles = [];
		for ( var i = 0; i < rows.length; i++) {
			fileTitles.push(rows[i].fileName);
		}
		$.messager.show({
			msg : '只能选择一个文件修改！您已经选择了【' + fileTitles.join(',') + '】'
					+ rows.length + '个文件',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件查看！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');

		var fileId = row.fileId;
		var fileName = row.fileName;
		var fileDesc = row.fileDesc;
		var browseTimes = row.browseTimes;
		var loadTimes = row.loadTimes;
		var isAppear = row.isAppear;

		// 所属项目
		var projectName;
		if (row.project != undefined || row.project != null) {
			projectName = row.project.projectName;
		}
		var createUser;
		if (row.usersByCreateUser != undefined || row.usersByCreateUser != null) {
			createUser = row.usersByCreateUser.loginName;
		}
		var createDate = row.createDate;
		// 文件路径
		var fileversion = row.fileversions;
		var fileUrl;
		if (fileversion != undefined || fileversion != null) {
			fileUrl = fileversion[0].fileUrl;
		}
		alert(fileUrl);
		// 加载文件内容
		$.post('/files/filesManage_getFileSource.action', {
			'fileUrl' : fileUrl
		}, function(r) {

			var json = eval('(' + r + ')');

			if (json && json.success) {
				// msgBox_close();
				updateEditor.html(json.obj);
				$.messager.show({
					msg : json.msg,
					title : '提示'
				});

			} else {
				msgBox_close();
				$.messager.alert('错误', json.msg, 'error');
			}

		});

		/*
		 * $("#see input[name='projectName']").val(projectName); $("#see
		 * input[name='fileName']").val(fileName); $("#see
		 * input[name='fileDesc']").val(fileDesc); $("#see
		 * input[name='browseTimes']").val(browseTimes); $("#see
		 * input[name='loadTimes']").val(loadTimes); $("#see
		 * input[name='createUser']").val(createUser); $("#see
		 * input[name='createDate']").val(createDate);
		 * 
		 * $("#fileOath").text("文件权限:" + isAppear);
		 * $("#fileOath").append("---------- <input type='button'
		 * value='下载文件'/>");
		 */

		msgBox('update', '修改');
	}

}

// 查看文件按钮操作
function seeFile() {
	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var fileTitles = [];
		for ( var i = 0; i < rows.length; i++) {
			fileTitles.push(rows[i].fileName);
		}
		$.messager.show({
			msg : '只能选择一个文件查看！您已经选择了【' + fileTitles.join(',') + '】'
					+ rows.length + '个文件',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件查看！',
			title : '提示'
		});
	}

	if (rows.length == 1) {

		var row = $("#filetab1").datagrid('getSelected');

		var fileId = row.fileId;
		var fileName = row.fileName;
		var fileType = row.fileType;
		var isBrows = row.isBrows;
		if (isBrows == '不可浏览') {
			$.messager.alert('提示', '该文件不可下载！', 'info');
			return;
		}
		// 加载文件内容
		$.post('/files/onlineSeeFile_onlineSee.action', {
			'file.fileId' : fileId,
			'fileType' : fileType,
			'file.fileName' : fileName

		}, function(r) {

			/*
			 * //var json = eval('(' + r + ')');
			 * 
			 * if (json && json.success) { // msgBox_close(); //
			 * editor.html(json.obj); $.messager.show({ msg : json.msg, title :
			 * '提示' }); } else { msgBox_close(); $.messager.alert('错误',
			 * json.msg, 'error'); }
			 */

		});

		/*
		 * $("#see input[name='projectName']").val(projectName); $("#see
		 * input[name='fileName']").val(fileName); $("#see
		 * input[name='fileDesc']").val(fileDesc); $("#see
		 * input[name='browseTimes']").val(browseTimes); $("#see
		 * input[name='loadTimes']").val(loadTimes); $("#see
		 * input[name='createUser']").val(createUser); $("#see
		 * input[name='createDate']").val(createDate);
		 * 
		 * $("#fileOath").text("文件权限:" + isAppear);
		 * $("#fileOath").append("---------- <input type='button'
		 * value='下载文件'/>");
		 */

		// msgBox('see', '查看');
	}

}

// 删除文件
function filedelete() {

	var rows = $("#filetab1").datagrid('getSelections');
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
					ids.push(rows[i].fileId);
				}

				$.post('/files/filesManage_delFile.action', {
					'fileIds' : ids.join(',')
				}, function(returnData, status) {
					// 重新加载数据
					$("#filetab1").datagrid('load', {
						'project.projectId' : projectId

					});
					$("#filetab1").datagrid('unselectAll');
					$.messager.show({
						msg : '删除成功',
						title : '提示'
					});

				});
			}
		});
	}

}

// 文件审核
function checkFile() {
	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var fileNames = [];
		for ( var i = 0; i < rows.length; i++) {
			fileNames.push(rows[i].keywordName);
		}
		$.messager.show({
			msg : '只能选择一个文件审核！您已经选择了【' + fileNames.join(',') + '】'
					+ rows.length + '个关键词',
			title : '提示'
		});
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
						'file.fileId' : fileId
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

/**
 * 文件下载
 */
function fileDownload() {

	var rows = $('#filetab1').datagrid('getSelections');

	if (rows.length != 1 && rows.length != 0) {

		var fileTitles = [];
		for ( var i = 0; i < rows.length; i++) {
			fileTitles.push(rows[i].fileName);
		}
		$.messager.show({
			msg : '只能选择一个文件下载！您已经选择了【' + fileTitles.join(',') + '】'
					+ rows.length + '个文件',
			title : '提示'
		});
	} else if (rows.length == 0) {
		$.messager.show({
			msg : '请选择一个文件下载！',
			title : '提示'
		});
	}

	if (rows.length == 1) {
		var row = $("#filetab1").datagrid('getSelected');

		var fileId = row.fileId;
		var fileName = row.fileName;
		var fileDesc = row.fileDesc;
		var browseTimes = row.browseTimes;
		var loadTimes = row.loadTimes;
		var isAppear = row.isAppear;
		if (isAppear == "没有权限下载") {
			$.messager.alert('提示', '您没有权限下载此文件、请联系管理员！', 'info');
			return;
		}

		// 文件路径
		var fileversion = row.fileversions;
		var fileUrl;
		if (fileversion != undefined || fileversion != null) {
			fileUrl = fileversion[0].fileUrl;
		}

		// 加载文件内容
		/*
		 * $.post('/fileDownload/fileDownLoad_getFileSource.action', { 'fileUrl' :
		 * fileUrl }, function(r) {
		 * 
		 * $.messager.show({ msg : '下载成功！', title : '提示' });
		 * 
		 * });
		 */
		/*
		 * $("#see input[name='projectName']").val(projectName); $("#see
		 * input[name='fileName']").val(fileName); $("#see
		 * input[name='fileDesc']").val(fileDesc); $("#see
		 * input[name='browseTimes']").val(browseTimes); $("#see
		 * input[name='loadTimes']").val(loadTimes); $("#see
		 * input[name='createUser']").val(createUser); $("#see
		 * input[name='createDate']").val(createDate);
		 * 
		 * $("#fileOath").text("文件权限:" + isAppear);
		 * $("#fileOath").append("---------- <input type='button'
		 * value='下载文件'/>");
		 */

	}

}
