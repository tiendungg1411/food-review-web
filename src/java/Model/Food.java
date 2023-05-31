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
public class Food {

    private String foodId;
    private String foodName, image, description;
    private String avgRating, category;

    public Food() {
        connect();
    }

    public Food(String foodId, String foodName, String image, String description, String avgRating, String category) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.image = image;
        this.description = description;
        this.avgRating = avgRating;
        this.category = category;
        connect();
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Food{" + "foodId=" + foodId + ", foodName=" + foodName + ", image=" + image + ", description=" + description + ", avgRating=" + avgRating + ", category=" + category + '}' + "\n";
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

    // Get all product in database
    public ArrayList<Food> getFood() {
        ArrayList<Food> data = new ArrayList<Food>();
        try {
            String strSelect = "select * from foods";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                category = String.valueOf(rs.getInt(5));
                description = rs.getString(4);
                avgRating = String.valueOf(rs.getDouble(6));
                data.add(new Food(foodId, foodName, image, description, avgRating, category));
            }
        } catch (Exception e) {
            System.out.println("getFood:" + e.getMessage());
        }
        return data;
    }

    // Get product by category id
    public ArrayList<Food> getProductByCatId(String catId) {
        ArrayList<Food> data = new ArrayList<Food>();
        try {
            String strSelect = "select * from foods \n"
                    + "where categoryId = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, catId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = String.valueOf(rs.getInt(5));
                avgRating = String.valueOf(rs.getDouble(6));
                data.add(new Food(foodId, foodName, image, description, avgRating, category));
            }
        } catch (Exception e) {
            System.out.println("getProductByCatId:" + e.getMessage());
        }
        return data;
    }
//    Get product by id

    public void getDetailproduct(String productId) {
        try {
            String strSelect = "select * from foods\n"
                    + "where food_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, productId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = String.valueOf(rs.getInt(5));
                avgRating = String.valueOf(rs.getDouble(6));
            }
        } catch (Exception e) {
            System.out.println("getDetailproduct:" + e.getMessage());
        }
    }

    public void addAvgRating(double avgRating, String productId) {
        try {
            String strAdd = "update foods set avg_rating =? where food_id =?";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setDouble(1, avgRating);
            pstm.setInt(2, Integer.parseInt(productId));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addAvgRating:" + e.getMessage());
        }

    }

    public ArrayList<Food> getFoodList() {
        ArrayList<Food> foodList = new ArrayList<>();
        try {
            String strSelect = "select f.food_id, f.food_name, f.image, f.description, c.name, f.avg_rating\n"
                    + "from foods f join categories c\n"
                    + "on f.categoryId = c.id";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = rs.getString(5);
                avgRating = rs.getString(6);
                foodList.add(new Food(foodId, foodName, image, description, avgRating, category));

            }
        } catch (Exception e) {
        }
        return foodList;
    }

    public void addNewProduct(String name, String image, String description, String categoryId) {
        try {
            String strAdd = "insert into foods\n"
                    + "(food_name, image, description, categoryId)\n"
                    + "values\n"
                    + "(?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, name);
            pstm.setString(2, image);
            pstm.setString(3, description);
            pstm.setString(4, categoryId);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addNewProduct:" + e.getMessage());
        }

    }

    public void delete(String id) {
        try {
            String strDelete = "delete from foods\n"
                    + "where food_id = ?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("delete:" + e.getMessage());
        }

    }

    public void updateProduct(String id, String image, String description, String categoryId) {
        try {
            String strUpdate = "update foods set image =?, description =?, "
                    + "categoryId =?"
                    + " where food_id=?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, image);
            pstm.setString(2, description);
            pstm.setInt(3, Integer.parseInt(categoryId));
            pstm.setInt(4, Integer.parseInt(id));
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateProduct:" + e.getMessage());
        }

    }

    public void getTop1Rate() {
        try {
            String strSelect = "SELECT  TOP(1) *\n"
                    + "FROM foods\n"
                    + "ORDER BY avg_rating desc";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = String.valueOf(rs.getInt(5));
                avgRating = String.valueOf(rs.getDouble(6));
            }

        } catch (Exception e) {
            System.out.println("getTop1Rate:" + e.getMessage());
        }
    }

    public void getTop1RateByCatId(String catId) {
        try {
            String strSelect = "SELECT  TOP(1) *\n"
                    + "FROM foods\n"
                    + "where categoryId = ?\n"
                    + "ORDER BY avg_rating desc";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, catId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = String.valueOf(rs.getInt(5));
                avgRating = String.valueOf(rs.getDouble(6));
            }

        } catch (Exception e) {
            System.out.println("getTop1Rate:" + e.getMessage());
        }
    }

    public ArrayList<Food> getFoodListByName(String txtSearch) {
        ArrayList<Food> data = new ArrayList<Food>();
        try {
            String strSelect = "select *\n"
                    + "from foods\n"
                    + "where food_name like ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, "%"+txtSearch+"%");
            rs = pstm.executeQuery();
            while (rs.next()) {
                foodId = String.valueOf(rs.getInt(1));
                foodName = rs.getString(2);
                image = rs.getString(3);
                description = rs.getString(4);
                category = String.valueOf(rs.getInt(5));
                avgRating = String.valueOf(rs.getDouble(6));
                data.add(new Food(foodId, foodName, image, description, avgRating, category));
            }
        } catch (Exception e) {
            System.out.println("getFoodListByName:" + e.getMessage());
        }
        return data;

    }

}
