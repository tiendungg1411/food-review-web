/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.ManageAccount;
import Model.Review;
import Model.Users;
import Model.Vote;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ManageControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Delete account
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            String id = req.getParameter("uid");
            // Delete from food_reviews by userId
            Review review = new Review();
            review.delete(id);
            // Delete from food_votes by userId
            Vote vote = new Vote();
            vote.delete(id);
            // Delete account by userId
            Users users = new Users();
            users.deleteAccount(id);
        }

        // Get list users from database
        ManageAccount manage = new ManageAccount();
        ArrayList<ManageAccount> listUsers = manage.getListUsers();
        // Set data to JSP
        req.setAttribute("listUsers", listUsers);
        req.getRequestDispatcher("ManageAccount.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
