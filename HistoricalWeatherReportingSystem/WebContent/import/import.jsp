<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Import Weather Data</title>
</head>
<body>
<s:form action="/import/import" enctype="multipart/form-data">
	<s:file name="file" label="File"/>
	<s:submit value="Submit" />
</s:form>

</body>
</html>