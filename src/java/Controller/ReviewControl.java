/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Food;
import Model.Review;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import Model.Users;
import Model.Vote;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ReviewControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Food food = new Food();
        // Get parameter from Detail.jsp
        String productId = req.getParameter("pid");
        String starRating = req.getParameter("vote");
        String comment = req.getParameter("comment");
        String mess = "";
        //Get userId from session
        HttpSession session = req.getSession();
        Users users = (Users) session.getAttribute("acc");
        String userId = users.getUserId();
        String userName = users.getAccount();
        // Pass parameters to constructor
        Review review = new Review(userId, productId, comment, userName);

        // If user click button vote
        if (req.getParameter("btnVote") != null) {

            // Insert data to the table food_votes
            Vote vote = new Vote(userId, starRating, productId);
            vote.addData();

            // Get avg rating from food_votes by product id
            double avgRating = vote.getAvgRating(productId);
            // Insert avgRating to foods by productId
            food = new Food();
            food.addAvgRating(avgRating, productId);
        }
        // If user click button comment
        if (req.getParameter("btnCom") != null && comment != "") {
            // Insert data to the table food_reviews
            review.addData();

        }
        // Get data from table food_reviews by product id
        ArrayList<Review> listReview = review.getData(productId);

        // Get detail infor product from foods
        food.getDetailproduct(productId);
        // Get category
        Category category = new Category();
        category.getCatName(food.getCategory());
        //        Get category Name by CategoryId
        category.getCatName(food.getCategory());
        // Get product top 1 rated
        Food foodTop = new Food();
        foodTop.getTop1RateByCatId(food.getCategory());
        // Set data to JSP
        req.setAttribute("foodTop", foodTop);
        req.setAttribute("listReview", listReview);
        req.setAttribute("food", food);
        req.setAttribute("cat", category);
        req.setAttribute("mess", mess);
        req.setAttribute("starRating", starRating);
        req.getRequestDispatcher("Detail.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }

}
