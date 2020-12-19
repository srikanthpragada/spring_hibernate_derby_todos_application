<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="allheader.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<title>Registration</title>
</head>
<body>
	<h1>Registration</h1>
	<sf:form modelAttribute="user" method="post">
     Username <br>
		<sf:input required="true" path="username" />
		<p />
     Password <br />
		<sf:password path="password" />
		<p />
		 Confirm Password <br />
		<sf:password path="confirmPassword" />
		<p/>
		 Email Address <br>
		<sf:input required="true" path="email" />
		<p />
		<button type="submit">Register</button>
	</sf:form>
	<p/>
	<a href="login">Login</a>
	<h4>${message}</h4>
</body>
</html>