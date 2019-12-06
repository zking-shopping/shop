var downOrderTime = "";//下单时间
var downOrderMoney = "";//下单总额

function productsList(showMyProducts){
    $.ajaxSettings.async = false;
    $.get('personInfo.do', function(result){
    	if(result==""){
    		alert("请先登录！");
    		window.location.href = "login.jsp";
    		return;
    	}
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

var isMyPersonInfoChange = 1;
//判断输入的密码是否符合要求
function checkPassword(cont){
	isMyPersonInfoChange = 1;
	//将元素的一些属性值初始化为匹配失败的属性值
	var pTipContent = '';//错误提示框内容为空
	
	//获得内容长度
	var len = cont.length;
	var re1 = new RegExp("[0-9]{"+len+"}");
	var re2 = new RegExp("[a-z]{"+len+"}");
	var re3 = new RegExp("[A-Z]{"+len+"}");
	//判断密码长度是否足够
	if(len<8||len>18){
		pTipContent = '密码的位数在8-18位之间！';
		
	}else{
		pTipContent = '密码必须包含数字、字母以及符号两种或以上！';
		
		if(/[^a-zA-Z0-9_]/g.test(cont)){
			pTipContent = '密码不能有下划线之外的符号！';
		}else if(!(re1.test(cont))&&!(re2.test(cont))&&!(re3.test(cont))){				
			pTipContent = '';
			isMyPersonInfoChange = 0;
		};
	};
	$('.pwdPrompt').html(pTipContent);
};

//判断输入的手机号是否符合要求
function checkPhoneNumber(cont){
	isMyPersonInfoChange = 1;
	//将元素的一些属性值初始化为匹配失败的属性值
	var pTipContent = '输入的手机号码格式有误！';//错误提示框内容为空
	
	//获得内容长度
	var len = cont.length;
	//判断是否是手机号码的正则表达式
	var re = /^1[3456789]\d{9}$/;
	if(re.test(cont)){			
		pTipContent = '';
		isMyPersonInfoChange = 0;
	};
	$('.phoneNumberPrompt').html(pTipContent);
	
};

function showMyProducts(result){	
    $('.myProducts').html("");
    $('.myNullOrder').show();
    $('.myrightinfo_body').hide();
    var orders = result[0];
    var detailOrderss = result[1];
   
    for(var i = 0; i < orders.length; i++){
    	var paiedMoney = 0;
    	for (var j = 0; j < detailOrderss[i].length; j++) {
    		paiedMoney = parseInt(paiedMoney) + parseInt(detailOrderss[i][j].price*detailOrderss[i][j].number);
    	}   	
    	var myOrders = `
        <div class="odder_info">下单时间:<span class="downOrderTimeSpan">${orders[i].time}</span> 订单号:<span>${orders[i].orderNumber}</span> 订单总额：<span class="paiedMoney">${paiedMoney}</span></div>
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
    		var waitToPay = '<a href="buyAgain.do?id='+orders[i].id+'">重新购买</a>';
    		if(stateid==1){
    			waitToPay = '<a href="toPay.do?id='+detailOrderss[i][j].orderId+'&total='+paiedMoney+'&addressId=1&receiveInfo=1">去付款</a>';
    		}
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
  			<li stateid="${stateid}" paiedMoney="${paiedMoney}">${state}</li><!--存了状态跟订单总价-->
  			<li class="a-hover">
  			    <input type="text" value="${orders[i].id}" style="display:none"/>
  			    <p>${waitToPay}</p>
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
	downOrderMoney = paiedMoney;
    var myOders = result[0];
    var myAddressInfo = result[1];
    console.log(result);
    $('.viewDetails_buttom_body_details').html("");
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
    	var viewDetails_buttom_orderInfo_waitToPay = `
    		    <h3>订单${state}</h3>
    		    <h3><button class="waitToPayButton">待付款￥${paiedMoney}.00</button></h3>   		    
    		    <h4 class="waitToPayTime" style="height:20px"></h4>    
         `;
    }else if(state==2){
    	stateImageOne = "img/list89.png";
    	stateImageTwo = "img/list99.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="待发货";
    }else if(state==3){
    	stateImageOne = "img/list89.png";
    	stateImageTwo = "img/list100.png";
    	stateImageThere = "img/list77.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="待收货";
    }else if(state==4){
    	stateImageOne = "img/list89.png";
    	stateImageTwo = "img/list100.png";
    	stateImageThere = "img/list78.png";
    	var stateFontOne = "待发货";
        var stateFontTwo = "待收货";
        var stateFontThere = "交易成功";
    	state="已完成";
    }else{
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
	$('.odder_infoInsert').append(downOrderTime);
    $('.viewDetails_buttom_orderInfo').append(viewDetails_buttom_orderInfo);
    if(state=="待付款"){
    	$('.viewDetails_left').html(viewDetails_buttom_orderInfo_waitToPay);
    }
    
    for(var i = 0; i < myOders.length; i++){
    	
        var myOrder = `
            <div class="logistics" orderId="${myOders[i].orderId}">暂无物流信息</div>
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


$(document).on('click','.waitToPayButton',function(){
	var orderId = $('.logistics').attr("orderId");
	window.location.href = "toPay.do?id="+orderId+"&total="+downOrderMoney+"&addressId=1&receiveInfo=1";
});

$(function(){
	productsList(showMyProducts);
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
	    downOrderTime = firstidInputFather.parentNode.previousElementSibling.firstElementChild.innerHTML;
	    
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
		
	        productsList(showMyProducts);
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
	        <form class="myPersonInfoForm">
	        <div class = "myPersonInfoHead">我的个人信息</div>
	        <input type="text" value="${result.id}" style="display:none" name="personId"/>
	        <table class="myPersonInfoTable">	        
				<tr>
					<td>账号</td>
					<td><span>${result.username}</span><input type="text" readonly ="readonly" value="${result.username}" style="display:none" name="username"/></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><span>不能给你看哦</span><input type="password" placeholder="如果要修改密码请输入你的新密码" style="display:none" name="password" class="passwordInput"/></td>
					<td><p class="pwdPrompt"></p></td>
			    </tr>
				<tr>
					<td>用户名</td>
					<td><span>${result.name}</span><input type="text" value="${result.name}" style="display:none" name="name"/></td>
				</tr>
				<tr>
					<td>手机号码</td>
					<td><span>${result.phoneNumber}</span><input type="text" value="${result.phoneNumber}" style="display:none" name="phoneNumber" class="phoneNumberInput"/></td>
					<td><p class="phoneNumberPrompt"></p></td>
				</tr>
				<tr>
					<td>				
						<button class="changeMyPersonInfo" type="button">修改信息</button>
						<button class="sureChangeMyPersonInfo" style="display:none" type="button">确认修改</button>
					</td>
				</tr>
		    </table>  
		    </form>
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
	
	//个人信息--修改信息
	$(document).on('click','.changeMyPersonInfo',function(e){		
		$('.myPersonInfoTable').children().children().children().children("span").css("display","none");
		$('.myPersonInfoTable').children().children().children().children("input").css("display","block");
		$('.sureChangeMyPersonInfo').css("display","block");
		$('.changeMyPersonInfo').css("display","none");
	});
	
	
	
	//输入框失焦事件
	$(document).on('blur','.myPersonInfoTable input',function(){
		
		//获得输入框内容
		var cont = $(this).val();
		//获得输入框的name属性
		var name = $(this).attr('name');
		if(cont!=''){
			if(name=='password'){
				//调用方法判断密码是否符合规范
				checkPassword(cont);
			}else if(name=='phoneNumber'){
				//调用方法判断手机号是否符合规范
				checkPhoneNumber(cont);
			};
		};	
	});
	
	
	//个人信息--修改信息--确认修改
	$(document).on('click','.sureChangeMyPersonInfo',function(e){
		var cont = $('.passwordInput').val();
		if(cont==""){
			isMyPersonInfoChange = 0;
		}else{
			
			checkPassword(cont);
		}
		if(isMyPersonInfoChange == 0 ){
			isMyPersonInfoChange = 1;
			cont = $('.phoneNumberInput').val();
			checkPhoneNumber(cont);
			if(isMyPersonInfoChange == 0){
				$.ajax({
					type: "POST",
					url:  "changeMyPersonInfo.do",
					data: $('.myPersonInfoForm').serialize(),
					async: false,
					success: function(result) {
						if(result){
							alert('修改成功');
							window.location.reload();
							
						}else{
							alert('修改失败');
						}
					}
				});
			}
			
		}
		
		
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
	    productsList(showMyProducts);
	    $('.viewDetails').hide();
	    $('#myPersonInfo').hide();
	    $('.myOrder').show();
	    $('.allOrder').siblings().css("color","black");
	    $('.allOrder').css("color","dodgerblue");
	});
});
