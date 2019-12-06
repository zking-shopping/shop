
var sort = $('#id').attr("value");



(function(){
	$.ajax({
		url:"sort.do",
		data:{
         "page":1,
         "pagesize":2,
         "sort":sort
          },
    success: function(result){
    	var str = ``;
    	  $.each(result, function (i, value) {
			 str += `
		      <div class="col-md-3 goods">
		        <div class="thumbnail">
		          <a value=${value.id} id='intro'>
		              <img src="${value.picture1}" />
		          <a/>
		          <caption>
		            <p class="goods-name">${value.goodsname}</p>
		            <p class="goods-desc">${value.introduction}</p>
		            <p>价格:${value.price}</p>
		            <button class="btn btn-info">加入购物车</button>
		          </caption>
		        </div>
		      </div>
		      `;	
			//每遍历一次就要去添加一次
			
		})
		$('#goodsList').html(str);
    	  for(var i=0;i<20;i++){
		    $("[id=intro]").eq(i).click(function(){
		    	var picid = $(this).attr("value");
		    	location.href = "introduction.do?picid="+picid;
		    })
    	  }
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
			url:"sort.do",
			data:{
	         "page":num,
	         "pagesize":2,
	         "sort":sort,
			},
		
         success:function(result){
			//渲染
        	 var str= ``;
        	 $.each(result, function (i, value) {
    			 str += `
    		      <div class="col-md-3 goods">
    		        <div class="thumbnail">
    		          <a value=${value.id} id='intro'>
    		              <img src="${value.picture1}" />
    		          <a/>
    		          <caption>
    		            <p class="goods-name">${value.goodsname}</p>
    		            <p class="goods-desc">${value.introduction}</p>
    		            <p>价格:${value.price}</p>
    		            <button class="btn btn-info">加入购物车</button>
    		          </caption>
    		        </div>
    		      </div>
    		      `;	
    			//每遍历一次就要去添加一次
    			
    		})
        			//每遍历一次就要去添加一次
        			$('#goodsList').append(str);
        		}
		   
	})
	page = num;
	if(page>1 && page<=5) {
		$('[class=three]').removeClass("three");
		    $('ul').children('li').eq(2).addClass('ignore1');
 			$('ul').children('li').eq(7).addClass('add');
 			$('ul').children('li').eq(8).addClass('ignore2');
 			$('[class=ignore1]').hide();
			$('[class=ignore2]').hide();
			$('[class=add]').hide();
			$('ul').children().eq(3).addClass("three");
			$('ul').children().eq(3).show();
			$('[class=three]').removeClass("three");
			content.eq(0).text(4);
			content.eq(1).text(5);
			content.eq(2).text(6);
			content.eq(3).text(7);
			content.eq(4).text(8);
			if(page==1&&page==2){
				$('[class="current"]').removeClass('current');
				$('ul').children('li').eq(page-1).addClass('current');
			}else{
			   $('[class="current"]').removeClass('current');
  			   $(this).addClass('current');
			}
	} 
		
		if(page > 5) {
    			$('[class=ignore1]').show();
    			$('[class=ignore2]').show();
    			$('[class=add]').show();
    			$('[class=ignore1]').removeClass("ignore1");
    			$('[class=ignore2]').removeClass("ignore2");
    			$('[class=add]').removeClass("add");
    			$('ul').children().eq(3).addClass("three");
    			$('[class=three]').removeClass("three");
    			var content = $('ul').children("#page");
    			content.eq(0).text((page-2).toString());
    			content.eq(1).text((page-1).toString());
    			content.eq(2).text(page.toString());
    			content.eq(3).text((page+1).toString());
    			content.eq(4).text((page+2).toString());
               $('[class="current"]').removeClass('current');
    			$('ul').children("#page").eq(2).addClass('current');
    	    }
		
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
    	url:"sort.do",
    	data:{
    		"page":num,
    		"pagesize":2,
    			"sort":sort,
    	},
        success:function(result){
        	 var str= ``;
        	 $.each(result, function (i, value) {
    			 str += `
    		      <div class="col-md-3 goods">
    		        <div class="thumbnail">
    		          <a value=${value.id} id='intro'>
    		              <img src="${value.picture1}" />
    		          <a/>
    		          <caption>
    		            <p class="goods-name">${value.goodsname}</p>
    		            <p class="goods-desc">${value.introduction}</p>
    		            <p>价格:${value.price}</p>
    		            <button class="btn btn-info">加入购物车</button>
    		          </caption>
    		        </div>
    		      </div>
    		      `;	
    			//每遍历一次就要去添加一次
    			
    		})
        			//每遍历一次就要去添加一次
        			$('#goodsList').append(str);
        		
    		$('[class="current"]').removeClass('current');
    		$('ul').children().eq(page - 1).addClass('current');
    		//当页码数小于3时，回复最初的选择
    		if(page <5) {
    			$('ul').children('li').eq(2).addClass('ignore1');
    			$('ul').children('li').eq(7).addClass('add');
    			$('ul').children('li').eq(8).addClass('ignore2');
    			$('[class="ignore1"]').hide();
    			$('[class="ignore2"]').hide();
    			$('[class="add"]').hide();
    			
    		
    			
//    			$('ul').children().eq(page).addClass('current');
    		}
    		if(page ==5) {
    			$('ul').children('li').eq(2).addClass('ignore1');
    			$('ul').children('li').eq(7).addClass('add');
    			$('ul').children('li').eq(8).addClass('ignore2');
    			$('[class="ignore1"]').hide();
    			$('[class="ignore2"]').hide();
    			$('[class="add"]').hide();
    			$('[class="ignore1"]').removeClass('ignore1');
    			$('[class="ignore2"]').removeClass('ignore2');
    			$('[class="add"]').removeClass('add');
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
	}
//
//
//
//点击下一页
function goNext(){
	page++;
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
	$.ajax({
		url:"sort.do",
		data:{
			"page":num,
			"pagesize":2,
				"sort":sort
		},
	    success:function(result){
	    	 var str= ``;
        	 $.each(result, function (i, value) {
    			 str += `
    		      <div class="col-md-3 goods">
    		        <div class="thumbnail">
    		          <a value=${value.id} id='intro'>
    		              <img src="${value.picture1}" />
    		          <a/>
    		          <caption>
    		            <p class="goods-name">${value.goodsname}</p>
    		            <p class="goods-desc">${value.introduction}</p>
    		            <p>价格:${value.price}</p>
    		            <button class="btn btn-info">加入购物车</button>
    		          </caption>
    		        </div>
    		      </div>
    		      `;	
    			//每遍历一次就要去添加一次
    			
    		})
        			//每遍历一次就要去添加一次
        			$('#goodsList').append(str);
        		
	    		$('[class="current"]').removeClass('current');
    		if(page > 2&&page<6) {
    			$('[class="three"]').removeClass('three');
		    			$('ul').children().eq(page).addClass('current');
		    		}
    		//当页码数大于4时，选择需要改变

//    		$('[class="current"]').removeClass('current');
    		if(page ==6) {
    			$('ul').children('li').eq(2).addClass('ignore1');
    			$('ul').children('li').eq(7).addClass('add');
    			$('ul').children('li').eq(8).addClass('ignore2');
    			$('[class=ignore1]').show();
    			$('[class=ignore2]').show();
    			$('[class=add]').show();
    			$('[class=ignore1]').removeClass("ignore1");
    			$('[class=ignore2]').removeClass("ignore2");
    			$('[class=add]').removeClass("add");
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
    		
    		if(page >6) {
    		
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

//}
////
//点击第一个省略号
$('[class="ignore1"]').click(function() {
	page--;
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
  $.ajax({
  	url:"sort.do",
  	data:{
  		"page":num,
  		"pagesize":2,
  			"sort":sort,
  	},
      success:function(result){
      	 var str= ``;
      	 $.each(result, function (i, value) {
  			 str += `
  		      <div class="col-md-3 goods">
  		        <div class="thumbnail">
  		          <a value=${value.id} id='intro'>
  		              <img src="${value.picture1}" />
  		          <a/>
  		          <caption>
  		            <p class="goods-name">${value.goodsname}</p>
  		            <p class="goods-desc">${value.introduction}</p>
  		            <p>价格:${value.price}</p>
  		            <button class="btn btn-info">加入购物车</button>
  		          </caption>
  		        </div>
  		      </div>
  		      `;	
  			//每遍历一次就要去添加一次
  			
  		})
      			//每遍历一次就要去添加一次
      			$('#goodsList').append(str);
      		
  		$('[class="current"]').removeClass('current');
  		$('ul').children().eq(page - 1).addClass('current');
  		//当页码数小于3时，回复最初的选择
  		if(page <5) {
  			$('ul').children('li').eq(2).addClass('ignore1');
  			$('ul').children('li').eq(7).addClass('add');
  			$('ul').children('li').eq(8).addClass('ignore2');
  			$('[class="ignore1"]').hide();
  			$('[class="ignore2"]').hide();
  			$('[class="add"]').hide();
  			$('[class="current"]').removeClass('current');
  			
  			$('ul').children().eq(page).addClass('current');
  		}
  		if(page ==5) {
  			$('ul').children('li').eq(2).addClass('ignore1');
  			$('ul').children('li').eq(7).addClass('add');
  			$('ul').children('li').eq(8).addClass('ignore2');
  			$('[class="ignore1"]').hide();
  			$('[class="ignore2"]').hide();
  			$('[class="add"]').hide();
  			$('[class="ignore1"]').removeClass('ignore1');
  			$('[class="ignore2"]').removeClass('ignore2');
  			$('[class="add"]').removeClass('add');
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
	alert(page)
	//记录哪一页
	var num = page;
	$('#goodsList .goods').remove();
	//分类ID
	$.ajax({
		url:"sort.do",
		data:{
			"page":num,
			"pagesize":2,
				"sort":sort
		},
	    success:function(result){
	    	 var str= ``;
      	 $.each(result, function (i, value) {
  			 str += `
  		      <div class="col-md-3 goods">
  		        <div class="thumbnail">
  		          <a value=${value.id} id='intro'>
  		              <img src="${value.picture1}" />
  		          <a/>
  		          <caption>
  		            <p class="goods-name">${value.goodsname}</p>
  		            <p class="goods-desc">${value.introduction}</p>
  		            <p>价格:${value.price}</p>
  		            <button class="btn btn-info">加入购物车</button>
  		          </caption>
  		        </div>
  		      </div>
  		      `;	
  			//每遍历一次就要去添加一次
  			
  		})
      			//每遍历一次就要去添加一次
      			$('#goodsList').append(str);
      		
	    		$('[class="current"]').removeClass('current');
  		if(page > 2&&page<6) {
  			$('[class="three"]').removeClass('three');
		    			$('ul').children().eq(page).addClass('current');
		    		} else {
		    			$('ul').children().eq(page - 1).addClass('current');
		    		}
  		//当页码数大于4时，选择需要改变

//  		$('[class="current"]').removeClass('current');
  		if(page > 5) {
  			$('[class=ignore1]').show();
  			$('[class=ignore2]').show();
  			$('[class=add]').show();
  			$('[class=ignore1]').removeClass("ignore1");
  			$('[class=ignore2]').removeClass("ignore2");
  			$('[class=add]').removeClass("add");
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


function searchpage(){
	     var num = parseInt($('#find').val());
	     $('#goodsList .goods').remove();
		//分类ID
		$.ajax({
			url:"sort.do",
			data:{
				page:num,
				pagesize:2,
				sort:sort,
			},
			success:function(result){
				 var str= ``;
		    	 $.each(result, function (i, value) {
					 str += `
				      <div class="col-md-3 goods">
				        <div class="thumbnail">
				          <a value=${value.id} id='intro'>
				              <img src="${value.picture1}" />
				          <a/>
				          <caption>
				            <p class="goods-name">${value.goodsname}</p>
				            <p class="goods-desc">${value.introduction}</p>
				            <p>价格:${value.price}</p>
				            <button class="btn btn-info">加入购物车</button>
				          </caption>
				        </div>
				      </div>
				      `;	
					//每遍历一次就要去添加一次
					
				})
		    			//每遍历一次就要去添加一次
		    			$('#goodsList').append(str);
		    		
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

