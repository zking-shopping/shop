	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE jsp PUBLIC "-//W3C//DTD jsp 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="css/login.css"/>
	</head>
	<body>
		<header>
			<nav>
				<div class="logo">
					<a href="#"><img src="img/logo-906b3317.png"/></a>
				</div>
			</nav>
		</header>
		<section class="content">
			<div class="title">账号登录</div>
			<form>
				<div>
					<input name="username" class="username" type="text" placeholder="请输入账号"/>
					<p class="name-tip"></p>
				</div>
				<div>
					<input name="password" class="password" type="password" placeholder="请输入密码"/>
					<p class="pass-tip"></p>
				</div>
			</form>
			<div>
				<input class="login" type="button" value="登录" style="text-align:center;" click="login()"/>
				<div class="unlogin">
					<div class="forget-pass"><a href="#">忘记密码&nbsp;&nbsp;|</a></div>
					<div class="register"><a href="register.jsp">&nbsp;&nbsp;注册账号</a></div>
				</div>
				<div class="division">
					<div class="line-left"></div>
					<div class="other-tip">其他方式登录</div>
					<div class="line-right"></div>
				</div>
				<div class="other-login">
					<a href="#" class="weixin">微信</a>
					<a href="#" class="qq">QQ</a>
					<a href="#" class="weibo">微博</a>
				</div>
			</div>
		</section>
		
		<!--脚部-->
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
<script type="text/javascript" src="js/jquery-3.4.1.min.js" ></script>
<script type="text/javascript" src="js/basic.js" ></script>
<script type="text/javascript" src="js/login.js" ></script>
