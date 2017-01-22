function pop_upShow(){
	var bodyheight = $("body").height();
	var bodywidth = $("body").width();
	var winheight = $(window).height();
	if(winheight>bodyheight){
		$(".fullbg").css({
			width: bodywidth,
			height: winheight,
			display: "block"
		})
		$(".pop_up").show();
	}else{
		$(".fullbg").css({
			width: bodywidth,
			height: bodyheight,
			display: "block"
		})
		$(".pop_up").show();
	}
	createCode();
}

function collect_pop_upShow(){
	var bodyheight = $("body").height();
	var bodywidth = $("body").width();
	var winheight = $(window).height();
	if(winheight>bodyheight){
		$(".collect_fullbg").css({
			width: bodywidth,
			height: winheight,
			display: "block"
		})
		$(".collect_pop_up").show();
	}else{
		$(".collect_fullbg").css({
			width: bodywidth,
			height: bodyheight,
			display: "block"
		})
		$(".collect_pop_up").show();
	}
}

function closeAll(){
	$(".fullbg,.pop_up,.collect_fullbg,.collect_pop_up").hide();
}