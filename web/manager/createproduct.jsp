<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/3/2020
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>From create new product</title>
</head>
<body>
<h1>Create new Product Form</h1>
<p>
    <c:if test='${requestScope["message"]!=null}'>
        <span  style="color: gold" class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p><a href="./product_manage">Back to list product</a></p>
<form method="post">
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="productName"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="productPrice"></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="productDes"></td>
            </tr>
            <tr>
                <td>Img URL</td>
                <td><input type="text" name="productImg"></td>
            </tr>
            <tr>
                <td>Maker</td>
                <td><input type="text" name="productMaker"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create new product"></td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>
