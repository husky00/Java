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
	
	<link rel="stylesheet" type="text/css" href="css/filedetail.css" />
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
			</s:else>
			<!--userInfo end-->
		</div>
		
	</div>

	<!--content start-->
	<div id="content">
		<div class="filedetail">
			<h2>软件工程</h2>
			<p class="author">chh</p>
			<p class="time">上传于5.16 12:53</p>
			<p class="filedetail_content">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean euismod bibendum laoreet. Proin gravida dolor sit amet lacus accumsan et viverra justo commodo. Proin sodales pulvinar tempor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Nam fermentum, nulla luctus pharetra vulputate, felis tellus mollis orci, sed rhoncus sapien nunc eget odio.</p>
			<!--operation start-->
			<div class="choice">
				<ul>
					<li><a href="">点赞</a></li>
					<li><a href="">差评</a></li>
					<li><a href="">收藏</a></li>
					<li><a href="">下载</a></li>
				</ul>
			</div>
			<!--operation end-->
			<p class="backindex"><a href="filelists.action">返回首页</a></p>
		</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
</body>
</html>