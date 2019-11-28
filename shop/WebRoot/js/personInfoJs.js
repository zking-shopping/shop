function productsList(page, showMyProducts){
    var page = page ? page : 1;
    $.ajaxSettings.async = false;
    $.get('personInfo.do', function(result){
        var result = JSON.parse(result);
//        if(result.code != 0){
//            console.log('数据请求失败');
//            return;
//        };
        showMyProducts(result);
    });
};

function showMyProducts(result){
    $('.myProducts').html("");
    $('.myNullOrder').show();
    $('.myrightinfo_body').hide();
    console.log(result);
//    var productsList = result;
    var orders = result[0];
    var detailOrderss = result[1];
    for(var i = 0; i < orders.length; i++){
    	var myOrders = `
        <div class="odder_info">下单时间:<span>${orders[i].time}</span> 订单号:<span>${orders[i].orderNumber}</span></div>
        `;
        $('.myProducts').append(myOrders);
    	for (var j = 0; j < detailOrderss[i].length; j++) {
    		var myDetailOrders = `          
            <ul>
  			<li>
                 <a><img src="${detailOrderss[i][j].url}"/></a>
                 <div>
                    <a>${detailOrderss[i][j].goodsName}</a>
                    <p>${detailOrderss[i][j].goodsColor}</p>
                 </div>
                 
  	        </li>
  			<li>${detailOrderss[i][j].price}</li>
  			<li>
  			${detailOrderss[i][j].number}
  			<li style="font-weight: bold;">${detailOrderss[i][j].price*detailOrderss[i][j].number}</li>
  			<li>已关闭</li>
  			<li class="a-hover">
  				<p><a href="shoppingCar.jsp">重新购买</a></p>
  				<p class="showDetails"><a>查看详情</a></p>
  			</li>
           </ul>
  `;
        $('.myProducts').append(myDetailOrders);	
		}
        $('.myNullOrder').hide();
        $('.myrightinfo_body').show();                
    };
};


function showMyDetails(result){
    var myOders = result.data;
    $('.viewDetails_buttom_body_details').html("");
    for(var i = 0; i < myOders.length; i++){
        var myOrder = `
            <div class="logistics">暂无物流信息</div>
            <ul>
			<li>
               <a><img src="${myOders[i].goods_thumb}"/></a>
               <div>
                  <a>${myOders[i].goods_name}</a>
                  <p>${myOders[i].goods_desc}</p>
               </div>
               
	        </li>
			<li>${myOders[i].price}</li>
			<li>
               1
			<li>${myOders[i].price}</li>
			</ul> 
			
`;
        $('.viewDetails_buttom_body_details').append(myOrder);

    };
};

$(function(){
	productsList(1,showMyProducts);
	$('.viewDetails').hide();
	$('.myReceiptAddress').hide();
	
	//查看详情--右
	$(document).on('click','.showDetails',function(){
	    $('.myOrder').hide();
	    $('#myPersonInfo').hide();
	    $.ajaxSettings.async = false;
	    $.get('personInfoShowDetails.do',{
	        'pagesize':1,
	        'page':1,
	    }, function(result){
	        var result = JSON.parse(result);
	        if(result.code != 0){
	            console.log('数据请求失败');
	            return;
	        };
	        showMyDetails(result);
	    });
	    $('.viewDetails').show();
	});
	
	//我的订单--左
	$(".showMyOrder").click(function () {
		console.log(666);
	        productsList(1,showMyProducts);
	        $('.viewDetails').hide();
	        $('#myPersonInfo').hide();
	        $('.myOrder').show();

		$('.main-info').hide();
	    $('.showMyOrder').siblings().css("border-left","0px");
	    $('.showMyOrder').siblings().css("background","white");
	    $('.showMyOrder').siblings().children().css("color","black");

	    $('.showMyOrder').parent().siblings().children().css("border-left","0px");
	    $('.showMyOrder').parent().siblings().children().css("background","white");
	    $('.showMyOrder').parent().siblings().children().children().css("color","black");

	    $('.showMyOrder').css("border-left","3px solid cornflowerblue");
	    $('.showMyOrder').css("background","rgba(102, 107, 255, 0.1)");
	    $('.showMyOrder').children().css("color","dodgerblue");
	    });

	//个人信息--左
	$('.myPersonInfo').click(function () {

	    $.ajaxSettings.async = false;
	    $.get('http://www.wjian.top/shop/api_goods.php',{
	        'pagesize':1,
	        'page':1,
	    }, function(result){
	        var result = JSON.parse(result);
	        if(result.code != 0){
	            console.log('数据请求失败');
	            return;
	        };
	        showMyDetails(result);
	    });
	        var myProduct = `
	            <p><span>用户</span><span>ID169853895</span></p>
	            <p><span>账号</span><span>282237742@qq.com</span></p>
	            <p><span>手机号码</span><span>绑定手机号码</span></p>        
	`;
	        $('.myOrder').hide();
	        $('.viewDetails').hide();
	        $('.main-info').hide();
	        $('#myPersonInfo').html(myProduct);
	        $('#myPersonInfo').show();

	        $('.myPersonInfo').siblings().css("border-left","0px");
	        $('.myPersonInfo').siblings().css("background","white");
	        $('.myPersonInfo').siblings().children().css("color","black");

	        $('.myPersonInfo').parent().siblings().children().css("border-left","0px");
	        $('.myPersonInfo').parent().siblings().children().css("background","white");
	        $('.myPersonInfo').parent().siblings().children().children().css("color","black");

	        $('.myPersonInfo').css("border-left","3px solid cornflowerblue");
	        $('.myPersonInfo').css("background","rgba(102, 107, 255, 0.1)");
	        $('.myPersonInfo').children().css("color","dodgerblue");
	});

	//收货地址--左
	$('.receiptAddress').click(function () {
		console.log(666);
	    $('.main-info').siblings().hide();
	    $('.main-info').show();

	    $('.receiptAddress').siblings().css("border-left","0px");
	    $('.receiptAddress').siblings().css("background","white");
	    $('.receiptAddress').siblings().children().css("color","black");

	    $('.receiptAddress').parent().siblings().children().css("border-left","0px");
	    $('.receiptAddress').parent().siblings().children().css("background","white");
	    $('.receiptAddress').parent().siblings().children().children().css("color","black");

	    $('.receiptAddress').css("border-left","3px solid cornflowerblue");
	    $('.receiptAddress').css("background","rgba(102, 107, 255, 0.1)");
	    $('.receiptAddress').children().css("color","dodgerblue");
	});

	//待付款--右
	$('.waitPay').click(function () {
	    $('.myNullOrder').show();
	    $('.myrightinfo_body').hide();
	    $('#myPersonInfo').hide();
	    $('.waitPay').siblings().css("color","black");
	    $('.waitPay').css("color","dodgerblue");
	});

	//代发货--右
	$('.waitDeliver').click(function () {
	    $('.myNullOrder').show();
	    $('.myrightinfo_body').hide();
	    $('#myPersonInfo').hide();
	    $('.waitDeliver').siblings().css("color","black");
	    $('.waitDeliver').css("color","dodgerblue");
	});

	//待收货--右
	$('.waitRecieve').click(function () {
	    $('.myNullOrder').show();
	    $('.myrightinfo_body').hide();
	    $('#myPersonInfo').hide();
	    $('.waitRecieve').siblings().css("color","black");
	    $('.waitRecieve').css("color","dodgerblue");
	});

	//全部订单--右
	$('.allOrder').click(function () {
	    productsList(1,showMyProducts);
	    $('.viewDetails').hide();
	    $('#myPersonInfo').hide();
	    $('.myOrder').show();
	    $('.allOrder').siblings().css("color","black");
	    $('.allOrder').css("color","dodgerblue");
	});
});
