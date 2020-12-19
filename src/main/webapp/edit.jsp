<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<title>Edit Todo</title>
</head>

<body>
	<%@include file="header.jsp"%>
	<h1>Edit Todo</h1>
	<sf:form modelAttribute="todo" method="post">
        Text <br>
		<sf:input required="true" path="text" size="50" />
		<p />
        Category <br />
		<sf:select path="category">
			<sf:option value="education" label="Education" />
			<sf:option value="finance" label="Finance" />
			<sf:option value="ent" label="Entertainment" />
			<sf:option value="family" label="Family and Friends" />
			<sf:option value="maint" label="Maintenance" />
			<sf:option value="others" label="Others" />
		</sf:select>
		<p />
		<button type="submit">Update Todo</button>
	</sf:form>
	<h4>${message}</h4>
</body>
</html>