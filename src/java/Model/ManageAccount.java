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
public class ManageAccount {

    private String userId, username, password, joinDate, roleName, totalRate;

    public ManageAccount() {
        connect();
    }

    public ManageAccount(String userId, String username, String password, String joinDate, String roleName, String totalRate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.joinDate = joinDate;
        this.roleName = roleName;
        this.totalRate = totalRate;
        connect();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    @Override
    public String toString() {
        return "ManageAccount{" + "userId=" + userId + ", username=" + username + ", password=" + password + ", joinDate=" + joinDate + ", roleName=" + roleName + ", totalRate=" + totalRate + '}' + "\n";
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

    public ArrayList<ManageAccount> getListUsers() {
        ArrayList<ManageAccount> listUsers = new ArrayList<>();
        try {
            String strSelect = "SELECT SUM(rating) AS total_rating, u.user_id, u.username, u.password,\n"
                    + "u.join_date, r.roleName\n"
                    + "FROM food_votes f right join users u \n"
                    + "on f.user_id = u.user_id\n"
                    + "join roles r\n"
                    + "on u.roleId = r.id\n"
                    + "group by u.user_id, u.username, u.password, u.join_date, r.roleName";
            pstm = cnn.prepareStatement(strSelect);
            rs = pstm.executeQuery();
            while (rs.next()) {
                totalRate = "0";
                if (rs.getString(1) != null) {
                    totalRate = String.valueOf(rs.getInt(1));
                }
                userId = String.valueOf(rs.getInt(2));
                username = rs.getString(3);
                password = rs.getString(4);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                joinDate = date.format(rs.getDate(5));
                roleName = rs.getString(6);

                listUsers.add(new ManageAccount(userId, username, password, joinDate, roleName, totalRate));
            }
        } catch (Exception e) {
        }
        return listUsers;
    }

    public void getInforUser(String id) {
        try {
            String strSelect = "SELECT SUM(rating) AS total_rating, u.user_id, u.username, u.password, r.roleName, u.join_date\n"
                    + "FROM food_votes f right join users u \n"
                    + "on f.user_id = u.user_id\n"
                    + "join roles r\n"
                    + "on u.roleId = r.id\n"
                    + "where u.user_id = ?\n"
                    + "group by u.user_id, u.username, u.password, u.join_date, r.roleName";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                totalRate = "0";
                if (rs.getString(1) != null) {
                    totalRate = String.valueOf(rs.getInt(1));
                }
                userId = String.valueOf(rs.getInt(2));
                username = rs.getString(3);
                password = rs.getString(4);
                roleName = rs.getString(5);
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                joinDate = date.format(rs.getDate(6));
            }
        } catch (Exception e) {
            System.out.println("getInforUser:"+e.getMessage());
        }
    }

}
