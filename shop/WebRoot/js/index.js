

//查询精选商品

function betterGoods(){
    	$.post({
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
	    	$.post({
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

	$('#user').mouseover(function() {
		console.log($(this).attr("value"));
	            if($(this).attr("value")==""){
	            	$('#user .dropdown').show();
				}else{
					$('#user .dropdown1').show();
				}
		})
	
	
	$('#user').mouseleave(function(){
		$('#user .dropdown').hide();
		$('#user .dropdown1').hide();
	})
	
	$('#user').click(function(){
		location.href="persionInfo.jsp";
	})
	
	//	购物车
	$('.glyphicon-shopping-cart').mouseover(function() {
		//如果没有登录
		if($('#user').attr("value")==""){
			$.post({
				url:"checkLogin2.do",
				success:function(result){
					var str ="";
					var priceAll = 0;
					var num = 0;
					if(result.length!=0){
						 $.each(result,function(i, value){
							 console.log(value.id);
							 str+="<li>"+
										  "<a>"+
										     "<img src="+value.url+" style='width:16%;height:40px;'/>"+
										  "</a>"+
											"<div style='width:20%; height:20%; display:inline-block; float:left;  margin-left: 20%;' >"+
												"<p style='display: block;'>"+value.goodsName+"</p>"+
												"<p style='display: block;'>价格:￥"+ value.price+"</p>"+
												"<p style='display: block; float:left;'>×"+value.number+
											"</div>"+
											"<span class='glyphicon glyphicon-remove' id='remove' style='float: right;' value="+value.id+"></span>"+
										"</li>"
							       ;
							 priceAll+=Number(value.price)*Number(value.number);
						})
						
						
						var qian = "<h1>总价:￥"+priceAll+".00</h1>"+
						"<a href='shoppingCar.jsp'><button style='font-size:18px; letter-spacing:2px'>去购物车</button></a>"
						$('#shop-down').html(qian)
						$('.dropdown2').show();
					    $('#carts').html(str);
					  
						 $('#shop-top').show();
						 $('#shop-down').show();
						 
				   }else{
					   $('#shop-top').hide();
						 $('#shop-down').hide();
						 $('.dropdown2').show();
						 $('#nogoods').show();
					}
					  $(document).on('click','#remove',function(e){
							  var id = e.target.getAttribute("value");
							  e.target.parentNode.parentNode.removeChild(e.target.parentNode);
							  location.method = "post";
							  location.href = "deleteCart1.do?id="+id;
					    })
				}
			})
		
		}else{
			$.post({
				url:"checkLogin.do",
				success:function(result){
					var str ="";
					var priceAll = 0;
					var num = 0;
					if(result.length!=0){
						 $.each(result,function(i, value){
							 str+="<li>"+
										  "<a>"+
										     "<img src="+value.url+" style='width:16%;height:40px;'/>"+
										  "</a>"+
											"<div style='width:20%; height:20%; display:inline-block; float:left;  margin-left: 20%;' >"+
												"<p style='display: block;'>"+value.goodsName+"</p>"+
												"<p style='display: block;'>价格:￥"+ value.price+"</p>"+
												"<p style='display: block; float:left;'>×"+value.number+
											"</div>"+
											"<span class='glyphicon glyphicon-remove' id='remove' style='float: right;' value="+value.goodsId+"></span>"+
										"</li>"
							       ;
							 priceAll+=Number(value.price)*Number(value.number);
						})
						var qian = "<h1>总价:￥"+priceAll+".00</h1>"+
						"<a href='shoppingCar.jsp'><button style='font-size:18px; letter-spacing:2px'>去购物车</button></a>"
						$('#shop-down').html(qian);
						$('.dropdown2').show();
					    $('#carts').html(str);
					 
						 $('#shop-top').show();
						 $('#shop-down').show();
				   }else{
					   	 $('#shop-top').hide();
						 $('#shop-down').hide();
						 $('.dropdown2').show();
						 $('#nogoods').show();
					}
					  $(document).on('click','#remove',function(e){
						  var goodsId = e.target.getAttribute("value");
						  e.target.parentNode.parentNode.removeChild(e.target.parentNode);
						  location.method = "post";
						  location.href = "deleteCart2.do?goodsId="+goodsId;
					  })
					
				}
			})
		}
	})
	
	$('#carsign').mouseleave(function() {
		$('#allgoods').hide();
		$('#nogoods').hide();
		$('#goshopping').hide();
		$('#shoptop').hide();
		$('#shopdown').hide();
	})
	

	
	//点击去购物车
	$('#goshopping').click(function(){
		 $('#no-goods').remove();
		 $('#goshopping').remove();
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
	

	
