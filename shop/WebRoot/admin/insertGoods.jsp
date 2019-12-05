<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
							<li class="normal">正常字体</li>
							<li class="enlarge">增大字体</li>
							<li class="narrow">缩小字体</li>
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
							<h3>系统管理</h3>
							<div class="title">
								<p><a href="/shop/admin/system.jsp">系统说明</a></p>
								<p><a href="/shop/admin/operation.jsp">权限管理</a></p>
							</div>
						</li>
						<li>
							<h3>产品管理</h3>
							<div class="title">
								<p><a href="admin/allGoods.do">全部商品</a></p>
								<p style="background : yellow;"><a>添加商品</a></p>
							</div>
						</li>
						<li>
							<h3>订单管理</h3>
							<div class="title">
								<p><a href="admin/allOrder.do?state=2">订单管理</a></p>
							</div>
						</li>
						<li>
							<h3>会员管理</h3>
							<div class="title">
								<p><a href="admin/allMember.do">全部用户</a></p>
								<p><a href="admin/memberAcc.do">用户封号与恢复</a></p>
							</div>
						</li>
						<li>
							<h3>数据分析</h3>
							<div class="title">
								<p><a href="admin/coreMember.do">用户数据</a></p>
								<p><a href="admin/rank.do">商品排行榜</a></p>
								<p><a href="admin/analysis.do">商品数据分析</a></p>
							</div>
						</li>
						<li>
							<h3>下载中心</h3>
							<div class="title">
								<p><a href="/shop/admin/upload.jsp">商品数据下载</a></p>
							</div>
						</li>
					</ul>
				</div>
				<div class="right">
					<form class="addForm" method="post" action="admin/addGoods.do" enctype="multipart/form-data">
						<table class="table">
							<tr>
								<td colspan="2">新增商品</td>
							</tr>
							<tr>
								<td>商品名称：</td>
								<td><input type="text" name="goodsName" id="goodsName"></td>
							</tr>
							<tr>
								<td>商品图片：</td>
								<td><img src="" width="60px" height="60px" id="img1"><input type="file" name = "photo1" onchange="show1(this)"/></td>
							</tr>
							<tr>
								<td>商品介绍图片1：</td>
								<td><img src="" width="60px" height="60px" id="img2"><input type="file" name = "photo2" onchange="show2(this)"/></td>
							</tr>
							<tr>
								<td>商品介绍图片2：</td>
								<td><img src="" width="60px" height="60px" id="img3"><input type="file" name = "photo3" onchange="show3(this)"/></td>
							</tr>
							<tr>
								<td>商品价格：</td>
								<td>￥<input type="text" name="price" id="price"></td>
							</tr>
							<tr>
								<td>商品描述：</td>
								<td><input type="text" name="introduction" id="introduction"></td>
							</tr>
							<tr>
								<td>商品类型：</td>
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
								<td colspan="2">颜色</td>
							</tr>
							<tr>
								<td>颜色</td>
								<td>存货</td>
							</tr>
							<tr>
								<td><input type="text" name="color"></td>
								<td><input type="text" name="colorContent"></td>
							</tr>
							<tr>
								<td colspan="2"><input class="submit" type="submit" value="提交"></td>
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
<script>
$('.addForm').submit(function(){
	alert("aa");
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