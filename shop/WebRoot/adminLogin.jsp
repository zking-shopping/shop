<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'adminLogin.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
		*{
			margin:0px;
			padding:0px;
		}
		ul{
			list-style:none;
		}
		video{
			position:fixed;
			right:0;
			bottom:0;
			min-width:100%;
			min-height:100%;
			width:auto;
			height:auto;
			z-index:-999;
		}
		.login{
			width:300px;
			height:200px;
			margin:140px auto;
			background-color:rgba(0,0,0,0.5);
			border-radius:20px;
			padding:40px;
		}
		.login ul li{
			width:230px;
			height:66px;
			font-size:18px;
			font-family:"Songti SC";
			line-height:66px;
			color:snow;
			margin-left:30px;
		}
		.submit{
			width:60px;
			height:30px;
			float:right;
			margin-top:20px;
			margin-right:3px;
			font-size:14px;
			background-color:darkblue;
			color:snow;
			border-radius:1px;
			border:1px solid #CCC;
		}
		.submit:hover{
			background-color:blue;
			color:#CCC;
		}
	</style>
  </head>
  
  <body>
	<video muted autoplay loop><source src="/shop/video/00.mp4" type="video/mp4"></video>
	<div class="login">
		<form action="adminLogin.do" method="post">
			<ul>
				<li>账号：<input type="text" name="username"></li>
				<li>密码：<input type="password" name="password" autocomplete="off" oncopy="returnfalse" oncut="return false" onpaste="return false"></li>
				<input type="hidden" id="code" name="code" value="${AdminCode }">
				<li><input type="submit" value="登录" id="submit" class="submit"></li>
			</ul>
		</form>
	</div>
  </body>
</html>
