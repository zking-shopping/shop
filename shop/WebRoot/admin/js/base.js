/**
 * 
 */
	// 点击h3,展开h3兄弟div,隐藏h3堂兄弟div
	$('.left>ul>li>h3').each(function(){
		$(this).click(function(){
			$(this).css('background-color', 'red').siblings('.title').slideDown().parent('li').siblings('li').children('.title').slideUp().siblings('h3').css('background-color', 'blue');
		});
	});
	var size = 24;
	$('.enlarge').click(function(){
		if(size > 32){
			size = 32;
		}
		size+=2;
		$('.right').css('font-size', size+'px');
	});
	$('.narrow').click(function(){
		if(size < 16){
			size = 16;
		}
		size-=2;
		$('.right').css('font-size', size+'px');
	});
	$('.normal').click(function(){
		size = 24;
		$('.right').css('font-size', size+'px');
	});