<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allOrder.jsp' starting page</title>
    
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
		.right table tr td{
			width: 150px;
			height: 50px;
			text-align: center;
		}
		.right 
	</style>
  </head>
  	
  <body>
    <nav>
			<container>
				<div class="myNav">
					<div class="logo"><a href="/shop/admin/index.jsp">Admin</a></div>
					<div class="strip">
						<ul>
							<li><a href="admin/allOrder.do?state=2">待发货</a></li>
							<li><a href="admin/allOrder.do?state=3">待结算</a></li>
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
								<p><a href="/shopp/admin/system.jsp">系统说明</a></p>
								<p><a href="/shop/admin/operation.jsp">权限管理</a></p>
							</div>
						</li>
						<li>
							<h3>产品管理</h3>
							<div class="title">
								<p><a href="admin/allGoods.do">全部商品</a></p>
								<p><a href="admin/insertGoods.do">添加商品</a></p>
							</div>
						</li>
						<li>
							<h3>订单管理</h3>
							<div class="title">
								<p style="background : yellow;"><a>订单管理</a></p>
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
				<jsp:useBean id="order" class="com.shopping.dto.OrderDto" scope="page"></jsp:useBean>
				<div class="right">
						<c:if test="${state =='2'}">
							<p>待发货</p>
						</c:if>
						<c:if test="${state =='3' }">
							<p>待结算</p>
						</c:if>
					<table>
						<c:if test="${fn:length(AllOrder) == 0 }">
							<p style="text-align:center;">暂无信息</p>
						</c:if>
						<c:if test="${fn:length(AllOrder)>'0'}">
							<tr><th>订单号</th><th>收货人</th><th>电话</th><th>总价</th><th>地址</th><th>下单时间</th><th>操作</th></tr>
							<c:forEach items="${AllOrder }" var="order" varStatus="i">
								<tr>
									<td>${order.orderNumber }</td>
									<td>${order.cousignee }</td>
									<td>${order.phoneNumber }</td>
									<td>${order.total }</td>
									<td>${order.provinces }${order.city }${order.area }</td>
									<td>${order.time }</td>
									<c:if test="${state =='2'}">
										<td><input type="submit" value="退订" onclick="del(${order.id})"><input type="submit" value="发货" onclick="send(${order.id})"></td>
									</c:if>
									<c:if test="${state =='3'}">
										<td><input type="submit" value="退订" onclick="del(${order.id})"></td>
									</c:if>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</container>
		</div>
  </body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
function del(id){
	doServlet(id, 5, ${state});
}
function send(id){
	doServlet(id, 3, ${state});
}
function doServlet(id,state,toState){
	$.ajax({
		url : 'admin/orderChangeState.do',
		type : 'post',
		data : {"id" : id, "state" : state},
		success : function(result){
			location.href = "admin/allOrder.do?state="+toState;
		}
	});
}
var f = function(){
	$('.left>ul>li>h3').eq(2).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');;
}
(f)();
</script>
