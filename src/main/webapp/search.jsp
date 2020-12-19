<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="header.jsp"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>Search Todo</title>
</head>
<body>
	<h1>Search Todos</h1>
	<form action="search">
        Text <br>
		<input required="true" name="text" size="20" />
	    <p />
		<button type="submit">Search</button>
	</form>
	
	 
	<c:if test="${!empty todos}">
	    <jsp:include page="list_todos.jsp" />
	</c:if>
	
	<h4>${message}</h4>
	
</body>
</html>