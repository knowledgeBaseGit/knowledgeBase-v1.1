<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.loongsoft.knowledgebase.bean.Project"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/Query.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery/themes/icon.css">

<script type="text/javascript" src="../jquery/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="../js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="../jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="../js/Tree.js"></script>
<script type="text/javascript" src="../js/Tab.js"></script>
<script type="text/javascript" src="../js/Popup.js"></script>
<script type="text/javascript" src="../js/TabColor.js"></script>

<script type="text/javascript" src="../js/quesOperate.js"></script>
<script type="text/javascript" src="../js/quesAutho.js"></script>




</head>
<body>
	<div class="all">
		<div class="left">
			<div class="lefttop">&nbsp;</div>
			<div class="leftmiddle" id="aaa">
				<ul id="nav">

					<s:iterator value="#request.list" var="project">
						<li
							onclick="cascadeKey('<s:property value="#project.projectId"/> ');"><a
							href="#"><img src="../resource/images/Icontree.png"
								align="absmiddle" /> <s:property value="#project.projectName" />
						</a> <s:if test="#project.projects.size>0">

								<ul>
									<s:iterator value="#project.projects" var="subpro">
										<li
											onclick="cascadeKey('<s:property value="#subpro.projectId"/> ');"><a
											href="#"><img src="../resource/images/Icontree.png"
												align="absmiddle" /> <s:property
													value="#subpro.projectName" /> </a></li>
									</s:iterator>
								</ul>

							</s:if></li>

					</s:iterator>
				</ul>
			</div>
			<div class="leftbottom">&nbsp;</div>
		</div>
		<div class="right">
			<div class="righttop">
				<div class="righttop-left">
					关键词： <input type="text" id="selectKeys" class="inputText" /> <input
						type="button" value="选择" class="inputbutton"
						onClick="msgBox('div1', '关键词选择');" /> <input type="button"
						id="search" onclick="search();" value="搜索" class="inputbutton" />
				</div>
				<div class="righttop-right">
					<ul>
						<li id='quesSee'><a href="#" onClick="seeQues();">查看</a></li>
						<li id="quesAsk"><a href="#" onClick="addQues();">提问</a></li>
						<li id="quesReply"><a href="#" onClick="replyQues();">解答问题</a></li>
						<li id="quesDele"><a href="#" onClick="deleteQues();">删除</a></li>
					</ul>
				</div>
			</div>
			<div class="rightmiddle">
				<!-- datagrid 显示 -->
				<table cellspacing="0" id="tab1"  class="easyui-datagrid">

				</table>
			</div>
		</div>
	</div>
	<!--弹出层-->
	<div id="div1" style="display: none; width: 350px">
		<table class="tab2" id="tab2">

			<tr>
				<td colspan="4"><input type="button" value="确 定" class="inputbutton" /> <input
					type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
		</table>
	</div>
	<%-- <div id="div2" style="display: none; width: 540px;height: 210px">
		<div id="tag">
			<div id="Tab0">
				<ul class="menu0" id="menu0">
					<li class="hover" onClick="setTab(0,0)">问题描述</li>
					<li onClick="setTab(0,1)">解决方案</li>
					<li onClick="setTab(0,2)">相关附件</li>
				</ul>
			</div>
			<div id="tagContent0">
				<ul class="block">
					<li>
						<table class="poptab">
							<tr>
								<td><br />
								<textarea id="quesDesc" class="textareastyle"></textarea></td>
							</tr>
							<tr>
								<td align="right"><span>提问人：小A <input type="button"
										value="提交" /> <input type="button" value="审核" />
								</span></td>
							</tr>
						</table>
					</li>
				</ul>
				<ul>
					<li><textarea id="solutionMethod" class="textareastyle"></textarea></li>
				</ul>
				<ul>
					<li><textarea id="solu_annex" class="textareastyle"></textarea></li>
				</ul>
			</div>
		</div>
	</div> --%>

	<!-- 弹出层旧版本 end-->



	<!--弹出层 新版本-->
	<div id="seeQues" style="display: none; width: 600px; height: 300px">
		<div id="tag">
			<div id="Tab0">
				<ul class="menu0" id="menu0">
					<li class="hover" onClick="setTab(0,0)">问题描述</li>
					<li onClick="setTab(0,1)">解决方案</li>
					<li onClick="setTab(0,2)">相关附件</li>
				</ul>
			</div>
			<div id="tagContent0">
				<ul class="block">
					<li>
						<table class="poptab">
							<tr>
								<td align="right">问题名称：</td>
								<td><input type="text" name="questionTitle" /></td>
								<td align="right">所属项目</td>
								<td><input type="text" name="projectName" /></td>
							</tr>
							<tr>
								<td align="right">提问人：</td>
								<td><input type="text" name="createUser" /></td>
								<td align="right">提问日期：</td>
								<td><input type="text" name="writeDate" /></td>
							</tr>
							<tr>
								<td align="right">问题描述：</td>
								<td colspan="3"><input type="text" style="width: 445px"
									name="questionDesc" /></td>
							</tr>
							<tr>
								<td align="right">审核人：</td>
								<td><input type="text" name="checkUser" /></td>
								<td align="right">审核状态：</td>
								<td><input type="text" name="checkStatus" /></td>
							</tr>

							<tr>
								<td align="right">解决状态：</td>
								<td><input type="text" name="solveStatus" /></td>

							</tr>
							<!-- <tr>
              <td colspan="4" align="center">
                <input type="button" value="提交" />
                <input type="button" value="审核" />
                <input type="button" value="取 消" onClick="msgBox_close();"/>
                </td>
            </tr> -->
						</table>
					</li>
				</ul>
				<ul id="solution" style="height: 240px; overflow: auto;">

						<!-- <li>
          <table class="poptab">
            <tr>
              <td align="right">方案名称：</td>
              <td><input type="text" name="soluName" /></td>
              <td align="right">解答人：</td>
              <td><input type="text" name="soluWriteUser"/></td>
            </tr>
            <tr>
              <td align="right">附件：</td>
              <td><input type="text" name="annex" /></td>
              <td align="right">上传时间：</td>
              <td><input type="text" name="soluCreateDate"/></td>
            </tr>
            <tr>
              <td align="right">方案内容：</td>
              <td colspan="3"><textarea style="width:417px; height:70px;" name="soluContent"></textarea></td>
            </tr>
          </table>
        </li> -->

				</ul>
				<ul id="annex">
						<!-- <li>
           <table class="poptab">
             <tr>
               <td align="right">附件名称：</td>
               <td><input type="text" /></td>
               <td><input type="button" value="查看" onclick="show('see');"/></td>
               <td><input type="button" value="下载" /></td>
             </tr>
           </table>
        </li> -->
				</ul>
			</div>
		</div>
	</div>
	<!--弹出层-->
	<!--查看弹出层开始-->
	<div id="see" class="white_content">
		<h2>
			<span>附件</span><a href="javascript:void(0)" onclick="hide('see')"><img
				src="../resource/images/Iconclose.jpg" height="18" width="18" /></a>
		</h2>
		<div style="width: 300px; height: 260px;">
			<textarea style="width: 340px; height: 250px;"></textarea>
		</div>
	</div>
	<!--查看弹出层 新版本 结束-->

	<!-- 提问 -->

	<div id="addQues" style="display: none; width: 600px; height: 250px">
		<table width="100%" class="tab3">
			<tr>
				<td align="right">问题名称：</td>
				<td><input type="text" name="questionTitle"/></td>
				<td align="right">所属项目：</td>
				<td><select style="width: 155px" id="addQues_project">
				</select></td>
			</tr>
			<tr>
				<td align="right">关键词：</td>
				<td><select style="width: 155px" id="addQues_keyword"></select>
				<td align="right">提问人：</td>
				<td><input type="text" name="createUser" /></td>
                 
			</tr>
			<tr>
				<td align="right">问题描述：</td>
				<td colspan="3"><textarea rows="5" cols="49"
						name="questionDesc"></textarea></td>
			</tr>

			<tr>
				<td colspan="4" align="center"><input type="button" onclick="sure_addQues();" value="提交" class="inputbutton" />
					<input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton"  /></td>
			</tr>
			<!--<tr>
      <td colspan="2" align="center"><input type="button" class="inputbutton" value="确定"/></td>
      <td  colspan="2" align="center"><input type="button" class="inputbutton" value="取 消" onClick="msgBox_close();" /></td>
    </tr> -->
		</table>
	</div>
	
	<!-- 提问结束 -->
	
	
	<!-- 解答问题开始 -->
   
	<div id="replyQues" style="display: none; width: 600px; height: 250px">
		
		<table class="tab3">
			<tr>
			    
				<td align="right">问题名称：</td>
				<td><input type="text" readonly="readonly" name="questionTitle"/> <input type="hidden" id="reply_Id"/>  </td>
				<td align="right">方案名称：</td>
				<td><input type="text" name="soluName"/></td>
			</tr>
			 
			<tr>
				<td align="right">方案内容：</td>
				<td colspan="3"><textarea rows="5" cols="49"
						name="soluContent"></textarea></td>
			</tr>
			<tr>
			     <td align="right">是否上传附件：</td>
			     <td> <input type="radio" name="upAnnex" value="0" onclick="chooseAnnex();"  />是&nbsp;&nbsp; <input type="radio" name="upAnnex" value="1" onclick="chooseAnnex();" />否</td>
			</tr>
			<tr id="showUpAnnex" style="display: none;">
				<td align="right">附件名称：</td>
				<td> <input type="text" name="annexName"> </td>
				<td colspan="2"><input type="file" name="upload"/></td>
			</tr>
           
			<tr>
				<td colspan="4" align="center"><input type="button" onclick="sure_replyQues();" value="提交" class="inputbutton" />
					<input type="button" value="取 消" onClick="msgBox_close();" class="inputbutton" /></td>
			</tr>
			<!--<tr>
      <td colspan="2" align="center"><input type="button" class="inputbutton" value="确定"/></td>
      <td  colspan="2" align="center"><input type="button" class="inputbutton" value="取 消" onClick="msgBox_close();" /></td>
    </tr> -->
   
		</table>
	</div>

 
</body>
</html>