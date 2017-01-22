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
	
	<link rel="stylesheet" type="text/css" href="css/filefolder.css" /> 
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
		<div class="filemenu">				
				<ul>
					<li><a href="filemylists.action?tid=0">首页</a></li>
					<s:iterator value="#request.tids" var="id">
		            	<li><a href="filemylists.action?tid=${id }"><s:property value="#request.parenttypes[#id]"/></a></li>
			    	</s:iterator>
				</ul>	
		</div>
		
		
		
		<div class="files">
			<!-- 陈祥添加，用作表示当前部分的内容 -->
			<div class="title">
				<h2 onclick="down()">文件分类</h2>
				<p></p>
				<a href="typemanagelists.action">分类管理</a>
			</div>		
			<div class="clearfix"></div>
			<div id="folder">
				<ul id="folder_text">
					<s:iterator value="#request.types" var="ty">
		            	<li>
							<img src="img/folderfile/file.png" />
							<a href="filemylists.action?tid=${ty.tid }"><span class="filetype">${ty.tname }</span></a>
							<!-- <p class="time">2016.2.30</p> -->
						</li>
			    	</s:iterator>
				</ul>
			</div>	
		</div>
		<div class="documents">
			<div class="title">
				<h2 onclick="up()">文件展示</h2>
				<p id="down_tri"></p>
				<a href="typelists.action">添加文件</a>
			</div>
			<div class="clearfix"></div>
			<div id="file">
				<ul id="file_text">
					<s:iterator value="#request.files" var="fi">
		            	<li>
							<img src="img/index/document.png" />
							<a href="filedownload.action?fid=${fi.fid }"><span class="documents_type">${fi.title }</span></a>
							<%-- <p class="time">${fi.time }</p> --%>
						</li>		
			    	</s:iterator>
			    </ul>
			</div>
		</div>
	</div>

	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>

<script type="text/javascript" src="js/addEventLoad.js"></script>
<script type="text/javascript" src="js/highlightheader.js"></script>
<script type="text/javascript" src="js/up_down.js"></script>
</body>
</html>