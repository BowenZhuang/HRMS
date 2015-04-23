<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Historical Weather Reporting System</title>
<link rel="stylesheet" type="text/css" href="../css/simple.css">
<link rel="stylesheet" href="../css/jquery-ui-1.11.4.custom/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="../css/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">
<script src="../javascript/jquery-2.1.3.min.js"></script>
<script src="../css/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
</head>
<% 
	session.setAttribute("currentModel", "user");
%>
<body>
<div id="header">
<h3>Historical Weather Reporting System</h3>
</div>
<div id="nav">
<jsp:include page="/template/header.jsp"></jsp:include>
</div>
<div id="section">
<h2>Welcome to Reporting System</h2>
<p>
	In the case where a user has already loaded data into system, 
a new load will cause all previous data to be overwritten.
</p>
<p>
	After the data is loaded, we’d like to provide the users some ability to explore the data 
the data they’ve provided.
</p>
</div>
<div id="footer">
<jsp:include page="/template/footer.jsp"></jsp:include>
</div>
</body>
</html>