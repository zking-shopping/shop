	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
     <meta name="viewport" content="width:width=device-width; initial-scale=1"/>
		<link rel="stylesheet" href="css/animate.css" />
		<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="css/base.css" type="text/css" />
		<link rel="stylesheet" href="css/details.css" type="text/css" />
		<link rel="stylesheet" href="css/index.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
			<span class="glyphicon glyphicon-user text-center hidden-sm hidden-xs hidden-md " id="user" value="${sessionScope.member}">
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
		
		<!-- 内容 -->
		<div class="buy">
			<div class="content">
				<!-- 左图片 -->
				<div class="buy_img" value="${bd.picture1 }">
					<!-- 滑块与图片 -->
					<div class="img_box animated rotateIn wow">
						<div class="slide"></div>
						<img src="${bd.picture1 }" />
					</div>
					<!-- 图片列表 -->
					<ul>
						<li class="selected"><img src="${bd.picture1 }" /></li>
						<li><img src="${bd.picture2 }" /></li>
						<li><img src="${bd.picture3 }" /></li>
					</ul>
				</div>
				<!-- 放大图 -->
				<div class="big"></div>
				<!-- 右信息 -->
				<div class="buy_standard">
						<h1>${bd.goodsname}</h1>
				<div class="desc">
					<p>${bd.introduction}</p>
					<a id="introduce" value="${bd.id}" style="cursor:pointer">深入了解产品></a>
				</div>
				<h2>1.规格</h2>
				<div class="types">
					<div class="type type_selected">${bd.color}</div>
				</div>
				<div class="buy_module">
					<p>您选择了以下产品</p>
					<p class="setMeal">${bd.color}</p>
					<p class="price">￥ ${bd.price}</p>
					<a class="buy_now"  value="${bd.id }">立即购买</a>
					<a class="buy_intocar"  value="${bd.id }">加入购物车</a>
				</div>
				</div>
			</div>
			<div class="pic" id="bigpic">
				<img src="${bd.picture3 }" class="animated zoomIn wow" data-wow-offset="200" />
			</div>
		</div>
		
		<!-- 底部 -->
		<div class="footer">
				<div class="footer-top text-center">
					<ul>
						<li>
							<span class="glyphicon glyphicon-film"></span>
							<p>自营电商</p>
						</li>
						<li>
							<span class="glyphicon glyphicon-eur"></span>
							<p>诚信经商</p>
						</li>
						<li>
							<span class="glyphicon glyphicon-font"></span>
							<p>全天经营</p>
						</li>
						<li>
							<span class="glyphicon glyphicon-oil"></span>
							<p>7天包退</p>
						</li>
						<div class="cut" style="width:1px; background:white;float:left;height:72px; margin-top: 24px;"></div>
					</ul>
					<!--分割线-->
					<div class="footer-top-r">
						<img src="img/contact.jpg" />
					</div>
				</div>
		
				<div class="footer-bottom text-center">
					<span>公司版权所有 © 1997-2019 ICP证：浙B2-20160106 浙ICP备15041168号 （粤）网械平台备字（2019）第00004号</span>
					<p>|</p>
					<a>企业来购</a>
					<p>|</p>
					<a>联系客服</a>
					<p>|</p>
					<a>营业执照</a>
					<p>|</p>
					<a>中文</a>
					<a>English</a>
				</div>
		</div>
		
		<!-- 
		<div class="aaa">
		<div class="modal fade" id="addToShoppingCarPrompt">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<h3>
							加入购物车成功！
						</h3>
						
					</div>
				</div>
			</div>
			</div> -->
		</div>		
	</body>
	</html>
<script src="js/jquery-3.4.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/wow.min.js"></script>
<script src="js/details.js"></script>

<script src="js/index.js"></script>
<script>
	new WOW().init();
</script>
