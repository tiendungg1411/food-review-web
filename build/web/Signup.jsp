<%-- 
    Document   : Signup
    Created on : Mar 4, 2023, 2:15:58 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/signup.css"> 
    </head>
    <body>
        <!--Food picture-->
        <div class="container">
            <div class="main">
                <img src="img/imgsignup1.jpg" class="img-feature">
                <div class="control prev"><i class='bx bx-chevron-left'></i></div>
                <div class="control next"><i class='bx bx-chevron-right'></i></div>
            </div>
            <div class="list-image">
                <div> <img src="img/imgsignup1.jpg" alt=""/></div>
                <div> <img src="img/imgsignup2.jpg" alt=""/></div>
                <div> <img src="img/imgsignup3.jpg" alt=""/></div>
                <div> <img src="img/imgsignup4.jpg" alt=""/></div>
                <div> <img src="img/imgsignup5.jpg" alt=""/></div>

            </div>

        </div>

        <form action="signup" id="sign" method="post">
            <h2 id="title">Sign up for food review</h2>
            <!--Input account and password-->
            <ion-icon class="icon" name="person-outline"></ion-icon>
            <label for="acc"><b class="tit_inp">Account</b></label> <br>
            <input type="text" placeholder="Enter account" name="acc" value="${acc}" required>
            <br> <h5 class="notice">${notAcc}</h5>
            <br> 
            <ion-icon class="icon" name="lock-closed-outline"></ion-icon>
            <label for="pass"><b class="tit_inp">Password</b></label> <br>
            <input type="password" placeholder="Enter password" name="pass" value="${pass}" required>
            <br> <h5 class="notice">${notPass}</h5>
            <br> 
            <!--Repeate password-->
            <ion-icon class="icon" name="lock-closed-outline"></ion-icon>
            <label for="pass-repeat"><b class="tit_inp">Repeat Password</b></label> <br>
            <input type="password" placeholder="Repeat Password" name="pass-repeat" value="${pass_repeat}" required>
            <br> <h5 class="notice"> ${notPassRepeat}</h5>
            <br>
            <!--Button Sign up and Login-->
            <div class="btn_box">
                <button id="signBtn" type="submit" name="btn_Sig">Sign up</button>
                <a href="Login.jsp"><button id="logBtn" type="button">Login</button></a>
                <div id="suc"><h3>${success}</h3></div>
            </div>
        </form>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

        <script src="signup.js"></script>
    </body>
</html>
