function highlightRows(){
	if(!document.getElementsByTagName) return false;
	var rows=document.getElementsByTagName("tr");
	for(var j=0;j<rows.length;j++){
		rows[j].onmouseover=function(){
			this.style.fontWeight="bold";
			this.style.fontSize="14px";
		}
		rows[j].onmouseout=function(){
			this.style.fontWeight="normal";
			this.style.fontSize="12px";
		}
	}
}
addLoadEvent(highlightRows);