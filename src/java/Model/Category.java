/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Category {

    private String catId, catName;

    public Category() {
        connect();
    }

    public Category(String catId, String catName) {
        this.catId = catId;
        this.catName = catName;
        connect();
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    Connection cnn;
    Statement stm;
    ResultSet rs;
    PreparedStatement pstm;

    private void connect() {
        try {
            cnn = (new DBContext().connection);
            if (cnn != null) {
                System.out.println("Connect success");
            } else {
                System.out.println("Connect fail");
            }
        } catch (Exception e) {
        }
    }

    public ArrayList<Category> getCategories() {
        ArrayList<Category> data = new ArrayList<Category>();
        try {
            String strSelect = "select * from categories";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                catId = String.valueOf(rs.getInt(1));
                catName = rs.getString(2);
                data.add(new Category(catId, catName));
            }
        } catch (Exception e) {
            System.out.println("getCategories:" + e.getMessage());
        }
        return data;
    }

    public void getCatName(String category) {
        try {
            String strSelect = "select * from categories where id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, category);
            rs = pstm.executeQuery();
            while (rs.next()) {
                catId = String.valueOf(rs.getInt(1));
                catName = rs.getString(2);
            }
        } catch (Exception e) {
            System.out.println("getCatName:" + e.getMessage());
        }

    }

}
