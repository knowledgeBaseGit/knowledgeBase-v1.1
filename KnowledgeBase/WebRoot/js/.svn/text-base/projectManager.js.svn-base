$(document)
		.ready(
				function() {
					//点击关键词，加载checkbox对应的项目中的关键词列表
					
					
					
					// 点击行选中checkbox
					$("#tab1 tr").first().nextAll()
							.click(
									function() {
										if ($(this).children().css(
												"background-color") == $(
												document.body).css(
												"background-color")) {
											$(this).children().css(
													"background-color",
													"#FFEE99");
											$(this).children().children().prop(
													"checked", true);
										} else {
											$(this).children().first()
													.children().prop("checked",
															false);
											$(this).children().css(
													"background", "");

										}
									});
					/* ----------------------------显示修改下拉列表，页面加载完毕，一次性添加，避免重复添加---------------------------- */
					$.ajax({
						type : "post",
						url : "/project/project_add.action",
						dataType : "json",
						success : function(msg) {
							// 将取出的json字符串转换为对象
							var obj = eval(msg);
							// 修改的显示下拉列表
							$("#parentProModi").append(
									"<option value='null'>无父级项目</option>");
							for ( var i = 0; i < obj.length; i++) {
								$("#parentProModi").append(
										"<option value='" + obj[i].projectId
												+ "'>" + obj[i].projectName
												+ "</option>");
							}
							// 增加的显示下拉列表
							$("#parentProAdd").append(
									"<option value='null'>无父级项目</option>");
							for ( var i = 0; i < obj.length; i++) {
								$("#parentProAdd").append(
										"<option value='" + obj[i].projectId
												+ "'>" + obj[i].projectName
												+ "</option>");
							}
						}
					});
					/*-----------------------------------新增项目时，通过ajax对项目名称的验证----------------------------------------------*/
					// 声明一个全局变量标签
//					var projectNameFlag = false;
//					$("#projectName")
//							.blur(
//									function() {
//										if ($("#projectName").val() == null
//												|| $("#projectName").val() == "") {
//											alert("项目名称不能为空！");
//										} else {
//											var projectName = $("#projectName").val();
//											$.ajax({
//														type : "post",
//														url : "/project/project_nameCheck.action",
//														data : {
//															"projectName" : projectName
//
//														},
//														success : function(data) {
//															if (!data) {
//																alert("项目名称已存在，请更换！");
//																projectNameFlag = false;
//															} else {
//																projectNameFlag = true;
//															}
//														}
//													});
//										}
//									});
					/*-----------------------------------新增项目保存时，对项目名称的验证----------------------------------------------*/
					$("#save_button").click(
							function() {
								if ($('#projectName').val() == null
										|| $('#projectName').val() == "") {
									alert("项目名称不能为空！");
								}
								else{
									var projectName = $("#projectName").val();
									$.ajax({
											type : "post",
											url : "/project/project_nameCheck.action",
											data : {
												"projectName" : projectName

											},
											success : function(data) {
												if (!data) {
													alert("项目名称已存在，请更换！");
													projectNameFlag = false;
												} else {
													$('#addForm').submit();
												}
											}
									});
								}
								
							});
					
					/*---------------------------修改项目后保存时，对项目名称的验证---------------------------*/
					$("#modi_button").click(
									function() {
										if ($('#projectName2').val() == null
												|| $('#projectName2').val() == "") {
											alert("修改后的项目名称不能为空");
										}
										var projectId = $('input:checked').val();
										$.ajax({
													url : "/project/project_see.action",
													data : {"projectId" : projectId},
													dataType : "json",
													success : function(data) {
														if ($('#projectName2').val() == data.projectName) {
															$('#modiForm').submit();
														}else{
															var projectName2 = $("#projectName2").val();
															$.ajax({
																type : "post",
																url : "/project/project_nameCheck.action",
																data : {
																	"projectName" : projectName2,
																},
																success : function(data) {
																	if (!data) {
																		alert("项目名称已存在，请更换！");
																	} else {
																		$('#modiForm').submit();
																	}
																}
															});
														}
													}
												});
									});

				});
/*-----------------------------项目查看的相关函数----------------------------------*/
function seePro() {
	// 判断选择的复选框个数
	if ($('input:checked').length < 1) {
		alert("请选择一个项目！");
		return;
	} else if ($('input:checked').length > 1) {
		alert("只能选择一个项目！");
		return;
	}
	// projectId通过选中框进行传递
	var projectId = $('input:checked').val();
	$.ajax({
		url : "/project/project_see.action",
		data : {
			"projectId" : projectId
		},
		dataType : "json",
		success : function(data) {
			$('#projectNameSee').val(data.projectName);
			$('#projectDesc').val(data.projectDesc);
			// 对于不清楚无parentId是什么格式时候，先弹出，然后在设置
			if (data.parentProId == undefined) {
				$('#parentPro').val("无父级项目");
			} else {
				$('#parentPro').val(data.parentProName);
			}
			$('#createTime').val(data.createTime);
			$('#creater').val(data.creater);
		}
	});
	msgBox('divsee', '查看');
};
/*-----------------------------项目删除的相关函数----------------------------------*/
function deletePro() {

	// 判断选择的复选框个数
	if ($('input:checked').length < 1) {
		alert("请选择要删除的项目！");
		return;
	}
	var projectIds = [];
	$('input:checked').each(function() {
		// js的push方法，将新元素添加到一个数组中
		projectIds.push($(this).val());
	});
	var r = window.confirm("确定要启用此项目吗？删除不可恢复！");
	if (r) {
		$.ajax({
			url : "/project/project_deleteAll.action",
			data : {
				"projectIds" : projectIds.join(",")
			},
			success : function() {
				alert("删除成功");
				location.href = "/project/project_list.action";
			}
		});
	}
}
/*---------------------------项目关键词的保存------------------------------------*/
function savekeyPro(){
	var keywordIds = [];
	$('#keywordPro :checked').each(function(){
		keywordIds.push($(this).val());
	});
	var projectId = $('#projectSelect :checked').val();
	var r = window.confirm("确定要上传吗？");
	if(r){
		$.ajax({	
			url:"/projectKeyword/projectKeyword_saveKeyPro.action",
			data:{
				"projectId":projectId,
				"keywordIds":keywordIds.join(",")
			},
			success:function(){
				alert("上传成功");
				location.href = "/project/project_list.action";
			}
		});
	}
}
/*-----------------------------项目相关关键词的函数-------------------------------*/
function keyPro(){
	
	
	
	//每次进入关键词操作之前，需要把上一次获取的元素清空。
	$("#keywordPro").empty();
	
	// 判断选择的项目个数
	if ($('input:checked').length < 1) {
		alert("请选择一个项目！");
		return;
	} else if ($('input:checked').length > 1) {
		alert("只能选择一个项目！");
		return;
	}
	
	var projectId = $('input:checked').val();
	
	//根据所选择的项目id查找对应的关键词
	
	$.ajax({
		//同步的话，可以先获取项目所含有的关键词，然后再执行下面的ajax。
		async: false,
		type:"post",
		dataType : "json",
		url : "/project/project_keyProject.action",
		data : {
			"projectId" : projectId
		},
		dataType : "json",
		success : function(data) {
			for(var i = 0; i <data.length;i++){
				$('#keywordPro').append("<div style='float:left;width:120px;'><input checked='checked' type='checkbox' value='"+data[i].keywordId+"' name='proKey.keyword.keywordId' />"+data[i].keywordName+"</div>");
				
			}
		}
	});
	//选取除选择项目所包含的的关键词以为的全部关键词
	$.ajax({
		type:"post",
		dataType : "json",
		url : "/project/project_aLLKeyProject.action",
		data : {
			"projectId" : projectId
			
		},
		dataType : "json",
		success : function(data) {
			for(var i = 0; i <data.length;i++){
				$('#keywordPro').append("<div style='float:left;width:120px;'><input  type='checkbox' value='"+data[i].keywordId+"' name='proKey.keyword.keywordId' />"+data[i].keywordName+"</div>");
			}
		}
	});
	msgBox('divkey', '选择关键词');
}
/*-----------------------------新增的相关函数----------------------------------*/
function addPro() {

	msgBox('divadd', '新增');
};

/*-----------------------------项目修改的相关函数----------------------------------*/
function modiPro() {
	// 判断选择的项目个数
	if ($('input:checked').length < 1) {
		alert("请选择一个项目！");
		return;
	} else if ($('input:checked').length > 1) {
		alert("只能选择一个项目！");
		return;
	}
	var projectId = $('input:checked').val();

	// 跟查看一样，返回数据
	$.ajax({
		url : "/project/project_see.action",
		data : {
			"projectId" : projectId
		},
		dataType : "json",
		success : function(data) {

			var stat = data.parentProId;
			if (stat == null) {
				stat = "null";
			}
			$("#parentProModi").val(stat);
			$('#projectName2').val(data.projectName);
			$('#projectDesc').val(data.projectDesc);
			$('#createTime').val(data.createTime);
			$('#creater').val(data.creater);
		}
	});

	msgBox('divmodi', '修改');
};
/*------------------------------全选-------------------------------------------*/
function selectProjects(inputObj) {
	var inputArray = document.getElementById("tab1").getElementsByTagName(
			"input");
	for ( var i = 1; i < inputArray.length; i++) {
		if (inputArray[i].type == "checkbox") {
			inputArray[i].checked = inputObj.checked;
		}
	}
}
