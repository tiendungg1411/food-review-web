/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class LoginControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Account and password input from Client
        String account = req.getParameter("acc");
        String password = req.getParameter("pass");
        String error = "Invalid account or password!";
        // Button LoginControl
        String btn_Log = req.getParameter("btnLog");
        //If user click LoginControl
        if (btn_Log != null) {
            // Check user in database
            Users user = new Users(account, password);
            boolean check = user.checkUser();
            // If user exist in database
            if (check) {
                // Get Infor user by username
                Users inforUser = new Users();
                inforUser.getInforUser(user.getAccount());

                HttpSession session = req.getSession();
                session.setAttribute("acc", inforUser);
                resp.sendRedirect("home");
            } else {
                req.setAttribute("error", error);
                req.getRequestDispatcher("Login.jsp").include(req, resp);
            }
        }

    }

}
