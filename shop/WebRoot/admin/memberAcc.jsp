<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/shop/admin/css/base.css">
	<style type="text/css">
		.right>table{
			width: 900px;
			height: 60px;
			margin: 0px auto;
			text-align: center;
			font-size: 24px;
		}
		.right table tr td{
			height: 60px;
		}
		.page{
			width: 500px;
			height: 25px;
			margin: 0px auto;
		}
		.button{
			width: 66px;
			height: 21px;
			background-color: white;
			float: left;
			font-size: 16px;
			text-align: center;
			line-height: 21px;
			border: 2px solid gold;
			margin: 15px 0px 0px 7px;
		}
		.button:hover{
			border: 2px solid yellow;
		}
	</style>
  </head>
  
  <body>
		<nav>
			<container>
				<div class="myNav">
					<div class="logo"><a href="/shop/admin/index.jsp">Admin</a></div>
					<div class="strip">
						<ul>
							<li class="normal">正常字体</li>
							<li class="enlarge">增大字体</li>
							<li class="narrow">缩小字体</li>
						</ul>
					</div>
				</div>
			</container>
		</nav>
		<div class="content">
			<container>
				<div class="left">
					<ul>
						<li>
							<h3>系统管理</h3>
							<div class="title">
								<p><a href="/shop/admin/system.jsp">系统说明</a></p>
								<p><a href="/shop/admin/operation.jsp">权限管理</a></p>
							</div>
						</li>
						<li>
							<h3>产品管理</h3>
							<div class="title">
								<p><a href="admin/allGoods.do">全部商品</a></p>
								<p><a href="admin/insertGoods.do">添加商品</a></p>
							</div>
						</li>
						<li>
							<h3>订单管理</h3>
							<div class="title">
								<p><a href="admin/allOrder.do?state=2">订单管理</a></p>
							</div>
						</li>
						<li>
							<h3>会员管理</h3>
							<div class="title">
								<p><a href="admin/allMember.do">全部用户</a></p>
								<p style="background : yellow;"><a>用户封号与恢复</a></p>
							</div>
						</li>
						<li>
							<h3>数据分析</h3>
							<div class="title">
								<p><a href="admin/coreMember.do">用户数据</a></p>
								<p><a href="admin/rank.do">商品排行榜</a></p>
								<p><a href="admin/analysis.do">商品数据分析</a></p>
							</div>
						</li>
						<li>
							<h3>下载中心</h3>
							<div class="title">
								<p><a href="/shop/admin/upload.jsp">商品数据下载</a></p>
							</div>
						</li>
					</ul>
				</div>
				<div class="right">
					<table>
						<tr>
							<th>编号</th>
							<th>会员账号</th>
							<th>会员昵称</th>
							<th>联系电话</th>
							<th>操作</th>
						</tr>
						<jsp:useBean id="member" class="com.shopping.pojo.Member" scope="page"></jsp:useBean>
						<c:forEach items="${delMember }" var="member" varStatus="i">
							<tr>
								<td>${i.count }</td>
								<td>${member.username }</td>
								<td>${member.name }</td>
								<td>${member.phoneNumber }</td>
								<td><button type="button" onclick="acc('${member.id}')" style="width:60px; height:30px;">解封</button></td>
							</tr>
						</tr>
						</c:forEach>
					</table>
					<div class="page">
						<a class="button" href="admin/memberAcc.do?adminPageNumber=1&pageSize='${pageSize }'">第一页</a>
						<a class="button" href="admin/memberAcc.do?adminPageNumber=${adminPageNumber-1 }&adminPageSize=${adminPageSize }">前一页</a>
						<a class="button">${adminPageNumber }</a>
						<a class="button" href="admin/memberAcc.do?adminPageNumber=${adminPageNumber+1 }&adminPageSize=${adminPageSize }">后一页</a>
						<a class="button" href="admin/memberAcc.do?adminPageNumber=${max }&adminPageSize=${adminPageSize }">最后页</a>
						<a class="button" style="float:right">共${max }页</a>
					</div>
				</div>
			</container>
		</div>
	</body>
</html>
<script src="/shop/admin/js/jquery-3.4.1.min.js"></script>
<script src="/shop/admin/js/base.js"></script>
<script>
function acc(id){
	location.href = "/shop/admin/acc.do?id="+id+"&adminPageNumber=${adminPageNumber }&adminPageSize=${adminPageSize }";
}
var f = function(){
	$('.left>ul>li>h3').eq(3).css('background-color', 'red').siblings('.title').show().parent('li').siblings('li').children('.title').hide().siblings('h3').css('background-color', 'blue');;
	var s = 24;
	$('.enlarge').click(function(){
		if(s > 32){
			s = 32;
		}
		s+=2;
		$('.right>table').css('font-size', s+'px');
	});
	$('.narrow').click(function(){
		if(size < 16){
			s = 16;
		}
		s-=2;
		$('.right>table').css('font-size', s+'px');
	});
	$('.normal').click(function(){
		s = 24;
		$('.right>table').css('font-size', s+'px');
	});
}
(f)();
</script>