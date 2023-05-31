/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Users {

    // User's information
    private String account, password, userId, roleId, joinDate;

    public Users() {
        connect();
    }

    public Users(String account, String password, String userId, String roleId, String joinDate) {
        this.account = account;
        this.password = password;
        this.userId = userId;
        this.roleId = roleId;
        this.joinDate = joinDate;
        connect();
    }

    public Users(String account, String password) {
        this.account = account;
        this.password = password;
        connect();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
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

    // Check user by account and password
    public boolean checkUser() {
        try {
            String strSelect = "select * from users"
                    + " where username =?"
                    + " and password =?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            pstm.setString(2, password);
            rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }

    // Check duplicate user by username
    public boolean checkDuplicateAccount() {
        try {
            String strSelect = "select * from users"
                    + " where username ='" + account + "'";
            stm = cnn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stm.executeQuery(strSelect);
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkUser:" + e.getMessage());
        }
        return false;
    }

    // Signup account
    public void signUp() {
        try {
            String strAdd = "insert into users \n"
                    + "(username, password,roleId)\n"
                    + "values(?,?,0)";
            pstm = cnn.prepareStatement(strAdd);
            pstm.setString(1, account);
            pstm.setString(2, password);

            pstm.execute();

        } catch (Exception e) {
            System.out.println("signUp:" + e.getMessage());
        }
    }

    public void getInforUser(String account) {
        try {
            String strSelect = "select * from users where username = ?";
            pstm = cnn.prepareStatement(strSelect);
            pstm.setString(1, account);
            rs = pstm.executeQuery();
            while (rs.next()) {
                userId = String.valueOf(rs.getInt(1));
                this.account = rs.getString(2);
                password = rs.getString(3);
                roleId = String.valueOf(rs.getInt(4));
                SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                joinDate = date.format(rs.getDate(5));
            }

        } catch (Exception e) {
            System.out.println("getInforUser:" + e.getMessage());
        }
    }

    public void deleteAccount(String id) {
        try {
            String strDelete = "delete from users\n"
                    + "where user_id =?";
            pstm = cnn.prepareStatement(strDelete);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteAccount:" + e.getMessage());
        }

    }

    public void changePassword(String id, String newPass) {
        try {
            String strUpdate = "update users\n"
                    + "set password = ?\n"
                    + "where user_id = ?";
            pstm = cnn.prepareStatement(strUpdate);
            pstm.setString(1, newPass);
            pstm.setString(2, id);
            pstm.execute();
        } catch (Exception e) {
        }

    }

}
