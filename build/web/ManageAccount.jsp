<%-- 
    Document   : ManageAccount
    Created on : Mar 13, 2023, 3:27:58 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account</title>
        <style>
            /*Title*/
            h1{
                font-size: 40px;
                color: red;
                text-decoration: underline;
                font-style: italic;
                margin-left: 50px;
            }
            /*Style for table*/
            table{
                font-size: 20px;
            }
            /*Link*/
            #op{
                opacity: 0.7;
            }

            #op:hover{
                opacity: 1;
                text-decoration: underline;
            }
            /*Role name*/
            #role{
                color: red;
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
        <h1>Manage Account</h1>

        <!--List Account-->
        <table border="1">
            <tr>
                <td>User ID</td>
                <td>UserName</td>
                <td>Password</td>
                <td>Join Date</td>
                <td>Role Name</td>
                <td>Stars Voted</td>
                <td>Option</td>
            </tr>
            <c:forEach  items="${listUsers}" var="item">
                <tr>
                    <td>${item.getUserId()}</td>
                    <td>${item.getUsername()}</td>
                    <td>${item.getPassword()}</td>
                    <td>${item.getJoinDate()}</td>
                    <td id="role">${item.getRoleName()}</td>
                    <td>${item.getTotalRate()} <i class='bx bx-star'></i></td>
                    <td id="op"><a href="manage?uid=${item.getUserId()}&mod=1">Delete</a></td>
                </tr>
            </c:forEach>

    </body>
</html>
