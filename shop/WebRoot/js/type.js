(function() {
	$.ajax({
		url:"type.do",
		data:{
         "page":1,
         "pagesize":20,
          },
    success: function(result){
	//渲染
		for(var i = 0; i < 20; i++) {
		
			var str = `
      <div class="col-md-3 goods">
        <div class="thumbnail">
          <a href="introduction.do">
              <img src=${result.time} />
          <a/>
          <caption>
            <p class="goods-name">${result.goodsName}</p>
            <p class="goods-desc">${result.introduction}</p>
            <p>价格:￥125.00;</p>
            <p><span class="glyphicon  glyphicon-star"></span>125</p>
            <button class="btn btn-info">加入购物车</button>
          </caption>
        </div>
      </div>
      `;	
			//每遍历一次就要去添加一次
			$('#goodsList').append(str);
		};
   }
});
		
})();

var page = 1;
$('li.current').attr('background', 'oranged');
//点击li确定去哪一页
$('li').click(function() {
	//记录哪一页
	var num = parseInt($(this).children().eq(0).context.innerText);

	$('#goodsList .goods').remove();

	
		//分类ID
		$.ajax({
			url:"type.do",
			data:{
	         "page":num,
	         "pagesize":20,
			},
		
         success:function(result){
			//渲染
        		for(var i = 0; i < 20; i++) {
        			
        			var str = `
              <div class="col-md-3 goods">
                <div class="thumbnail">
                  <a href="introduction.do"><img src=${result.time} /></a>
                  <caption>
                    <p class="goods-name">${result.goodsName}</p>
                    <p class="goods-desc">${result.introduction}</p>
                    <p>价格:￥125.00;</p>
                    <p><span class="glyphicon  glyphicon-star"></span>125</p>
                    <button class="btn btn-info">加入购物车</button>
                  </caption>
                </div>
              </div>
              `;
        			//每遍历一次就要去添加一次
        			$('#goodsList').append(str);
        		};
		   }

	})
	$('[class="current"]').removeClass('current');
	$(this).addClass('current');
})

//点击每一张图片去详情页
$('a').click(function(){
	var src = $(this).attr("src");
	window.location.href="introduction.do?src="+src;
})


//
////点击上一页
function goPrev() {
	page--;
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
    $.ajax({
    	url:"type.do",
    	data:{
    		"page":"2",
    		"pagesize":"20"
    	},
        success:function(result){
        	for(var i = 0; i < 20; i++) {
        		
    			var str = `
          <div class="col-md-3 goods">
            <div class="thumbnail">
              <a href="introduction.do"><img src=${result.time} /></a>
              <caption>
                <p class="goods-name">${result.goodsName}</p>
                <p class="goods-desc">${result.introduction}</p>
                <p>价格:￥125.00;</p>
                <p><span class="glyphicon  glyphicon-star"></span>125</p>
                <button class="btn btn-info">加入购物车</button>
              </caption>
            </div>
          </div>
          `;
    			//每遍历一次就要去添加一次
    			$('#goodsList').append(str);
    		};
    		$('[class="current"]').removeClass('current');
    		$('ul').children().eq(page - 1).addClass('current');
    		//当页码数小于3时，回复最初的选择
    		if(page < 6) {
    			$('ul').children('li').eq(2).addClass('ignore1');
    			$('ul').children('li').eq(7).addClass('add');
    			$('ul').children('li').eq(8).addClass('ignore2');
    			$('[class="ignore1"]').hide();
    			$('[class="ignore2"]').hide();
    			$('[class="add"]').hide();
    			var content = $('ul').children("#page");
    			var a;
    			$('[class="current"]').removeClass('current');
    			content.each(function() {
    				a = parseInt($(this).text());
    				a = a - 1;
    				$(this).text(a.toString());

    			})
    			$('ul').children("#page").eq(2).addClass('current');
    		}
    		alert(page);
    		if(page > 5) {

    			var content = $('ul').children("#page");
    			var a;
    			$('[class="current"]').removeClass('current');
    			content.each(function() {
    				a = parseInt($(this).text());
    				a = a - 1;
    				$(this).text(a.toString());

    			})
    			$('ul').children("#page").eq(2).addClass('current');
    		}
        }
    })
	}



//点击下一页
function goNext(){
	page++;
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
	$.ajax({
		url:"type.do",
		data:{
			"page":"5",
			"pagesize":"20"
		},
	    success:function(result){
            for(var i = 0; i < 20; i++) {
        		
    			var str = `
			          <div class="col-md-3 goods">
			            <div class="thumbnail">
			              <a href="introduction.do"><img src=${result.time} /></a>
			              <caption>
			                <p class="goods-name">${result.goodsName}</p>
			                <p class="goods-desc">${result.introduction}</p>
			                <p>价格:￥125.00;</p>
			                <p><span class="glyphicon  glyphicon-star"></span>125</p>
			                <button class="btn btn-info">加入购物车</button>
			              </caption>
			            </div>
			          </div>
			          `;
    			//每遍历一次就要去添加一次
	    			$('#goodsList').append(str);
	    		};
	    		$('[class="current"]').removeClass('current');
    		if(page > 2&&page<6) {
		    			$('ul').children().eq(page).addClass('current');
		    		} else {
		    			$('ul').children().eq(page - 1).addClass('current');
		    		}
    		//当页码数大于4时，选择需要改变

//    		$('[class="current"]').removeClass('current');
    		if(page > 5) {
    			$('[class="ignore1"]').show();
    			$('[class="ignore2"]').show();
    			$('[class="add"]').show();
    			$('[class="ignore1"]').removeClass("ignore1");
    			$('[class="ignore2"]').removeClass("ignore2");
    			$('[class="add"]').removeClass("add");
    			var content = $('ul').children("#page");
    			var a;

    			content.each(function(){
    				a = parseInt($(this).text());
    				a = a + 1;
    				$(this).text(a.toString());

    			})
               $('[class="current"]').removeClass('current');
    			$('ul').children("#page").eq(2).addClass('current');
    	    }
    	 
	    }
	})
}
//
//点击第一个省略号
$('[class="ignore1"]').click(function() {

       page--;
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
       $.ajax({
    	   url:"type.do",
    	   data:{
    		   "page":page,
    		   "pagesize":20
    	   },
    	   success:function(result){
    		   for(var i = 0; i < 20; i++) {
           		
       			var str = `
   			          <div class="col-md-3 goods">
   			            <div class="thumbnail">
   			              <a href="introduction.do"><img src=${result.time} /></a>
   			              <caption>
   			                <p class="goods-name">${result.goodsName}</p>
   			                <p class="goods-desc">${result.introduction}</p>
   			                <p>价格:￥125.00;</p>
   			                <p><span class="glyphicon  glyphicon-star"></span>125</p>
   			                <button class="btn btn-info">加入购物车</button>
   			              </caption>
   			            </div>
   			          </div>
   			          `;
       			//每遍历一次就要去添加一次
   	    			$('#goodsList').append(str);
   	    		};
   	    		$('[class="current"]').removeClass('current');
   	 		$('ul').children().eq(page - 1).addClass('current');
   	 		//当页码数小于3时，回复最初的选择
   	 		if(page < 6) {
   	 			$('ul').children('li').eq(2).addClass('ignore1');
   	 			$('ul').children('li').eq(7).addClass('add');
   	 			$('ul').children('li').eq(8).addClass('ignore2');
   	 			$('[class="ignore1"]').hide();
   	 			$('[class="ignore2"]').hide();
   	 			$('[class="add"]').hide();
   	 			var content = $('ul').children("#page");
   	 			var a;
   	 			$('[class="current"]').removeClass('current');
   	 			content.each(function() {
   	 				a = parseInt($(this).text());
   	 				a = a - 1;
   	 				$(this).text(a.toString());

   	 			})
   	 			$('ul').children("#page").eq(2).addClass('current');
   	 		}
   	 		if(page > 5) {

   	 			var content = $('ul').children("#page");
   	 			var a;
   	 			$('[class="current"]').removeClass('current');
   	 			content.each(function() {
   	 				a = parseInt($(this).text());
   	 				a = a - 1;
   	 				$(this).text(a.toString());

   	 			})
   	 			$('ul').children("#page").eq(2).addClass('current');
   	 		}
    	   }
       })
})

//点击第二个省略号
$('[class="ignore2"]').click(function() {

     
	page++;

	//记录哪一页
	var num = page;

	$('#goodsList .goods').remove();

	//分类ID
	 $.ajax({
  	   url:"type.do",
  	   data:{
  		   "page":page,
  		   "pagesize":20
  	   },
  	   success:function(result){
  		   for(var i = 0; i < 20; i++) {
         		
     			var str = `
 			          <div class="col-md-3 goods">
 			            <div class="thumbnail">
 			              <a href="introduction.do"><img src=${result.time} /></a>
 			              <caption>
 			                <p class="goods-name">${result.goodsName}</p>
 			                <p class="goods-desc">${result.introduction}</p>
 			                <p>价格:￥125.00;</p>
 			                <p><span class="glyphicon  glyphicon-star"></span>125</p>
 			                <button class="btn btn-info">加入购物车</button>
 			              </caption>
 			            </div>
 			          </div>
 			          `;
     			//每遍历一次就要去添加一次
 	    			$('#goodsList').append(str);
 	    		};
		
		$('[class="current"]').removeClass('current');
		if(page > 2&&page<6) {
			$('ul').children().eq(page).addClass('current');
		} else {
			$('ul').children().eq(page - 1).addClass('current');
		}
		//当页码数大于4时，选择需要改变

//		$('[class="current"]').removeClass('current');
		if(page > 5) {
			$('[class="ignore1"]').show();
			$('[class="ignore2"]').show();
			$('[class="add"]').show();
			$('[class="ignore1"]').removeClass("ignore1");
			$('[class="ignore2"]').removeClass("ignore2");
			$('[class="add"]').removeClass("add");
			var content = $('ul').children("#page");
			var a;

			content.each(function(){
				a = parseInt($(this).text());
				a = a + 1;
				$(this).text(a.toString());

			})
           $('[class="current"]').removeClass('current');
			$('ul').children("#page").eq(2).addClass('current');
	    }
    }
   })
})

//
function searchpage(obj){
	     var num = parseInt($('#find').val());
	     $('#goodsList .goods').remove();
		//分类ID
		$.ajax({
			url:"type.do",
			data:{
				"page":page,
				"pagesize":20
			},
			success:function(result){
				  for(var i = 0; i < 20; i++) {
		         		
		     			var str = `
		 			          <div class="col-md-3 goods">
		 			            <div class="thumbnail">
		 			              <a href="introduction.do"><img src=${result.time} /></a>
		 			              <caption>
		 			                <p class="goods-name">${result.goodsName}</p>
		 			                <p class="goods-desc">${result.introduction}</p>
		 			                <p>价格:￥125.00;</p>
		 			                <p><span class="glyphicon  glyphicon-star"></span>125</p>
		 			                <button class="btn btn-info">加入购物车</button>
		 			              </caption>
		 			            </div>
		 			          </div>
		 			          `;
		     			//每遍历一次就要去添加一次
		 	    			$('#goodsList').append(str);
		 	    		};
				page = num;
				//选择的页面大于4，选择方式改变
				if(num > 3) {
						$('ul').children('li').eq(2).addClass('ignore1');
				$('ul').children('li').eq(7).addClass('add');
				$('ul').children('li').eq(8).addClass('ignore2');
					$('[class="ignore1"]').show();
					$('[class="ignore2"]').show();
					$('[class="add"]').show();
					$('[class="ignore1"]').removeClass("ignore1");
					$('[class="ignore2"]').removeClass("ignore2");
					$('[class="add"]').removeClass("add");
					$('ul').children("#page").eq(0).text((page-2).toString());
				    $('ul').children("#page").eq(1).text((page-1).toString());
				    $('ul').children("#page").eq(2).text((page).toString());
				    $('ul').children("#page").eq(3).text((page+1).toString());
				    $('ul').children("#page").eq(4).text((page+2).toString());
				}

				if(page < 3) {
					page = $('#find').
					$('ul').children('li').eq(2).addClass('ignore1');
					$('ul').children('li').eq(7).addClass('add');
					$('ul').children('li').eq(8).addClass('ignore2');
					$('[class="ignore1"]').hide();
					$('[class="ignore2"]').hide();
					$('[class="add"]').hide();
					var content = $('ul').children("#page");
					var a = 2;
					
					$('[class="current"]').removeClass('current');
					content.each(function() {
						a = a + 1;
						$(this).text(a.toString());

					})
					$('ul').children("#page").eq(2).addClass('current');
				
				}
				$('[class="current"]').removeClass('current');
				 $('ul').children("#page").eq(2).addClass('current');
			}
		})
			
			
			
}
