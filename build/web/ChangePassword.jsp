<%-- 
    Document   : ChangePassword
    Created on : Mar 14, 2023, 8:49:39 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <style>
            /*Style for title*/
            h1{
                font-size: 40px;
                color: red;
                text-decoration: underline;
                font-style: italic;
            }

            /*Style for input box*/
            input{

            }


            /*Style for button*/
            button{
                margin-top: 20px;
                cursor: pointer;
                height: 25px;

            }


            /*Style for table*/
            table{
                font-size: 20px;
            }
            /*Style for title input*/
            .first{
                font-weight: bolder;
            }
            /*Style for notice*/
            .notice{
                color: red;
            }
        </style>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
         <a style="font-size: 30px;" href="home"><i class='bx bxs-home'></i></a>
        <h1>Change Password</h1>

        <form action="profile" method="post">
            <table>
                <tr>
                    <td class="first">Id</td>
                    <td><input type="text" name="id" value="${account.getUserId()}" readonly=""/></td>
                </tr>

                <tr>
                    <td class="first">Username</td>
                    <td><input type="text" name="name" value="${account.getUsername()}" readonly=""/></td>
                </tr>

                <tr>
                    <td class="first">Password</td>
                    <td id="des"><input type="password" name="pass" value="${account.getPassword()}" readonly="" ></td>                  
                </tr>


                <tr>
                    <td class="first">New Password</td>
                    <td><input type="password" name="new" value="${newPass}" required="" /></td>
                </tr>
                <tr><td class="notice">${notPass}</td> </tr>


                <tr>
                    <td class="first">Repeat Password</td>
                    <td><input type="password" name="re" value="${rePass}" required="" /></td>

                </tr>
                <tr><td class="notice">${notPassRepeat}</td> </tr>



                <tr>

                    <td><button type="submit" name="change">Change</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
