<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		.right .top{
			width: 100%;
			height: 300px;
		}
		.right .top .sta{
			width: 300px;
			height: 300px;
			float: left;
			background-color: gray;
			border-radius: 50%;
			position: relative;
			line-height: 300px;
			text-align: right;
			font-size: 16px;
		}
		.right .top .sta .second{
			width: 200px;
			height: 200px;
			background-color: orange;
			border-radius: 50%;
			position: absolute;
			top: 50px;
			left: 50px;
			line-height: 200px;
			text-align: right;
			font-size: 14px;
		}
		.right .top .sta .second .core{
			width: 100px;
			height: 100px;
			background-color: red;
			border-radius: 50%;
			position: absolute;
			top: 50px;
			left: 50px;
			line-height: 100px;
			text-align: center;
			font-size: 16px;
		}
		.right .top .info{
			width: 630px;
			height: 300px;
			float: left;
		}
		.right .top .info p{
			width: 200px;
			height: 30px;
			font-size: 20px;
			text-align: center;
			line-height: 30px;
			color: #FFDAB9;
		}
		.right .top .info table{
			margin: 0px auto;
		}
		.right .top .info table tr th{
			width: 200px;
			height: 40px;
			font-size: 20px;
			font-weight: bold;
			text-align: center;
		}
		.right .top .info table tr td{
			width: 200px;
			height: 40px;
			line-height: 40px;
			text-align: left;
		}
		.right .bottom{
			padding-top: 20px;
		}
		#button{
			width: 50px;
			height: 24px;
			line-height: 24px;
			text-align: center;
			border-radius: 10px; 
			border: none;
		}
		#button:hover{
			background-color: blue;
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
								<p style="background : yellow;"><a>用户数据</a></p>
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
					<div class="top">
						<div class="sta">
							<div class="second">
								<div class="core">
									<c:if test="${coreMember>0 }">
										<fmt:formatNumber type="number" maxFractionDigits="2" value="${coreMember*100/totalMember }"></fmt:formatNumber>%
									</c:if>
									<c:if test="${coreMember==0 }">
										0%
									</c:if>
								</div>
								<c:if test="${secondMember>0 }">
									<fmt:formatNumber type="number" maxFractionDigits="2" value="${secondMember*100/totalMember }"></fmt:formatNumber>%
								</c:if>
								<c:if test="${secondMember==0 }">
									0%
								</c:if>
							</div>
							<c:if test="${edgeMember>0 }">
								<fmt:formatNumber type="number" maxFractionDigits="2" value="${edgeMember*100/totalMember }"></fmt:formatNumber>%
							</c:if>
							<c:if test="${edgeMember==0 }">
								0%
							</c:if>
						</div>
						<div class="info">
							<p style="background-color:red;">核心用户
								<c:if test="${coreMember>0 }">
									<fmt:formatNumber type="number" maxFractionDigits="2" value="${coreMember*100/totalMember }"></fmt:formatNumber>%
								</c:if>
								<c:if test="${coreMember==0 }">
									0%
								</c:if>
							</p>
							<p style="background-color:orange;">次级用户
								<c:if test="${secondMember>0 }">
									<fmt:formatNumber type="number" maxFractionDigits="2" value="${secondMember*100/totalMember }"></fmt:formatNumber>%
								</c:if>
								<c:if test="${secondMember==0 }">
									0%
								</c:if>
							</p>
							<p style="background-color:gray;">边缘用户
								<c:if test="${edgeMember>0 }">
									<fmt:formatNumber type="number" maxFractionDigits="2" value="${edgeMember*100/totalMember }"></fmt:formatNumber>%
								</c:if>
								<c:if test="${edgeMember==0 }">
									0%
								</c:if>
							</p>
							<table>
								<tr><th>2019年${month }月</th><th><input type="submit" value="上一月" onclick="prev(${month})"><input type="submit" value="下一月" onclick="next(${month})"></th></tr>
								<tr><td>全部用户（人）</td><td>${totalMember }</td></tr>
								<tr><td>用户平均在线（分钟/月）</td><td>${avgTime }</td></tr>
								<tr><td>用户平均消费（人民币/月）</td><td>${avgCost }</td></tr>
							</table>
						</div>
					</div>
					<div class="bottom">
						<form action="admin/coreMember.do" method="post">
							<table>
								<tr><td>核心用户：平均在线><input type="text" name="coreTime" value="${coreTime }" onblur="verification1(this)">平均消费><input type="text" name="coreCost" value="${coreCost }"></td></tr>
								<tr><td>边缘用户：平均在线<<input type="text" name="edgeTime" value="${edgeTime }">平均消费<<input type="text" name="edgeCost" value="${edgeCost }"><input type="hidden" name="month" value="${month }"></td></tr>
								<tr><td style="text-align: center;"><input id="button" type="submit" value="查询 "></td></tr>
							</table>
						</form>
					</div>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
	function verification1(obj){
		var reg = /^[0-9]*$/;
		var value = obj.val();
		if(!reg.test(value)){
			obj.val(1800);
		}
	}
	function verification2(obj){
		var reg = /^[0-9]*$/;
		var value = obj.val();
		if(!reg.test(value)){
			obj.val(1000);
		}
	}
	function verification3(obj){
		var reg = /^[0-9]*$/;
		var value = obj.val();
		if(!reg.test(value)){
			obj.val(600);
		}
	}
	function verification4(obj){
		var reg = /^[0-9]*$/;
		var value = obj.val();
		if(!reg.test(value)){
			obj.val(200);
		}
	}
	function prev(month){
		month = month - 1;
		location.href = "admin/coreMember.do?month="+month;
	}
	function next(month){
		month = month + 1;
		location.href = "admin/coreMember.do?month="+month;
	}
var f = function(){
	$('.left>ul>li>h3').eq(4).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');
}
(f)();
</script>