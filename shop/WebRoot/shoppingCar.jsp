<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>购物车</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" type="text/css"
	href="bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/shoppingCarCss.css" />
<link rel="stylesheet" href="css/index.css" />
<link rel="stylesheet" href="css/base.css" />
</head>

<body>
	<!-- nav -->
	<div class="navbar wholenav">
		<!--导航条-->
		<nav class="navbar navbar-white">
		<div class="container">
			<div class="navbar navbar-header">
				<!--导航条左边-->
				<!--logo-->
				<div class="navbar-brand">
					<a href="index.jsp">OurLogo</a>
				</div>
			</div>
			<!--导航条右边-->
			<ul class=" navbar-nav hidden-xs" id="myNav">
				<li><a href="javascript:;">插线板</a>
					<div class="dropdown" style="display: none; left: -180%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li><a href="javascript:;">音响耳机</a>
					<div class="dropdown" style="display: none; left: -280%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li><a href="javascript:;">手机配件</a>
					<div class="dropdown" style="display: none; left: -380%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li><a href="javascript:;">电脑办公</a>
					<div class="dropdown" style="display: none; left: -480%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li><a href="javascript:;">电池电源</a>
					<div class="dropdown" style="display: none; left: -580%;">
						<div class="nav-cateCard">
							<ul class="card-list">

							</ul>
						</div>
					</div></li>
				<li><a href="javascript:;">生活电器</a>
					<div class="dropdown" style="display: none; left: -680%;">
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
			<span
				class="glyphicon glyphicon-user text-center hidden-sm hidden-xs hidden-md ">
				<div class="dropdown" style="display: none;">
					<a href="register.jsp">
						<button class="btn btn-default navbar-btn navbar-right">注册</button>
					</a> <a href="login.jsp">
						<button class="btn btn-default navbar-btn navbar-right">登录</button>
					</a>
				</div>
			</span>


		</div>
	</div>
    <!--最小框的登录和注册-->
	<ul id="min-reg" class="nav navbar-nav navbar-collapse collapse ">

	<div class="shopingCar">
		<div class="container">

			<div>
				<span>我的购物车</span> <span>产品是否购买成功，以最终下单为准，请尽快结算</span>
			</div>

			<ul>
				<li><input type="checkbox" class="checkAll" />全选</li>
				<li>商品信息</li>
				<li>单价</li>
				<li>数量</li>
				<li>小计</li>
				<li>操作</li>
			</ul>

			<div class="products">
				<p>购物车里空空如也，赶紧去逛逛吧！</p>
				<p>或者你也可以先登录看看</p>
				<button class="btn btn-info">立即登录</button>
				<button class="btn btn-success">立即去购物</button>
			</div>
			<div class="myProducts"></div>
		</div>

		<div class="modal fade" id="delOneProductModal" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h3>
							确定删除该商品？<span class="close" data-dismiss="modal">&times;</span>
						</h3>
						<button class="btn btn-danger btn-lg" id="isDeleteOne">确定</button>
						<button class="btn btn-info btn-lg" data-dismiss="modal"
							id="noDeleteOne">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="noProductModal" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h3>
							没有选择任何商品噢！<span class="close" data-dismiss="modal">&times;</span>
						</h3>
						<button class="btn btn-danger btn-lg" data-dismiss="modal">确定</button>
						<button class="btn btn-info btn-lg" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="delProductModal" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h3>
							确定删除该商品？<span class="close" data-dismiss="modal">&times;</span>
						</h3>
						<button class="btn btn-danger btn-lg" id="isDelete">确定</button>
						<button class="btn btn-info btn-lg" data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="balanceModal" data-backdrop="static">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h3>
							请先选择商品<span class="close" data-dismiss="modal">&times;</span>
						</h3>
						<button class="btn btn-danger btn-lg" id="isBalance"
							style="margin-left: 43%">确定</button>
					</div>
				</div>
			</div>
		</div>
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
<script src="js/jquery-3.4.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/shoppingCarJs.js"></script>
<script src="js/index.js"></script>
