<%-- 
    Document   : Login.jsp
    Created on : Feb 25, 2023, 9:42:44 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="css/login.css">
        <style>
            section{
                display: flex;
                justify-content: center;
                align-items: center;
                min-height: 100vh;
                width: 100%;


                background: url('img/background.jpg')no-repeat;
                background-position: center;
                background-size: cover;
            }
        </style>

    </head>
    <body>
        <section>
            <!--<h1 id="title">You & Me - Food Review</h1>-->
            <div class="form-box">
                <div class="form-value">
                    <form action="login" method="post">
                        <h2>Login</h2>
                        <div class="inputbox">
                            <ion-icon name="person-outline"></ion-icon>
                            <input type="text" name="acc" required>
                            <label for="">Account</label>
                        </div>
                        <div class="inputbox">
                            <ion-icon name="lock-closed-outline"></ion-icon>
                            <input type="password" name="pass" required>
                            <label for="">Password</label>
                        </div>
                        <br> <h5 id="notice">${error}</h5>

                        <button type="submit" name="btnLog">Log in</button>
                        <div class="register">
                            <p>Don't have a account <a href="Signup.jsp">Register</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>

