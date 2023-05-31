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
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class DetailControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Check if the user is logged in
        HttpSession session = req.getSession();
        if (session.getAttribute("acc") == null) {
            req.getRequestDispatcher("Login.jsp").forward(req, resp);
        }

//        Get product id
        String productId = req.getParameter("pid");
        Review review = new Review();
        ArrayList<Review> listReview = review.getData(productId);
//       Get product by id 
        Food food = new Food();
        food.getDetailproduct(productId);
//        Get top rate by category id
        Food foodTop = new Food();
        foodTop.getTop1RateByCatId(food.getCategory());
//        Get category Name by CategoryId
        Category category = new Category();
        category.getCatName(food.getCategory());
//        Set data to JSP
        req.setAttribute("foodTop", foodTop);
        req.setAttribute("listReview", listReview);
        req.setAttribute("food", food);
        req.setAttribute("cat", category);
        req.getRequestDispatcher("Detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
