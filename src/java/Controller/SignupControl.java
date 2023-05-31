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
import java.io.IOException;
import java.util.regex.Pattern;

/**
 *
 * @author HP
 */
public class SignupControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get parameteter from input
        String acc = req.getParameter("acc");
        String pass = req.getParameter("pass");
        String pass_repeat = req.getParameter("pass-repeat");
        // Notice to users
        String notAcc = "";
        String notPass = "";
        String notPassRepeat = "";
        String success = "";
        // regex check characters
        Pattern patternChar = Pattern.compile("[a-zA-Z0-9]{6,12}$");
        Pattern patternPass = Pattern.compile("[a-zA-Z0-9]{8,12}$");

        // click button sign up
        if (req.getParameter("btn_Sig") != null) {
            // Check account valid or not
            if (!patternChar.matcher(acc).find()) {
                notAcc = "Account must be from 6 to 12 characters, no spaces and no accents";
            } // Check password valid or not
            else if (!patternPass.matcher(pass).find()) {
                notPass = "Password must be at least 8 characters";
            } // Check whether the repeated password matches the password or not
            else if (!pass_repeat.equalsIgnoreCase(pass)) {
                notPassRepeat = "Password repeat must match the password";
            } // Sign up
            else {
                Users users = new Users(acc, pass);
                // Check duplicate account
                boolean check = users.checkDuplicateAccount();
                if (check) {
                    notAcc = "Account already exists";
                } else {
                    users.signUp();
                    success = "Account successfully created";
                }

            }
        }

        // send attributes
        req.setAttribute("acc", acc);
        req.setAttribute("pass", pass);
        req.setAttribute("pass_repeat", pass_repeat);
        req.setAttribute("notAcc", notAcc);
        req.setAttribute("notPass", notPass);
        req.setAttribute("success", success);
        req.setAttribute("notPassRepeat", notPassRepeat);
        req.getRequestDispatcher("Signup.jsp").forward(req, resp);
    }
}
