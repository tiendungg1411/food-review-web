/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ManageAccount;
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
public class ManageProfileControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get parameter id of user
        String id = req.getParameter("id");
        // Get infor user by id
        ManageAccount account = new ManageAccount();
        account.getInforUser(id);
        System.out.println(account);
        // Request change password
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            req.setAttribute("account", account);
            req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
        }

        // Set data to JSP
        req.setAttribute("account", account);
        req.getRequestDispatcher("ManageProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Change Password
        if (req.getParameter("change") != null) {
            // Get parameter id, user, oldPass of user
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String oldPass = req.getParameter("pass");
            // Get infor user
            ManageAccount account = new ManageAccount();
            account.getInforUser(id);
            // Get parameter new pass and re-pass
            String newPass = req.getParameter("new");
            String rePass = req.getParameter("re");
            // regex check password valid
            Pattern patternPass = Pattern.compile("[a-zA-Z0-9]{8,12}$");

            // Check new password valid or not
            if (!patternPass.matcher(newPass).find()) {
                // Notice to users
                String notPass = "Password must be at least 8 characters";
                // Set data to JSP
                req.setAttribute("account", account);
                req.setAttribute("newPass", newPass);
                req.setAttribute("notPass", notPass);
                req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
                // Check whether the repeated password matches the password or not
            } else if (!rePass.equalsIgnoreCase(newPass)) {
                // Notice to users
                String notPassRepeat = "Password repeat must match the password";
                // Set data to JSP
                req.setAttribute("account", account);
                req.setAttribute("newPass", newPass);
                req.setAttribute("rePass", rePass);
                req.setAttribute("notPassRepeat", notPassRepeat);
                req.getRequestDispatcher("ChangePassword.jsp").forward(req, resp);
            } else {
                // Change password by userId
                Users users = new Users();
                users.changePassword(id, newPass);
                // Back to ManageProfile.jsp
                ManageAccount accountNewPass = new ManageAccount();
                accountNewPass.getInforUser(id);
                req.setAttribute("account", accountNewPass);
                req.getRequestDispatcher("ManageProfile.jsp").forward(req, resp);
            }
        }

    }
}
