<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<s:form action="/user/login">
	
	<table width="70%" align="center">
		<tr>
			<td> 
				<s:textfield name="name" label="User name" />
			</td>
			 
		</tr>
		<tr>
			<td>
				<s:textfield name="pwd" label="Password" />
			</td>
			 
		</tr>
		<tr>
			<td>
			 <s:submit value="Submit" />
			 </td>
		</tr>
		
	</table>
	</s:form>
</body>
</html>