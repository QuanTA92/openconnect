package com.fpt.openconnect.entity;

import com.fpt.openconnect.entity.keys.ProductTicketOrderKeys;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "product_ticket_order")
public class ProductTicketOrderEntity {

    @EmbeddedId
    private ProductTicketOrderKeys keys;

    @Column(name = "quantity_product")
    private Integer quantityProduct;

    @Column(name = "quantity_ticket")
    private Integer quantityTicket;

    @Column(name = "price")
    private double price;

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

    public ProductTicketOrderKeys getKeys() {
        return keys;
    }

    public void setKeys(ProductTicketOrderKeys keys) {
        this.keys = keys;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
