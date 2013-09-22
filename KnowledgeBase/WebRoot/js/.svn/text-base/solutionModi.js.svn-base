function ModiSolu(){
	if ($('input:checked').length < 1) {
		alert("请选择一个项目！");
		return;
	} else if ($('input:checked').length > 1) {
		alert("只能选择一个项目！");
		return;
	}
	var solutionId = $('input:checked').val();
	alert(solutionId);
	$.ajax({
		url : "/solution/solution_seeSolu.action",
		data : {
			"solutionId" : solutionId
		},
		dataType : "json",
		success : function(data) {

			$('#solutionName').val(data.solutionName);
			$('#solutionId').val(data.solutionId);
			}
	});
	
	
	msgBox('divmodi', '方案修改');
}