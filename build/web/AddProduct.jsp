<%-- 
    Document   : AddProduct
    Created on : Mar 13, 2023, 10:09:21 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
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



            button{
                margin-top: 20px;
                cursor: pointer;
                height: 25px;

            }



            table{
                font-size: 20px;
            }


        </style>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

    </head>
    <body>
        <a style="font-size: 30px;" href="home"><i class='bx bxs-home'></i></a>
        <h1>Add New Product</h1>

        <form action="product" method="post">
            <table>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="name" value="" required=""/></td>
                </tr>

                <tr>
                    <td>Image:</td>
                    <td><input type="text" name="img" value="" required=""/></td>
                </tr>

                <tr>
                    <td>Description:</td>
                    <td id="des"><textarea rows="7" cols="50" type="text" name="des" value="" required=""></textarea></td>
                </tr>

                <tr>
                    <td>Category</td>
                    <td><input type="radio" name="cat" value="0" checked=""/>Food</td>
                    <td><input style="margin-left: -280px" type="radio" name="cat" value="1" />Drinks</td>
                </tr>
                <tr>

                    <td><button type="submit" name="add">Add Product</button></td>
                </tr>
            </table>
        </form>
    </body>
</html>
