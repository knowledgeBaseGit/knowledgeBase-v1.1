<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../resource/css/QueryList.css" type="text/css" rel="stylesheet" />
<style type="text/css">
	/*分页样式--张瑞祥-2013-9-2*/
	#pages{width: 100%;text-align: center;height: 34px;line-height: 34px;margin-top: 5px; background: #f8f8f8; border-top: 1px #e9e9e9 solid }
	#pages a, #pages a.current_page:hover{padding: 5px 10px;}
	#pages a:hover{padding: 5px 9px;}
	#pages a{color: #000;}
	#pages a:hover{background: #cddde4;border: #97b9c9 solid 1px;color: #067db5;}
	#pages a.current_page{background: #FFF; border: #89bdd8 solid 1px;color: #067db5;}
</style>
</head>

<body>
<div class="all">
<div class="rightmiddle">
      <div style=" height:610px">
      <table cellspacing="0" id="tab1">
      
        <tr style="background: #f8f8f8">
          <td style=" border-left:1px #e9e9e9 solid"><input type="checkbox"/></td>
          <td>问题描述</td>
          <td>是否解决</td>
          <td>提出时间</td>
        </tr>
       <s:iterator value="questions" var="question">
        <tr>
          <td style=" border-left:1px #e9e9e9 solid">
			<s:property value="#question.questionId"/>
		  </td>
		  <!-- 点击问题，跳转到     QueryAsk.jsp -->
          <td><a href="../jsp/QueryAsk.jsp"><s:property value="#question.questionDesc"/></a></td>
          <td><s:if test="#question.isSolve == 0">否</s:if>
               <s:elseif test="##question.isSolve == 1">是</s:elseif>
          </td>
          <td><s:property value="#question.writeDate"/></td>
         
        </tr>
         </s:iterator>
      </table>
      </div>
      <!--分页-->
		<div id="pages">
			<s:if test="totalPages>=1">
				<s:if test="page>1">
					<a href="/question/question_findByHotKeyword.action?page=${page-1}">上一页</a>
				</s:if>
				<s:else>
					<a>上一页</a>
				</s:else>
				<s:iterator value="new int[totalPages]" status="i">
					<s:if test="page == #i.count">
						<a href="/question/question_findByHotKeyword.action?page=${i.count}"
							class="current_page">${i.count}</a>
					</s:if>
					<s:else>
						<a href="/question/question_findByHotKeyword.action?page=${i.count}">${i.count}</a>
					</s:else>
				</s:iterator>
				<s:if test="page<totalPages">
					<a href="/question/question_findByHotKeyword.action?page=${page+1}">下一页</a>
				</s:if>
				<s:else>
					<a>下一页</a>
				</s:else>
			</s:if>
		</div>
</div>
</div>
</body>
</html>