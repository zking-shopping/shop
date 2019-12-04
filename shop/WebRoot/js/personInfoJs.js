function productsList(page, showMyProducts){
    var page = page ? page : 1;
    $.ajaxSettings.async = false;
    $.get('personInfo.do', function(result){
        var result = JSON.parse(result);
        showMyProducts(result);
    });
};

function waitPay(){	
    $.ajaxSettings.async = false;
    $.get('personInfo.do',{'state':1}, function(result){
        var result = JSON.parse(result);
        showMyProducts(result);
    });
};

function waitDeliver(){	
    $.ajaxSettings.async = false;
    $.get('personInfo.do',{'state':2}, function(result){
        var result = JSON.parse(result);
        showMyProducts(result);
    });
};

function waitRecieve(){	
    $.ajaxSettings.async = false;
    $.get('personInfo.do',{'state':3}, function(result){
        var result = JSON.parse(result);
        showMyProducts(result);
    });
};

function showMyProducts(result){
    $('.myProducts').html("");
    $('.myNullOrder').show();
    $('.myrightinfo_body').hide();
    console.log(result);
    var orders = result[0];
    var detailOrderss = result[1];
    
    for(var i = 0; i < orders.length; i++){
    	var paiedMoney = 0;
    	for (var j = 0; j < detailOrderss[i].length; j++) {
    		paiedMoney = parseInt(paiedMoney) + parseInt(detailOrderss[i][j].price*detailOrderss[i][j].number);
    	}   	
    	var myOrders = `
        <div class="odder_info">下单时间:<span>${orders[i].time}</span> 订单号:<span>${orders[i].orderNumber}</span> 支付金额：<span class="paiedMoney">${paiedMoney}</span></div>
        `;
        $('.myProducts').append(myOrders);
        var stateid = orders[i].state;
        var state = "";
        if(stateid==1){
        	state = "待付款";
        }else if(stateid==2){
        	state = "待发货";
        }else if(stateid==3){
        	state = "待收货";
        }else if(stateid==4){
        	state = "已完成";
        }else{state = "已关闭"}
        
    	for (var j = 0; j < detailOrderss[i].length; j++) {
    		
    		var myDetailOrders = `          
            <ul>
  			<li> 
                 <a><img src="${detailOrderss[i][j].url.substring(28)}"/></a>
                 <div>
                    <a>${detailOrderss[i][j].goodsName}</a>
                    <p>${detailOrderss[i][j].goodsColor}</p>
                 </div>
                 
  	        </li>
  			<li>${detailOrderss[i][j].price}</li>
  			<li>
  			${detailOrderss[i][j].number}
  			<li style="font-weight: bold;">${detailOrderss[i][j].price*detailOrderss[i][j].number}</li>
  			<li stateid="${stateid}" paiedMoney="${paiedMoney}"></li><!--存了状态跟订单总价-->
  			<li class="a-hover">
  			    <input type="text" value="${orders[i].id}" style="display:none"/>
  			    <p><a href="buyAgain.do?id=${orders[i].id}">重新购买</a></p>
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

function showMyDetails(paiedMoney,state,result){
	
    var myOders = result[0];
    var myAddressInfo = result[1];
    $('.viewDetails_buttom_body_details').html("");
//    alert(state);
    $('.viewDetails_buttom_orderInfo').html(""); 
    var stateImageOne = "img/list4.png";
    var stateImageTwo = "img/list5.png";
    var stateImageThere = "img/list7.png";
    var stateFontOne = "提交申请";
    var stateFontTwo = "取消处理";
    var stateFontThere = "取消成功";
    if(state==1){
    	stateImageOne = "img/list88.png";
    	stateImageTwo = "img/list99.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="待付款";
    }else if(state==2){
    	stateImageOne = "img/list88.png";
    	stateImageTwo = "img/list99.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="待发货";
    }else if(state==3){
    	stateImageOne = "img/list88.png";
    	stateImageTwo = "img/list99.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="待收货";
    }else if(state==4){
    	stateImageOne = "img/list88.png";
    	stateImageTwo = "img/list99.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="已关闭";
    }
    var viewDetails_buttom_orderInfo = `
	<div class="viewDetails_left">
	    <h3>订单${state}</h3>
	    <h3>实付款￥${paiedMoney}.00</h3>
	    <h4>实退款￥0.00</h4>
	</div>
	<div class="viewDetails_right">
	    <ul>
	        <li>
	            <img src="${stateImageOne}"/>
	            <span class="wool_right"></span>
	            <p>${stateFontOne}</p>
	            <p>时间</p>
	        </li>
	        <li>
	            <span class="wool_left"></span>
	            <img src="${stateImageTwo}"/>
	            <span class="wool_right"></span>
	            <p>${stateFontTwo}</p>
	            <p>时间</p>
	        </li>
	        <li>
	            <span class="wool_left"></span>
	            <img src="${stateImageThere}"/>
	            <p>${stateFontThere}</p>
	            <p>时间</p>
	        </li>
	       
	    </ul>
	</div>
	`;
    $('.viewDetails_buttom_orderInfo').append(viewDetails_buttom_orderInfo);
    
    for(var i = 0; i < myOders.length; i++){
    	
        var myOrder = `
            <div class="logistics">暂无物流信息</div>
            <ul>
			<li>
               <a><img src="${myOders[i].url.substring(28)}"/></a>
               <div>
                  <a>${myOders[i].goodsName}</a>
                  <p>${myOders[i].goodsColor}</p>
               </div>               
	        </li>
			<li>${myOders[i].price}</li>
			<li>
			    ${myOders[i].number}
			<li>${myOders[i].price*myOders[i].number}</li>
			</ul> 
			
`;
        $('.viewDetails_buttom_body_details').append(myOrder);
    };
    var orderInfo = `
    <p><span>收货人：${myAddressInfo.cousignee}</span><span>支付方式：未支付</span><span>实付款：￥${paiedMoney}.00</span></p>
    <p><span>联系方式：${myAddressInfo.phoneNumber}</span><span>活动优惠：-￥0.00</span><span>运费：￥0.00</span></p>
    <p><span>收货地址：${myAddressInfo.provinces}${myAddressInfo.city}${myAddressInfo.area}${myAddressInfo.detailAddress}</span><span>优惠券：-￥0.00</span></p>
    `;
    $('#orderInfo').html("");
    $('#orderInfo').append(orderInfo);
};

$(function(){
	productsList(1,showMyProducts);
	$('.viewDetails').hide();
	$('.myReceiptAddress').hide();
	
	//查看详情--右
	$(document).on('click','.showDetails',function(e){
	    $('.myOrder').hide();
	    $('#myPersonInfo').hide();
	    $.ajaxSettings.async = false;
	    
	    var firstidInputFather = e.target.parentNode.parentNode;
	    var id = firstidInputFather.firstElementChild.value;
	    var state = firstidInputFather.previousElementSibling.getAttribute("stateid");
	    var paiedMoney = firstidInputFather.previousElementSibling.getAttribute("paiedMoney");
	   
	    $.get('personInfoShowDetails.do',{
	        'id':id
	    }, function(result){
	        var result = JSON.parse(result);	        
	        showMyDetails(paiedMoney,state,result);
	    });
	    $('.viewDetails').show();
	});
	
	//我的订单--左
	$(".showMyOrder").click(function () {
		
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
	    
	    $('.allOrder').siblings().css("color","black");
	    $('.allOrder').css("color","dodgerblue");
	    });

	//个人信息--左
	$('.myPersonInfo').click(function () {

	    $.ajaxSettings.async = false;
	    $.get('myPersonInfo.do',function(result){
	        var result = JSON.parse(result);
	        var myProduct = `
            <p><span>用户</span><span>${result.name}</span></p>
            <p><span>账号</span><span>${result.username}</span></p>
            <p><span>手机号码</span><span>${result.phoneNumber}</span></p>        
`;
            $('#myPersonInfo').html(myProduct);
	    });
	        
	        $('.myOrder').hide();
	        $('.viewDetails').hide();
	        $('.main-info').hide();
	        
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
	    
	    cleanAllInput();

	    //加载数据
	    $.ajax({
			url:"showAddress.do",
			data:"",
			success:function(result){
				if(result.length!=0){
					//显示可添加地址的数量
					if($(".number-title").length!=0){
						$('.number-title').remove();
					};
					var havedAddress = `
						<h3 class="number-title">
							新增收货地址（您目前已有${result.length}个地址，最多还可增加${10-result.length}个）
						</h3>
					`;
					$('.main-info').prepend(havedAddress);
					
					//移除原本已有的地址数据
					var lens = $('.address-list tbody tr').length;
					if(lens!=0){
						$('.address-list tbody').empty();
					};
					
					//更新表格
					updateAddressList(result);
				};
			}
		});
	});

	//待付款--右
	$('.waitPay').click(function () {
		waitPay();
//	    $('.myNullOrder').show();
//	    $('.myrightinfo_body').hide();
	    $('#myPersonInfo').hide();
	    $('.waitPay').siblings().css("color","black");
	    $('.waitPay').css("color","dodgerblue");
	});

	//代发货--右
	$('.waitDeliver').click(function () {
		waitDeliver();
//	    $('.myNullOrder').show();
//	    $('.myrightinfo_body').hide();
	    $('#myPersonInfo').hide();
	    $('.waitDeliver').siblings().css("color","black");
	    $('.waitDeliver').css("color","dodgerblue");
	});

	//待收货--右
	$('.waitRecieve').click(function () {
		waitRecieve();
//	    $('.myNullOrder').show();
//	    $('.myrightinfo_body').hide();
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
