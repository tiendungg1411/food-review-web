/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Food;
import Model.Review;
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
public class ManageProductControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Food food = new Food();

        // Request update product
        if (req.getParameter("mod") != null && req.getParameter("mod").equals("1")) {
            // Get product infor by id
            food.getDetailproduct(req.getParameter("id"));
            req.setAttribute("food", food);
            req.getRequestDispatcher("UpdateProduct.jsp").forward(req, resp);
        }

        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            // Id of product need delete
            String id = req.getParameter("id");
            // Delete product from food_votes
            Vote vote = new Vote();
            vote.deleteProduct(id);
            // Delete product from food_reviews
            Review review = new Review();
            review.deleteProduct(id);

            // Delete product
            food.delete(id);
        }

        // Get list product from database
        ArrayList<Food> listProduct = food.getFoodList();
        // Set data to JSP
        req.setAttribute("listProduct", listProduct);
        req.getRequestDispatcher("ManageProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get data from input 
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String image = req.getParameter("img");
        String description = req.getParameter("des");
        String categoryId = req.getParameter("cat");
        Food food = new Food();
        // Add data to database
        if (req.getParameter("add") != null) {
            food.addNewProduct(name, image, description, categoryId);
        }
        
        // Update product by id
        if(req.getParameter("update") != null){
            food.updateProduct(id, image, description, categoryId);
        }

        // Get list product from database
        ArrayList<Food> listProduct = food.getFoodList();
        // Set data to JSP
        req.setAttribute("listProduct", listProduct);
        req.getRequestDispatcher("ManageProduct.jsp").forward(req, resp);

    }

}
