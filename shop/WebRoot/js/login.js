//定义判断用户名、密码以及手机号是否符合要求的初始值，默认都不符合
var nameIsTrue = 1;
var passIsTrue = 1;
var cellIsTrue = 1;
//定义判断结果是否正确的初始值，默认不正确
var resultIsTrue = 1;
var result = 0;
$('a').prop('target','_blank');



//点击登录按钮
function login(){
	var username = $('.username').val();
	var password = $('.password').val();
	if(username==''){
		$('.name-tip').html('请输入账号！').siblings('input').css('border-color','#ff6700');
	};
	if(password==''){
		$('.pass-tip').html('请输入密码！').siblings('input').css('border-color','#ff6700');
	};
	if(username!=''&&password!=''){
		var frm = document.getElementById("frm");
		frm.submit();
	};
}

//输入事件
$('#frm>div>input').keydown(function(event){
	//获得事件的主体元素
	var ele = $(this).attr('class');
	//获得输入框的颜色
	var color1 = $(this).css('border-color');
	//获得输入框的内容
	var cont1 = $(this).val();
	
	if(!(cont1==''&&event.keyCode==8)){
		$(this).css('border-color','#CCCCCC').siblings('p').html('');
	};
});

//输入框失去焦点
$('#frm>div>input').blur(function(){
	//如果输入框边框不是红色
	if(colorRGBtoHex($(this).css('border-color'))=='#ea3d3d'){
		$('.login').attr("disabled",false);
	}
	//获得输入框内容
	var cont = $(this).val();
	//获得输入框的name属性
	var name1 = $(this).attr('name');
	var name2 = null;
	if(cont!=''){
		if(name1=='username'){
			//调用方法判断用户名是否符合规范
			checkUsername(cont);
			name2='password';
		}else if(name1=='password'){
			//调用方法判断密码是否符合规范
			checkPassword(cont);
			name2='username';
		}else if(name1=='tel'){
			//调用方法判断手机号是否符合规范
			checkPhoneNumber(cont);
			name2='verify';
		}else if(name1=='verify'){
			//调用方法判断结果是否正确
			checkResult(cont,result);
			name2='tel';
		};
	}else{
		//如果输入框边框不是红色
		if(colorRGBtoHex($(this).css('border-color'))!='#ea3d3d'){
			$(this).css('border-color','#CCCCCC')
				.siblings('p').html('');
		}
	};
	changUpTipStyle(name1);
});

//输入框输入事件
$('#frm>div>input').keydown(function(event){
	//获得事件的主体元素
	var ele = $(this).attr('class');
	//获得输入框的颜色
	var color1 = $(this).css('border-color');
	var color2 = $(this).parent('div').siblings('div')
					.children('input').css('border-color');
	//获得输入框的内容
	var cont1 = $(this).val();
	var cont2 = $(this).parent('div').siblings('div').children('input').val();
	
	if(!(cont1==''&&event.keyCode==8)){
		inputChangeStyle(color1,color2,cont2,ele);
	};
});

//输入框获焦事件
$('#frm>div>input').focus(function(){
	if(colorRGBtoHex($(this).css('border-color'))!='#ea3d3d'){
		$(this).css('border-color','#2C82FF');
	};
});
