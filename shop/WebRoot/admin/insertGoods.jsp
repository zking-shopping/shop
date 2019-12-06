<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/shop/admin/css/base.css">
	<style>
		.table{
			width: 500px;
			height: 60px;
			margin: 0px auto;
			text-align: center;
			font-size: 24px;
			border: 1px solid black;
		}
		.table tr td{
			width: 250px;
			height: 60px;
			border: 1px solid black;
		}
		.submit{
			width: 66px;
			height: 36px;
			border: 2pd solid black;
			background-color: gray;
			border-radius: 14px;
			font-size: 18px;
			color: white;
		}
	</style>
  </head>
  
  <body>
		<nav>
			<container>
				<div class="myNav">
					<div class="logo"><a href="/shop/admin/index.jsp">Admin</a></div>
					<div class="strip">
						<ul>
							<li class="normal">��������</li>
							<li class="enlarge">��������</li>
							<li class="narrow">��С����</li>
						</ul>
					</div>
				</div>
			</container>
		</nav>
		<div class="content">
			<container>
				<div class="left">
					<ul>
						<li>
							<h3>ϵͳ����</h3>
							<div class="title">
								<p><a href="/shop/admin/system.jsp">ϵͳ˵��</a></p>
								<p><a href="/shop/admin/operation.jsp">Ȩ�޹���</a></p>
							</div>
						</li>
						<li>
							<h3>��Ʒ����</h3>
							<div class="title">
								<p><a href="admin/allGoods.do">ȫ����Ʒ</a></p>
								<p style="background : yellow;"><a>�����Ʒ</a></p>
							</div>
						</li>
						<li>
							<h3>��������</h3>
							<div class="title">
								<p><a href="admin/allOrder.do?state=2">��������</a></p>
							</div>
						</li>
						<li>
							<h3>��Ա����</h3>
							<div class="title">
								<p><a href="admin/allMember.do">ȫ���û�</a></p>
								<p><a href="admin/memberAcc.do">�û������ָ�</a></p>
							</div>
						</li>
						<li>
							<h3>���ݷ���</h3>
							<div class="title">
								<p><a href="admin/coreMember.do">�û�����</a></p>
								<p><a href="admin/rank.do">��Ʒ���а�</a></p>
								<p><a href="admin/analysis.do">��Ʒ���ݷ���</a></p>
							</div>
						</li>
						<li>
							<h3>��������</h3>
							<div class="title">
								<p><a href="/shop/admin/upload.jsp">��Ʒ��������</a></p>
							</div>
						</li>
					</ul>
				</div>
				<div class="right">
					<form class="addForm" method="post" action="admin/addGoods.do" enctype="multipart/form-data" >
						<table class="table">
							<tr>
								<td colspan="2">������Ʒ</td>
							</tr>
							<tr>
								<td>��Ʒ���ƣ�</td>
								<td><input type="text" name="goodsName" id="goodsName" onblur="goodsNameBlur()"></td>
							</tr>
							<tr>
								<td>��ƷͼƬ��</td>
								<td><img src="" width="60px" height="60px" id="img1"><input type="file" name="photo1" id="photo1" onchange="show1(this)" onblur=""/></td>
							</tr>
							<tr>
								<td>��Ʒ����ͼƬ1��</td>
								<td><img src="" width="60px" height="60px" id="img2"><input type="file" name="photo2" id="photo2" onchange="show2(this)"/></td>
							</tr>
							<tr>
								<td>��Ʒ����ͼƬ2��</td>
								<td><img src="" width="60px" height="60px" id="img3"><input type="file" name="photo3" id="photo3" onchange="show3(this)"/></td>
							</tr>
							<tr>
								<td>��Ʒ�۸�</td>
								<td>��<input type="text" name="price" id="price" onblur="priceBlur()"></td>
							</tr>
							<tr>
								<td>��Ʒ������</td>
								<td><input type="text" name="introduction" id="introduction" onblur="introductionBlur()"></td>
							</tr>
							<tr>
								<td>��Ʒ���ͣ�</td>
								<td>
									<select name="type">
										<jsp:useBean id="type" class="com.shopping.pojo.Type" scope="page"></jsp:useBean>
										<c:forEach items="${typeList }" var="type" varStatus="i">
											<option value="${type.id }">${type.name }</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td colspan="2">��ɫ</td>
							</tr>
							<tr>
								<td>��ɫ</td>
								<td>���</td>
							</tr>
							<tr>
								<td><input type="text" name="color" id="color" onblur="colorBlur()"></td>
								<td><input type="text" name="colorContent" id="colorContent" onblur="colorContentBlur()"></td>
							</tr>
							<tr>
								<td colspan="2"><input class="submit" type="submit" value="�ύ"></td>
							</tr>
						</table>
					</form>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script type="text/javascript">

function goodsNameBlur(){
	var reg = /^[\u4e00-\u9fa5]|[a-zA-z0-9]*$/;
	if(!reg.test($('#goodsName').val())){
		alert("��Ʒ�����������ġ�Ӣ�Ļ�����");
	}
}

function priceBlur(){
	var numberreg = /^[0-9]*$/;
	if(!numberreg.test($('#price').val())){
		alert("�۸�����������");
		$('#price').val(200);
	}
}

function introductionBlur(){
	var reg = /^[\u4e00-\u9fa5]|[a-zA-z0-9]*$/;
	if(!reg.test($('#introduction').val())){
		alert("�������������ġ�Ӣ�Ļ�����");
		$('#introduction').val("������");
	}
}

function colorBlur(){
	var reg = /^[\u4e00-\u9fa5]|[a-zA-z0-9]*$/;
	if(!reg.test($('#color').val())){
		alert("��ɫ���������ġ�Ӣ�Ļ�����");
		$('#color').val("��");
	}
}

function colorContentBlur(){
	var numberreg = /^[0-9]*$/;
	if(!numberreg.test($('#colorContent').val())){
		alert("��Ʒ��������������");
		$('#colorContent').val(200);
	}
}

$('.addForm').submit(function(){
	var photo1 = $('#photo1').val();
	var photo2 = $('#photo2').val();
	var photo3 = $('#photo3').val();
	var picreg = /\.(jpg|png|PNG)$/;
		
	if(photo1==null && photo2==null && photo3==null){
		alert("���ϴ�ͼƬ");
		return false;
	}else{
		return true;
	}
	
	if(!picreg.test(photo1) && !picreg.test(photo2) && !picreg.test(photo3)){
		alert("���ϴ���ȷ��ͼƬ����");
		return false;
	}else{
		return true;
	}
});

function show1(obj){
	var fr = new FileReader();
	var f = obj.files[0];
	fr.readAsDataURL(f);
	fr.onload=function(e){
		var content = e.target.result;
		document.getElementById("img1").src=content;
	}
}
function show2(obj){
	var fr = new FileReader();
	var f = obj.files[0];
	fr.readAsDataURL(f);
	fr.onload=function(e){
		var content = e.target.result;
		document.getElementById("img2").src=content;
	}
}
function show3(obj){
	var fr = new FileReader();
	var f = obj.files[0];
	fr.readAsDataURL(f);
	fr.onload=function(e){
		var content = e.target.result;
		document.getElementById("img3").src=content;
	}
}
var f = function(){
	$('.left>ul>li>h3').eq(1).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');;
	
}
(f)();
</script>
