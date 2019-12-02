

(function(){
	$(document).mousewheel(function(){
		var scrollH = $(document).scrollTop();
		if(scrollH > 1){
			$('.introduce').addClass('introduce_hover');
		}else{
			$('.introduce').removeClass('introduce_hover');
		}
	});
})();

$('#buy').click(function(){
	var id = $(this).attr("value");
	location.href = "details.do?id="+id;
})

