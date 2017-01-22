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
	<link rel="stylesheet" type="text/css" href="css/userManage.css" />
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
			<!--now page 当前页-->
			<div class="nowpage">
				<h2>用户管理</h2>
				<!-- 修改 2016-5-30 陈祥  替换成下面的
				<input type="button" name="add" id="addInfo" value="添加" />
				-->
				<a href="./html/addUser.jsp" >添加</a>
			</div>
			
			<!--user detail news 用户管理及详细信息-->
			<div class="userdetailnews">
				<table border="1">
					<tr>
						<th>序号</th>
						<th>用户昵称</th>
						<th>性别</th>
						<th>邮箱</th>
						<th>操作</th>
					</tr>
					<s:iterator value="#request.pager.getItems()" var="upage" status="index">
            	  		<tr id="<s:property value="#index.index"/>">
            	   			<td><s:property value="%{#index.getIndex()+1}"/></td>
            				<td>${upage.uname }</td>
            				<td>
            					<s:if test="#upage.sex == 1">男</s:if>
            					<s:else>女</s:else>
            				</td>
            				<td>${upage.email }</td>
            				<!-- <td><a href="userdelete.action?userid=${upage.userid }" name="delete">删除</a></td>-->
            				<td><a onclick="del('<s:property value="#upage.userid"/>','<s:property value="#index.index"/>')">删除</a></td>
            	  		</tr>	
		        	</s:iterator>
				</table>
			</div>
		</form>
		<!--2016-5-28  添加 陈祥 -->
	<!-- 翻页按钮区 -->
	<div class="page">
        <ul>
          	<li><a href="userlists.action?currPage=1">首页</a></li>
          
          	<li>
            	<s:if test="#request.pager.currPage == 1">
            		<a href="userlists.action?currPage=1">上一页</a>
	        	</s:if>
		        <s:else>
			     	<a href="userlists.action?currPage=<s:property value="#request.pager.currPage -1"/>">上一页</a>
		        </s:else>
          	</li>
          
          	<li>
             	<s:if test="#request.pager.currPage == #request.pager.totalPage">
             		<a href="userlists.action?currPage=<s:property value="#request.pager.totalPage"/>">下一页</a>
	         	</s:if>
	         	<s:else>
	            	<a href="userlists.action?currPage=<s:property value="#request.pager.currPage +1"/>">下一页</a>
	         	</s:else>
          	</li>
          
          	<li>
		  		<a href="userlists.action?currPage=<s:property value="#request.pager.totalPage"/>">尾页</a>
          	</li>
        </ul>
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
	function del(userid,index){
		var data = {userid:userid};
		$.ajax({
			type : "POST",
			url : "userdelete",
			data : data,
			dataType : "json",
			success : function(data) {
				var num = eval("("+data+")");
				if(num.success) {
					$("#"+index).remove();
				} else {
					alert("删除用户失败");
				}
			}
		})
	};
</script>
</html>