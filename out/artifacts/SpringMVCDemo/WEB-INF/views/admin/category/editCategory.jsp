<%--
  Created by IntelliJ IDEA.
  User: asids
  Date: 5/9/2022
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit Category</title>
</head>
<body>
<div align="center">
    <h2>Edit Category</h2>

    <form:form action="update?id=${category.catalogId}" method="post" modelAttribute="category">
        <table>
            <tr>
                <td>ID: </td>
                <td>
                    ${category.catalogId}
                    <form:hidden path="catalogId" />
                </td>
            </tr>

            <tr>
                <td>Catalog Name: </td>
                <td><form:input path="catalogName" /></td>
            </tr>
            <tr>
                <td>Priority: </td>
                <td><form:input path="priority" /></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Save" /></td>
            </tr>
        </table>
    </form:form>

</div>
</body>
</html>
