<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <link rel="stylesheet" type="text/css" href="bootstrap-3.3.7-dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="css/index.css" />
    <link rel="stylesheet" href="css/base.css" />
    <link rel="stylesheet" type="text/css" href="css/personInfoCss.css"/>
    <link rel="stylesheet" type="text/css" href="css/submitOrder.css"/>
    
	
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
			<span class="glyphicon glyphicon-user text-center hidden-sm hidden-xs hidden-md " id="user" value="${sessionScope.member}" href="">
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
	
	<!--中间内容-->
	<div class="myOrderInfo">
		
	    <div class="container">
	    	
	    	<!--左边的具体选项-->
	    	<div class="headfont">
	    		<a>首页</a>
	            <span>>个人中心</span>
	    	</div>
	        <div class="myleft">
	            <div>
	                <p><img src="img/order.png"/>订单中心</p>
	                <p class="showMyOrder"><a>我的订单</a></p>
	            </div>
	            <div>
	                <p><img src="img/user.png"/>个人中心</p>
	                <p class="receiptAddress"><a>收货地址</a></p>
	                <p><a>优惠券</a></p>
	                <p class="myPersonInfo"><a>个人信息</a></p>
	            </div>
	            <div>
	                <p><img src="img/other.png"/>更多</p>
	                <p><a>帮助中心</a></p>
	                <p><a>规则中心</a></p>
	                <p><a>隐私政策</a></p>
	            </div>
	        </div>
	        
	        <!--右边具体内容-->
	        <div class="myright">
	            <div class="myrightinfo">
	            	
	            	<!--我的订单内容-->
	                <div class="myOrder">
	                	<!--订单状态-->
	                    <ul class="myrightinfo_head">
	                        <li class="allOrder">全部订单</li>
	                        <li>|</li>
	                        <li class="waitPay">待付款</li>
	                        <li>|</li>
	                        <li class="waitDeliver">待发货</li>
	                        <li>|</li>
	                        <li class="waitRecieve">待收货</li>
	                    </ul>
	                    <div class="myNullOrder">
	                        <p><img src="img/noorder.png"/></p>
	                        <p>快去逛逛吧~</p>
	                    </div>
	                    <!--商品属性-->
	                    <ul class="myrightinfo_body">
	                        <li>商品信息</li>
	                        <li>单价</li>
	                        <li>数量</li>
	                        <li>金额</li>
	                        <li>状态</li>
	                        <li>操作</li>
	                        <div class="myProducts"></div>
	                    </ul>
	                </div>
	                
	                <!--订单状态-->
	                <div class="viewDetails">
	                    <div class="odder_info odder_infoInsert">下单时间<span></span></div>
	                    <div class="viewDetails_buttom_orderInfo">
	                        
	                    </div>
	                    <div class="viewDetails_buttom">
	                        <ul class="viewDetails_buttom_body">
	                            <li>商品信息</li>
	                            <li>单价</li>
	                            <li>数量</li>
	                            <li>小计</li>
	                        </ul>
	                        <div class="viewDetails_buttom_body_details"></div>
	                        <div class="odder_info">订单信息<span></span></div>
	                        <div class="orderInfo" id="orderInfo">
	                            <p><span>收货人：111</span><span>支付方式：未支付</span><span>实付款：￥0.00</span></p>
	                            <p><span>联系方式：155****5555</span><span>活动优惠：-￥0.00</span><span>运费：￥0.00</span></p>
	                            <p><span>收货地址：北京市北京市丰台区大红门街道11111</span><span>优惠券：-￥0.00</span></p>
	                        </div>
	                    </div>
	                </div>
	
	                <div id="myPersonInfo"></div>
	
	                <!--尹厚粤代码整合start-->
	                <div class="main-info">
						<!--收货人-->
						<div class="receiver">
							<label>收货人</label>
							<input type="text" name="consignee" id="consignee" placeholder="长度为2-16个字符"/>
							<span>*</span>
						</div>
						<!--电话-->
						<div class="tel">
							<label>手机号码</label>
							<input type="text" name="phone-number" id="phone-number" placeholder="请输入手机号码"/>
							<span>*</span>
						</div>
						<!--大概地址-->
						<div class="address">
							<label>所在地区</label>
							<select name="provinces" id="provinces" onChange="changeCity()">
								<option value="">省</option>
							</select>
							<select name="city" id="city" onChange="changeArea()">
								<option value="">市</option>
							</select>
							<select name="area" id="area">
								<option value="">区&县</option>
							</select>
							<span>*</span>
						</div>
						<!--详细地址-->
						<div class="main-address">
							<label class="area">详细地区</label>
							<textarea name="detail-address" id="detail-address" placeholder="输入5-150个字符的详细地址"></textarea>
							<span>*</span>
							<p class="no-delivery">不支持港澳台地区配送</p>
						</div>
						<!--默认复选框-->
						<div class="set-default">
							<input type="checkbox" name="default" id="default"/><p>设为默认</p>
						</div>
						<!--保存-->
						<div class="keep">
							<button id="save">保存</button>
						</div>
						
						<!--已有地址列表-->
						<div class="haved-address">
							
						</div>
					</div>
	                <!--尹厚粤代码整合end-->
	            </div>
	            
	        </div>
	        
	    </div>
	
	</div>
	
	<!--底部-->
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
	    
	<!--删除界面-->
	<section class="transparent"></section>
	<div class="center-delete">
		<div class="ask-delete">确认删除？</div>
		<input type="button" value="确认" class="enter-delete"/>
		<input type="button" value="取消" class="no-delete"/>
		<div class="close-ask"></div>
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
  </body>
</html>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/basic.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/address.js" ></script>
<script type="text/javascript" src="js/personInfoJs.js"></script>
<script type="text/javascript" src="js/linkage.js"></script>
<script type="text/javascript" src="js/submitOrder.js"></script>
