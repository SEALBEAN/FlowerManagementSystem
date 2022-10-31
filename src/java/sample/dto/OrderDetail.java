/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.dto;

/**
 *
 * @author xuhet
 */
public class OrderDetail {
    private int detailID;
    private int orderID;
    private int PID;
    private int quantity;
    private int price;
    private String PlantName;
    private String ImgPath;

    public OrderDetail() {
    }

    public OrderDetail(int detailID, int orderID, int PID, int quantity, int price, String PlantName, String ImgPath) {
        this.detailID = detailID;
        this.orderID = orderID;
        this.PID = PID;
        this.quantity = quantity;
        this.price = price;
        this.PlantName = PlantName;
        this.ImgPath = ImgPath;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String PlantName) {
        this.PlantName = PlantName;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String ImgPath) {
        this.ImgPath = ImgPath;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "detailID=" + detailID + ", orderID=" + orderID + ", PID=" + PID + ", quantity=" + quantity + ", price=" + price + ", PlantName=" + PlantName + ", ImgPath=" + ImgPath + '}';
    }

}
