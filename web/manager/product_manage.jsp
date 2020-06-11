<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/10/2020
  Time: 1:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Productmanagement</title>
</head>
<body>
<h1 style="right: 0px"><a href="./logout">Đăng xuất</a></h1>
<h1>Product list</h1>
<div>
<%--    action="./product_manage?action=search--%>
    <form method="post" action="./product_manage?action=search" >
        <input type="text" name="searchname" placeholder=" Enter a name of product">
        <input type="hidden" name="address" value="manager/product_manage.jsp">
        <button type="submit">Search</button>
    </form>
</div>
<a href="./product_manage?action=create">Create new product</a>
<table border="3">
    <tr>
        <td>#</td>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Description</td>
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
            <td><a href="./product_manage?action=edit&id=${product.getId()}">Edit</a></td>
            <td><a href="./product_manage?action=delete&id=${product.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
