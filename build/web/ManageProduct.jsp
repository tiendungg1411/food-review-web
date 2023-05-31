<%-- 
    Document   : ManageProduct
    Created on : Mar 13, 2023, 4:39:52 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Product</title>
        <style>
            /*Title*/
            h1{
                font-size: 50px;
                color: red;
                text-decoration: underline;
                font-style: italic;
                margin-left: 50px;
                text-align: center;
            }
            
            a{
                text-decoration: none;
                opacity: 0.7;
            }
            
            a:hover{
                text-decoration: underline;
                opacity: 1;
            }
        </style>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <a style="font-size: 30px;" href="home"><i class='bx bxs-home'></i></a>
        <h1>List Product</h1>

        <table border="1">
            <tr>
                <td>Product ID</td>
                <td>Product Name</td>
                <td>Image</td>
                <td>Description</td>
                <td>Average Star Vote</td>
                <td>Category Name</td>
                <td>Option</td>
            </tr>
            <c:forEach  items="${listProduct}" var="item">
                <tr>
                    <td><a href="product?id=${item.getFoodId()}&mod=1">${item.getFoodId()}</a></td>
                    <td>${item.getFoodName()}</td>
                    <td>${item.getImage()}</td>
                    <td>${item.getDescription()}</td>
                    <td style="color: red">${item.getAvgRating()}</td>
                    <td>${item.getCategory()}</td>
                    <td><a href="product?id=${item.getFoodId()}&mod=2">Delete</a> </td>
                </tr>
            </c:forEach>       
        </table>
        <br>
        <button style="height: 30px;">  <a href="AddProduct.jsp">Add New Product</a> </button>
    </body>
</html>
