<%-- 
    Document   : Home.jsp
    Created on : Mar 6, 2023, 12:22:29 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Food" %>
<%@page import="Model.Users" %>
<%@page import="Model.Category" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food Review</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <!--begin of menu-->
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="home">Food Review</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse justify-content-end" id="navbarsExampleDefault">
                    <ul class="navbar-nav m-auto">
                        <c:if test="${sessionScope.acc.getRoleId() == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="manage">Account Management</a>
                            </li>                                
                        </c:if>
                            
                        <c:if test="${sessionScope.acc.getRoleId() == 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="product">Product Management</a>
                            </li> 
                        </c:if>                      
                            
                        <c:if test="${sessionScope.acc == null}">
                            <li class="nav-item">
                                <a class="nav-link" href="Login.jsp">Login</a>
                            </li>
                        </c:if>

                        <c:if test="${sessionScope.acc != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="profile?id=${sessionScope.acc.userId}">Hello ${sessionScope.acc.account}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logout">Logout</a>
                            </li
                        </c:if>

                    </ul>

                    <form action="search" method="post" class="form-inline my-2 my-lg-0">
                        <div style="margin-left: 300px;" class="input-group input-group-sm">
                            <input name="txt" type="text" class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" value="${txtSearch}" placeholder="Search...">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-secondary btn-number">
                                    <i class="fa fa-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </nav>
        <section class="jumbotron text-center">
            <div class="container">
                <h1 class="jumbotron-heading">Let's review food together</h1>
                <p class="lead text-muted mb-0"></p>
            </div>
        </section>
        <!--end of menu-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">                       
                            <li class="breadcrumb-item"><a href="home"><i class='bx bx-home-heart'></i></a></li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <!--List categories-->
                            <c:forEach items="${catList}" var="item">
                                <li class="list-group-item text-white ${tag == item.catId ? "active": ""}"><a href="category?cid=${item.catId}">${item.catName}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Top rate <i class='bx bxs-star'></i> </div>
                        <div class="card-body">
                            <img class="img-fluid" src="${food.getImage()}" />
                            <h5 class="card-title">${food.getFoodName()}</h5>
                            <p class="card-text"></p>
                            <p class="bloc_left_price">${food.getAvgRating()}<i class='bx bxs-star'></i></p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="row">
                        <!--List Food and Drinks-->
                        <c:forEach items="${foodList}" var="item">
                            <div class="col-12 col-md-6 col-lg-4">
                                <div class="card">
                                    <img class="card-img-top" src="${item.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${item.foodId}" title="View Product">${item.foodName}</a></h4>
                                        <p class="card-text show_txt">${item.description}</p>
                                        <div class="row">
                                            <div class="col">
                                                <p class="btn btn-danger btn-block">${item.avgRating} <i class='bx bxs-star'></i></p>
                                            </div>
                                            <div class="col">
                                                <a href="detail?pid=${item.foodId}" class="btn btn-success btn-block">Let's review the food</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>

        <!-- Footer -->
        <footer class="text-light">
            <a href="home">  <h2 style="text-align: center; color: grey; font-style: italic">@foodreview</h2></a>
        </footer>
       
    </body>
</html>
