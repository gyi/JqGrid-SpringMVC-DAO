<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'head.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
	<!--
	body {
		font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif;
		margin: 0;
		padding: 0;
		color: #000;
	}
	</style>
  </head>
  
  <body>
    <div style="width:100%;background-color:#1F2833;height:47px;background:#000000;filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;">
    	<div style="width:700px;margin:0 auto;text-align:right;line-height:47px;">
    		<div style="float:left;">
    			<img style="vertical-align:middle;" src="http://6.assets.dajieimg.com/images/header/dj_logo_2013.png">
    		</div>
    		<span style="color:#657384;">
    			登陆&nbsp;&nbsp;注册
    		</span>
    	</div>
    </div>
  </body>
</html>
