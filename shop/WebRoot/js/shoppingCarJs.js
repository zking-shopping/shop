// JavaScript Document

productsList(1,showMyProducts);

function productsList(page, showMyProducts){
	
    $.ajaxSettings.async = false;
    $.get('shoppingCar.do',function(result){
    	console.log(result);
        var result = JSON.parse(result);
       showMyProducts(result);
// var page = page ? page : 1;
// $.ajaxSettings.async = false;
// $.get('http://www.wjian.top/shop/api_goods.php',{
// 'pagesize':6,
// 'page':page,
// }, function(result){
// var result = JSON.parse(result);
// if(result.code != 0){
// console.log('数据请求失败');
// return;
// };
// showMyProducts(result);
        });
};
	
	// 做判断，在数据库如果返回有购物车信息，则把.products设为hide(),把数据库的商品内容设置在.myProducts的ul中

    // ----------------------------------------------------------------------------------------------

function countChange(id,count){
	$.ajaxSettings.async = false;
    $.get('shoppingCarCountChange.do',
    	{'id':id,'count':count}
    		);
}

function deleteGoods(id){
	$.ajaxSettings.async = false;
    $.get('shoppingCarDeleteGoods.do',{'id':id});
}


function showMyProducts(result){
    $('.products').hide();
    console.log(result);
    var productsList = result;
    
    for(var i = 0; i < productsList.length; i++){
        var myProduct = `
          <ul>
            <li>
            <input type="text" class="goodsId" style="display:none" value="${productsList[i].id}"/>
            <input type="checkbox" class="oneCheckbox"/>
            </li>
			<li>
               <a><img src="${productsList[i].url}"/></a>
               <div>
                  <a>${productsList[i].goodsName}</a>
                  <p>${productsList[i].goodsColor}</p>
               </div>
               
	        </li>
			<li>${productsList[i].price}.00</li>
			<li>
               <span class="reduce">-</span><input type="text" class="myProductCount" value="${productsList[i].number}"/><span class="add">+</span></li>
			<li>${productsList[i].price*productsList[i].number}.00</li>
			<li><span class="deleteOne">&times</span></li>
         </ul>
`;
        $('.myProducts').append(myProduct);
    };
    showMySumPrice();
    isHasProduct();
};

	// 底部显示总价
	function showMySumPrice(){
		var mySumPrice = `
          <ul class="mySumPrice" data-spy="affix" data-offset-top="1">
            <li><input type="checkbox" class="checkAll"/>全选</li>
			<li style="width:15%"><a class="deleteChecked">删除</a></li>
			<li>原价：￥0.00  活动优惠：-￥0.00 | </li>
			<li>合计(不含运费)：</li>
			<li>￥0.00</li>
			<li><button class="myBalance">结算</button></li>
          <ul>
    `;
		$('.myProducts').append(mySumPrice);
	}
	
	// ----------------------------------------------------------------------------------------------

    // ul点击事件
    var sum = 0;	
    var deleteOneRemenber = null;
    
	$("ul").click(function(e){
		//event.preventDefault() ：阻止默认行为，可以用 event.isDefaultPrevented() 来确定preventDefault是否被调用过了
		//event.stopPropagation() ：阻止事件冒泡，事件是可以冒泡的，为防止事件冒泡到DOM树上，不触发任何前辈元素上的事件处理函数，可以用 event.isPropagationStopped() 来确定stopPropagation是否被调用过了 
		
		if(!e.isPropagationStopped()){//确定stopPropagation是否被调用过
		// 减
		if(e.target.className=='reduce'){
			var number = e.target.nextElementSibling.value;
			
			if(number<=1){return;}
			number--;
			e.target.nextElementSibling.value = number;
			var price = e.target.parentNode.previousElementSibling.innerHTML;
			var id = e.target.parentNode.parentNode.firstElementChild.firstElementChild.value;
			e.target.parentNode.nextElementSibling.innerHTML = price*number+".00";
			calculateSumPrice();
			
			countChange(id,number);
			

		}
		// 加
		if(e.target.className=='add'){
			var number = e.target.previousElementSibling.value;
			number++;
			e.target.previousElementSibling.value = number;
			var price = e.target.parentNode.previousElementSibling.innerHTML;
			var id = e.target.parentNode.parentNode.firstElementChild.firstElementChild.value;
			e.target.parentNode.nextElementSibling.innerHTML = price*number+".00";
			calculateSumPrice();
			countChange(id,number);
		}
		// 后面删除一条
		if(e.target.className=='deleteOne'){
			
			e.target.setAttribute('data-toggle','modal');
			e.target.setAttribute('data-target','#delOneProductModal');
			deleteOneRemenber=e.target;
			var id = e.target.parentNode.parentNode.firstElementChild.firstElementChild.value;
			isHasProduct();
			deleteGoods(id);
		}
		// 总价ul中删除
		if(e.target.className=='deleteChecked'){	
			var isDel = false;
			var id = "";
			$('.oneCheckbox').each(function(){
					if($(this).prop('checked')){
					isDel = true;
					var getId = $(this).prev().val();
					id = id +"," + getId;
					getId = null;
					return;
				}	
			});
			if(isDel){
				e.target.setAttribute('data-toggle','modal');
				e.target.setAttribute('data-target','#delProductModal');
			}else{
				e.target.setAttribute('data-toggle','modal');
				e.target.setAttribute('data-target','#noProductModal');
			}
			deleteGoods(id);
			isHasProduct();
		}
		// 全选
		if(e.target.className=='checkAll'){
			
			// 全选中或全不选中
			$(e.target).prop('checked',true);
			var lock = false;
			$("[type='checkbox']").each(function(){	
				if(!$(this).prop('checked')){
					lock = true;	
				}
			});
			
			if(lock){
				
				$("[type='checkbox']").prop('checked',true);
			}else{
				
				$("[type='checkbox']").prop('checked',false);
			}
			// 计算总价
			calculateSumPrice();
		}
		// 选中前面框框总价显示
		if(e.target.className=='oneCheckbox'){
			calculateSumPrice();
			
		}
		// 结算
		if(e.target.className=='myBalance'){
			var balanceCount = 0;
			var balanceGooods = "";
		    $('.oneCheckbox').each(function(){
			    if($(this).prop('checked')){
				    balanceCount++;
				    var id = $(this).prev().val();
			    	var number = $(this).parent().parent().children('li').eq(3).children('input').eq(0).val();			    	
			    	balanceGooods = balanceGooods+"-、、-"+id+"-_~-_~"+number;
			    	
			    }
		    });
		    if(balanceCount<=0){
			    e.target.setAttribute('data-toggle','modal');
			    e.target.setAttribute('data-target','#balanceModal');
		    }else{		  
		        	window.location.href = "isLogin.do?balanceGooods="+balanceGooods;	  
		    }
		    
		}
    } 
		 e.stopPropagation();//必须要，不然e.isPropagationStopped()无法判断stopPropagation是否调用过
});


    // 滚轮滚动事件
	$(document).scroll(function(){
		var scrollH = $(document).scrollTop();
		var myProductsH = 160*$('.oneCheckbox').length;
		var clientH = $(window).height();
		
		if(scrollH>=myProductsH+108-clientH){
			$('.mySumPrice').removeClass('affix');
		}else{
			$('.mySumPrice').addClass('affix');
		}

	});

    // input框失焦事件
    $('.myProductCount').blur(function(){
		    var number = parseInt($(this).val());
		    number=number>0 ?number : 1;
		    $(this).val(number);
			var price = $(this).parent().prev().text();
			var id = $(this).parent().parent().children("li:first-child").children("input:first-child").val();
			$(this).parent().next().text(price*number+".00");
			calculateSumPrice();
			countChange(id,number);
	});
	
   // -----------------------------------------------------------------------------------------------

    // modal按钮点击事件
	$('#isDeleteOne').click(function(){
		deleteOneRemenber.parentNode.parentNode.remove();
		calculateSumPrice();

		isHasProduct();
		$('#isDeleteOne').attr('data-dismiss','modal');
		 });
	$('#isDelete').click(function(){
		$('.oneCheckbox').each(function(){
			if($(this).prop('checked')){
				$(this).parent().parent().remove();
			}
		});
		calculateSumPrice();

		isHasProduct();
		$('#isDelete').attr('data-dismiss','modal');
	});
    $('#isBalance').click(function(){
		$('#isBalance').attr('data-dismiss','modal');
	});
    
	// 计算所有勾选价格
	function calculateSumPrice(){
		sum = 0;
		$(".oneCheckbox").each(function(){	
			if($(this).prop('checked')){
              sum+=parseInt($(this).parent().parent().children().eq(4).text());
			}
		});
		$('.mySumPrice').children().eq(4).text("￥"+sum+".00");
		$('.mySumPrice').children().eq(2).text("原价：￥"+sum+".00"+"  活动优惠：-￥0.00 |");
	}

    // 判断是否有商品
    function isHasProduct(){
		if($('.oneCheckbox').length<=0){
		   $('.myProducts').hide();
		   $('.products').show();
	   }
	}
    

    // 清空购物车
    // $('.myProducts').empty(e.target.parentNode.parentNode);
