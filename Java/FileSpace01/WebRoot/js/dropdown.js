window.onload = function(){
	var divselect = document.getElementById('divselect');
	var personalName = document.getElementById('personalName');
	var drop = document.getElementById('drop');
	var lis = drop.getElementsByTagName('li');
	personalName.onmouseover = function(event){
		drop.style.display = "block";
		//阻止事件冒泡
		event=event || window.event;
		if(event.stopPropagation){ 
			//FF阻止方法
			event.stopPropagation();
		}else{ 
			//IE阻止方法
			event.cancelBubble=true;
		}

	}
	for(var i=0; i<lis.length;i++){
		lis[i].onmouseover = function(){
			this.style.background = '#e43532';
		}
		lis[i].onmouseout = function(){
			this.style.background = '#ccc';
		}
	}
	window.onclick = function(){
		drop.style.display = 'none';
	}
}
