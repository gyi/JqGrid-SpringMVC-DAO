<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'showAddOperator.jsp' starting page</title>

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
	<form action="index.do?method=doModify" method="post" id="form" name="form">
		<table>
			<tr>
				<td><span>name:</span></td>
				<td><input type="text" id="name" name="name"
					value="${helpCategory.name }"/>
					<input type="hidden" id="helpCategoryId"
					name="helpCategoryId" value="${helpCategory.help_category_id }"/>
					</td>
			</tr>
			<tr>
				<td><span>parent_category_id:</span></td>
				<td><input type="text" id="parentCategoryId"
					name="parentCategoryId" value="${helpCategory.parent_category_id }"/>
				</td>
			</tr>
			<tr>
				<td><span>url</span></td>
				<td><input type="text" id="url"
					name="url" value="${helpCategory.url }">
				</td>
			</tr>
			<tr>
				<td>
					<input type="button" id="submit" name="submit" value="submit" onclick="onSaveClick()">
				</td>
				<td>
					<input type="reset" id="reset" name="reset" value="reset">
				</td>
				
			</tr>
		</table>
	</form>
</body>
<jsp:include flush="false" page="/jsp/import/js.jsp">
	<jsp:param name="list" value="true" />
</jsp:include>
<script type="text/javascript" src="js/showModifyOperator.js"></script>
</html>
