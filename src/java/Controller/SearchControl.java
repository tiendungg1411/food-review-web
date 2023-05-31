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
public class SearchControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Setting to receive parameters is Vietnamese
        req.setCharacterEncoding("UTF-8");
        // Get parameter
        String txtSearch = req.getParameter("txt");
        // Get food list by txtSearch
        Food food = new Food();
        ArrayList<Food> listFood = food.getFoodListByName(txtSearch);
//        Get product top 1 rated
        food.getTop1Rate();
//        List categories
        Category category = new Category();
        ArrayList<Category> catList = category.getCategories();
        // Set data to JSP
        req.setAttribute("food", food);
        req.setAttribute("catList", catList);
        req.setAttribute("txtSearch", txtSearch);
        req.setAttribute("foodList", listFood);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);

    }

}
