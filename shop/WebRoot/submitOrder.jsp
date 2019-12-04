<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>确认订单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/submitOrder.css"/>
	
	<script type="text/javascript" src="js/jquery-3.4.1.min.js" ></script>
	<script type="text/javascript" src="js/basic.js" ></script>
	<script type="text/javascript" src="js/linkage.js" ></script>
	<script type="text/javascript" src="js/address.js" ></script>
	<script type="text/javascript" src="js/submitOrder.js"></script>
	<script type="text/javascript" src="js/personInfoJs.js"></script>
	
  </head>
  	
  	<body>
		<!--导航栏-->
		<header>
			<div class="header-nav">	
				<div class="left-logo">
			        <a href="index.html">OurLogo</a>
			    </div>
			    <!--跳转个人信息页面-->
			    <div class="user-header">
			    	<a href="/shop/persionInfo.jsp"></a>
			    </div>
			</div>
		</header>
		
		<!--收货信息-->
		<section class="receiveSection">
			<div class="title"><p>确认订单</p></div>
			<div class="receive-info">
				<h3>收货信息</h3>
				<!--填写信息-->
				<div class="main-info" style="display: none;">
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
						<button id="exit-add">取消</button>
					</div>
				</div>
				<!--选择地址-->
				<div class="select-address" style="display: block;">
					<table class="address-list">
						<tbody>
						
						</tbody>
					</table>
					
					<p class="no-deliverys">不支持港澳台地区配送</p>
				</div>
			</div>
		</section>
		
		<!--商品列表-->
		<section class="goodsSection">
			<h3>商品信息</h3>
			<table>
				<thead>
					<tr>
						<td>商品信息</td>
						<td>单价</td>
						<td>数量</td>
						<td>小计</td>
					</tr>
				</thead>
				<tbody>
					
				</tbody>
			</table>
		</section>

		<!--付款-->
		<section class="pay-one">
			<ul>
				<li class="total-price">
					<h2>商品总价：</h2>
					<p>
						<span id="symbol">￥</span>
						<span id="count">50</span>
					</p>
				</li>
				<li class="discount">
					<h2>优惠：</h2>
					<p>
						<span id="symbol">￥</span>
						<span id="count">0</span>
					</p>
				</li>
				<li class="freight">
					<h2>运费：</h2>
					<p>
						<span id="symbol">￥</span>
						<span id="count">0</span>
					</p>
				</li>
				<li class="payable">
					<h2>应付：</h2>
					<p>
						<span id="symbol">￥</span>
						<span id="count">50</span>
					</p>
				</li>
				<li class="agree-clause">
					<input type="checkbox" name="agree-bule" id="agree-bule" checked="checked"/>
					<label>
						我已同意
						<a href="#n">[XXX服务协议]</a>
					</label>
				</li>
			</ul>
			<button class="to-pay">去付款</button>
		</section>
		
		<!--删除界面-->
		<section class="transparent"></section>
		<div class="center-delete">
			<div class="ask-delete">确认删除？</div>
			<input type="button" value="确认" class="enter-delete"/>
			<input type="button" value="取消" class="no-delete"/>
			<div class="close-ask"></div>
		</div>
		<!--底部-->
		<footer>
		    <div class="footer-tops">
		        <ul>
		            <li>
		                <p>自营电商</p>
		            </li>
		            <li>
		                <p>诚信经商</p>
		            </li>
		            <li>
		                <p>全天经营</p>
		            </li>
		            <li>
		                <p>7天包退</p>
		            </li>
		        </ul>
		        <!--分割线-->
		        <div class="cuts"></div>
		        <!--二维码-->
		        <div class="qr-code">
		            <img src="img/contact.jpg" />
		        </div>
		    </div>
		
		    <div class="footer-bottoms">
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
		</footer>
  </body>
</html>
