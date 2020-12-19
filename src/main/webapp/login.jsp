<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="allheader.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<title>Login</title>
</head>
<body>
	<h1>Login</h1>
	<sf:form modelAttribute="user" method="post">
     Username <br>
		<sf:input required="true" path="username" />
		<p />
     Password <br />
		<sf:password path="password" />
		<p />
		<button type="submit">Login</button>
	</sf:form>
	
	<h4><a href="register">Register as new user!</a></h4>
	 
	<h4>${message}</h4>
</body>
</html>