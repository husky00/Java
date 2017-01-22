$(window).on("scroll",function(){
	var scrollTop = $(window).scrollTop();
	console.log(scrollTop);
	if(scrollTop > 200){
		$("#backtoTop").show();
		$("#backtoTop").css({
			"position": "fixed",
			"left": ($(window).width()/2+512) + 'px',
		});
	}else{
		$("#backtoTop").hide();
	}
	
	$("#backtoTop").on("click",function(){
		$(window).scrollTop(0);
	})
})