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
	
	<link rel="stylesheet" type="text/css" href="css/modify.css" />
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
			<!--user 用户设置-->
			<div class="contentform">
				<form action="useradd.action" method="post">
					<div class="user">
						<label for="username">用户名：</label>
						<input type="text" name="uname" id="username" pattern="[A-Za-z]{3,}" placeholder="由字母(大小写），且3位以上组成" />
					</div>
					<div class="user" id="user_sex">
						<label for="sex">性别：</label>
						<input type="radio" name="sex" value="1" id="male" checked="checked" />男
						<input type="radio" name="sex" value="0" id="female" />女
					</div>
					<div class="user">
						<label for="myemail">邮&nbsp;&nbsp;&nbsp;箱：</label>
						<input type="email" name="email" id="myemail" placeholder="请输入注册邮箱" />
					</div>
					<input type="submit" name="submit" id="submit" value="确定" />
					<a href="userlists.action"><input type="button" id="back" value="返回" /></a>
				</form>
			</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>