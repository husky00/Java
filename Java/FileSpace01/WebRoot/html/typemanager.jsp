<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FileSpace</title>
	<base href="<%=basePath%>"/>
	
	<link rel="icon" href="img/1.2.png" type="image/x.png" />
	
	<link rel="stylesheet" type="text/css" href="css/userManage.css" />
	<link rel="stylesheet" type="text/css" href="css/dropdown.css" />
	<link rel="stylesheet" type="text/css" href="css/addstyle.css" />
<script type="text/javascript">
function showMsg() {
	var msg = '${message}';
	if(msg!=null&&msg!=""){
		alert(msg);
	}
}
</script>
<body onload="showMsg()">
<div id="container">
	<!--header start-->
	<div id="header">
		<div class="header_detail">
			<!--userInfo start-->
			<s:if test="#session.user==null">
				<p class="login"><a href="javascript:pop_upShow();">登录</a></p>
			</s:if>
			<s:else>
			<!--下拉菜单-->
			<div class="dropdown" id="divselect">
				<a id='personalName'>欢迎你：<s:property value="#session.user.uname"/></a>
				<ul id='drop'>
					<li><a href="filelists.action">回到首页</a></li>
					<li><a href="./html/personInfo.jsp">个人中心</a></li>
					<li><a href="filemylists.action?tid=0">我的文件</a></li>
					<li><a href="collectionlist.action">我的收藏</a></li>
					<s:if test="#session.user.isadmin == 1">
						<li><a href="userlists.action">用户管理</a></li>
					</s:if>
					<li><a href="userlogout.action">退出系统</a></li>
				</ul>
			</div>
			<script type="text/javascript" src="js/dropdown.js"></script> 
			</s:else>
			<!--userInfo end-->
		</div>
	</div>

	<!--content start-->
	<div id="content">
		<div class="fullbg"></div>
		<div class="pop_up">
			<p class="close"><a onclick="closeAll()">关闭</a></p>
			<form action="typeadd.action" method="post" id="add_style">
				<div class="style">
					<div class="father">
						<label for="style_name">父类:</label>
						<select name="parentid" id="classify">
							<s:iterator value="#request.types" var="ty">
								<option value="${ty.tid }">${ty.tname }</option>
					    	</s:iterator>
						</select>
					</div>
					<div class="cls_inp">
						<label for="style_name" id="name_style">类名:</label>
						<input type="text" name="tname" id="style_name" required="required" />
					</div>
					<input type="submit" id="submit" value="确定" />
				</div>
			</form>
		</div>
		
		<form>
			<!--nowpage 当前页-->
			<div class="nowpage">
				<h2>分类管理</h2>
				<input type="button" name="add" id="addInfo" value="添加" onclick="pop_upShow()" />
			</div>
			
			<!--userdetailnews 用户管理及详细信息-->
			<div class="userdetailnews">
				<table border="1">
					<tr>
						<th width="15%">序号</th>
						<th width="25%">名称</th>
						<th >删除</th>
					</tr>
					<s:iterator value="#request.types" var="ty" status="item">
            	  		<tr>
            	   			<td><s:property value="%{#item.getIndex()+1}"/></td>
							<td><s:property value="#ty.tname"/></td>
							<s:if test="#ty.tname!='默认'">
            					<td width="20%">
            						<a href="typedelete.action?tid=${ty.tid }&flag=false">
            						<input type="button" name="delete" class="delete" value="删除" />
            						</a>
            					</td>
							</s:if>
							<s:else>
								<td width="25%">不可以删除</td>
							</s:else>
            	  		</tr>	
		        	</s:iterator>
				</table>
			</div>
		</form>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
<script type="text/javascript" src="js/addEventLoad.js"></script>
<script type="text/javascript" src="JQuery/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/pop_up.js"></script>
<script type="text/javascript" src="js/highlightheader.js"></script>
</body>
</html>