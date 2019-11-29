<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>首页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport"
	content="width=device-width,initial-scale=1,userable-scale=no" />
<link rel="stylesheet" href="/shop/css/base.css" type="text/css" />
<link rel="stylesheet" href="/shop/css/index.css" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css"
	type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<div class="navbar wholenav">
		<!--导航条-->
		<nav class="navbar navbar-white">
		<div class="container">
			<div class="navbar navbar-header">
				<!--导航条左边-->
				<!--logo-->
				<div class="navbar-brand">
					<a href="index.jsp" style="text-decoration:none">OurLogo</a>
				</div>
			</div>
			<!--导航条右边-->
			<ul class=" navbar-nav hidden-xs" id="myNav" style="width:72%">
				<li style="width:14%"><a href="type.jsp">插线板</a>
					<div class="dropdown" style="display: none; left: -180%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">音响耳机</a>
					<div class="dropdown" style="display: none; left: -280%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">手机配件</a>
					<div class="dropdown" style="display: none; left: -380%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">电脑办公</a>
					<div class="dropdown" style="display: none; left: -480%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">电池电源</a>
					<div class="dropdown" style="display: none; left: -580%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">生活电器</a>
					<div class="dropdown" style="display: none; left: -680%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li style="width:14%"><a href="type.jsp">个护健康</a>
					<div class="dropdown" style="display: none; left: -780%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
			</ul>

			<!-- 	搜索
					<div class="navbar-form navbar-left hidden-sm hidden-xs hidden-md text-center" id="search">
						<div class="input-group">
							<input type="text" class="form-control" />
							<span class="input-group-btn" >
								<button class="btn btn-primary " >
									<span class="glyphicon glyphicon-search"></span>
							   	</button>
							</span>
						</div>
					</div> -->

			<!--用户 包含登录注册-->
			<span class="glyphicon glyphicon-user text-center hidden-sm hidden-xs hidden-md ">
				<div class="dropdown" style="display: none;">
					<a href="register.jsp">
						<button class="btn btn-default navbar-btn navbar-right">注册</button>
					</a> <a href="login.jsp">
						<button class="btn btn-default navbar-btn navbar-right">登录</button>
					</a>
				</div>
			</span> 
			<span class="badge bg-danger hidden-sm hidden-xs hidden-md"
				style="background: white;"> <!--购物车车标-->
				<div id="shopsign">
					<!--数量-->
					<p class="count">1</p>
					<!--购物车-->
					<span class="glyphicon glyphicon-shopping-cart text-left "></span>

					<!--下拉框-->
					<div class="dropdown" style="display: none; " id="allgoods">
						<div id="shop-top" style="display: none;">
							<ul style=" overflow-x: auto; ">
								<li><a href="introduction.jsp"><img
										src="img/chazuo1.jpg" /></a>
									<div
										style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
										<p style="display: block;">万能插座</p>
										<p style="display: block;">价格:￥135.00</p>
									</div> <span class="glyphicon glyphicon-remove" style="float: right;"></span>
								</li>

								<li><a href="introduction.jsp"> <img
										src="img/chazuo1.jpg" />
								</a>
									<div
										style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
										<p style="display: block;">万能插座</p>
										<p style="display: block;">价格:￥135.00</p>
									</div> <span class="glyphicon glyphicon-remove" style="float: right;"></span>
								</li>

								<li><a href="introduction.jsp"> <img
										src="img/chazuo1.jpg" />
								</a>
									<div
										style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
										<p style="display: block;">万能插座</p>
										<p style="display: block;">价格:￥135.00</p>
									</div> <span class="glyphicon glyphicon-remove" style="float: right;"></span>

								</li>

								<li><a href="introduction.jsp"> <img
										src="img/chazuo1.jpg" />
								</a>
									<div
										style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
										<p style="display: block;">万能插座</p>
										<p style="display: block;">价格:￥135.00</p>
									</div> <span class="glyphicon glyphicon-remove" style="float: right;"></span>
								</li>
								<li><a href="introduction.jsp"> <img
										src="img/chazuo1.jpg" />
								</a>
									<div
										style="width: 20%; height: 20%; display: inline-block; float: left;  margin-left: 20%;">
										<p style="display: block;">万能插座</p>
										<p style="display: block;">价格:￥135.00</p>
									</div> <span class="glyphicon glyphicon-remove" style="float: right;"></span>
								</li>
							</ul>
						</div>
						<div id="shop-down" style="display: none;">
							<h1>总价:￥135.00</h1>
							<a href="shoppingCar.jsp"><button>去购物车</button></a>
						</div>
						<div id="no-goods" style=" color: gray;height: 50%; ">
							<span
								style="margin: 50% 20%; font-size:18px; font-weight:lighter; letter-spacing:1px">还没有商品,快去选中商品</span>
						</div>
						<div id="goshopping" style="display:none">
							<a style="margin: 50% 20%;" href="shoppingCar.jsp"><button>去购物车</button></a>
						</div>
					</div>
				</div>
			</span>
		</div>
		</nav>
	</div>

	<!--最小框的登录和注册-->
	<ul id="min-reg" class="nav navbar-nav navbar-collapse collapse ">
		<li class="visible-xs"><a href="register.jsp">
				<button class="btn btn-primary btn-xs">注册</button>
		</a></li>
		<li class="visible-xs"><a href="login.jsp">
				<button class="btn btn-success btn-xs">登录</button>
		</a></li>
	</ul>
	
	
	<!--轮播图-->
	<div class="banner">
		<!--列表-->
		<ul>
			<li class="active"><a href="#"> <img
					src="img/autoround1.jpg" />
			</a></li>
			<li><a href="#"> <img src="img/autoround2.jpg" />
			</a></li>
			<li><a href="#"> <img src="img/autoround3.jpg" />
			</a></li>
			<li><a href="#"> <img src="img/autoround4.jpg" />
			</a></li>

		</ul>

		<!--按钮-->
		<a href="javascript:;" class="next"> </a> <a href="javascript:;"
			class="prev"> </a>

		<!--序号-->
		<div class="number">
			<span class="current">1</span> <span>2</span> <span>3</span> <span>4</span>
		</div>
	</div>
	</div>

	<!--精选商品-->
	<div class="chosed">
		<h1>精选商品</h1>
		<ul class="bettergoods">

		</ul>
	</div>

	<!--底部-->
	<div class="footer">
		<div class="footer-top text-center">
			<ul>
				<li><span class="glyphicon glyphicon-film"></span>
					<p>自营电商</p></li>
				<li><span class="glyphicon glyphicon-eur"></span>
					<p>诚信经商</p></li>
				<li><span class="glyphicon glyphicon-font"></span>
					<p>全天经营</p></li>
				<li><span class="glyphicon glyphicon-oil"></span>
					<p>7天包退</p></li>
				<div class="cut"
					style="width:1px; background:white;float:left;height:72px; margin-top: 24px;"></div>
			</ul>
			<!--分割线-->
			<div class="footer-top-r">
				<img src="img/contact.jpg" />
			</div>
		</div>

		<div class="footer-bottom text-center">
			<span>公司版权所有 © 1997-2019 ICP证：浙B2-20160106 浙ICP备15041168号
				（粤）网械平台备字（2019）第00004号</span>
			<p>|</p>
			<a>企业来购</a>
			<p>|</p>
			<a>联系客服</a>
			<p>|</p>
			<a>营业执照</a>
			<p>|</p>
			<a>中文</a> <a>English</a>
		</div>
	</div>
</body>
</html>
<script src="/shop/js/jquery-3.4.1.min.js"></script>
<script src="/shop/js/index.js"></script>
