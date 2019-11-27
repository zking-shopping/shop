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
