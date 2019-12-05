function addAddress(){
	$(".select-address").css("display","none");
	$(".main-info").css("display","block");
	$("#exit-add").css("display","inline-block");
	cleanAllInput();
};

//地址框变换事件
function changeState(obj){
	$(obj).css({
		"margin" : "9px",
		"border" : "2px solid #1989E9",
		"background" : "url(./img/default.png) no-repeat right bottom",
		"background-origin" : "border-box"
	}).siblings().css({
		"margin" : "10px",
		"border" : "1px solid #d6d6d6",
		"background" : "none"
	});
	$(obj).attr("data-selected","true");
	$(obj).siblings().attr("data-selected","false");
};

$(function(){
	autoSize();
	$(window).resize(autoSize);
	
	//加载地址列表
	$.ajax({
		type:"post",
		url:"showAddress.do",
		data:"",
		success:function(result){
			console.log(result);
			if(result.length!=0){
				//移除原本已有的地址数据
				$('.address-list tbody tr').each(function(){
					$(this).remove();
				});
				
				//更新表格
				updateAddressList(result);
			}else{
				$(".select-address").css("display","none");
				$(".main-info").css("display","block");
				$("#exit-add").css("display","none");
			};
			cleanAllInput();
		}
	});
	
	//加载商品列表
	$.ajax({
		type:"post",
		url:"showSettlementList.do",
		data:"",
		success:function(result){
			console.log(result);
			var addPrice = 0;
			$.each(result, function (i, value) {
				var addStr = `
					<tr data-goodsId=${value.goodsId} data-number=${value.number}>
						<td>
							<a href="#n">
								<img src=${value.url.substring(28)} />
							</a>
							<a href="#666"><h2>${value.goodsName}</h2></a>
							<p>${value.goodsColor}</p>
						</td>
						<td>
							<span id="unit-price">￥</span>${value.price}
						</td>
						<td>${value.number}</td>
						<td>
							<span id="subtotal">￥</span>${value.number*value.price}
						</td>
					</tr>
				`;
				$(".goodsSection tbody").append(addStr);
				addPrice = addPrice+Number(value.number*value.price);
			})
			
			//付款信息
			$(".total-price #count").html(addPrice);
			var discount = Number($(".discount #count").html());
			var freight = Number($(".freight #count").html());
			var payable = addPrice-discount+freight;
			$(".payable #count").html(payable);
		}
	});
	
	//取消添加地址
	$("#exit-add").click(function(){
		$(".select-address").css("display","block");
		$(".main-info").css("display","none");
	});
	
	//设置付款按钮是否可用
	$("#agree-bule").click(function(){
		$(".to-pay").prop("disabled",!$(this).prop("checked"));
	});
	
	//点击去付款
	$(".to-pay").click(function(){
		//获得需要传的数值
		var addressId = "";
		$(".address-list tr").each(function(){
			var selected = $(this).attr("data-selected");
			if(selected=="true"){
				addressId = $(this).attr("data-id")
			};
		});
		console.log(addressId);
	});
});
