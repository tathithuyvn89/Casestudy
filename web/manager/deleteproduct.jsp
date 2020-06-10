<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/3/2020
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting Product</title>
</head>
<body>
<h1>Delete Product</h1>
<p>
    <a href="./myproduct">Back to list product</a>
</p>
<form method="post">
    <h3>You are sure?</h3>
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Product Image</td>
                <td>${requestScope["product"].getImg()}</td>

            </tr>
            <tr>
                <td>Name</td>
                <td>${requestScope["product"].getName()}</td>

            </tr>
            <tr>
                <td>Price</td>
                <td>${requestScope["product"].getPrice()}</td>
            </tr>
            <tr>
                <td>Descriptip</td>
                <td>${requestScope["product"].getDescription()}</td>
            </tr>
            <tr>
                <td>Maker</td>
                <td>${requestScope["product"].getMaker()}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="DeleteProduct"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
