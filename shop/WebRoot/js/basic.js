//将rgb颜色转换为十六进制颜色
function colorRGBtoHex(color) {
    var rgb = color.split(',');
    var r = parseInt(rgb[0].split('(')[1]);
    var g = parseInt(rgb[1]);
    var b = parseInt(rgb[2].split(')')[0]);
    var hex = "#" + ((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1);
    return hex;
 }

//获得地址栏参数值
function getUrlVal(property){
  //地址栏
  var urlStr = window.location.search.substring(1);
  var re = new RegExp('(^|&)'+ property +'=([^&]*)(&|$)');
  var result = urlStr.match(re);
  if(result == null){return null};
  return result[2];
};

//判断输入的账号是否符合要求
function checkUsername(cont){
	//设置用户名默认为不符合要求
	nameIsTrue = 1;
	//将元素的一些属性值初始化为匹配失败的属性值
	var inputBorderColor = '#ea3d3d';//输入框边框为红色
	var pTipContent = '';//错误提示框内容为空
	
	//获得内容长度
	var len = cont.length;
	var tlen = len;
	//检测前三位是否全为字母
	if(len>3){
		len=3;
	};
	var re1 = new RegExp("[A-Za-z]{"+len+"}");
	//如果账号前三位不全为字母，就提示用户
	if(!re1.test(cont.substring(0,3))){
		pTipContent = '用户账号前三位必须全为英文字母！';
	}else{
		//账号长度小于六位或大于16位
		if(tlen<6||tlen>16){
			//通过选择器找到对应的提示标签，改变其内容
			pTipContent = '用户帐号须由6-16个字符(数字、字母以及"_")组成！';
		}else{
			//通过选择器找到对应的提示标签，改变其内容
			pTipContent = '用户帐号不能包含字母、数字或下划线之外的字符！';
			if(!(/[^a-zA-Z0-9_]/g.test(cont))){
				inputBorderColor = '#CCCCCC';
				pTipContent = '';
				nameIsTrue = 0;
			};
		};
	};
	
	$('.username').css('border-color',inputBorderColor)
		.siblings('p').html(pTipContent);
};

//判断输入的密码是否符合要求
function checkPassword(cont){
	//设置密码默认为不符合要求
	passIsTrue = 1;
	//将元素的一些属性值初始化为匹配失败的属性值
	var inputBorderColor = '#ea3d3d';//输入框边框为红色
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
			inputBorderColor = '#cccccc';
			pTipContent = '';
			passIsTrue = 0;
		};
	};
	
	$('.password').css('border-color',inputBorderColor)
		.siblings('p').html(pTipContent);
};

//判断输入的手机号是否符合要求
function checkPhoneNumber(cont){
	//设置手机号默认为不符合要求
	cellIsTrue = 1;
	//将元素的一些属性值初始化为匹配失败的属性值
	var inputBorderColor = '#ea3d3d';//输入框边框为红色
	var pTipContent = '输入的手机号码格式有误！';//错误提示框内容为空
	
	//获得内容长度
	var len = cont.length;
	//判断是否是手机号码的正则表达式
	var re = /^1[3456789]\d{9}$/;
	if(re.test(cont)){
		inputBorderColor = '#cccccc';
		pTipContent = '';
		cellIsTrue = 0;
	};
	
	$('.tel').css('border-color',inputBorderColor)
		.siblings('p').html(pTipContent);
};

//变换步骤框的样式
function changUpTipStyle(name1){
	var name2 = '';
	var num = -1;
	if(name1=='username'){
		name2='password';
		num = 0;
	}else if(name1=='password'){
		name2=name1;
		name1='username';
		num = 0;
	}else if(name1=='tel'){
		name2='verify';
		num = 1;
	}else if(name1=='verify'){
		name2=name1;
		name1='tel';
		num = 1;
	};
//	$("input[name="+name+"]");
	//获得输入框的颜色
	var color1 = $("input[name="+name1+"]").css('border-color');
	var color2 = $("input[name="+name2+"]").css('border-color');
	//获得输入框的内容
	var cont1 = $("input[name="+name1+"]").val();
	var cont2 = $("input[name="+name2+"]").val();
	//都不符合要求
	if(colorRGBtoHex(color1)=='#ea3d3d'&&colorRGBtoHex(color2)=='#ea3d3d'){
		$('section .step ul li .line div').eq(num).css('background','none')
			.parent().css('background','#ea3d3d').parent().css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	};
	//都符合要求
	if((((nameIsTrue==0&&passIsTrue==0))&&(name1=='username'||name1=='password'))
		||(((cellIsTrue==0&&resultIsTrue==0))&&(name1!='username'&&name1!='password'))){
		$('section .step ul li .line div').eq(num).css('background','#2C82FF')
			.parent().css('background','#2C82FF').parent().css('color','#2C82FF')
			.children('.content').html(num+1+'').css('border-color','#2C82FF');
	};
	//其中一项不符合要求，另一项可能符合要求
	if((colorRGBtoHex(color1)=='#ea3d3d'&&colorRGBtoHex(color2)!='#ea3d3d')
		||(colorRGBtoHex(color1)!='#ea3d3d'&&colorRGBtoHex(color2)=='#ea3d3d')){
		$('section .step ul li .line div').eq(num).css('background','#ea3d3d')
			.parent().css('background','#F2F2F2').parent().css('color','#ea3d3d')
			.children('.content').html('!').css('border-color','#ea3d3d');
	}
	//其中一项符合要求，另一项为空
	if((((nameIsTrue==0&&cont2=='')||(passIsTrue==0&&cont1==''))&&(name1=='username'||name1=='password'))
		||(((cellIsTrue==0&&cont2=='')||(resultIsTrue==0&&cont1==''))&&(name1!='tel'&&name1!='verify'))){
		if(colorRGBtoHex(color1)!='#ea3d3d'&&colorRGBtoHex(color2)!='#ea3d3d'){
			$('section .step ul li .line div').eq(num).css('background','#2C82FF')
				.parent().css('background','#F2F2F2').parent().css('color','#2C82FF')
				.children('.content').html(num+1+'').css('border-color','#2C82FF');
		};
	};
	//都为空
	if(cont1==''&&cont2==''){
		if(colorRGBtoHex(color1)!='#ea3d3d'&&colorRGBtoHex(color2)!='#ea3d3d'){
			$('section .step ul li .line div').eq(num).css('background','#F2F2F2')
				.parent().css('background','#F2F2F2').parent().css('color','#2C82FF')
				.children('.content').html(num+1+'').css('border-color','#2C82FF');
		};
	};
};
//输入字符时样式变换
function inputChangeStyle(color1,color2,cont2,ele){
	var num = 0;
	if(ele=='tel'||ele=='verify'){
		num = 1;
	}else{
		num = 0;
	};
	//当前输入框为红色
	if(colorRGBtoHex(color1)=='#ea3d3d'){
		$('.'+ele).css('border-color','#2C82FF')
			.siblings('p').html('');
			if(ele=='verify'){
				$('.'+ele).css('border-color','#2C82FF')
					.siblings('.verify-tip').children('p').html('');
			};
		if(colorRGBtoHex(color2)=='#ea3d3d'){
			$('section .step ul li .line div').eq(num).css('background','#ea3d3d')
				.parent().css('background','#F2F2F2');
		};
		if(colorRGBtoHex(color2)=='#cccccc'){
			$('section .step ul li .line div').eq(num).css('background','#2C82FF')
				.parent('.line').css('background','#F2F2F2')
				.parent('li').css('color','#2C82FF')
				.children('.content').html(num+1+'').css('border-color','#2C82FF');
			if(cont2==''){
				$('section .step ul li .line div').eq(num).css('background','#F2F2F2')
					.parent('.line').css('background','#F2F2F2')
					.parent('li').css('color','#2C82FF')
					.children('.content').html(num+1+'').css('border-color','#2C82FF');
			};
		};
	};
	//当前输入框为蓝色
	if(colorRGBtoHex(color1)=='#2c82ff'){
		if(colorRGBtoHex(color2)=='#cccccc'){
			$('section .step ul li .line div').eq(num).css('background','#2C82FF')
				.parent('.line').css('background','#F2F2F2')
				.parent('li').css('color','#2C82FF')
				.children('.content').html(num+1+'').css('border-color','#2C82FF');
			if(cont2==''){
				$('section .step ul li .line div').eq(num).css('background','#F2F2F2')
					.parent('.line').css('background','#F2F2F2')
					.parent('li').css('color','#2C82FF')
					.children('.content').html(num+1+'').css('border-color','#2C82FF');
			};
		};
	};
};

//更换验证码
function replaceImageCode(){
	//加载验证码
	$('#codeImage').attr("src","/shop/imgCode?id="+new Date().getTime());
};

function getPageName(){
	var url = window.location.href;
	var pageName = url.substring(url.lastIndexOf("/")+1,url.indexOf("."));
	
	return pageName;
};