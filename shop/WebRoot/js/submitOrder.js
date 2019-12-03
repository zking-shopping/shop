//清空输入的数据
function cleanAllInput(){
	$("#consignee").val("");
	$("#phone-number").val("");
	$("#provinces option").eq(0).prop("selected",true);
	$("#city option").eq(0).prop("selected",true);
	$("#area option").eq(0).prop("selected",true);
	$("#detail-address").val("");
	$("#default").prop("checked",false);
	
	$("#save").attr("data-id","");
};

//设置确认你删除界面的大小
function autoSize(){
	//获得窗口的大小
	var windowWidth = document.body.clientWidth;
	var windowHeight = document.body.clientHeight;
	//获得
	var width = $(".center-delete").width();
	var height = $(".center-delete").height();;
	
	$(".transparent").css({
		"width" : windowWidth,
		"height" : windowHeight
	});
	$(".center-delete").css({
		"left" : (windowWidth-width)/2,
		"top" : (windowHeight-height)/2-50
	});
};

//隐藏和显示设为默认按钮的方法
function defaultDisplayOver(obj){
	var displays = $(obj).find("#default-address").css("display");
	if(displays!="inline-block"){
		$(obj).find("#set-default-address").css("display","inline-block");
	};
};
function defaultDisplayOut(obj){
	var displays = $(obj).find("#default-address").css("display");
	if(displays!="inline-block"){
		$(obj).find("#set-default-address").css("display","none");
	};
};
//更改默认地址
function setDefaultAddress(obj){
	//获得已经默认的地址的id
	var hadDefaultId = null;
	$(obj).parents("tr").siblings().each(function(e){
		var isDefault = $(this).attr("data-defaultAddress");
		console.log(isDefault);
		if(isDefault=="true"){
			hadDefaultId = $(this).attr("data-id");
		};
	});
	//获得所有已有id
	var willDefaultId = $(obj).parents("tr").attr("data-id");
	//更改数据库数据
	$.ajax({
		type: "post",
		url: "setDefaultAddress.do",
		data: "id0="+hadDefaultId+"&id1="+willDefaultId,
		success:function(result){
			$(obj).css("display","none");
			$(obj).parents("tr").attr("data-defaultaddress","true");
			//判断当前表格是否有显示默认
			var len = $(obj).siblings("#default-address").length;
			if(len>0){
				$(obj).siblings("#default-address").css("display","inline-block");
				$(obj).parents("tr").siblings("tr").find("#default-address").css("display","none");
			};
			//改变
			$(obj).parents("tr").siblings().each(function(e){
				if($(this).attr("data-defaultaddress")=="true"){
					$(this).attr("data-defaultaddress","false");
				};
			});
		}
	});
};

//更新地址列
function updateAddressList(result){
	//添加表格数据
	$.each(result, function (i, value) {
		//添加基础表格和标题
		var len = $('.address-title').length;
		if(len==0){
			var myAddressTable = `
				<h3 class="address-title">已有地址</h3>
				<table class="address-list">
					<thead>
						<tr>
							<td>收货人</td>
							<td>地址</td>
							<td>联系方式</td>
							<td>操作</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			`;
			$('.haved-address').append(myAddressTable);
		};
		
		var allAddress = value.provinces+value.city+value.area+value.detailAddress;
		if(value.defaultAddress!="true"){
			value.defaultAddress = "false";
		};
		var myAddress = `
			<tr data-cousignee=${value.cousignee} data-phonenumber=${value.phoneNumber}
			 data-provinces=${value.provinces} data-city=${value.city}
			 data-area=${value.area} data-detailaddress=${value.detailAddress}
			 data-id=${value.id} data-defaultaddress=${value.defaultAddress}
			 onmouseover="defaultDisplayOver(this)" onmouseout="defaultDisplayOut(this)" >
				<td>${value.cousignee}</td>
				<td>${allAddress}</td>
				<td>${value.phoneNumber}</td>
				<td>
					<span id="modify" onclick="modifyAddress(this)">修改</span>
					<span id="delete" onclick="deleteAddress(this)">删除</span>
				</td>
				<td>
					<span id="set-default-address" onclick="setDefaultAddress(this)">设为默认</span>
					<span id="default-address">默认地址</span>
				</td>
			</tr>
		`;
		$('.address-list tbody').append(myAddress);
		
		//设置默认地址的显示状况
		var defaultAddress = value.defaultAddress;
		if(defaultAddress=="true"){
			$('.address-list #default-address').eq(i).css("display","inline-block");
		};
	})
};

//保存地址
function saveAddress(){
	//获得输入的值
	var cousignee = $("#consignee").val();
	var phoneNumbers = $("#phone-number").val();
	var provinces = $("#provinces").val();
	var city = $("#city").val();
	var area = $("#area").val();
	var detailAddress = $("#detail-address").val();
	var defaultAddress = $("#default").prop("checked");
	
	//获得默认的id
	var id = null;
	$(".address-list tbody tr").each(function(){
		var defaults = $(this).attr("data-defaultAddress");
		if(defaults=="true"){
			id = $(this).attr("data-id");
		}
	});
	
	//提示信息
	var tip = "";
	//需要的元素
	var object = null;
	//是否能够保存
	var canSave = false;
	if(cousignee==""){
		tip = "输入不可为空！";
		object = "#consignee";
	}else{
		if(phoneNumbers==""){
			tip = "输入不可为空！";
			object = "#phone-number";
		}else if(!(/^1[3456789]\d{9}$/.test(phoneNumbers))){
			//判断手机号码是否符合规范
			tip = "手机号码格式不正确！";
			object = "#phone-number";
		}else{
			if(provinces==""){
				tip = "请选择省份！";
				object = "#provinces";
			}else{
				if(city==""){
					tip = "请选择城市！";
					object = "#city";
				}else{
					if(area==""){
						tip = "请选择地区！";
						object = "#area";
					}else{
						if(detailAddress==""){
							tip = "请填写详细地址！";
							object = "#detail-address";
						}else{
							canSave = true;
						};
					};
				};
			};
		};
	};
	
	//设置跳转方式
	var jumpMode = "savaAddress.do";
	if($("#save").attr("data-id")!=""){
		jumpMode = "modifyAddress.do";
		id = $("#save").attr("data-id");
	};
	
	if(canSave==false&&$(".error-tip").length==0){
		var ele = `
			<p class="error-tip">
				<img src="/shop/img/error.png">
				<span class="error-text">${tip}</span>
			</p>
		`;
		//添加提示
		$(object).parent().append(ele);
		$(object).css("border-color","#FF6700");
	}else{
		//传值
		$.ajax({
			type:"post",
			url: jumpMode,
			data:"cousignee="+cousignee+"&phoneNumber="+phoneNumbers+"&provinces="+provinces+"&city="+city+
				"&area="+area+"&detailAddress="+detailAddress+"&defaultAddress="+defaultAddress+"&saveId="+id,
			success:function(result){
				$(".address-list tbody tr").remove();
				//更新表格
				updateAddressList(result);
				//清空输入的数据
				cleanAllInput();
			}
		});
	};
};

//取消删除方法
function exitDelete(){
	$(".transparent").css("display","none");
	$(".center-delete").css("display","none");
	//移出要删除id
	if($(".center-delete").attr("data-delete-id")!=null){
		$(".center-delete").removeAttr("data-delete-id");
	};
};
//删除地址
function deleteAddress(obj){
	//弹出确认框
	$(".transparent").css("display","block");
	$(".center-delete").css("display","block");
	
	//获得当前地址id
	var id = $(obj).parents("tr").attr("data-id");
	$(".center-delete").attr("data-delete-id",id);
};

//修改地址
function modifyAddress(obj){
	//获得基础信息
	var cousignee = $(obj).parents("tr").attr("data-cousignee");
	var phoneNumbers = $(obj).parents("tr").attr("data-phonenumber");
	var provinces = $(obj).parents("tr").attr("data-provinces");
	var city = $(obj).parents("tr").attr("data-city");
	var area = $(obj).parents("tr").attr("data-area");
	var detailAddress = $(obj).parents("tr").attr("data-detailaddress");
	var defaultAddress = $(obj).parents("tr").attr("data-defaultaddress");
	
	//填入
	$("#consignee").val(cousignee);
	$("#phone-number").val(phoneNumbers);
	$("#provinces").find("option:contains('"+provinces+"')").prop("selected",true);
	changeCity();
	$("#city").find("option:contains('"+city+"')").prop("selected",true);
	changeArea();
	$("#area").find("option:contains('"+area+"')").prop("selected",true);
	$("#detail-address").val(detailAddress);
	if(defaultAddress=="true"){
		$("#default").prop("checked",true);
	};
	
	//传id
	var id = $(obj).parents("tr").attr("data-id");
	$("#save").attr("data-id",id);
	
};

$(function(){
	autoSize();
	$(window).resize(autoSize);
	
	//获得当前的会员ID
	var memberId = 0;
	//判断是否有地址
	$.ajax({
		type:"post",
		url:" ",
		data:"memberId="+memberId,
		success:function(result){
			//返回的地址列表为空
			if(result==null){
				//显示新增地址界面
				$(".select-address").css("display","block");
				$(".main-info").css("display","none");
			}else{
				//添加地址到页面中
				var len = result.length;
				//逐个添加
				for(var i=0;i<result.length;i++){
					$(".select-address table tbody").prepend("");
				};
			};
		}
	});
	//地址框鼠标事件
	$(".select-address tr").each(function(n){
		//除了最后一个tr都有的事件
		if(n<$(".select-address tr").length-1){
			//点击事件
			$(this).click(function(){
				$(this).css({
					"margin" : "9px",
					"border" : "2px solid #1989E9",
					"background" : "url(./img/default.png) no-repeat right bottom",
					"background-origin" : "border-box"
				}).siblings().css({
					"margin" : "10px",
					"border" : "1px solid #d6d6d6",
					"background" : "none"
				});
			});
			//移入事件
			$(this).mouseover(function(){
				$(this).find("span").css("display","inline-block");
			});
			//移出事件
			$(this).mouseout(function(){
				$(this).find("span").css("display","none");
			});
			
			//点击设为默认
			$(this).find("#set-defaults").click(function(){
				$(this).css("display","none");
				$(this).parent("tr").siblings().find("#set-defaults").css("display","block");
			});
		};
		//添加地址
		if(n==$(".select-address tr").length-1){
			$(this).click(function(){
				$(".select-address").css("display","none");
				$(".main-info").css("display","block");
				$("#exit-add").css("display","inline-block");
			});
		};
	});
	
	//取消添加地址
	$("#exit-add").click(function(){
		$(".select-address").css("display","block");
		$(".main-info").css("display","none");
	});
	
	//点击删除和取消删除地址
	$(".no-delete").click(exitDelete);
	$(".close-ask").click(exitDelete);
	//点击确认删除
	$(".enter-delete").click(function(){
		//获得当前的地址ID
		var id = $(".center-delete").attr("data-delete-id");
		if(id==null){
			alert("地址删除失败！请刷新页面重试！");
		}else{
			//通过地址ID删除此地址
			$.ajax({
				type:"post",
				url:"deleteAddress.do",
				data:"deleteId="+id,
				success:function(result){
					if(result=="0"){
						alert("地址删除失败！请刷新页面重试！");
					}else if(result=="1"){
						//清除界面中对应的地址
						$(".address-list tbody tr").each(function(e){
							var trId = $(this).attr("data-id");
							if(trId==id){
								$(this).remove();
							};
						});
						//清空地址列
						if($(".address-list tbody tr").length==0){
							$('.haved-address').empty();
						};
						//判断地址列表是否为空，空就显示新增地址界面
						if($(".address-list .add-address").length==1){
							$(".select-address").css("display","none");
							$(".main-info").css("display","block");
							$("#exit-add").css("display","none");
						};
					};
				}
			});
		};
		exitDelete();
	});
	/**
	 * 个人信息页面
	 */
	//保存地址
	$("#save").click(saveAddress);
	//删除地址
	$(".enter-delete").click(function(){
		
	});
	
	//输入事件
	$("#consignee,#phone-number,#detail-address").keydown(function(event){
		var borderColor = colorRGBtoHex($(this).css("border"));
		if(borderColor = "#ff6700" && event.keyCode!=8){
			$(this).css("border-color","#d6d6d6")
			$(this).parent().find(".error-tip").remove();
		};
	});
	
});
