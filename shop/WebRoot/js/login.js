
$('a').prop('target','_blank');

//点击登录按钮
function login(obj){
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
	if(username!=''&&password!=''){
		
		 $.ajax({
             url : "/shop/login",
             type : "POST",
             dataType : "json",
             data : {
     			username : username,
     			password : password,
             },
             success : function(result) {
//                 $("#mydiv").html(result.name);
            	 System.out.println("成功了");
             },
             error : function(xhr) {
 				$('.name-tip').html(xhr).siblings('input').css('border-color','#ff6700');
             }
         });
	};
}

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
