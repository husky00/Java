var code; //定义全局变量code
//创建验证码
function createCode(){
	code = ''; //验证码初始化
	var codeLen = 4; //验证码长度
	var codetext = document.getElementById("code_text"); //获取验证码对象
	var codeArr = new Array(0,1,2,3,4,5,6,7,8,9,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R',  
                 'S','T','U','V','W','X','Y','Z');//随机数  
	for(var i = 0;i<codeLen;i++){
		var random = Math.floor(Math.random()*36);
		code += codeArr[random];
	}
	codetext.value = code;
}

//校验验证码
function checkCode(){
	var inputCode = document.getElementById('code').value.toUpperCase(); //获取验证码并转换成大写
	var pro = document.getElementById('pro');
	if(inputCode.length <= 0){ //验证码不为空
		pro.innerHTML = "验证码不能为空";
		pro.style.color = 'red';
	}else if(inputCode != code){ //校验
		pro.innerHTML = '验证码错误' ;
		pro.style.color = 'red';
		createCode(); //生成验证码
		document.getElementById('code').value = ''; //输入验证码部分清空
	}else{
		pro.innerHTML = "验证码正确";
		document.getElementById('login_form').submit();
	}
}

