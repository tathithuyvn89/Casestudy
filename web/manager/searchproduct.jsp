<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/3/2020
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<h3>We have product which you want. Please see</h3>


<form>
    <table border="3">
        <tr>
            <td>#</td>
            <td>ID</td>
            <td>Name</td>
            <td>Price</td>
            <td>Description</td>
            <td>Image</td>
            <td>Maker</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach  items='${requestScope["products"]}' var="product">
            <tr>
                <td></td>
                <td><a href="./product_manage?action=view&id=${product.getId()}">${product.getId()}</a></td>
                <td>${product.getName()}</td>
                <td>${product.getPrice()}</td>
                <td>${product.getDescription()}</td>
                <td>${product.getMaker()}</td>
                <td><img src="${product.getImg()}"></td>
                <td><a href="./product_manage?action=edit&id=${product.getId()}">Edit</a></td>
                <td><a href="./product_manage?action=delete&id=${product.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</form>
<p>
    <a href="./product_manage">Back to list product</a>
</p>
</body>
</html>
