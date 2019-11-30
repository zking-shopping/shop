<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册账号</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/register.css"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
		<header>
			<nav>
				<div class="logo">
					<a href="#"><img src="img/logo-906b3317.png"/></a>
				</div>
			</nav>
		</header>
		<section>
			<div class="step">
				<ul>
					<li class="current">
						<div class="content">1</div>
						<div class="line">
							<div class="line-half"></div>
						</div>
						<div class="main-tips">填写账号密码</div>
					</li>
					<li>
						<div class="content">2</div>
						<div class="line">
							<div class="line-half"></div>
						</div>
						<div class="main-tips">验证手机号码</div>
					</li>
					<li>
						<div class="content">3</div>
						<div class="main-tips">完成注册</div>
					</li>
				</ul>
			</div>
			<!--注册第一步：填写账号密码-->
			<div class="register-step1">
				<form>
					<div>
						<input name="username" class="username" type="text" placeholder="请输入要注册的账号"/>
						<p></p>
					</div>
					<div>
						<input name="password" class="password" type="password" placeholder="请输入8-18位的密码"/>
						<p></p>
					</div>
				</form>
				<button class="next-step">下一步</button>
				<div class="to-login">
					已有账号?
					<a href="login.jsp">去登陆</a>
				</div>
			</div>
			<!--注册第二步：验证手机号码-->
			<div class="register-step2" style="display: none;">
				<div class="tips">为了确保帐号安全，您的手机号码将作为该帐号的安全手机，其他人不会看到您的手机号码。</div>
				<form>
					<div>
						<div class="area-code">
							<p>+86</p>
							<div class="drop-down"></div>
						</div>
						<input name="tel" class="tel" type="text" placeholder="请输入手机号"/>
						<p class="tel-tip"></p>
					</div>
					<div>
						<input name="verify" class="verify" type="text" placeholder="请输入结果"/>
						<img id="codeImage" src="imageCode.do" onclick="flushCode(this)"/>
						<div class="get-verify">看不清？换一题</div>
					</div>
				</form>
				<div class="button-list">
					<button class="prev">上一步</button>
					<button class="ensure-reg">注册</button>
				</div>
				<div class="agree">
					<input type="checkbox" name="agreebox" class="agreebox" />
					<label>用户勾选即代表同意</label>
					<a href="#">
						《XX邮箱服务条款》
					</a>
					<p>和</p>
					<a href="#">《XX隐私政策》</a>
				</div>
			</div>
			<!--注册第三步：成功！！！-->
			<div class="register-step3" style="display: none;">
				<div class="title">注册成功</div>
				<div class="register-result">
					<p>恭喜！您的帐号  <span></span></p>
					<p>已经注册成功！</p>
				</div>
				<button class="goto-login">去登陆界面登陆</button>
			</div>
		</section>
		
		<footer>
			<ul>
				<li><a href="#">关于我们</a></li><div></div>
				<li><a href="#">公司简介</a></li><div></div>
				<li><a href="#">联系方式</a></li><div></div>
				<li><a href="#">协议认证</a></li><div></div>
				<li><a href="#">招聘信息</a></li><div></div>
				<li><a href="#">客服服务</a></li><div></div>
				<li><a href="#">相关法律</a></li><div></div>
				<li><a href="#">网络营销</a></li>
			</ul>
			<p>网易公司版权所有 ©1997-2019</p>
		</footer>
	</body>
</html>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/basic.js" ></script>
<script type="text/javascript" src="js/register.js"></script>