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

<link rel="icon" href="img/1.2.png" type="image/x.png" />

<title>FileSpace</title>
	<base href="<%=basePath%>"/>
	<link rel="stylesheet" type="text/css" href="css/collection.css" />
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
		<div class="nowpage">
			<h2>我的收藏</h2>		
		</div>
			
		<div class="userdetailnews">
			<table border="1">
				<tr>
					<th>序号</th>
					<th>文件名称</th>
					<th>操作</th>
				</tr>
				<s:iterator value="#request.files" var="fi" status="index">
            	  	<tr id="<s:property value="#index.index"/>">
            	   		<td><s:property value="%{#index.getIndex()+1}"/></td>
						<td><s:property value="#fi.title"/></td>
            			<td><a onclick="del('<s:property value="#fi.fid"/>','<s:property value="#index.index"/>')">
            				<input type="button" name="delete" class="delete" value="删除" /></a>
            			</td>
            	  	</tr>	
		        </s:iterator>
			</table>
		</div>		
	</div>
	
	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>

<script type="text/javascript" src="JQuery/jquery-2.1.4/jquery.js"></script>
<script type="text/javascript" src="../js/addEventLoad.js"></script>
<script type="text/javascript" src="../js/highlightheader.js"></script>
</body>
<script type="text/javascript">
	function del(fid,index){
		var data = {fid:fid};
		$.ajax({
			type : "POST",
			url : "collectiondelete",
			data : data,
			dataType : "json",
			success : function(data) {
				var num = eval("("+data+")");
				if(num.success) {
					$("#"+index).remove();
				} else {
					alert("删除失败");
				}
			}
		})
	};
</script>
</html>