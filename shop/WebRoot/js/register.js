//定义判断用户名、密码以及手机号是否符合要求的初始值，默认都不符合
var nameIsTrue = 1;
var passIsTrue = 1;
var cellIsTrue = 1;
//定义判断结果是否正确的初始值，默认不正确
var resultIsTrue = 1;
var result = 0;

//输入框移出移入事件
$('section div form div input').mouseover(function(){
	//边框为灰色就变蓝
	if(colorRGBtoHex($(this).css('border'))=='#cccccc'){
		$(this).css('border-color','#076BF1');
	};
});
$('section div form div input').mouseout(function(){
	//边框为蓝色就变灰
	if(colorRGBtoHex($(this).css('border'))=='#076bf1'){
		$(this).css('border-color','#cccccc');
	};
});

//输入框获焦事件
$('section div form div input').focus(function(){
	if(colorRGBtoHex($(this).css('border-color'))!='#ea3d3d'){
		$(this).css('border-color','#2C82FF');
	};
});
//输入框失焦事件
$('section div form div input').blur(function(){
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
$('section div form div input').keydown(function(event){
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

//下一步按钮点击事件
$('.next-step').click(function(){
	//获得输入框中的用户名和密码
	var name = $('.username').val();
	var pass = $('.password').val();
	//输入框都为空
	if(name==''&&pass==''){
		//改变输入框的边框颜色
		$('section .register-step1 form div input').css('border-color','#ea3d3d');
		//改变提示框的内容
		$('section .register-step1 form div p').eq(0).html('请输入账号！');
		$('section .register-step1 form div p').eq(1).html('请输入密码！');
		$('section .step ul li .line div').eq(0).css('background','none')
			.parent('.line').css('background','#ea3d3d')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(name!=''&&pass==''){
		$('section .register-step1 form div p').eq(1).html('请输入密码！');
		$('section .step ul li .line div').eq(0).css('background','#ea3d3d')
			.parent('.line').css('background','#F2F2F2')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(name==''&&pass!=''){
		$('section .register-step1 form div p').eq(0).html('请输入密码！');
		$('section .step ul li .line div').eq(0).css('background','#ea3d3d')
			.parent('.line').css('background','#F2F2F2')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(nameIsTrue==0&&passIsTrue==0&&colorRGBtoHex($('.username').css('border-color'))!='#ea3d3d'){
		//改变顶部步骤的样式
		$('section .step ul li .line div').eq(0).css('background','none')
			.parent('.line').css('background','#2C82FF')
			.parent('li').css('color','#2C82FF')
			.children('.content').html('').css('border-color','#2C82FF');
		$('section .step ul li .line div').eq(1).css('background','none')
			.parent('.line').css('background','#F2F2F2')
			.parent('li').css({
				'color' : '#2C82FF',
				'font-weight' : 'bold'})
			.children('.content').html('2').css({
				'border-color' : '#2C82FF',
				'border-width' : 2});
		//更换背景图片
		$('section .step>ul>li>.content').eq(0).css({
			'background' : 'url(./img/true.png) 3px 5px no-repeat',
			'background-size' :'80% 80%',
			'background-color' : '#2C82FF'
		});
		//加载验证码
		replaceImageCode();
		//第一步隐藏，第二步显示
		$('section .register-step1').css('display','none');
		$('section .register-step2').css('display','block');
	};
});

//点击上一步的按钮
$('.prev').click(function(){
	//第二步隐藏，第一步显示
	$('section .register-step1').css('display','block');
	$('section .register-step2').css('display','none');
	$('section .step ul li').eq(0).css({
			'color' : '#2C82FF',
			'font-weight' : 'bold'})
		.children('.content').html('1').css({
			'background' : 'none',
			'border-color' : '#2C82FF',
			'border-width' : 2});
	$('section .step ul li .line div').eq(1).css('background','none')
		.parent('.line').css('background','#F2F2F2')
		.parent('li').css({
			'color' : '#D8D8D8',
			'font-weight' : 'normal'})
		.children('.content').html('2').css({
			'border-color' : '#D8D8D8',
			'border-width' : 1});
	//清除全部样式和内容
	$('.tel').val('').css('border-color','#CCCCCC');
	$('.tel-tip').html('');
	$('.verify').val('').css('border-color','#CCCCCC');
	$('.res-tip').html('');
	$('.agreebox').prop("checked",false);
});

//点击注册的按钮
$('.ensure-reg').click(function(){
	//获得输入框中的用户名和密码
	var tel = $('.tel').val();
	var result = $('.verify').val();
	var usernames = $('.username').val();
	var passwords = $('.password').val();
	//同意协议没点
	if(!($('.agreebox').is(':checked'))){
		alert('您需要同意相关条款才能注册!');
	};
	//输入框都为空
	if(tel==''&&result==''){
		//改变输入框的边框颜色
		$('section .register-step2 form div input').css('border-color','#ea3d3d');
		//改变提示框的内容
		$('.tel-tip').html('请输入手机号码！');
		$('.res-tip').html('请输入结果！');
		$('section .step ul li .line div').eq(1).css('background','none')
			.parent('.line').css('background','#ea3d3d')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(tel!=''&&result==''){
		$('.res-tip').html('请输入结果！');
		$('section .step ul li .line div').eq(1).css('background','#ea3d3d')
			.parent('.line').css('background','#F2F2F2')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(tel==''&&result!=''){
		$('.tel-tip').html('请输入密码！');
		$('section .step ul li .line div').eq(1).css('background','#ea3d3d')
			.parent('.line').css('background','#F2F2F2')
			.parent('li').css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	if(cellIsTrue==0&&resultIsTrue==0&&$('.agreebox').is(':checked')){
		//验证账号
		$.post('http://www.wjian.top/shop/api_user.php',{
			status : 'register',
			username : usernames,
			password : passwords,
		}, function(re){
			console.log(JSON.parse(re));
			var code = JSON.parse(re).code;
			var tip = JSON.parse(re).message;
			if(code==2001){
				//第二步隐藏，第一步显示
				$('section .register-step1').css('display','block');
				$('section .register-step2').css('display','none');
				//提示用户
				$('.username').css('border-color','#ea3d3d')
					.siblings('p').html(tip);
				//将当前顶部步骤样式退回
				$('section .step ul li .line div').eq(1).css('background','none')
					.parent('.line').css('background','#F2F2F2')
					.parent('li').css({
						'color' : '#D8D8D8',
						'font-weight' : 'normal'})
					.children('.content').html('2').css({
						'border-color' : '#D8D8D8',
						'border-width' : 1});
				$('section .step ul li .line div').eq(0).css('background','#ea3d3d')
					.parent('.line').css('background','#F2F2F2')
					.parent('li').css('color','#ea3d3d')
					.children('.content').html('!').css({
						'border-color' : '#ea3d3d',
						'background' : 'none'});
				//清除全部样式和内容
				$('.tel').val('').css('border-color','#CCCCCC');
				$('.tel-tip').html('');
				$('.verify').val('').css('border-color','#CCCCCC');
				$('.res-tip').html('');
				$('.agreebox').prop("checked",false);
			}else{
				//改变顶部步骤的样式
				$('section .step ul li .line div').eq(1).css('background','none')
					.parent('.line').css('background','#2C82FF')
					.parent('li').css({
						'color' : '#2C82FF',
						'font-weight' : 'bold'})
					.children('.content').html('').css({
						'border-color' : '#2C82FF',
						'border-width' : 2});
				$('section .step ul li').eq(2).css({
						'color' : '#2C82FF',
						'font-weight' : 'bold'})
					.children('.content').html('').css({
						'border-color' : '#2C82FF',
						'border-width' : 2});
				//更换背景图片
				$('section .step>ul>li>.content').css({
					'background' : 'url(./img/true.png) 3px 5px no-repeat',
					'background-size' :'80% 80%',
					'background-color' : '#2C82FF'
				});
				//获得账号
				var account = $('.username').val();
				$('.register-result>p>span').html(account);
				//第二步隐藏，第三步显示
				$('section .register-step2').css('display','none');
				$('section .register-step3').css('display','block');
			};
		});
	};
});

//点击按钮跳转到登录界面
$('.goto-login').click(function(){
	window.open('login.html');
});
