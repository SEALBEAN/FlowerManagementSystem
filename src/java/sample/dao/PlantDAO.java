/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import sample.dto.Plant;
import sample.utils.DBUtils;

/**
 *
 * @author xuhet
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception {
        ArrayList<Plant> list = new ArrayList<Plant>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null && searchby != null) {
                String sql = "SELECT [PID],[PName], [price], [imgPath], [description], [status], pl.CateID, cate.CateName \n"
                        + "FROM Plants pl INNER JOIN Categories cate\n"
                        + "ON pl.CateID = cate.CateID ";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql = sql + "WHERE PName LIKE ?";
                } else {
                    sql = sql + "WHERE cate.CateName LIKE ?";
                }
                PreparedStatement pst = cn.prepareStatement(sql);
                System.out.println(sql);
                pst.setString(1, "%" + keyword + "%");
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        int id = rs.getInt("PID");
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        Plant plant = new Plant(id, name, price, imgpath, description, status, cateid, catename);
                        list.add(plant);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return list;
    }
    
    public static Plant getPlant(int pid) throws Exception {
        Plant p = new Plant();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if (cn != null) {
                String sql = "SELECT [PID],[PName], [price], [imgPath], [description], [status], pl.CateID, cate.CateName \n"
                        + "FROM Plants pl INNER JOIN Categories cate\n"
                        + "ON pl.CateID = cate.CateID ";
                sql = sql + "WHERE PID LIKE ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                System.out.println(sql);
                pst.setInt(1, pid);
                ResultSet rs = pst.executeQuery();
                if (rs != null) {
                    while (rs.next()) {
                        String name = rs.getString("PName");
                        int price = rs.getInt("price");
                        String imgpath = rs.getString("imgPath");
                        String description = rs.getString("description");
                        int status = rs.getInt("status");
                        int cateid = rs.getInt("CateID");
                        String catename = rs.getString("CateName");
                        p = new Plant(pid, name, price, imgpath, description, status, cateid, catename);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cn.close();
        return p;
    }
}
