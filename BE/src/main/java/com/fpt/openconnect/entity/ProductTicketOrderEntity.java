package com.fpt.openconnect.entity;

import com.fpt.openconnect.entity.keys.ProductTicketOrderKeys;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "product_ticket_order")
public class ProductTicketOrderEntity {

    @EmbeddedId
    private ProductTicketOrderKeys keys;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @Column(name = "quantity_ticket")
    private double quantityTicket;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "id_ticket", insertable = false, updatable = false) // thêm vào đê tránh đi vòng lặp vô tận
    private TicketEntity ticketEntity;

    @ManyToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false) // thêm vào đê tránh đi vòng lặp vô tận
    private OrdersEntity ordersEntity;

    @Column(name = "create_date")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ProductTicketOrderKeys getKeys() {
        return keys;
    }

    public void setKeys(ProductTicketOrderKeys keys) {
        this.keys = keys;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public double getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(double quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public TicketEntity getTicketEntity() {
        return ticketEntity;
    }

    public void setTicketEntity(TicketEntity ticketEntity) {
        this.ticketEntity = ticketEntity;
    }

    public OrdersEntity getOrdersEntity() {
        return ordersEntity;
    }

    public void setOrdersEntity(OrdersEntity ordersEntity) {
        this.ordersEntity = ordersEntity;
    }


}
