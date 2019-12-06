<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<style type="text/css">
		.right{
			padding-top: 20px;
		}
		.right .dataContent{
			width: 100%;
			height: 230px;
		}
		.right .dataContent .show{
			width: 140px;
			height: 180px;
			background-color: white;
			margin-left: 90px;
			float: left;
			position: relative;
			padding: 20px;
		}
		#typeName{
			font-size: 20px;
			color: red;
		}
		.show>p{
			width: 90px;
			height: 90px;
			font-size: 24px;
			color: red;
			display: block;
			text-align: center;
			line-height: 90px;
			border: 10px solid black;
			border-radius: 50%;
			float: right;
			position: absolute;
			right: 30px;
			bottom: 30px;
		}
		.right .dataGraph{
			width: 800px;
			height: 300px;
			margin: 0px auto;
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
							<li class="prevMonth" onclick="prevMonth()">上一月</li>
							<li>${time }</li>
							<li class="nextMonth" onclick="nextMonth()">下一月</li>
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
								<p><a href="admin/rank.do">商品排行榜</a></p>
								<p style="background : yellow;"><a>商品数据分析</a></p>
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
					<div class="dataContent">
						<div class="show"><h6><span id="typeName">${maxKey }</span>类商品平均卖出一件用户点击</h6><p>${max }次</p></div>
						<div class="show"><h6>本月平均卖出一件商品用户点击</h6><p>${avg }次</p></div>
						<div class="show"><h6><span id="typeName">${minKey }</span>类商品平均卖出一件用户点击</h6><p>${min }次</p></div>
					</div>
					<div class="dataGraph">
						<img src="admin/dataGraph.do" onclick="type(this)">
					</div>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
	var dataGraph = 0; 
	function type(obj){
		dataGraph = dataGraph+1;
		obj.src = "admin/dataGraph.do?type="+dataGraph;
	}
	
	function prevMonth(){
		location.href = "admin/analysis.do?time="+${time-1};
	}
	
	function nextMonth(){
		location.href = "admin/analysis.do?time="+${time+1};
	}
	
var f = function(){
	$('.left>ul>li>h3').eq(4).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');
}
(f)();
</script>