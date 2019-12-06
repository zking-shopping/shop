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
	<div class="navbar wholenav" >
		<!--导航条-->
		<nav class="navbar navbar-white" style="border-bottom-color:#d6d6d6">
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
						<li style="width:14%">
							<a id="sort1" value="1">插线板</a>
							<div class="dropdown" style="display: none; left: -190%;">
								<div class="nav-cateCard">
									<ul class="card-list">
										
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort2" value="2">音响耳机</a>
							<div class="dropdown" style="display: none; left: -290%;" >
								<div class="nav-cateCard">
									<ul class="card-list">
										
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort3" value="3">手机配件</a>
							<div class="dropdown" style="display: none; left: -390%;">
								<div class="nav-cateCard">
									<ul class="card-list">
										
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort4" value="4">电脑办公</a>
							<div class="dropdown" style="display: none; left: -490%;">
								<div class="nav-cateCard">
									<ul class="card-list">
										
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort5" value="5">电池电源</a>
							<div class="dropdown" style="display: none; left: -590%;">
								<div class="nav-cateCard">
									<ul class="card-list">
										
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort6" value="6">生活电器</a>
							<div class="dropdown" style="display: none; left: -690%;">
								<div class="nav-cateCard">
									<ul class="card-list">
									
									</ul>
								</div>
							</div>
						</li>
						<li style="width:14%">
							<a id="sort7" value="7">个护健康</a>
							<div class="dropdown" style="display: none; left: -790%;">
								<div class="nav-cateCard">
									<ul class="card-list">
									
									</ul>
								</div>
							</div>
						</li>
					</ul>


			<!--用户 包含登录注册-->
			<span class="glyphicon glyphicon-user text-center hidden-sm hidden-xs hidden-md " id="user" value="${sessionScope.member}" >
			              <div class="dropdown" style="display:none;" >
								<a href='register.jsp'>
									<button class="btn btn-default navbar-btn navbar-right">注册</button>
								</a> <a href="login.jsp">
									<button class="btn btn-default navbar-btn navbar-right">登录</button>
								</a>
						    </div>
						    
						     <div class="dropdown1" style="display:none;" >
								<a href="shoppingCar.jsp">
									<button class="btn btn-default navbar-btn navbar-right">我的订单</button>
								</a> <a href="quitLogin.do">
									<button class="btn btn-default navbar-btn navbar-right">退出登录</button>
								</a>
						    </div>
			</span> 
			<span class="badge bg-danger hidden-sm hidden-xs hidden-md" id="carsign" value="${cart}"
				style="background: white;"> <!--购物车车标-->
				<div id="shopsign">
					<!--数量-->
					<p class="count">8</p>
					<!--购物车-->
					<span class="glyphicon glyphicon-shopping-cart text-left "></span>

					<!--下拉框-->
					<div class="dropdown2" style="display: none; width:900%" id="allgoods">
						<div id="shop-top" style="display: none;">
							<ul  id="carts">
						       
							</ul>
						</div>
						<div id="shop-down" style="display: none;">
							
						</div>
						
						
					     <div id="nogoods" style=" color: gray; height: 50%; display: none;">
								<span style="margin: 40% 20%; font-size:18px; font-weight:lighter; letter-spacing:1px">还没有商品,快去选中商品</span>
					     </div>
					     <div id="goshopping" style="display: none;">
							<a style="margin: 50% 20%; " href="shoppingCar.jsp"><button style="height:30px;width:72px;">去购物车</button></a>
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
			
				<button class="btn btn-info shoppingRightNow">立即去购物</button>
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
