$(function(){
	var addressId = $("#addressId").val();
	var total = $("#total").val();
	var receiveInfo = $("#receiveInfo").val();
	var id = $("#id").val();
	console.log(id);
	console.log(addressId);
	console.log(total);
	console.log(receiveInfo);
	
	$("#immediate-payment").click(function(){
		//传值修改订单状态
		$.ajax({
			type:"post",
			url:"immediatePayment.do",
			data:"id="+id,
			success:function(result){
				
			}
		});
	});
});