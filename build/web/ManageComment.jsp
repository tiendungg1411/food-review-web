<%-- 
    Document   : ManageComment
    Created on : Mar 17, 2023, 1:52:04 PM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <a style="font-size: 30px;" href="home"><i class='bx bxs-home'></i></a>
        <h1>Manage Comment</h1>
        <table border="1">
            <tr>
                <td>Comment Id</td>
                <td>User Id</td>
                <td>Username</td>
                <td>Comment</td>
                <td> Food Id</td>
            </tr>
            <c:forEach  items="${listComment}" var="item">
                <tr>
                    <td>${item.getComment_id()}</a></td>
                    <td>${item.getUser_id()}</td>
                    <td>${item.getUsername()}</td>
                    <td>${item.getComment()}</td>
                    <td>${item.getFood_id()}</td> 
                    <td><a href="accept?cid=${item.getComment_id()}">Accept</a></td>
                    <td><a href="delete?cid=${item.getComment_id()}">Delete</a></td>
                </tr>
            </c:forEach>       
    </body>
</html>
