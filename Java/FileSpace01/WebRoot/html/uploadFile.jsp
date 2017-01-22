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
	
	<link rel="stylesheet" type="text/css" href="css/uploadfile.css" /> 
	<link rel="stylesheet" type="text/css" href="css/addstyle.css" />
	<script type="text/javascript" src="js/dropdown.js"></script>
<script type="text/javascript">
function showMsg() {
	var msg = '${message}';
	if(msg!=null&&msg!=""){
		alert(msg);
	}
}
function CheckChinese(obj,val){     
	var reg = new RegExp("[\\u4E00-\\u9FFF]+","g");
	if(reg.test(val)){     
	   alert("不能输入汉字！");  
	   var strObj = document.getElementById(obj);  
	   strObj.value = "";  
	   strObj.focus();          
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
		<div class="project">
			<form action="fileupload.action" method="post" enctype="multipart/form-data">
				<!--<s:file name="file" label="上传文件" /><br />-->
				<input id="file" type="file" name="file" onblur="CheckChinese('file',this.value)" /><br />
				<label for="classify">分类:</label>
				<select name="tid" id="classify">
					<s:iterator value="#request.types" var="ty">
						<option value="${ty.tid }">${ty.tname }</option>
			    	</s:iterator>
				</select>
				<input type="button" class="addtype" name="addclassify" value="添加分类" onclick="pop_upShow()" /><br />
				<div class="clearfix"></div>
				<input type="submit" name="submit" value="确定" />
				<a href="filemylists.action?tid=0"><input type="button" name="quit" class="return" value="返回" /></a>
			</form>
		</div>
	</div>

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
	
	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>
<script type="text/javascript" src="js/addEventLoad.js"></script>
<script type="text/javascript" src="JQuery/jquery-2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="js/pop_up.js"></script>
<script type="text/javascript" src="js/highlightheader.js"></script>
</body>
</html>