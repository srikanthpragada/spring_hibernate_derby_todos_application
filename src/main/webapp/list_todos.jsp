
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--  find out whether we have any items in the list using length() function of JSTL  -->

<c:if test="${fn:length(todos) gt 0}">
	<table style="width: 100%">
		<tr class="tableheader">
			<td style="text-align: center">Description</td>
			<td style="text-align: center">Category</td>
			<td></td>

			<c:forEach var="todo" items="${todos}">
				<tr>
					<td style="text-align: center">${todo.text}</td>
					<td style="width:20%;text-align: center">${todo.category}</td>
					<td style="text-align: center;width:10%"><a href="edit/${todo.id}">Edit</a>
						<a onclick="return confirm('Do you really want to delete?')"
						href="delete/${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
	</table>
</c:if>

<c:if test="${fn:length(todos) == 0}">
	<h4>Sorry! No todos found!</h4>
</c:if>