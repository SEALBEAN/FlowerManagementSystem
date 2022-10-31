/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import sample.dto.Order;
import sample.dto.OrderDetail;

import sample.utils.DBUtils;

/**
 *
 * @author xuhet
 */
public class OrderDAO {

    public static ArrayList<Order> getOrders(String email) throws Exception {
        ArrayList<Order> list = new ArrayList<Order>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && email != null) {
                String sql = "SELECT [OrderID], [OrdDate], [shipdate], ord.status, ord.AccID\n"
                        + "FROM Orders ord FULL JOIN Accounts acc\n"
                        + "ON ord.AccID = acc.accID\n"
                        + "WHERE acc.email LIKE ?";

//                Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery(sql);
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + email + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        Date orderDate = rs.getDate("OrdDate");
                        Date shipDate = rs.getDate("shipdate");
                        int statusID = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, statusID, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return list;
    }

    public static ArrayList<Order> getOrders() throws Exception {
        ArrayList<Order> list = new ArrayList<Order>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [OrderID], [OrdDate], [shipdate], ord.status, ord.AccID\n"
                        + "                        FROM Orders ord FULL JOIN Accounts acc\n"
                        + "                        ON ord.AccID = acc.accID\n"
                        + "						WHERE OrderID IS NOT NULL";
//                Statement st = cn.createStatement();
//                ResultSet rs = st.executeQuery(sql);
                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        Date orderDate = rs.getDate("OrdDate");
                        Date shipDate = rs.getDate("shipdate");
                        int statusID = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, statusID, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return list;
    }

    public static ArrayList<Order> getOrders(String email, Date from, Date to) throws Exception {
        ArrayList<Order> list = new ArrayList<Order>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && email != null) {
                String sql = "SELECT [OrderID], [OrdDate], [shipdate], ord.status, ord.AccID\n"
                        + "FROM Orders ord FULL JOIN Accounts acc\n"
                        + "ON ord.AccID = acc.accID\n"
                        + "WHERE acc.email LIKE ? AND OrdDate >= ? AND OrdDate <= ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + email + "%");
                pst.setDate(2, from);
                pst.setDate(3, to);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int orderID = rs.getInt("OrderID");
                        Date orderDate = rs.getDate("OrdDate");
                        Date shipDate = rs.getDate("shipdate");
                        int statusID = rs.getInt("status");
                        int accID = rs.getInt("AccID");
                        Order order = new Order(orderID, orderDate, shipDate, statusID, accID);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return list;
    }

    public static ArrayList<OrderDetail> getOrderDetail(int orderID) throws Exception {
        ArrayList<OrderDetail> list = new ArrayList<OrderDetail>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && orderID != 0) {
                String sql = "SELECT DetailId, orddet.PID, quantity, price, PName, imgPath\n"
                        + "FROM OrderDetails orddet FULL JOIN Plants pla\n"
                        + "ON orddet.PID = pla.PID\n"
                        + "WHERE orddet.OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, orderID);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int detailID = rs.getInt("DetailId");
                        int PID = rs.getInt("PID");
                        int quantity = rs.getInt("quantity");
                        int price = rs.getInt("price");
                        String plantName = rs.getString("PName");
                        String imgPath = rs.getString("imgPath");
                        OrderDetail order = new OrderDetail(detailID, PID, PID, quantity, price, plantName, imgPath);
                        list.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return list;
    }

    public static boolean updateOrderStatus(int orderID, int status) throws Exception {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && orderID != 0) {
                String sql = "UPDATE Orders\n"
                        + "SET status = ?\n"
                        + "WHERE OrderID = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, status);
                pst.setInt(2, orderID);
                int rs = pst.executeUpdate();
                if (rs > 0) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return result;
    }

    public static boolean insertOrder(String email, HashMap<String, Integer> cart) throws Exception {
        Connection cn = null;
        boolean result = false;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                int accid = 0, orderid = 0;
                cn.setAutoCommit(false); // tat autocommit bat dau transaction
//                get acction id by email
                String sql = "SELECT accID from Accounts WHERE email = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, email);
                ResultSet rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    accid = rs.getInt("accID");
                }

                //insert new order
                System.out.println("accountid: " + accid);
                Date d = new Date(System.currentTimeMillis());
                System.out.println("order date: " + d);
                sql = "INSERT Orders(OrdDate, status, AccID) VALUES (?,?,?)";
                pst = cn.prepareStatement(sql);
                pst.setDate(1, d);
                pst.setInt(2, 1);
                pst.setInt(3, accid);
                pst.executeUpdate();
                //get order id that is the largest number
                sql = "SELECT TOP 1 orderID from Orders ORDER BY orderID DESC";
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    orderid = rs.getInt("orderID");
                }
                //insert order details
                System.out.println("orderid: " + orderid);
                Set<String> pids = cart.keySet();
                for (String pid : pids) {
                    sql = "INSERT OrderDetails VALUES (?,?,?)";
                    pst = cn.prepareStatement(sql);
                    pst.setInt(1, orderid);
                    pst.setInt(2, Integer.parseInt(pid.trim()));
                    pst.setInt(3, cart.get(pid));
                    pst.executeUpdate();

                    //ket thuc transaction
                    cn.commit();
                    //mo lai auto commit
                    cn.setAutoCommit(true);
                }
                return true;
            } else {
                System.out.println("ko chen order duoc");
            }
        } catch (Exception e) {
            try {
                if (cn != null) {
                    cn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
