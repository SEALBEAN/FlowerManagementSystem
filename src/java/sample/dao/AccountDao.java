/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import sample.dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.utils.DBUtils;

/**
 *
 * @author SEAL
 */
public class AccountDao {

    //ham nay de lay all account trong DB
    public static ArrayList<Account> getAccounts() throws Exception {
        ArrayList<Account> list = new ArrayList();

        //step 1: make connection
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            //step 2: viet sql & excute
            String sql = "select [accID],[email],[password],[fullname],[phone],[status],[role] FROM [dbo].[Accounts]";
            Statement st = cn.createStatement();
            ResultSet table = st.executeQuery(sql);

            //step 3: xu li ket qua cua step 2
            if (table != null) {
                while (table.next()) {
                    int accid = table.getInt("accID");
                    String email = table.getString("email");
                    String password = table.getString("password");
                    String fullname = table.getString("fullname");
                    String phone = table.getString("phone");
                    int status = table.getInt("status");
                    int role = table.getInt("role");
                    Account acc = new Account(accid, fullname, email, password, phone, status, role);
                    list.add(acc);
                }
            }
            //het step 3
            //step 4
            cn.close();
        }
        return list;
    }

    public static ArrayList<Account> getAccounts(String email) throws Exception {
        ArrayList<Account> list = new ArrayList();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [accID],[email],[password],[fullname],[phone],[status],[role] \n"
                    + "FROM Accounts WHERE email LIKE ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "%" + email + "%");
            ResultSet table = pst.executeQuery();
            if (table != null) {
                while (table.next()) {
                int accid = table.getInt("accID");
                email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                Account acc = new Account(accid, fullname, email, password, phone, status, role);
                list.add(acc);
                }
            }
            cn.close();
        }
        return list;
    }

    //ham nay de lay 1 account dua vao account ID
    //input: accid
    //ouput: object account
    public static Account getAccount(int accid) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [accID],[email],[password],[fullname],[phone],[status],[role] \n"
                    + "FROM Accounts WHERE accID = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, accid);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, fullname, email, password, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    //ham nay de return account khi biet email, password
    public static Account getAccount(String email, String password) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [accID],[email],[password],[fullname],[phone],[status],[role] \n"
                    + "FROM Accounts WHERE email = ? AND password = ? COLLATE SQL_Latin1_General_CP1_CS_AS";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accid");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, fullname, email, password, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static Account getAccount(String token) throws Exception {
        Account acc = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [accID],[email],[password],[fullname],[phone],[status],[role] \n"
                    + "FROM Accounts WHERE token = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            ResultSet table = pst.executeQuery();
            if (table != null && table.next()) {
                int accid = table.getInt("accID");
                String email = table.getString("email");
                String password = table.getString("password");
                String fullname = table.getString("fullname");
                String phone = table.getString("phone");
                int status = table.getInt("status");
                int role = table.getInt("role");
                acc = new Account(accid, fullname, email, password, phone, status, role);
            }
            cn.close();
        }
        return acc;
    }

    public static boolean updateAccountStatus(String email, int status) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE Accounts\n"
                    + "SET [status] = ?\n"
                    + "WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, status);
            pst.setString(2, email);
            result = pst.executeUpdate();
            cn.close();
        }
        if (result == 1) {
            return true;
        }
        if (result == 0) {
            System.out.println("Not found!");
            return false;
        } else {
            System.out.println("There an error in update process");
            return false;
        }
    }

    public static boolean updateAccount(String email, String newFullName, String newPhone) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE Accounts\n"
                    + "SET fullname = ?, phone = ?\n"
                    + "WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newFullName);
            pst.setString(2, newPhone);
            pst.setString(3, email);
            result = pst.executeUpdate();
            cn.close();
        }
        if (result == 1) {
            return true;
        }
        if (result == 0) {
            System.out.println("Not found!");
            return false;
        } else {
            System.out.println("There an error in update process");
            return false;
        }
    }

    public static boolean insertAccount(String newEmail, String newPassword, String newFullname, String newPhone, int newStatus, int newRole) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT [dbo].[Accounts] ([email],[password], [fullname], [phone], [status], [role])\n"
                    + "                   	VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, newEmail);
            pst.setString(2, newPassword);
            pst.setString(3, newFullname);
            pst.setString(4, newPhone);
            pst.setInt(5, newStatus);
            pst.setInt(6, newRole);
            result = pst.executeUpdate();
            cn.close();
        }
        if (result == 1) {
            return true;
        }
        if (result == 0) {
            System.out.println("Not found!");
            return false;
        } else {
            System.out.println("There an error in update process");
            return false;
        }
    }

    public static boolean updateToken(String token, String email) throws Exception {
        int result = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE Accounts\n"
                    + "SET token = ?\n"
                    + "WHERE email = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, token);
            pst.setString(2, email);
            result = pst.executeUpdate();
            cn.close();
        }
        if (result == 1) {
            return true;
        }
        if (result == 0) {
            System.out.println("Not found!");
            return false;
        } else {
            System.out.println("There an error in update process");
            return false;
        }
    }

}
