<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/sign.css">
<script src="./javascript/jquery-2.1.3.min.js"></script>
<script src="./css/jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/jquery-ui-1.11.4.custom/jquery-ui.theme.min.css">
<title>Login Page</title>
</head>
<% 
	session.removeAttribute("User");
	session.removeAttribute("currentModel");
%>
<body>
  		<div class="body"></div>
		<div class="grad"></div>
		<div class="header">
			<div>Group<span>Ten</span></div>
		</div>
		<br>
		<div class="login">
			<form id="login" action="<%=request.getContextPath() %>/user/login" method="post">
				<input type="text" placeholder="username" name="name" id="name"><br>
				<input type="password" placeholder="password" name="pwd" id="pwd"><br>
				<input type="button" value="Login" id="loginButton">
			</form>
		</div>
<script type="text/javascript">
	$("#loginButton").bind("click",function(){
		var name= $("#name").val();
		var pwd=$("#pwd").val();
		if(name&&pwd){
			$("#login").submit();
		}else{
			alert("Please input name and password");
		}
	});
</script>
</body>
</html>