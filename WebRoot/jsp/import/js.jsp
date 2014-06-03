<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String portPath = null;
int port = request.getServerPort();
if(80==port){
    portPath = "";
}else{
    portPath = ":"+String.valueOf(port);
}
String basePath = request.getScheme()+"://"+request.getServerName()+portPath+path+"/";
%>
<script type="text/javascript">
	var realPath = "<%=basePath%>";
</script>
<link rel="stylesheet" type="text/css" media="screen" href="component/css_/ui-lightness/jquery-ui-1.7.3.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="component/js_/src/css/ui.jqgrid.css" />
<link rel="stylesheet" type="text/css" media="screen" href="component/js_/src/css/jquery.searchFilter.css" />

<script src="component/js_/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="component/js_/src/grid.loader.js" type="text/javascript"></script> 
<script src="component/js_/jquery.jqGrid.min-3.8.js" type="text/javascript"></script> 
<script src="component/base/util.js" type="text/javascript"></script> 
<script src="component/base/IE.js" type="text/javascript"></script> 