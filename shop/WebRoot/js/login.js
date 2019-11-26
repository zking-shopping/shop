
$('a').prop('target','_blank');

//点击登录按钮
$('.login').click(function(){
	var username = $('.username').val();
	var password = $('.password').val();
	console.log(username);
	console.log(password);
	if(username==''){
		$('.name-tip').html('请输入账号！').siblings('input').css('border-color','#ff6700');
	};
	if(password==''){
		$('.pass-tip').html('请输入密码！').siblings('input').css('border-color','#ff6700');
	};
	if(usernames!=''&&passwords!=''){
//		$.post('http://www.wjian.top/shop/api_user.php',{
//			status : 'login',
//			username : usernames,
//			password : passwords,
//		}, function(re){
//			console.log(JSON.parse(re));
//			if(code==0){
//				
//			};
//			if(code==1000||code==2002){
//				$('.name-tip').html(result).siblings('input').css('border-color','#ff6700');
//			};
//			if(code==1001){
//				$('.pass-tip').html(result).siblings('input').css('border-color','#ff6700');
//			};
//		});
		
		 $.ajax({
             url : ".login",
             type : "POST",
             async : true,
             dataType : "json",
             data : {
     			username : usernames,
     			password : passwords,
             },
             success : function(result) {
//                 $("#mydiv").html(result.name);
            	 System.out.println("成功了");
             },
             error : function(xhr) {
 				$('.name-tip').html(result).siblings('input').css('border-color','#ff6700');
             }
         });
	};
});

//输入事件
$('.content>form>div>input').keydown(function(event){
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
