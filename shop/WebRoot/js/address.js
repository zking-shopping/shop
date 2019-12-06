//清空输入的数据
function cleanAllInput(){
	$("#consignee").val("");
	$("#phone-number").val("");
	$("#provinces option").eq(0).attr("selected",true);
	$("#city option").eq(0).attr("selected",true);
	$("#area option").eq(0).attr("selected",true);
	$("#detail-address").val("");
	$("#default").attr("checked",false);
	
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
	var len = $(obj).parent().siblings("thead").length;
	if(len==0){
		$(obj).find("span").css("display","inline-block");
	}else{
		var displays = $(obj).find("#default-address").css("display");
		if(displays!="inline-block"){
			$(obj).find("#set-default-address").css("display","inline-block");
		};
	};
};
function defaultDisplayOut(obj){
	var len = $(obj).parent().siblings("thead").length;
	if(len==0){
		$(obj).find("span").css("display","none");
	}else{
		var displays = $(obj).find("#default-address").css("display");
		if(displays!="inline-block"){
			$(obj).find("#set-default-address").css("display","none");
		};
	};
};
//更改默认地址
function setDefaultAddress(obj){
	//获得已经默认的地址的id
	var hadDefaultId = null;
	$(obj).parents("tr").siblings().each(function(e){
		var isDefault = $(this).attr("data-defaultAddress");
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
			}else{
				$(obj).css("display","none");
				$(obj).parents("tr").siblings("tr").find("#set-defaults").css("display","inline-block");
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
		var myAddress1 = `
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
		var myAddress2 = `
			<tr data-cousignee=${value.cousignee} data-phonenumber=${value.phoneNumber}
			 data-provinces=${value.provinces} data-city=${value.city}
			 data-area=${value.area} data-detailaddress=${value.detailAddress}
			 data-id=${value.id} data-defaultaddress=${value.defaultAddress}
			 onmouseover="defaultDisplayOver(this)" onmouseout="defaultDisplayOut(this)"
			 onclick="changeState(this)">
				<td id="consignees"><p>${value.cousignee}</p></td>
				<td id="phone-numbers"><p>${value.phoneNumber}</p></td>
				<td id="address"><p>${allAddress}</p></td>
				<td id="operation">
					<span id="modify" onclick="modifyAddress(this)">修改</span>
					<span id="separator">|</span>
					<span id="delete" onclick="deleteAddress(this)">删除</span>
				</td>
				<td id="set-defaults" onclick="setDefaultAddress(this)">设为默认</td>
			</tr>
		`;
		
		if($('.address-list thead').length!=0){
			$('.address-list tbody').append(myAddress1);
		}else{
			$('.address-list tbody').append(myAddress2);
			if(result.length==(i+1)){
				var str = `
					<tr id="add-address">
						<td id="add-address" onclick="addAddress()">
							<img src="img/add.png" />
							<p>添加新的地址</p>
						</td>
					</tr>
				`;
				$('.address-list tbody').append(str);
			};
		};
		
		//设置默认地址的显示状况
		var defaultAddress = value.defaultAddress;
		if(defaultAddress=="true"){
			$('.address-list #default-address').eq(i).css("display","inline-block");
			if($("#exit-add").length!=0){
				$('.address-list #set-defaults').eq(i).css("display","none");
				changeState($('.address-list #set-defaults').eq(i).parents("tr"));
			};
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
		console.log(999);
		jumpMode = "modifyAddress.do";
		id = $("#save").attr("data-id");
	};
	
	if(canSave==false){
		if($(".error-tip").length==0){
			var ele = `
				<p class="error-tip">
					<img src="/shop/img/error.png">
					<span class="error-text">${tip}</span>
				</p>
			`;
			//添加提示
			$(object).parent().append(ele);
			$(object).css("border-color","#FF6700");
		};
	}else{
		//传值
		$.ajax({
			type:"post",
			url: jumpMode,
			data:"cousignee="+cousignee+"&phoneNumber="+phoneNumbers+"&provinces="+provinces+"&city="+city+
				"&area="+area+"&detailAddress="+detailAddress+"&defaultAddress="+defaultAddress+"&saveId="+id,
			success:function(result){
				if(result.length==0){
					alert("地址设置出现错误，请重试！");
					window.location.reload();
				}else{
					$(".address-list tbody tr").remove();
					//更新表格
					updateAddressList(result);
					//清空输入的数据
					cleanAllInput();
					if($("#exit-add").length!=0){
						$(".select-address").css("display","block");
						$(".main-info").css("display","none");
					};
				};
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
	if($("#exit-add").length!=0){
		$(".select-address").css("display","none");
		$(".main-info").css("display","block");
		$("#exit-add").css("display","inline-block");
	};
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
	$("#provinces").find("option:contains('"+provinces+"')").attr("selected",true);
	changeCity();
	$("#city").find("option:contains('"+city+"')").attr("selected",true);
	changeArea();
	$("#area").find("option:contains('"+area+"')").attr("selected",true);
	$("#detail-address").val(detailAddress);
	if(defaultAddress=="true"){
		$("#default").attr("checked",true);
	};
	
	//传id
	var id = $(obj).parents("tr").attr("data-id");
	$("#save").attr("data-id",id);
	
};

$(function(){
	autoSize();
	$(window).resize(autoSize);
	
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
						if($(".address-list tr#add-address").length==1 && $(".address-list tr").length==1){
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
	
	//输入事件
	$("#consignee,#phone-number,#detail-address").keydown(function(event){
		var borderColor = colorRGBtoHex($(this).css("border"));
		if(borderColor = "#ff6700" && event.keyCode!=8){
			$(this).css("border-color","#d6d6d6")
			$(this).parent().find(".error-tip").remove();
		};
	});
	
});