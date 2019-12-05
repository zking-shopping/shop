<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '404.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>

        * {
            margin: 0px;
            padding: 0px;
        }

        .box {
            width: 693px;
            height: 380px;
            background: url("/shop/img/404.jpg") no-repeat;
            margin: 150px auto;
            position: relative;
        }

        .box .back {
            width: 400px;
            height: 40px;
            position: absolute;
            left: 50%;
            bottom: 60px;
            margin-left: -200px;
        }

        .box .back a {
            display: block;
            width: 400px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            text-decoration: none;
            color: #e84e00;
        }

    </style>
  </head>
  
  <body>
    <div class="box">
        <div class="back">
            <a href="/shop/index.jsp" id="a">您访问的页面不存在, 本页面将在5秒后跳转到首页</a>
        </div>
    </div>
  </body>
</html>
<script>

        window.onload = function() {
            //1. 获取id
            var a = document.getElementById('a');
            var count = 5;
            setTimeout(function(){
                count --;
                a.innerHTML = '您访问的页面不存在, 本页面将在' + count + '秒后跳转到首页';
                if (count > 0) {
                    setTimeout(arguments.callee, 1000);
                } else {
                    window.location.href = "/shop/index.jsp";
                }
            }, 1000);
        }

    </script>