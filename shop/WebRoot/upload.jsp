<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'upload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function flushCode(obj){
			$(obj).attr("src","image?id="+new Date().getTime());
		}
		function show(obj){
			//获得图片名字和后缀
			var str=$(obj).val();
			var fileName = str.substring(str.lastIndexOf('\\')+1,str.lastIndexOf('.'));
			var fileExt = str.substring(str.lastIndexOf('.') + 1);
			$("#fileName").val(fileName);
			$("#fileExt").val(fileExt);
			
			//显示图片
			var fr =new FileReader();
			var f = obj.files[0];
			fr.readAsDataURL(f);
			fr.onload=function(e){
				var content = e.target.result;
				//预览
				$("#image").attr("src",content);
				//将编码后的字符串（长）放入隐藏域
				$("#content").val(content);
				
				//是图片就展示
				if($("#image").attr("src")!=""&&
				(fileExt=="jpg"||fileExt=="png"||fileExt=="gif"||fileExt=="jepg")){
					$("#image").css("display","block");
				}
			}
		}
		$(function(){
			if($("#image").attr("src")==""){
				$("#image").css("display","none");
			};
		});
		function getFileName(path) {
			var pos1 = path.lastIndexOf('/');
			var pos2 = path.lastIndexOf('\\');
			var pos = Math.max(pos1, pos2);
			if (pos < 0) {
				return path;
			}else {
				return path.substring(pos + 1);
			}
		};
	</script>
	<style type="text/css">
		div{
			margin-left: 300px;
			margin-top:30px;
		}
		input,
		img{
			margin-top: 10px;
		}
		#msg{
			color: red;
			border: none;
		}
		#msg:disabled{
			background: none;
		}
	</style>
  </head>
  
  <body>
    	<div>
    		<img src="" width="50px" height="50px" id="image"/>
    		<form action="upload" method="post">
    			<input type="file" id="photo" name = "photo" onchange="show(this)"/>
    			<input type="text" value="${imgmsg }" id="msg" disabled="disabled"/><br/>
    			<input type="hidden" value="" id="content" name="content"/>
    			<input type="hidden" value="" id="fileName" name="fileName"/>
    			<input type="hidden" value="" id="fileExt" name="fileExt"/>
    			<img src="imageCode.do" onclick="flushCode(this)"/><br/>
    			验证码:<input type="text" name="validateCode"/>
    			<input type="text" value="${codemsg }" id="msg" disabled="disabled"/><br/>
    			<input type="submit" value="上传"/><br />
    			<input type="text" value="${filemsg }" id="msg" disabled="disabled"/><br/>
    		</form>
    	</div>
  </body>
</html>
