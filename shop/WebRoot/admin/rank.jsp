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
		.right .click{
			width: 440px;
			height: 500px;
			float: left;
		}
		.right .click table tr td,th{
			width: 110px;
			height: 60px;
			text-align: center;
			line-height: 30px;
			border: 1px solid black;
			padding: 0px;
			margin: 0px;
		}
		.right .buy{
			width: 440px;
			height: 500px;
			float: left;
			padding-left: 20px;
		}
		.right .buy table tr td,th{
			width: 110px;
			height: 60px;
			text-align: center;
			line-height: 30px;
			border: 1px solid black;
			padding: 0px;
			margin: 0px;
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
								<p><a href="admin/insertGoods.do">添加商品</a></p>
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
								<p style="background : yellow;"><a>商品排行榜</a></p>
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
					<div class="click">
						<table>
							<tr><th colspan="4"><input type="submit" onclick="clickPrev()" value="◀">点击排行榜<input type="submit" onclick="clickNext()" value="▶"></th></tr>
							<tr><td>商品名称</td><td>商品价格</td><td>商品类型</td><td>点击量</td></tr>
							<jsp:useBean id="goodsStatisticsClick" class="com.shopping.dto.GoodsStatisticsDto" scope="page"></jsp:useBean>
							<c:forEach items="${GoodsStatisticsClickList }" var="goodsStatisticsClick" varStatus="i">
								<tr>
									<td>${goodsStatisticsClick.goodsName }</td>
									<td>${goodsStatisticsClick.price }</td>
									<td>${goodsStatisticsClick.type }</td>
									<td>${goodsStatisticsClick.clickNumber }</td> 
								</tr>
							</c:forEach>
						</table>
					</div>
					<div class="buy">
						<table>
							<tr><th colspan="4"><input type="submit" onclick="buyPrev()" value="◀">销售排行榜<input type="submit" onclick="buyNext()" value="▶"></th></tr>
							<tr><td>商品名称</td><td>商品价格</td><td>商品类型</td><td>销售量</td></tr>
							<jsp:useBean id="goodsStatisticsBuy" class="com.shopping.dto.GoodsStatisticsDto" scope="page"></jsp:useBean>
							<c:forEach items="${GoodsStatisticsBuyNumberList }" var="goodsStatisticsBuy" varStatus="i">
								<tr>
									<td>${goodsStatisticsBuy.goodsName }</td>
									<td>${goodsStatisticsBuy.price }</td>
									<td>${goodsStatisticsBuy.type }</td>
									<td>${goodsStatisticsBuy.buyNumber }</td> 
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
function clickPrev(){
	location.href = "admin/rank.do?clickPage="+${clickPageNumber-1}+"&buyPage="+${buyPageNumber};
}
function clickNext(){
	location.href = "admin/rank.do?clickPage="+${clickPageNumber+1}+"&buyPage="+${buyPageNumber};
}
function buyPrev(){
	location.href = "admin/rank.do?buyPage="+${buyPageNumber-1}+"&clickPage="+${clickPageNumber};
}
function buyNext(){
	location.href = "admin/rank.do?buyPage="+${buyPageNumber+1}+"&clickPage="+${clickPageNumber};
}
var f = function(){
	$('.left>ul>li>h3').eq(4).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');;
}
(f)();
</script>