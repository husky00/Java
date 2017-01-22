$('.choice ul li').each().bind('click',function(praiseCount){
	$.ajax({
		url: 'filemodify.action',
		type: 'POST',
		data: {
			'nice': nice,
		},
		dataType: 'JSON',
		success: function(d){
			if(d.success){
				var strategyPraiseNo = ParseInt(nice)+1;
				$('.praiseNO').html(strategyPraiseNo)
			}
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("无法连接服务器:" + textStatus);
        }
	});
})