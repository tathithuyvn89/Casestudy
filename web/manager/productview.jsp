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
    <title>Product</title>
</head>
<body>

<p>
    <a href="./product_manage">Back to list product</a>
</p>
<form>

    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Hình ảnh</td>
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
        </table>
    </fieldset>
</form>
</body>
</html>
