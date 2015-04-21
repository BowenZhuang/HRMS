<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.groupTen.model.User" %>
    <span>Welcome: <% 
    	Object user= session.getAttribute("User");
    	if(user!=null){
    		User u=(User)user;
    		out.print(u.getUsrName());
    	}
    %></span><br>
    <% 
    	Object model=session.getAttribute("currentModel");
    	String currentModel="import";
    	if(model!=null){
    		currentModel=String.valueOf(model);
    	}
    %>
    <a href="<%=request.getContextPath() %>/import/import.jsp" class="<%
    	if(currentModel.equals("import")){
    		out.print("button_selected");
    	}else{
    		out.print("button");
    	}
    %>">Upload Data</a>
    <a href="<%=request.getContextPath() %>/json/report.jsp" class="<% 
    	if(currentModel.equals("report")){
    		out.print("button_selected");
    	}else{
    		out.print("button");
    	}
    %>">Report</a>
    <a href="<%=request.getContextPath() %>/index.jsp" class="button">Logout</a>
    