<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
  <head>
	<meta http-equiv="description" content="This is my page">
		<meta name="viewport" content="width=device-width,initial-scale=1,userable-scale=no" />
		
			<link rel="stylesheet" type="text/css" href="css/sort.css" />
			<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css" type="text/css" />
		<title></title>
	</head>

	<body>
		<div class="container text-center" style="margin: 0 auto; width: 80%;">
			<div class="row" id="goodsList" style="margin: 20px auto;">

			</div>
		</div>
		<div class="c" value="$(type)">
			<div class="footer">
					 <input type="button" id="prev" style="width:50px;  height: 42px; background:gray;" value="上一页" onclick="goPrev()"></input>
				<ul style="margin: 0 auto;">
					<li class="current">
						<a>1</a>
					</li>
					<li>
						<a>2</a>
					</li>
					<li class="ignore1" style="display: none;">
						<a href="javascript:;">...</a>
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
						<a href="javascript:;">...</a>
					</li>
				</ul>
				   <input id="next" type="button"  style="width:50px;  height: 42px; background:gray;" value="下一页" onclick="goNext()"></input> <p style="height:42px; text-align:center; line-height:42px">&nbsp;共有20页  &nbsp;&nbsp;</p>   去<input type="text" id="find" style="height: 42px;"></input>页<input type="button" style="background:gray;" ondblclick="searchpage($(this))" value="确定"/>
			</div>
		</div>

	</body>

</html>
<script src="/shop/js/home-jquery.min.js"></script>
<script src="/shop/js/home-bootstrap.min.js"></script>
<script src="/shop/js/sort.js"></script>
