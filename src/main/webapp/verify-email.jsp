<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.appsdeveloperblog.app.ws.service.UsersService" %>
<%@ page import="com.appsdeveloperblog.app.ws.service.impl.UsersServiceImpl" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Verification</title>
</head>
<body>
	<h1>Email Verification Page</h1>
	
	<%
	String token = request.getParameter("token");
	UsersService usersService = new UsersServiceImpl();
	boolean isEmailVerified = usersService.verifyEmail(token);
	
	if(isEmailVerified){
	%>
	
	<p>Thank you! Your email has been verified!</p>
		
	<%	
	} else{
	%>
	
	<p>Sorry, your email has not been verified. Try again.</p>
	
	<%	
	}
	%>

</body>
</html>