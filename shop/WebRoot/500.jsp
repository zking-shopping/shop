<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '500.jsp' starting page</title>
    
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
            width: 98%;
            height: 600px;
            background: url("/shop/img/500.png") no-repeat;
            margin: 0px auto;
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
            color: #7456f8;
            font-size:20px;
        }

    </style>
  </head>
  
  <body>
    <body>
    <div class="box">
        <div class="back">
            <a href="/shop/index.jsp" id="a">屏幕前的小可爱，我将在5秒后带你回去~</a>
        </div>
    </div>
  </body>
  </body>
</html>
<script>

        window.onload = function() {
            //1. 获取id
            var a = document.getElementById('a');
            var count = 5;
            setTimeout(function(){
                count --;
                a.innerHTML = '屏幕前的小可爱，我将在' + count + '秒后带你回去~';
                if (count > 0) {
                    setTimeout(arguments.callee, 1000);
                } else {
                    window.location.href = "/shop/index.jsp";
                }
            }, 1000);
        }

    </script>
