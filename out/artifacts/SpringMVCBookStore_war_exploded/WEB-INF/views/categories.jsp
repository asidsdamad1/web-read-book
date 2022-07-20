<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List Catalog</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>CatalogID</th>
				<th>Catalog Name</th>
				<th>Description</th>
				<th>Priority</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${listCat }" var="cat">
				<tr>
					<td>${cat.catalogId }</td>
					<td>${cat.catalogName }</td>
					<td>${cat.description }</td>
					<td>${cat.priority }</td>
					<td>${cat.status }</td>
					<td><a href="initEdit.htm?catalogId=${cat.catalogId }">Update</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="initCreate.htm">Create New Catalog</a>
</body>
</html>