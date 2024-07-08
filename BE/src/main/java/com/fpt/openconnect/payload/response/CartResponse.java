package com.fpt.openconnect.payload.response;

public class CartResponse {

    private int idUser;
    private int idProduct;
    private int idTicket;
    private int idCart;
    private String nameProduct;
    private String nameTicket;
    private String nameWorkshop;
    private String imageWorkshop;
    private double priceProduct;
    private double priceTicket;
    private Integer quantityProduct; // Sử dụng Integer thay vì int
    private Integer quantityTicket; // Sử dụng Integer thay vì int
    private double totalPrice;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameTicket() {
        return nameTicket;
    }

    public void setNameTicket(String nameTicket) {
        this.nameTicket = nameTicket;
    }

    public String getNameWorkshop() {
        return nameWorkshop;
    }

    public void setNameWorkshop(String nameWorkshop) {
        this.nameWorkshop = nameWorkshop;
    }

    public String getImageWorkshop() {
        return imageWorkshop;
    }

    public void setImageWorkshop(String imageWorkshop) {
        this.imageWorkshop = imageWorkshop;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public Integer getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(Integer quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public Integer getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(Integer quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
