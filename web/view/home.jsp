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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/css.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Trang chủ</title>
    <style>
        #mato{
            background-color: rebeccapurple;
        }
        #dongcuoi{
            background-color: rebeccapurple;
        }
        header {
            position: relative;
        }
        .shopping-mall{
            position: absolute;
            bottom: 20px;
            font-variant: small-caps;
        }

        .shopping-mall>h1{
            font-family: Impact;
            font-size: 50px;
            color: white;
            letter-spacing: 0.3px;
            text-shadow: 0 0 2px black;
            padding-bottom: 0px;
            border-bottom: 1px dotted gray;
            margin: 0px;
        }
        .shopping-mall>h5{
            margin-top: 5px;
            letter-spacing: 0.5px;
        }
        .poly-cart{
            margin-top: 5px;
        }

        .poly-cart ul {
            padding: 0px;
            margin: 0px;
            list-style: none;
            font-variant: small-caps;
        }

        .poly-cart .panel-heading strong {
            font-variant: small-caps;
            font-size: larger;
            text-shadow: 0 0 2px darkgray;
        }

        .poly-prod{
            text-align:center;
            padding:5px;
        }

        .poly-prod .panel-body img{
            height: 150px;
            max-width: 95%;
        }
    </style>
</head>
<body>
<div class="container" style = "height: auto">

    <nav class="navbar navbar-inverse" id="mato">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="./home?action=list"><span class="glyphicon glyphicon-home"></span> Trang chủ</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li ><a href="#"> <span class="glyphicon glyphicon-list-alt"></span> Giới thiệu<span class="sr-only">(current)</span></a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-earphone"></span> Liên hệ</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-envelope"></span> Góp ý</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-question-sign"></span> Hỏi đáp</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> Tài khoản<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Đăng nhập</a></li>
                            <li><a href="#">Quên mật khẩu</a></li>
                            <li><a href="#">Đăng ký thành viên</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="./logout">Đăng xuất</a></li>
                            <li><a href="#">Đổi mật khẩu</a></li>
                            <li><a href="#">Cập nhật hồ sơ</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="#">Đơn hàng</a></li>
                            <li><a href="#">Hàng đã mua</a></li>
                        </ul>
                    </li>
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">English</a></li>
                    <li><a href="#">Tiếng Việt</a></li>
                    <li><a href="#">${name} </a></li>
<%--                    <li><h3>${name}</h3> </li>--%>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <div class = "row">

        <h1 style="color: red">${NOTFOUNDRESULTSEARCH}</h1>
        <article class="col-sm-9">
        <c:forEach items="${listProduct}" var="product">

                <div class = "col-sm-4 poly-prod">
                    <div class = "panel panel-default">
                        <div class = "panel-heading">
                            <h4 class = "panel-title">

                            </h4>
                        </div>
                        <div class = "panel-body">
                            <H4>${product.getName()}</H4>
                            <img src="${product.getImg()}" height="228" width="292"/>
                            <div id="chitiet">
                                <button type="submit" style="margin-top: -40px;margin-left: 175px;"><a href="./home?action=addcard&productid=${product.getId()}">Add vào giỏ hàng</a></button>
                            </div>
                        </div>
                        <div class = "panel-footer">
                                ${product.getPrice()}
                        </div>
                        <div class = "panel-footer">
                                ${product.getMaker()}
                        </div>
                    </div>
                </div>

        </c:forEach>
        </article>
        <aside class="col-sm-3">
            <div class = "poly-cart">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <img class="col-sm-5" src="images/shoppingcart.gif"/>
                        <ul class="col-sm-7">
                            <li>100 items</li>
                            <li>$56.8</li>
                            <li><a href="#">Xem giỏ hàng</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form method="post" action="./home?action=search">
                        <input type="text" name="nameproduct" placeholder="Keywords" class="form-control"/>
                        <input type="hidden"  name="address" value="view/home.jsp">
                        <button type="submit">Search</button>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class = "glyphicon glyphicon-th-list"></span>
                    <strong>Nhà sản xuất</strong>
                </div>
                <div class="list-group">
                    <a href="./home?action=group&maker=Samsung" class="list-group-item">Sam sung</a>
                    <a href="./home?action=group&maker=Xiaomi" class="list-group-item">Xiaomi</a>
                    <a href="./home?action=group&maker=Lock&Lock" class="list-group-item">Lock and lock</a>
                    <a href="./home?action=group&maker=OEM" class="list-group-item">OEM</a>
                    <a href="./home?action=group&maker=Apple" class="list-group-item">Apple</a>
                    <a href="./home?action=group&maker=Vsmart" class="list-group-item">Vsmart</a>
                    <a href="./home?action=group&maker=Aazon" class="list-group-item">Amazon</a>
                </div>
            </div>
        </aside>
    </div>
    <footer class="panel panel-default" id="dongcuoi">
        <div class = "panel-heading text-center">
            <p>CodeGym &copy; 2020</p>
        </div>
    </footer>
</div>
</body>
</html>