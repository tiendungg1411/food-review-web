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
public class CategoryControl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//       Get category
        String catId = req.getParameter("cid");
        Food food = new Food();
//        Get Product by categoryId
        ArrayList<Food> listByCat = food.getProductByCatId(catId);
//        Get top rate by category id
        food.getTop1RateByCatId(catId);
//        List categories
        Category category = new Category();
        ArrayList<Category> catList = category.getCategories();
//       Set data to JSP
        req.setAttribute("food", food);
        req.setAttribute("foodList", listByCat);
        req.setAttribute("catList", catList);
        req.setAttribute("tag", catId);
        req.getRequestDispatcher("Home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
