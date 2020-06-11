<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/6/2020
  Time: 10:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <title>Giỏ hàng</title>
    <style>
        .social a {
            font-size: 4rem;
            padding: 70px;
        }
        footer{
            background-color: rgba(82,64,120,0.9)!important;
            color: navy;
        }

    </style>
</head>

</head>
<body>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/d3js/5.15.1/d3.min.js"></script>


<h3>Thông tin sản phẩm</h3>

<form method="post" action="./cart_iem">
    <table>
        <tr>
            <td>Hình ảnh </td>
            <td>Thông tin sản phẩm</td>
            <td>Số lượng</td>
            <td>Đơn giá</td>
        </tr>
        <c:forEach items="${listitem}" var="orderItem">
         <tr>

             <td><img src="<c:out value="${orderItem.getProduct().getImg()}"/>"></td>
             <td><c:out value="${orderItem.getProduct().getName()}"/></td>
             <td> <input type="text" name="quantity" value="<c:out value="${orderItem.getQuantity()}"/>"></td>
             <td> <c:out value="${orderItem.getPrice()}"/></td>
<%--             <td>(${orderItem.getProduct().getName()})</td>--%>
<%--             <td>${orderItem.getQuantity()}</td>--%>
<%--             <td>${orderItem.getPrice()}</td>--%>>
         </tr>
        </c:forEach>
    </table>
   <input type="submit" value="Tiến hành đặt hàng">
</form>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>