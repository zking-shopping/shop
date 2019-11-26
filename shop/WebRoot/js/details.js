(function(){
	get();
	function get(){
		// var goodsId = getUrlVal('goods_id');
		var goodsId = '34396';
		$.get('http://www.wjian.top/shop/api_goods.php', {
			goods_id : goodsId,
		}, function(result){
			var res = JSON.parse(result);
			var goods = res.data[0];
			var str = `
				<h1>${goods.goods_name}</h1>
				<div class="desc">
					<p>${goods.goods_desc}</p>
					<a href="introduction.html">深入了解产品></a>
				</div>
				<h2>1.规格</h2>
				<div class="types">
					<div class="type type_selected">套餐一</div>
					<div class="type">套餐二</div>
					<div class="type">套餐三</div>
				</div>
				<div class="buy_module">
					<p>您选择了以下产品</p>
					<p class="setMeal">套餐一</p>
					<p class="price">￥${goods.price}</p>
					<a class="buy_now" href="shoppingCar.html">立即购买</a>
					<a class="buy_intocar" href="shoppingCar.html">加入购物车</a>
				</div>
			`;
			$('.buy_standard').html(str);
			$('.buy>.content>.buy_img>.img_box>img').attr('src', goods.goods_thumb);
			$('.buy>.content>.buy_img>ul>li:first>img').attr('src', goods.goods_thumb);
			$('.buy>.content>.big').css({
				'background' : 'url(' + goods.goods_thumb + ')' ,
				'background-size' : '1160px 840px','background-repeat' : 'no-repeat',
			});
			func();
		});
	}
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
})();