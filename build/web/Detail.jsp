<%-- 
    Document   : Detail.jsp
    Created on : Mar 7, 2023, 12:22:09 AM
    Author     : HP
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Model.Food" %>
<%@page import="Model.Category" %>
<%@page import="Model.Review" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${food.getFoodName()}</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link href="css/detail.css" rel="stylesheet" type="text/css"/>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

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
            </div>
        </section>

        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block"> 
                            <li class="list-group-item text-black ">${cat.getCatName()}</li>   
                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Top rated <i class='bx bxs-star'></i></div>
                        <div class="card-body">
                            <img class="img-fluid" src="${foodTop.getImage()}" />
                            <h5 class="card-title">${foodTop.getFoodName()}</h5>
                            <p class="card-text"></p>
                            <p class="bloc_left_price">${foodTop.getAvgRating()}<i class='bx bxs-star'></i></p>
                        </div>
                    </div>
                </div>

                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="${food.getImage()}"></a></div>
                                            <br>  
                                            <!--Users review-->
                                            <h5 style="color: red; font-weight: bolder; text-decoration: underline; font-style: italic">Users reviews:</h5> <br>
                                            <c:forEach items="${listReview}" var="item">
                                                <h6>${item.getUserName()}: ${item.getReviewText()} (${item.getReviewDate()})</h6> <br>
                                            </c:forEach>

                                        </div> <!-- slider-product.// -->

                                        <div class="img-small-wrap">
                                        </div> <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${food.getFoodName()}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency"></span><span class="num">${food.getAvgRating()}<i class='bx bxs-star'></i></span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->                                       
                                        <form action="review" method="post" >
                                            <dl class="item-property">
                                                <dt>Description</dt>
                                                <dd><p>
                                                        ${food.getDescription()}
                                                    </p></dd>
                                            </dl>

                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-5">
                                                    <dl class="param param-inline">
                                                        <input type="text" style="display: none" name="pid" value="${food.getFoodId()}" readonly="">
                                                        <dt>VOTED <i class='bx bx-star'></i> </dt>
                                                       
                                                            <h3 style="color: red">${mess}</h3>
                                                        <dd>
                                                            <select name="vote" class="form-control form-control-sm" style="width:70px;">
                                                                <option value="1"> 1  </option>
                                                                <option value="2"> 2  </option>
                                                                <option value="3"> 3 </option>
                                                                <option value="4"> 4 </option>
                                                                <option value="5"> 5 </option>
                                                            </select>
                                                        </dd>
                                                    </dl>  <!-- item-property .// -->
                                                </div> <!-- col.// -->

                                            </div> <!-- row.// -->
                                            <hr>
                                            <button type="submit" class="btn btn-lg btn-primary text-uppercase" name="btnVote"> VOTE </button>
                                            <button type="submit" class="btn btn-lg btn-outline-primary text-uppercase" name="btnCom"> Comment </button> <br> <br>
                                            <textarea name="comment" rows="7" cols="50" placeholder="Let's Review..."></textarea>

                                        </form>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
