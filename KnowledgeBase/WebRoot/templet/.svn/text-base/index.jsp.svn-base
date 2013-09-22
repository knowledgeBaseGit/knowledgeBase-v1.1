<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Title</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="../resource/css/index.css">
<script type="text/javascript" src="../js/NavClick.js"></script>
<script type="text/javascript" src="../js/indexPopup.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../js/getUserName.js"></script>
<script type="text/javascript" src="../js/index.js"></script>
</head>
<body onload="timer()">
<div style="height:100%; margin:0 auto;">
  <!--top1开始-------------------------------------------------------------------->
  <div class="top1">
    <div class="top1-middle">
      <span>【${user.loginName}】,欢迎进入知识库</span>
      <div class="top1-middle-left" id="clock"></div>
      <div class="top1-middle-right"><a href="../jsp/Register.jsp">注册</a>|<a href="../jsp/Login.jsp" >退出</a></div>
    </div>
  </div>
  <!--top1结束-------------------------------------------------------------------->
  <div class="top2">
    <div class="top2-middle">
      <div class="logo"><img src="../resource/images/logo.jpg" height="47" /></div>
      <!--menu开始-------------------------------------------------------------------->
      <div class="nav">
        <div id="container">
          <div id="title">
            <ul>
              <li id="nav8" style="background: url('../resource/images/navbackdown.jpg');border-left:1px #CCCCCC solid"><a href="../register/register_login.action" onclick="switchNav('nav8','mains8');this.blur();"><span>首页</span></a></li>
              <li id="nav9"><a href="#" onclick="switchNav('nav9','mains9');this.blur();"><span>问题管理</span></a></li>
              <li id="nav10"><a href="#" onclick="switchNav('nav10','mains10');this.blur();"><span>文件管理</span></a></li>
              <li id="nav13" onMouseOver ="document.getElementById('menu2').style.display ='block' " onMouseOut="document.getElementById('menu2').style.display = 'none' "><a href="#" ><span>权限管理</span></a>
                <div id="menu2" style="display:none">
                   <div id="nav5"><a href="#" onclick="switchNav('nav5','mains5');this.blur();">用户管理</a></div>
                   <div id="nav6"><a href="#" onclick="switchNav('nav6','mains6');this.blur();">角色管理</a></div>
                   <div id="nav7"><a href="#" onclick="switchNav('nav7','mains7');this.blur();">资源管理</a></div>
                </div>
              </li>
              <li id="nav11"><a href="#" onclick="switchNav('nav11','mains11');this.blur();"><span>类别管理</span></a></li>
              <li id="nav12"><a href="#" onclick="switchNav('nav12','mains12');this.blur();"><span>关键词管理</span></a></li>
              <li id="nav14" onMouseOver ="document.getElementById('menu1').style.display ='block'" onMouseOut="document.getElementById('menu1').style.display = 'none'" ><a href="#" ><span>审核管理</span></a>
                <div id="menu1" style="display:none">
                   <div id="nav1"><a href="#" onclick="switchNav('nav1','mains1');this.blur();">问题审核</a></div>
                   <div id="nav2"><a href="#" onclick="switchNav('nav2','mains2');this.blur();">文件审核</a></div>
                   <div id="nav3"><a href="#" onclick="switchNav('nav3','mains3');this.blur();">关键词审核</a></div>
                   <div id="nav4"><a href="#" onclick="switchNav('nav4','mains4');this.blur();">方案审核</a></div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--menu结束---------------------------------------------------------------------->
  </div>
  <!--top3开始-------------------------------------------------------------------->
  <div class="top3">
    <div class="top3-middle"><div class="inputdiv"><input type="text" class="Searchtext" /><input type="button" value=" "  class="Searchbtn"/></div></div>
  </div>
  <!--top3结束-------------------------------------------------------------------->
  <div id="mains" class="mains">
  <!--打开首页后默认调用的框架iFrame1 -->
    <iframe id="iFrame1" name="iFrame1" width="100%" height="100%" style="border:none;" onload="this.height=document.body.scrollHeight-190" src="/keyword/keyword_searchHot.action"></iframe>
  </div>
  <div class="footer"> 石家庄恒运网络科技有限公司LOONGSOFT  版权所有 </div>
</div>
<!-- 登陸彈出層 -->
<div id="divLo" style="display:none;">
<table class="Lontab">
<tr>
<td colspan="2"><a href="../jsp/Register.jsp">还没有账号？去注册</a></td>
</tr>
<tr>
<td align="right">用户名</td><td><input name="user.loginName" id="userName" type="text" class="Loninput" /><div id="div1"></div></td>
</tr>
<tr>
<td align="right">密&nbsp;&nbsp;码</td><td><input type="text" id="loginPassword" name="user.loginPassword"  class="Loninput" /><div id="div2"></div></td>
</tr>
<tr>
<td align="right">验证码</td><td><input type="text" class="Loninput" name="checkcode" id="checkcode"/></td>
</tr>
<tr>
<td colspan="2" align="center"><img src="../jsp/image.jsp" id="randimage" name="randimage"><a href="javascript:loadimage();"><font size="2s">看不清点我</font></a> <div id="div3"></div></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="button" class="Loninput1" id="loginsub" value=" 登 录" /></td>
</tr>

</table>
</div>
</body>
</html>