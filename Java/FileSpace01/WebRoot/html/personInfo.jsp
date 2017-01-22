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
	
	<link rel="stylesheet" type="text/css" href="./css/personalInfo.css" />
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
		<form>
			<!--user 用户设置-->
			<div class="contentform">
				<div id="personInfo">
					<div class="userimage">
						<s:if test="#session.user.sex == 1"><img src="img/man.jpg" alt="userimage" /></s:if>
            			<s:else><img src="img/girl.jpeg" alt="userimage" /></s:else>
					</div>
					<div class="userdetail">
						<p><span>用户名：</span><s:property value="#session.user.uname"/></p>
						<p><span>性别：</span>
							<s:if test="#session.user.sex == 1">男</s:if>
            				<s:else>女</s:else>
            			</p>
						<p><span>手机号：</span><s:property value="#session.user.phone"/></p>
						<p><span>邮箱：</span><s:property value="#session.user.email"/></p>
					</div>
					<div class="user">
						<a href="./html/modifyPersonInfo.jsp"><input type="button" name="modifyPersonInfo" id="modifyPersonInfo" value="修改信息" /></a>
						<a href="./html/modifyPassword.jsp"><input type="button" name="modifyPassword" id="modifyPassword" value="修改密码" /></a>
					</div>					
				</div>
			</div>				
		</form>

	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>