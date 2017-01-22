<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<link rel="stylesheet" type="text/css" href="css/index.css" /> 
<script type="text/javascript">
function showMsg() {
	var msg = '${message}';
	if(msg != null&&msg != ""){
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
			<script type="text/javascript" src="js/dropdown.js"></script>
			</s:else>
			<!--userInfo end-->
		</div>
	</div>
	
	<!-- 登录弹窗 -->
	<div class="fullbg"></div>
	<div class="pop_up">
		<p class="close"><a onclick="closeAll()">关闭</a></p>
		<form action="userlogin.action" method="post" id='login_form'>
			<div class="user">
				<label for="username">用户名:</label>
				<input type="text" name="uname" id="username" required="required" pattern="[A-Za-z]{3,}" placeholder="首位字母且3位以上"></input>
			</div>
			<div class="user">
				<label for="password">密&nbsp;&nbsp;&nbsp;码:</label>
				<input type="password" name="upass" id="password" required="required" pattern="[A-Za-z0-9]{6,}" placeholder="由6个以上字母数字组成"></input>
			</div>
			<div class="user">
				<label for="code">验证码:</label>
				<input type="text" name="code" id="code" placeholder="填写正确验证码"></input>
				<input type="button" name="" id="code_text" onclick="createCode()"></input>
				<span class="prompt" id="pro">点击切换验证码</span>
			</div>
			<input type="button" id="submit_login" value="登录" onclick="return checkCode()" class="pop_up_submit"></input>
			<input type="reset" id="cancel" value="取消" onclick="closeAll()" class="pop_up_submit"></input>
			<span class="forget"><a href="#">忘记密码?</a></span>
		</form>
	</div>
	
	
	
	
	<!--content start-->
	<div id="content">
		<ol>
			<s:iterator value="#request.pager.getItems()" var="upage" status="index">
				<li>				
					<div class="num"></div>
	
					<!--detail start-->
					<div class="detail">
						<p class="document_name">
							<a href="fileopen.action?fid=${upage.fid }"><s:property value="#upage.title"/></a>
						</p>
						<p class="classify"><s:property value="#upage.typename"/></p>
						<p class="document_author"><s:property value="#upage.username"/></p>
						<p class="time"><s:property value="#upage.time"/></p>
					</div>
					<!--detail end-->
	
					<!--operation start-->
					<div class="choice">
						<ul>
							<li><a onclick="praise('<s:property value="#upage.fid"/>','<s:property value="#index.index"/>')">
								点赞(<span class="praiseNO" id="praiseNum<s:property value="#index.index"/>"><s:property value="#upage.nice"/></span>)</a></li>
							<li><a onclick="bad('<s:property value="#upage.fid"/>','<s:property value="#index.index"/>')">
								差评(<span class="praiseNO" id="badNum<s:property value="#index.index"/>"><s:property value="#upage.bad"/></span></a>)</li>
							<li><a onclick="collect('<s:property value="#upage.fid"/>','<s:property value="#index.index"/>')">
								<span class="praiseNO" id="collectArea<s:property value="#index.index"/>">收藏</span></a></li>
							<li><a href="filedownload.action?fid=${upage.fid }">下载</a></li>
						</ul>
					</div>
					<!--operation end-->				
				</li>
		    </s:iterator>
			
		</ol>
		<script type="text/javascript" src="js/click.js"></script>
		<!--2016-5-28  添加 陈祥 -->
		<!-- 翻页按钮区 -->
		<div class="page">
	        <ul>
	          	<li><a href="filelists.action?currPage=1">首页</a></li>
	          
	          	<li>
	            	<s:if test="#request.pager.currPage == 1">
	            		<a href="filelists.action?currPage=1">上一页</a>
		        	</s:if>
			        <s:else>
				     	<a href="filelists.action?currPage=<s:property value="#request.pager.currPage -1"/>">上一页</a>
			        </s:else>
	          	</li>
	          
	          	<li>
	             	<s:if test="#request.pager.currPage == #request.pager.totalPage">
	             		<a href="filelists.action?currPage=<s:property value="#request.pager.totalPage"/>">下一页</a>
		         	</s:if>
		         	<s:else>
		            	<a href="filelists.action?currPage=<s:property value="#request.pager.currPage +1"/>">下一页</a>
		         	</s:else>
	          	</li>
	          
	          	<li>
			  		<a href="filelists.action?currPage=<s:property value="#request.pager.totalPage"/>">尾页</a>
	          	</li>
	        </ul>
	    </div>
	    <div id="backtoTop">
			<p>返回顶部</p>
		</div>
	</div>
	
	<script type="text/javascript" src="JQuery/jquery-2.1.4/jquery.js"></script>
	<script type="text/javascript" src="js/pop_up.js"></script>
	<script type="text/javascript" src="js/code.js"></script>
	<script type="text/javascript" src="js/scroll.js"></script>
	<!--footer start-->
	<div id="footer"><p>Copyright &copy;2016.All Rights Reserved.</p></div>
</div>


</body>
 
<script type="text/javascript">
function praise(niceId,index){
	var data= {niceId:niceId};
	$.ajax({
		type : "POST",
		url : "filepraise.action",
		data : data,
		dataType : "json",
		success : function(data){
			var num = eval("("+data+")");
			if(num.success){
				$("#praiseNum"+index).html(num.niceNum);
			}else{
				alert("点赞失败");
			}
		}
	})
};

function bad(badId,index){
	var data= {badId:badId};
	$.ajax({
		type : "POST",
		url : "filebad.action",
		data : data,
		dataType : "json",
		success : function(data){
			var num = eval("("+data+")");
			if(num.success){
				$("#badNum"+index).html(num.badNum);
			}else{
				alert("差评失败");
			}
		}
	})
};

function collect(fid,index){
	var data = {fid:fid};
	$.ajax({
		type : "POST",
		url : "collectionadd.action",
		data : data,
		dataType : "json",
		success : function(data) {
			var num = eval("("+data+")");
			if(num.success) {
				$("#collectArea"+index).html("已收藏");
				alert(num.message);
			} else {
				alert(num.message);
			}
		}
	})
};

</script>
</html>