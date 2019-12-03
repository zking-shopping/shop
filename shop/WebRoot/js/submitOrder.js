//取消删除方法
function exitDelete(){
	$(".transparent").css("display","none");
	$(".center-delete").css("display","none");
};

//设置确认你删除界面的大小
function autoSize(){
	//获得窗口的大小
	var windowWidth = window.innerWidth;
	var windowHeight = window.innerHeight;
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
	//获得所有已有id
	var idStr = "id0="+$(obj).parents("tr").attr("data-id");
	
	$(obj).parents("tr").siblings().each(function(e){var lens = $(this).find("span").length;
		var lens = $(this).find("span").length;
		//获得元素对应的id
		if(lens!=0){
			idStr += "&id"+(e+1)+"="+$(this).attr("data-id");
		};
	});
	
	var id = $(obj).parents("tr").attr("data-id");
	//更改数据库数据
	$.ajax({
		type: "post",
		url: "setDefaultAddress.do",
		data: idStr,
		success:function(result){
			$(obj).css("display","none");
			//判断当前表格是否有显示默认
			var len = $(obj).siblings("#default-address").length;
			if(len>0){
				$(obj).siblings("#default-address").css("display","inline-block");
				$(obj).parents("tr").siblings("tr").find("#default-address").css("display","none");
			};
		}
	});
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
			
			//点击删除按钮
			$(this).find("#delete").click(function(){
				$(".transparent").css("display","block");
				$(".center-delete").css("display","block");
				
				$(".enter-delete").click(function(){
					//获得当前的会员ID
					var memberId = 0;
					//通过会员ID删除此地址
					$.ajax({
						type:"post",
						url:" ",
						data:"memberId="+memberId,
						success:function(result){
							//清除界面中对应的地址
							$(".select-address tr").eq(n).remove();
							$(".transparent").css("display","none");
							$(".center-delete").css("display","none");
							//判断地址列表是否为空，空就显示新增地址界面
							if($(".select-address tr").length==1){
								$(".select-address").css("display","none");
								$(".main-info").css("display","block");
								$("#exit-add").css("display","none");
							};
						}
					});
				});
					
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

	/**
	 * 个人信息页面
	 */
	//新增地址
	$("#save").click(function(){
		//获得输入的值
		var cousignee = $("#consignee").val();
		var phoneNumbers = $("#phone-number").val();
		var provinces = $("#provinces").val();
		var city = $("#city").val();
		var area = $("#area").val();
		var detailAddress = $("#detail-address").val();
		var defaultAddress = $("#default").prop("checked");
		
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
				url:"savaAddress.do",
				data:"cousignee="+cousignee+"&phoneNumber="+phoneNumbers+"&provinces="+provinces+"&city="+city+
					"&area="+area+"&detailAddress="+detailAddress+"&defaultAddress="+defaultAddress,
				success:function(result){
					console.log(6666);
				}
			});
		};
	});
	
	//输入事件
	$("#consignee,#phone-number,#detail-address").keydown(function(event){
		var borderColor = colorRGBtoHex($(this).css("border"));
		if(borderColor = "#ff6700" && event.keyCode!=8){
			$(this).css("border-color","#d6d6d6")
			$(this).parent().find(".error-tip").remove();
		};
	});
	
	//点击删除方法
	$(".enter-delete").click(function(){
		
	});
	
	//点击保存地址按钮
	$("#save").click(function(){
	//	//获得所有已经填写的信息
	//	var cousignee = $("#consignee").val();
	//	var phoneNumber = $("#phone-number").val();
	//	var provinces = $("#provinces").val();
	//	var city = $("#city").val();
	//	var areas = $("#area").val();
	//	var detailAddress = $("#detail-address").val();
	//	var isDefault = $("#default").is(":checked");
	//	
	//	if(isDefault==true){
	//		
	//	};
	//	//保存到数据库
	//	$.ajax({
	//		type:"post",
	//		url:" ",
	//		data:"cousignee="+cousignee+"&phoneNumber="+"&provinces="+provinces+
	//				"&city="+city+"&areas="+areas+"&detailAddress="+detailAddress,
	//		success:function(result){
	//			if(isDefault==true){
	//				console.log(666);
	//			};
	//		}
	//	});
	});
});
