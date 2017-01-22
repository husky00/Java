<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>FileSpace</title>
	
	<link rel="icon" href="img/1.2.png" type="image/x.png" />
	
	<link rel="stylesheet" type="text/css" href="css/modify.css" />
	<link rel="stylesheet" type="text/css" href="css/dropdown.css" />
	<script type="text/javascript" src="js/dropdown.js"></script>
</head>
<body>
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
			<form action="usermodifyPassword.action" name="information" method="post" id="modifyPassword">
				<div class="userimage">
					<s:if test="#session.user.sex == 1"><img src="img/man.jpg" alt="userimage" /></s:if>
            		<s:else><img src="img/girl.jpeg" alt="userimage" /></s:else>
				</div>
				<div class="user" style="display:none;">
					<input type="text" name="userid" value="<s:property value="#session.user.userid"/>" id="username" />
				</div>
				<div class="user">
					<label for="newpassword">新密码：</label>
					<input type="password" name=newpassword id="newpassword" pattern="[A-Za-z0-9]{6,}" placeholder="由字母数字，至少六个字符">
				</div>
				<div class="user">
					<label for="againpassword">确认密码：</label>
					<input type="password" name="againpassword" id="againpassword" pattern="[A-Za-z0-9]{6,}" placeholder="请确认您的密码">
				</div>	
				<input type="submit" name="submit" id="submit" value="确认" />
				<a href="./html/personInfo.jsp"><input type="button" id="back" value="返回" /></a>
			</form>
		</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>