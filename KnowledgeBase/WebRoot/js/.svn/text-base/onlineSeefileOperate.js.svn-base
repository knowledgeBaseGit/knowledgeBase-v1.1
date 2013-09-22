//加载页面时显示所有已审核问题
jQuery(function($) {

	var fileId = $("#seeFileId").val();
	 
	seeFile(fileId);

});

// 查看文件按钮操作
function seeFile(fileId) {

	// 加载文件内容
	$.post('/files/filesManage_getFileSource.action', {
		'file.fileId' : fileId
	}, function(r) {
       
		var json = eval('(' + r + ')');

		if (json && json.success) {
			 
	CKEDITOR.instances.fileSource.insertHtml(json.obj);
			// editor.html(json.obj);
			$.messager.show({
				msg : json.msg,
				title : '提示'
			});
		} else {

			$.messager.alert('错误', json.msg, 'error');
		}

	}, "text");

}
