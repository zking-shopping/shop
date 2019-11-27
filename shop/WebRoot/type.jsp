<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分类</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		<meta name="viewport" content="width=device-width,initial-scale=1,userable-scale=no" />
		<link rel="stylesheet" type="text/css" href="css/type.css" />
		<title></title>
	</head>

	<body>
		<div class="container text-center" style="margin: 0 auto; width: 80%;">
			<div class="row" id="goodsList" style="margin: 20px auto;">

			</div>

		</div>
		<div class="c">
			<div class="footer">
					 <button id="prev" style="width:100px;  height: 55px;" >上一页</button>
				<ul style="margin: 0 auto;">
					<li class="current">
						<a>1</a>
					</li>
					<li>
						<a>2</a>
					</li>
					<li class="ignore1" style="display: none;">
						<a href="#">...</a>
					</li>
					<!--当页面大于x时消失-->
					<li style="display:block;" id="page">
						<a>3</a>
					</li>
					<li id="page">
						<a>4</a>
					</li>
					<li id="page">
						<a>5</a>
					</li>
					<li id="page">
						<a>6</a>
					</li>
				    <li style="display: none;" class="add" id="page">
						<a>7</a>
					</li>
					
					<li class="ignore2" style="display: none;">
						<a href="#">...</a>
					</li>
				</ul>
				   <button id="next"  style="width:100px;  height: 55px;">下一页</button><input type="text" id="find" style="height: 52px;"></input><input type="button" ondblclick="searchpage($(this))" value="提交"/>
			</div>
		</div>

	</body>

</html>
<script src="/shop/js/home-jquery.min.js"></script>
<script src="/shop/js/home-bootstrap.min.js"></script>
<script src="/shop/js/type.js"></script>
