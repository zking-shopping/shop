<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择付款方式</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--payment
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="css/payment.css">
	
	<script type="text/javascript" src="js/jquery-3.4.1.min.js" ></script>
	<script type="text/javascript" src="js/payment.js"></script>

  </head>
  
  <div style="display: none;">
			<input type="text" id="addressId" value="${addressId}" />
			<input type="text" id="total" value="${total}" />
			<input type="text" id="receiveInfo" value="${receiveInfo}" />
			<input type="text" id="id" value="${id}" />
  		</div>
		<!--导航栏-->
		<header>
			<div class="header-nav">	
				<div class="left-logo">
			        <a href="index.jsp">OurLogo</a>
			    </div>
			    <!--跳转个人信息页面-->
			    <div class="user-header">
			    	<a href="persionInfo.jsp"></a>
			    </div>
			    <!--注册登录-->
			    <div class="dropdown">
					<a href="register.jsp">
						<button class="register-btn">注册</button>
					</a>
					<a href="login.html">
						<button class="login-btn">登录</button>
					</a>
				</div>
			</div>
		</header>
		<section class="content">
			<h1>
				支付订单
				<span id="buy-tips">产品是否购买成功，以最终下单为准哦，请尽快结算</span>
			</h1>
			<div class="pay-info">
				<div class="order-info">
					<p>
						订单编号
						<span id="order-id">519956215178989568</span>
					</p>
					<p>
						收货信息
						<span id="receive-info">黑龙江省佳木斯市其他区15115403633 151****3633</span>
					</p>
				</div>
				<div class="payment-method">
					<p>支付方式</p>
					<div class="pay">
						<div class="pay-method">
							<h2 class="select-method">选择支付方式</h2>
							<div class="total-paynumber">
								<p class="total-left">交易号：20191205192022YX3CDD1644459347</p>
								<p class="total-right">
									实付：
									<span id="total">¥${total}</span>
								</p>
							</div>
						</div>
						<div class="pay-now">
							<button id="immediate-payment">立即付款</button>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>
