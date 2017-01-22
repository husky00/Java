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
	
	<link rel="stylesheet" type="text/css" href="./css/download.css" />
	<link rel="stylesheet" type="text/css" href="css/dropdown.css" />
	<script type="text/javascript" src="js/dropdown.js"></script> 
<script type="text/javascript">
function showMsg() {
	var msg = '${message}';
	if(msg!=null&&msg!=""){
		alert(msg);
	}
}
</script>
</head>
<body onload="showMsg()">
<div id="container">
	<!--header start-->
	<div id="header">
		<div class="header_detail">
			<!--userInfo start-->
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
			<!--userInfo end-->
		</div>
	</div>

	<!--content start-->
	<div id="content">
	<s:debug></s:debug>
		<ul>
			<li><a href="#"></a></li>
		</ul>
		<p class="backindex"><a href="">返回</a></p>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>