<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/3/2020
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product Form</title>
</head>
<body>
<h1>Edit Product</h1>
<p>
   <c:if test='${requestScope["message"]!=null}'>
       <span style="color:rebeccapurple;">${requestScope["message"]}</span>
   </c:if>
</p>
<p>
    <a href="./product_manage">Back list product</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="newName" value="${requestScope["product"].getName()}"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="newPrice" value="${requestScope["product"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Descriptip</td>
                <td><input type="text" name="newDes" value="${requestScope["product"].getDescription()}"></td>
            </tr>
            <tr>
                <td>IMG URL</td>
                <td><input type="text" name="newImg" value="${requestScope["product"].getImg()}"></td>
            </tr>
            <tr>
                <td>Maker</td>
                <td><input type="text" name="newMaker" value="${requestScope["product"].getMaker()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update Product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
