//加载页面时显示所有已审核问题
jQuery(function($) {
	getFunsOfUser();
});

// 获取用户功能权限
function getFunsOfUser() {
	$.post('/users/userManage_getFunsOfUser.action', {}, function(r) {
		var json = eval('(' + r + ')');

		if (json && json.success) {

			// 添加数组方法属性
			Array.prototype.Exists = function(v) {
				var b = false;
				for ( var i = 0; i < this.length; i++) {
					if (this[i] == v) {
						b = true;
						break;
					}
				}
				return b;
			}
			// 当前登陆用户的功能权限Ids
			var funsId = json.obj;
			// 当前页面的控件
			var funs = [ "cheKey", "keyUndoCheck"];
            //判断用户是否有该页面的操作
			var operate = 0;
			// 判断是否存在当前功能操作
			for ( var i = 0; i < funs.length; i++) {
				if (funsId.Exists(funs[i])) {
					$('#'+funs[i]).show();
				} else {
					 operate++;
					$('#'+funs[i]).hide();
				}
			}
			if (operate == funs.length) {
				window.open('/templet/index.jsp');
			}

		} else{
			window.open('/jsp/Login.jsp'); 
				$.messager.show({
					msg : '请登录！',
					title : '提示'
				});
		}
	});
}