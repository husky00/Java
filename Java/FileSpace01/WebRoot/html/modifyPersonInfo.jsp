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
				<form action="usermodifyInfo.action" method="post" name="information" id="modifyPersonInfo">
					<div class="userimage">
						<s:if test="#session.user.sex == 1"><img src="img/man.jpg" alt="userimage" /></s:if>
            			<s:else><img src="img/girl.jpeg" alt="userimage" /></s:else>
					</div>
					<p id="username"><span>用户名：</span><s:property value="#session.user.uname"/></p>
					<div style="display:none;"><input type="text" name="userid" value="<s:property value="#session.user.userid"/>"></div>
					<div class="user" id="user_sex">
						<label for="sex">性别：</label>
						<s:if test="#session.user.sex == 1">
							<input type="radio" name="sex" value="1" id="male" checked="checked" />男
							<input type="radio" name="sex" value="0" id="female" />女
						</s:if>
            			<s:else>
            				<input type="radio" name="sex" value="1" id="male"/>男
							<input type="radio" name="sex" id="female" value="0" checked="checked" />女
            			</s:else>
					</div>
					<div class="user">
						<label for="myphone">手机号：</label>
						<input type="tel" name="phone" id="myphone" pattern="{11,}" placeholder="请输入手机号">
					</div>
					<input type="submit" name="submit"  id="submit" value="确定" />
					<a href="./html/personInfo.jsp"><input type="button" id="back" value="返回" /></a>
				</form>
			</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>