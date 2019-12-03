	 //查询精选商品
function betterGoods(){
    	$.ajax({
    			url:"bettergoods.do",
    			data:{
                 
                  },
          success: function(result){
        	  var str = "";
        	  $.each(result, function (i, value) {
        		str +="<li class='col-4-lg col-3-md col-6-sm col-12-xs' style='height:380px'>"+
         		          "  <div class='thumbnail' style='height:360px;'>"+
         		          "<a  value="+value.id+" id='id'>"+
         		          "<img src="+value.picture1+"  />"+
         		          "</a>"+
         		          "<p >"+value.goodsname+"</p>"+
         		          "<p class='desc' style='color:#d6d6d6; font-size:14px'>商品介绍:"+value.introduction+"</p>"+
         		          "<p style='color:#1989e9;'>价格:"+value.price+"</p>"+
         		          "</a>"+"</div>"+"</li>";
         	 })
         	   $('.bettergoods').html(str);
         	  for(var i=0;i<8;i++){
				    $("[id=id]").eq(i).click(function(){
				    	var picid = $(this).attr("value");
				    	location.href = "introduction.do?picid="+picid;
				    })
		    	  }
    	   }
    	})
	 }

  
    
	 function cardList(sort){
	    	$.ajax({
	    		url:"mintype.do",
	    		data:{
	    			"sort": sort,
	    		},
	    		success:function(result){
	    			var len = result.length;
	    			var str = "";
	    			$('.nav-cateCard .card-list').eq(parseInt(sort)-1).empty()
	    			 $.each(result,function(i,value){
	        	 	    str += "<li class='col-4-lg col-3-md col-6-sm col-12-xs' style='width:180px;height:180px'>"+
	                              "<a  value="+value.id+" id='id' >"+
	                                      "<img src="+value.picture1+" alt='' />"+
	                                "</a>"+
	                                      "<p>"+value.goodsname+"</p>"+
	                                      "<p>价格:"+value.price+"</p>"+
	                           "</li>"
	                           ;
	            	 })
	            	 $('.nav-cateCard .card-list').eq(parseInt(sort)-1).html(str);
	    			 for(var i=0;i<len;i++){
	    				    $("[id=id]").eq(i).click(function(){
	    				    	var picid = $(this).attr("value");
	    				    	location.href = "introduction.do?picid="+picid;
	    				    })
	    		      }
	    		}
	    	})

	    };
	 
	 betterGoods();
	 
	 //点击每一张图片去详情页
	 

	//轮播图中图片的个数
	var liL = $('.banner>ul li').length;
	
	//单个图片的宽度
	var liWidth = document.documentElement.clientWidth;
	
	//信号量
	var n = 0;
	var x = 0;
	var flag = true;
	
	//购物车商标添加货物
//	var str = `
//		                   <li>
//												    	<a href="introduction.html">
//												       <img src="img/chazuo1.jpg" />
//											        </a>
//											      <div style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
//													  	<p style="display: block;">万能插座</p>
//													  	<p style="display: block;">价格:￥135.00</p>
//													  </div>
//													<span class="glyphicon glyphicon-remove" style="float: right;"></span>
//								        </li>
//							`
//	
	//定时器
	var timer;
	
	
	//	商品列表
	
	$('#myNav>li>a').mouseover(function() {
		$(this).parent().children().eq(1).show();
			    	var sort = $(this).attr("value");
			    	cardList(sort);
	})
	
	$('#myNav>li').mouseleave(function() {
		     $('.nav-cateCard .card-list').empty();
		 
	})
	
	//	登录注册
	
	$('.glyphicon-user').mouseover(function() {
		$('.glyphicon-user .dropdown').show();
	})
	
	$('.glyphicon-user').mouseleave(function() {
		$('.glyphicon-user .dropdown').hide();
	})
	
	//	购物车
	$('#shopsign').mouseover(function() {
		$('#allgoods').show();
	})
	
	$('#shopsign').mouseleave(function() {
		$('#allgoods').hide();
	})
	
	$('#shop-top li .glyphicon-remove').click(function() {
		if($('#shop-top>ul>li').length != 0) {
			$(this).parent().remove();
		}
	
		if($('#shop-top>ul>li').length == 0) {
			$('#shop-down').remove();
		}
	})
	
	//点击去购物车
	$('#goshopping').click(function(){
		 $('#no-goods').remove();
		 $('#goshopping').remove();
		 $('#allgoods').append(str);
		 $('#allgoods #shop-down').show();
	})
	
	
	//下一张
	$('.next').click(rightBtn);
	
	function rightBtn() {
		if(flag == false) {
			return;
		}
		flag = false;
		n++;
		if(n > liL - 1) {
			n = 0;
		};
	
		//		图片显示
		$('.banner ul li').eq(n).fadeIn().siblings('li').fadeOut();
	
		//小圆点
		x++;
		if(x > 3) {
			x = 0
		};
		//当前等于x的变粉，其它变灰
		$('.number span').eq(x).css('background', '#b8b8b8').siblings('span').css('background', '#666');
		flag = true;
	}
	
	//	  上一张
	$('.prev').click(function() {
		if(flag == false) {
			return;
		}
		flag = false;
		n--;
		if(n < 0) {
			n = liL - 1;
		};
	
		//		图片显示
		$('.banner ul li').eq(n).fadeIn().siblings('li').fadeOut();
	
		//小圆点
		x--;
		if(x < 0) {
			x = 3;
		};
		//当前等于x的变粉，其它变灰
		$('.number span').eq(x).css('background', '#b8b8b8').siblings('span').css('background', '#666');
	
		flag = true;
	
	});
	
	//点击小圆点
	$('.number span').each(function(i) {
		if(flag == false) {
			return;
		}
		flag = false;
	
		//批量绑定事件
		$(this).click(function() {
			//联动
			n = i;
			x = i;
			$('.banner ul li').eq(n).fadeIn().siblings('li').fadeOut();
			//当前等于i的变粉，其它变灰
			$('.number span').eq(x).css('background', '#b8b8b8').siblings('span').css('background', '#666');
		});
		flag = true;
	});
	
	//自动轮播
	timer = setInterval(rightBtn, 2000);
	
	//鼠标移入自动轮播停止
	$('.banner').mouseover(function() {
		clearInterval(timer);
		$('.banner a').show();
	})
	
	//鼠标移开 自动轮播
	$('.banner').mouseleave(function() {
		$('.banner a.prev').hide();
		$('.banner a.next').hide();
		timer = setInterval(rightBtn, 2000);
	})
	
	//点击每一种分类，发送一个种类的类型，存到request中
	$('#sort1').click(function(){
		
		location.href="sort.jsp?sort=1";
	})
	$('#sort2').click(function(){
		location.href="sort.jsp?sort=2";
	})
	$('#sort3').click(function(){
		location.href="sort.jsp?sort=3";
	})
	$('#sort4').click(function(){
	   location.href="sort.jsp?sort=4";
	})
	$('#sort5').click(function(){
		location.href="sort.jsp?sort=5";
	})
	$('#sort6').click(function(){
		location.href="sort.jsp?sort=6";
	})
	$('#sort7').click(function(){
		location.href="sort.jsp?sort=7";
	})
