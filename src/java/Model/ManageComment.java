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
public class ManageComment {

    private String comment_id, user_id, username, comment, food_id;

    public ManageComment() {
        connect();
    }

    public ManageComment(String comment_id, String user_id, String username, String comment, String food_id) {
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.username = username;
        this.comment = comment;
        this.food_id = food_id;
        connect();
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    @Override
    public String toString() {
        return "ManageComment{" + "comment_id=" + comment_id + ", user_id=" + user_id + ", username=" + username + ", comment=" + comment + '}';
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
            String strAdd = "insert into manage_comments\n"
                    + "(user_id, username, comment, food_id)\n"
                    + "values(?,?,?,?)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, user_id);
            pstm.setString(2, username);
            pstm.setString(3, comment);
            pstm.setString(4, food_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addData:" + e.getMessage());
        }

    }

    public ArrayList<ManageComment> getListComment() {
        ArrayList<ManageComment> listComment = new ArrayList<>();
        try {
            String strSelect = "select * from manage_comments";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                comment_id = String.valueOf(rs.getInt(1));
                user_id = String.valueOf(rs.getInt(2));
                username = rs.getString(3);
                comment = rs.getString(4);
                food_id = String.valueOf(rs.getString(5));
                listComment.add(new ManageComment(comment_id, user_id, username, comment, food_id));
            }
        } catch (Exception e) {
            System.out.println("getListComment:" + e.getMessage());
        }
        return listComment;
    }

    public void getDetailComment(String cid) {
        try {
            String strSelect = "select *\n"
                    + "from manage_comments\n"
                    + "where comment_id = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cid);
            rs = pstm.executeQuery();
            while(rs.next()){
                comment_id = String.valueOf(rs.getInt(1));
                user_id = String.valueOf(rs.getInt(2));
                username = rs.getString(3);
                comment = rs.getString(4);
                food_id = String.valueOf(rs.getString(5));
                
            }
        } catch (Exception e) {
            System.out.println("getDetailComment:"+e.getMessage());
            
        }
    }

    public void deleteCom(String cid) {
        try {
            String strSelect = "delete from manage_comments\n"
                    + "where comment_id =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, cid);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("deleteCom:"+e.getMessage());
        }
    }
}
