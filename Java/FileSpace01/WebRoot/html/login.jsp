<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href="<%=basePath%>"/>
	<title>FileSpace</title>
	
	<link rel="icon" href="img/1.2.png" type="image/x.png" />
	
	<link rel="stylesheet" type="text/css" href="css/login.css" />
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
			<!--userInfo start 登录页不需要-->
			<!--userInfo end-->
		</div>
	</div>

	<!--content start-->
	<div id="content">		
		<div class="contentform">
			<form action="userlogin.action" method="post" name="information" id="login">
				<div class="userimage">
					<img src="img/login/user.png" alt="userimage" />
				</div>
				<div class="user">
					<label for="username">用户名：</label>
					<input type="text" name="uname" id="username" pattern="[A-Za-z]{3,}" placeholder="由字母(大小写），且3位以上组成" />
				</div>
				<div class="user">
					<label for="password">密&nbsp;&nbsp;码：</label>
					<input type="password" name="upass" id="password" pattern="[A-Za-z0-9]{6,}" placeholder="由字母数字，至少六个字符">
				</div>
				<input type="submit" name="submit" id="submit" value="登录" />
				<a href="filelists.action"><input type="button" name="back" id="back" value="返回" /></a>
		
			</form>
		</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>