<%-- 
    Document   : ManageProfile
    Created on : Mar 14, 2023, 8:28:37 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
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
        <h1>Account Information</h1>
        <table border="1">
            <tr>
                <td>User ID</td>
                <td>UserName</td>
                <td>Password</td>
                <td>Join Date</td>
                <td>Role Name</td>
                <td>Stars Voted</td>
                <td style="text-align: center">Option</td>
            </tr>
            <tr>
                <td>${account.getUserId()}</td>
                <td>${account.getUsername()}</td>
                <td>${account.getPassword()}</td>
                <td>${account.getJoinDate()}</td>
                <td id="role">${account.getRoleName()}</td>
                <td>${account.getTotalRate()} <i class='bx bx-star'></i></td>
                <td id="op"><a href="profile?id=${account.getUserId()}&mod=1">Change Password</a></td>
            </tr>
    </body>
</html>
