function addAddress(){
	$(".select-address").css("display","none");
	$(".main-info").css("display","block");
	$("#exit-add").css("display","inline-block");
	cleanAllInput();
};

$(function(){
	//加载地址列表
	$.ajax({
		type:"post",
		url:"showAddress.do",
		data:"",
		success:function(result){
			if(result.length!=0){
				//移除原本已有的地址数据
				$('.address-list tbody tr').each(function(){
					$(this).remove();
				});
				//更新表格
				updateAddressList(result);
				var url = window.location.href;
				var pageName = url.substring(url.lastIndexOf("/")+1,url.IndexOf("."));
				console.log(url);
			}else{
				$(".select-address").css("display","none");
				$(".main-info").css("display","block");
				$("#exit-add").css("display","none");
			};
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
					<tr data-goodsId=${value.goodsId} data-number=${value.number} data-id=${value.id}>
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
		var allGoodsInfo = "";
		var receiveInfo = "";
		//获得需要传的数值
		$(".address-list tr").each(function(){
			var selected = $(this).attr("data-selected");
			if(selected=="true"){
				allGoodsInfo += "addressId="+$(this).attr("data-id");
				var phoneNumbers = $(this).attr("data-phonenumber");
				var provinces = $(this).attr("data-provinces");
				var city = $(this).attr("data-city");
				var area = $(this).attr("data-area");
				var detailAddress = $(this).attr("data-detailaddress");
				receiveInfo += provinces+city+area+detailAddress+"  "+phoneNumbers;
			};
		});
		//拼接订单的所有的商品id和数量
		var goodsNum = 0;
		$(".goodsSection tbody tr").each(function(e){
			var goodsId = $(this).attr("data-goodsId");
			var number = $(this).attr("data-number");
			var id = $(this).attr("data-id");
			var cart = new Object();
			cart.id = id;
			cart.goodsId = goodsId;
			cart.num = number;
			var json = JSON.stringify(cart);
			allGoodsInfo += "&goods"+ e + "=" + json;
			goodsNum++;
		});
		allGoodsInfo += "&goodsNum="+goodsNum;
		//获得总价
		var total = $(".payable #count").html();
		allGoodsInfo += "&total="+total;
		console.log(allGoodsInfo);
		
		//将数据传给后台
		$.ajax({
			type:"post",
			url:"settlement.do",
			data:allGoodsInfo,
			success:function(result){
				console.log(result.addressId);
				console.log(result.total);
				console.log(receiveInfo);
//				window.open('settlement.jsp');
				window.location.href = "toPay.do?addressId="+result.addressId+"&id="+result.id
										+"&total="+result.total+"&receiveInfo="+receiveInfo;
//				Response.Redirect("/shop/settlement.jsp?addressId="+result.addressId,true);
			}
		});
	});
});
