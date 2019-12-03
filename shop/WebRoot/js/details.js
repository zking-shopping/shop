(function(){
	var img = $('.buy_img').attr("value");
//	alert(img);
		$('.buy>.content>.buy_img>.img_box>img').attr('src', img);
		$('.buy>.content>.buy_img>ul>li:first>img').attr('src', img);
		$('.buy>.content>.big').css({
			'background' : 'url(' + img + ')' ,
			'background-size' : '1160px 840px','background-repeat' : 'no-repeat',
		});
		func();
	})()
	function func(){
		// 定义滑块与图片大小比例
		var proportion = 2;
		$('.img_box').mouseenter(function(){
			$(this).mousemove(function(event){
				var x = parseInt(event.clientX - $(this).offset().left - $('.slide').outerWidth()/2);
				var y = parseInt(event.clientY - $(this).offset().top - $('.slide').outerHeight()/2) + $(document).scrollTop();
				if(x <= 0){x = 0};
				if(y <= 0){y = 0};
				if(x >= $('.img_box').width() - $('.slide').outerWidth()){
					x = $('.img_box').width() - $('.slide').outerWidth();
				}
				if(y >= $('.img_box').height() - $('.slide').outerHeight()){
					y = $('.img_box').height() - $('.slide').outerHeight();
				}
				// 设置滑块位置
				$('.slide').css({left : x , top : y});
				
				var str = -x*proportion + 'px ' + -y*proportion + 'px';
				$('.big').css('backgroundPosition', str);
			});
		});
		$('.img_box').hover(function(){
			if($('.slide').is(':animated')) return;
			$('.slide').fadeIn();
			$('.big').fadeIn();
		}, function(){
			if($('.slide').is(':animated')) return;
			$('.slide').fadeOut();
			$('.big').fadeOut();
		})
		// 点击图片改变大图与放大图
		$('.buy>.content>.buy_img>ul>li>img').each(function(i){
			$(this).click(function(){
				var address = $(this).attr('src');
				$('.buy>.content>.buy_img>.img_box>img').attr('src', address);
				$('.buy>.content>.buy_img>ul>li').eq(i).addClass('selected').siblings().removeClass('selected');
				$('.buy>.content>.big').css({'background' : 'url(' + address + ')' ,'background-size' : '1160px 840px','background-repeat' : 'no-repeat'});
			});
		});
		
		// 选择套餐
		$('.buy .content .buy_standard .types .type').each(function(i){
			$(this).click(function(){
				$(this).addClass('type_selected').siblings().removeClass('type_selected');
				$('.buy .content .buy_standard .buy_module .setMeal').html('套餐'+(i+1));
			});
		})
	
}

//点击立即购买
    $(".buy_now").click(function(){
    	var id = $(this).attr("value");
  	  var type_selected = $(".type_selected").html();
  	  location.href = "buy_now.do?id="+id+"&type_selected="+type_selected;
    })






//点击加入购物车
     $(".buy_intocar").click(function(){
    	 var id = $(this).attr("value");
    	    var type_selected = $(".type_selected").html();
    	    $.get('addToShoppingCar.do',{'id':id,'type_selected':type_selected});
    })
    
 //点击深入了解
    $("[id=introduce]").click(function(){
    	 var id = $(this).attr("value");
    	 location.href  = "introduction.do?picid="+id;
    })