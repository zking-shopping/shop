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
								<p style="background : yellow;"><a">系统说明</a></p>
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
					<p style="font-size:36px;">XX商城操作系统说明</p>
					<p>版本：1.0</p>
					<p>服务器要求：</p>
					<p style="text-indent:2em;">日均业务量小于50</p>
					<p style="text-indent:4em;">CPU:8核以上、内存32G以上、存储空间2T以上1台</p>
					<p style="text-indent:2em;">日均业务量大于50小于200</p>
					<p style="text-indent:4em;">CPU:8核以上、内存32G以上、存储空间2T以上1台</p>
					<p>数据库：MySQL</p>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
var f = function(){
	$('.left>ul>li>h3').eq(0).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');;
}
(f)();
</script>