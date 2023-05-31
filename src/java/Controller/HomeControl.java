/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Category;
import Model.Food;
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
public class HomeControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       Get list food and drinks
        Food food = new Food();
        ArrayList<Food> foodList = food.getFood();
//       Get list categories
        Category category = new Category();
        ArrayList<Category> catList = category.getCategories();
        
//        Get product top 1 rated
          food.getTop1Rate();
//       Set data to JSP
        req.setAttribute("food", food);
        req.setAttribute("foodList", foodList);
        req.setAttribute("catList", catList);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
