/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP
 */
public class Vote {

    private String voteId, userId, voteDate, rating, foodId;

    public Vote() {
        connect();
    }

    public Vote(String voteId, String userId, String voteDate, String rating, String foodId) {
        this.voteId = voteId;
        this.userId = userId;
        this.voteDate = voteDate;
        this.rating = rating;
        this.foodId = foodId;
        connect();
    }

    public Vote(String userId, String rating, String foodId) {
        this.userId = userId;
        this.rating = rating;
        this.foodId = foodId;
        connect();
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(String voteDate) {
        this.voteDate = voteDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    Connection cnn;
    Statement stm;
    ResultSet rs;
    PreparedStatement pstm;

    // Connect to database
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

    public void addData() {
        try {
            String strAdd = "insert into food_votes\n"
                    + "(user_id, rating, food_id)\n"
                    + "values(?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, userId);
            pstm.setString(2, rating);
            pstm.setString(3, foodId);
            pstm.execute();
        } catch (Exception e) {
        }
    }

    public double getAvgRating(String productId) {
        String num_votes = "";
        String total_rating = "";
        double avgRating = 0;
        double num = 0;
        double total = 0;
        try {
            String strSelect = "SELECT COUNT(*) AS num_votes, SUM(rating) AS total_rating FROM food_votes WHERE food_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, productId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                num_votes = String.valueOf(rs.getInt(1));
                total_rating = String.valueOf(rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println("getAvgRating:" + e.getMessage());
        }
        num = Double.parseDouble(num_votes);
        total = Double.parseDouble(total_rating);
//        Avg rating
        avgRating = total / num;
        return avgRating;

    }

    public void delete(String id) {
        try {
            String strDelete = "delete from food_votes\n"
                    + "where user_id =?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        }
    }

    public void deleteProduct(String id) {
        try {
            String strDelete = "delete from food_votes\n"
                    + "where food_id =?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteProduct:" + e.getMessage());
        }

    }



}
