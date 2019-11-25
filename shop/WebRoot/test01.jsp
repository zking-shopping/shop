<%@page import="com.shopping.pojo.Member"%>
<%@page import="com.shopping.dao.daoImpl.MemberDaoImpl"%>
<%@page import="com.shopping.dao.MemberDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.shopping.db.DBHelper"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test01.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%
    	Connection conn = DBHelper.getConnection();
		MemberDao dao = new MemberDaoImpl();
		Member m = new Member();
		m.setUsername("aaa");
		m.setPassword("aaa");
		Member member = (Member) dao.select("selectForLogin", m, conn);
		System.out.println(member.getPhoneNumber());
    %>
  </body>
</html>
