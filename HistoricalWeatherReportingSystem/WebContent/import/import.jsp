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
<s:form id="form" action="/import/import" enctype="multipart/form-data">
	<s:file name="file" label="File" accept=".csv"/>
</s:form>
<br/>
	<input type="button" id="checkButton" value="OK">
</div>
<div id="footer">
<jsp:include page="/template/footer.jsp"></jsp:include>
</div>

<script type="text/javascript">
	$("#checkButton").bind("click",function(){
		$.getJSON("<%=request.getContextPath()%>/json/checkUser",  
				function( result ) {
					if($("#import_file").val()==""){
						alert("Please choose a file.");
						return;
					}
					if(result.exist){
						if(confirm('There already exists weather data, do you want to overwrite them?')){
							$.getJSON('<%=request.getContextPath()%>/json/deleteUserData',
							function(result){
								if(result.delete){
									$("#form").submit();
								}	
							});
						}
					}else{
						//alert("submit");
						$("#form").submit();
					}
					
				});
	});
</script>
</body>
</html>