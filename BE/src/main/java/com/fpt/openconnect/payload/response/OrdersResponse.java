package com.fpt.openconnect.payload.response;

import java.util.ArrayList;
import java.util.List;

public class OrdersResponse {
    private int idUser;
    private int idOrder;
    private double priceTotal;
    private String nameStatus;
    private String createDate;
    private List<OrderDetailTicketResponse> orderDetailsTicket;
    private List<OrderDetailProductResponse> orderDetailsProduct;

    // Getters and setters

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<OrderDetailTicketResponse> getOrderDetailsTicket() {
        return orderDetailsTicket;
    }

    public void setOrderDetailsTicket(List<OrderDetailTicketResponse> orderDetailsTicket) {
        this.orderDetailsTicket = orderDetailsTicket;
    }

    public List<OrderDetailProductResponse> getOrderDetailsProduct() {
        return orderDetailsProduct;
    }

    public void setOrderDetailsProduct(List<OrderDetailProductResponse> orderDetailsProduct) {
        this.orderDetailsProduct = orderDetailsProduct;
    }
}

