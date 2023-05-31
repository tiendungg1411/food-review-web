/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Review {

    private String reviewId, userId, foodId, reviewText, reviewDate, userName;

    public Review() {
        connect();
    }

    public Review(String reviewId, String userId, String foodId, String reviewText, String reviewDate, String userName) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.foodId = foodId;
        this.reviewText = reviewText;
        this.reviewDate = reviewDate;
        this.userName = userName;
        connect();
    }

    public Review(String userId, String foodId, String reviewText, String userName) {
        this.userId = userId;
        this.foodId = foodId;
        this.reviewText = reviewText;
        this.userName = userName;
        connect();
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", userId=" + userId + ", foodId=" + foodId + ", reviewText=" + reviewText + ", reviewDate=" + reviewDate + ", userName=" + userName + '}' + "\n";
    }

    PreparedStatement pstm;
    Connection cnn;
    ResultSet rs;

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
            String strAdd = "insert into food_reviews\n"
                    + "(user_id, food_id, review_text, username)\n"
                    + "values(?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, userId);
            pstm.setString(2, foodId);
            pstm.setString(3, reviewText);
            pstm.setString(4, userName);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addData:" + e.getMessage());
        }

    }

    public ArrayList<Review> getData(String productId) {
        ArrayList<Review> listData = new ArrayList<>();
        try {
            String strSelect = "select * from food_reviews where food_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, productId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String reviewId = String.valueOf(rs.getInt(1));
                String userId = String.valueOf(rs.getInt(2));
                String foodId = String.valueOf(rs.getInt(3));
                String reviewText = rs.getString(4);
                // format date
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String reviewDate = sdf.format(rs.getDate(5));
                String userName = rs.getString(6);
                listData.add(new Review(reviewId, userId, foodId, reviewText, reviewDate, userName));
            }
        } catch (Exception e) {
            System.out.println("getData:" + e.getMessage());
        }
        return listData;
    }

    public void delete(String id) {
        try {
            String strDelete = "delete from food_reviews\n"
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
            String strDelete = "delete from food_reviews\n"
                    + "where food_id =?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteProduct:" + e.getMessage());
        }

    }
}
