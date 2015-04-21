<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
	session.setAttribute("currentModel", "import");
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
<s:form action="/import/import" enctype="multipart/form-data">
	<s:file name="file" label="File"/>
	<s:submit value="Submit" />
</s:form>
</div>
<div id="footer">
<jsp:include page="/template/footer.jsp"></jsp:include>
</div>
</body>
</html>