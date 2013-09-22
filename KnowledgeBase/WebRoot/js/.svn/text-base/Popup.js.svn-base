var msgBoxCurDiv = null;
var msgBoxDiv = null;
var msgBoxBackDiv = null;
var msgBoxParentDiv = null;
var msgBoxBackFunction = null;
var msgBoxSelectList = null;
var isIE = !+"\v1";
//主函数 id:内容,strTitle:标题,fn_msgBoxBack:关闭时回调函数,notShowClose:是否隐藏关闭按钮
function msgBox(id, strTitle, fn_msgBoxBack, hideClose)
{
	if(msgBoxDiv && msgBoxDiv.style.display == 'block')
	{
		msgBox_close();
	}
	var div = document.getElementById(id); 
	if(!div)
	{
		alert('内容不存在！');
	}
	msgBoxCurDiv = div;
	msgBox_getBackDiv();
	msgBox_getMsgDiv();
	msgBox_innerHTML(document.getElementById('msgBoxTitle'), strTitle || '无标题');
	document.getElementById('msgBoxBtnClose').style.display = hideClose ? 'none':'inline';
	msgBoxBackFunction = fn_msgBoxBack;
	msgBoxParentDiv = msgBoxCurDiv.parentNode;
	msgBoxParentDiv.removeChild(msgBoxCurDiv);
	msgBoxCurDiv.style.display = 'block';
	msgBoxDiv.appendChild(msgBoxCurDiv);
	msgBoxDiv.onkeyup = function(evt)
		{
			evt = evt ? evt : (window.event ? window.event : null);
			if(evt.keyCode==27 && document.getElementById('msgBoxBtnClose').style.display != 'none')
			msgBox_close();
		}
	
	//IE6将SELECT隐藏
	msgBoxSelectList = new Array();
	if(navigator.appVersion.indexOf('MSIE 6.0') != -1){
		var selectList = document.getElementsByTagName('select');
		var mySelectList = msgBoxCurDiv.getElementsByTagName('select');
		for(var i=0; i<selectList.length; i++){
			var isMy = true;
			for(var j=0; j<mySelectList.length; j++){
				if(selectList[i].uniqueID == mySelectList[j].uniqueID){
					isMy=false;
					break;
				}
			}
			if(isMy && selectList[i].style.visibility != 'hidden')
			{
				msgBoxSelectList.push(selectList[i]);
				selectList[i].style.visibility = 'hidden';
			}
		}
	}
	
	//window.setTimeout(msgBox_showMsg, 100);
	msgBox_showMsg();
}

function msgBox_innerHTML(obj, strHTML)
{
	obj.innerHTML = strHTML;
}

var msgBox_inputFocus = null;
function msgBox_showMsg() {
	var pageSizeObj = getPageSize();
	var left = (pageSizeObj.width - msgBoxDiv.offsetWidth) / 2;
	msgBoxDiv.style.top = '50px'
	msgBoxDiv.style.left = left < 0 ? pageSizeObj.left : pageSizeObj.left + left + 'px';
	msgBoxDiv.style.width = msgBoxDiv.clientWidth - parseInt(msgBoxDiv.style.paddingLeft) - parseInt(msgBoxDiv.style.paddingRight) + 'px';
	//让第一个可用的Input获得焦点
	var allElement = msgBoxCurDiv.getElementsByTagName('*');
	for(var i=0; i<allElement.length; i++)
	{
		var element = allElement[i];
		if((element.tagName == 'INPUT' || element.tagName == 'SELECT' || element.tagName == 'TEXTAREA') && !element.disabled)
		{
			element.focus();
			break;
		} 
	}
}

//关闭对话框
function msgBox_close(srcElement)
{
	if(msgBoxBackFunction)
	{
		if(srcElement == document.getElementById('msgBoxBtnClose'))
		{
			msgBoxBackFunction(true);
		}
		else
		{
			msgBoxBackFunction();
		}
	}
	msgBoxDiv.style.width = 'auto';
	msgBoxDiv.style.height = 'auto';
	msgBoxDiv.style.display = 'none';
	msgBoxBackDiv.style.display = 'none';
	msgBoxDiv.removeChild(msgBoxCurDiv);
	msgBoxParentDiv.appendChild(msgBoxCurDiv);
	msgBoxCurDiv.style.display = 'none';
	for(var i=0; i<msgBoxSelectList.length; i++)
	{
		msgBoxSelectList[i].style.visibility = 'inherit';
	}
	msgBox_moveFinish();
}

//获取弹出层
function msgBox_getMsgDiv()
{
	if(msgBoxDiv)
	{
		msgBoxDiv.style.display = 'block';
		return;
	}
	var div = document.createElement('div');
	div.style.zIndex = 101;
	div.style.position = 'absolute';
	 
	div.style.backgroundColor = 'white';
	div.style.border = 'solid 2px #cccccc';
	div.style.padding = '5px';
	var strHTML = '<div style="position:relative; cursor:default; font-size:14px; height:20px;" onmousedown="msgBox_moveStart(event);">';
	strHTML += '<b id="msgBoxTitle"></b>';
	strHTML += '<a id="msgBoxBtnClose" style="position:absolute; right:5px;" href="javascript:msgBox_close(this);"><img src="../resource/images/Iconclose.jpg" height="18" width="18" /></a></div>';
	strHTML += '<div style="border-top:solid 1px gray; margin:5px auto;"></div>';
	msgBox_innerHTML(div, strHTML);
	if(document.forms.length == 0)
	{
		msgBoxDiv = document.body.appendChild(div);
	}
	else
	{
		msgBoxDiv = document.forms[0].appendChild(div);
	}
}

//层移动
var msgBox_mouseX, msgBox_mouseX, msgBox_x, msgBox_y;
function msgBox_moveStart(evt)
{
	evt = evt ? evt : (window.event ? window.event : null);
	msgBox_mouseX = evt.clientX;
	msgBox_mouseY = evt.clientY;
	msgBox_x = parseInt(msgBoxDiv.style.left);
	msgBox_y = parseInt(msgBoxDiv.style.top);
	if(isIE)
	{
		document.attachEvent('onmousemove', msgBox_move);
		document.attachEvent('onmouseup', msgBox_moveFinish);
		document.attachEvent('onselectstart', msgBox_notSelect);
	}
	else
	{
		document.addEventListener('mousemove', msgBox_move,false);
		document.addEventListener('mouseup', msgBox_moveFinish,false);
		document.addEventListener('selectstart', msgBox_notSelect,false);
	}
}
function msgBox_notSelect()
{
	return false;
}
function msgBox_move(evt)
{
	evt = evt ? evt : (window.event ? window.event : null);
	var left = msgBox_x + (evt.clientX - msgBox_mouseX);
	var top = msgBox_y + (evt.clientY - msgBox_mouseY);
	left = left < 0 ? 0 : left;
	top = top < 0 ? 0 : top;
	left = left + msgBoxDiv.offsetWidth < msgBoxBackDiv.offsetWidth ? left : (msgBoxBackDiv.offsetWidth - msgBoxDiv.offsetWidth);
	top = top + msgBoxDiv.offsetHeight < msgBoxBackDiv.offsetHeight ? top : (msgBoxBackDiv.offsetHeight - msgBoxDiv.offsetHeight);
	msgBoxDiv.style.left = left + 'px';
	msgBoxDiv.style.top = top + 'px';
}
function msgBox_moveFinish()
{
	if(isIE)
	{
		document.detachEvent('onmousemove', msgBox_move);
		document.detachEvent('onmouseup', msgBox_moveFinish);
		document.detachEvent('onselectstart', msgBox_notSelect);
	}
	else
	{
		document.removeEventListener('mousemove', msgBox_move,false);
		document.removeEventListener('mouseup', msgBox_moveFinish,false);
		document.removeEventListener('selectstart', msgBox_notSelect,false);
	}
}

//获取背景层
function msgBox_getBackDiv()
{
	if(msgBoxBackDiv)
	{
		msgBoxBackDiv.style.width = document.body.scrollWidth + 'px';
		msgBoxBackDiv.style.height = document.body.scrollHeight + 'px';
		msgBoxBackDiv.style.display = 'block';
		return;
	}
	
	var div = document.createElement('div');
	div.style.position = 'absolute';
	div.style.top = '0px';
	div.style.left = '0px';
	if(isIE)
	{
		div.style.filter = 'Alpha(opacity=30)';
	}
	else if(navigator.userAgent.indexOf("Chrome") > -1) //判断是否Chrome
	{
		div.style.opacity = 0.3;
	}
	else
	{
		div.style.opacity = 0.3;
	}
	div.style.width = document.body.scrollWidth + 'px';
	div.style.height = document.body.scrollHeight + 'px';
	div.style.backgroundColor = 'black';
	div.style.display = 'block';
	div.style.zIndex = 100;
	msgBoxBackDiv = document.body.appendChild(div);
}

//alert对话框
var msgBoxAlertDiv = null;
function msgBox_alert(strAlert, strTitle)
{
	if(!msgBoxAlertDiv)
	{
		var div = document.createElement('div');
		var strHTML = strAlert;
		strHTML += '<div style="text-align:center;"><input type="button" value="确 定" onclick="msgBox_close();" /></div>';
		msgBox_innerHTML(div, strHTML);
		div.style.display = 'none';
		div.id = 'divMsgBoxAlert';
		div.style.lineHeight = '30px';
		div.style.width = '300px';
		msgBoxAlertDiv = document.body.appendChild(div);
	}
	strTitle = strTitle || '提示信息'
	msgBox('divMsgBoxAlert', strTitle);
}

//加载中
var msgBoxLoadingDiv = null;
var msgBoxLoadingAuto = null;
function msgBox_loading(strAlert, strTitle)
{
	if(!msgBoxLoadingDiv)
	{
		var div = document.createElement('div');
		var strHTML = '';
		strHTML += '<div style="text-align:center;">';
		if(strAlert)
		{
			strHTML += '<div>' + strAlert + '</div>';
		}
		for(var i=1; i<=10; i++)
		{
			strHTML += '<span id="msgBoxLoadingSpan' + i + '" style="color:gray;">O</span>';
		}
		strHTML += '</div>';
		msgBox_innerHTML(div, strHTML);
		div.style.display = 'none';
		div.id = 'divMsgBoxLoading';
		div.style.lineHeight = '30px';
		div.style.width = '300px';
		msgBoxLoadingDiv = document.body.appendChild(div);
	}
	strTitle = strTitle || '加载中';
	msgBox('divMsgBoxLoading', strTitle, msgBox_loadingClose, true);
	document.getElementById('msgBoxLoadingSpan1').style.color = 'red';
	msgBoxLoadingAuto = window.setInterval(msgBox_loadingAuto, 200);
}

var msgBoxLoadingIndex = 1;
function msgBox_loadingAuto()
{
	var span = document.getElementById('msgBoxLoadingSpan' + msgBoxLoadingIndex);
	span.style.color = 'gray';
	if(msgBoxLoadingIndex == 10)
	{
		msgBoxLoadingIndex = 1;
	}
	else
	{
		msgBoxLoadingIndex++;
	}
	document.getElementById('msgBoxLoadingSpan' + msgBoxLoadingIndex).style.color = 'red';
}

function msgBox_loadingClose()
{
	window.clearInterval(msgBoxLoadingAuto);
}

function getPageSize() {
	var obj = new Object();
	obj.allWidth = document.body.scrollWidth;
	obj.allHeight = document.body.scrollHeight;
	if (-[1, ]) {	//非IE
		obj.top = document.body.scrollTop;
		obj.left = document.body.scrollLeft;
		if (document.compatMode === 'CSS1Compat') {
			obj.width = document.documentElement.clientWidth;
			obj.height = document.documentElement.clientHeight;
		}
		else {
			obj.width = document.body.clientWidth;
			obj.height = document.body.clientHeight;
		}
	} else {
		if (document.compatMode === 'CSS1Compat') {
			obj.width = document.documentElement.clientWidth;
			obj.height = document.documentElement.clientHeight;
			obj.top = document.documentElement.scrollTop;
			obj.left = document.documentElement.scrollLeft;
		}
		else {
			obj.width = document.body.clientWidth;
			obj.height = document.body.clientHeight;
			obj.top = document.body.scrollTop;
			obj.left = document.body.scrollLeft;
		}
	}
	return obj;
}


//二层弹出层
function show(tag) {
    var light = document.getElementById(tag);
    var fade = document.getElementById('fade');
    light.style.display = 'block';
    fade.style.display = 'block';
}
function hide(tag) {
    var light = document.getElementById(tag);
    var fade = document.getElementById('fade');
    light.style.display = 'none';
    fade.style.display = 'none';
}
//二层弹出层鼠标拖住弹出层
function drag(pop) {
    var oWin = document.getElementById(pop);
    var oLay = document.getElementById("fade");
    var oH2 = oWin.getElementsByTagName("h2")[0];
    var bDrag = false;
    var disX = disY = 0;
    oH2.onmousedown = function (event) {

        var event = event || window.event;
        bDrag = true;
        disX = event.clientX - oWin.offsetLeft;
        disY = event.clientY - oWin.offsetTop;
        this.setCapture && this.setCapture();
        return false
    };
    document.onmousemove = function (event) {
        if (!bDrag) return;
        var event = event || window.event;
        var iL = event.clientX - disX;
        var iT = event.clientY - disY;
        var maxL = document.documentElement.clientWidth - oWin.offsetWidth;
        var maxT = document.documentElement.clientHeight - oWin.offsetHeight;
        iL = iL < 0 ? 0 : iL;
        iL = iL > maxL ? maxL : iL;
        iT = iT < 0 ? 0 : iT;
        iT = iT > maxT ? maxT : iT;

        oWin.style.marginTop = oWin.style.marginLeft = 0;
        oWin.style.left = iL + "px";
        oWin.style.top = iT + "px";
        return false
    };
    document.onmouseup = window.onblur = oH2.onlosecapture = function () {
        bDrag = false;
        oH2.releaseCapture && oH2.releaseCapture();
    };
};
 