package com.fpt.openconnect.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @Column(name = "quantity_ticket")
    private int quantityTicket;

    @Column(name = "price")
    private double price;

    @OneToMany(mappedBy = "ordersEntity")
    private List<ProductTicketOrderEntity> productTicketOrderEntities;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusEntity status;

    @Column(name = "create_date")
    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<ProductTicketOrderEntity> getProductTicketOrderEntities() {
        return productTicketOrderEntities;
    }

    public void setProductTicketOrderEntities(List<ProductTicketOrderEntity> productTicketOrderEntities) {
        this.productTicketOrderEntities = productTicketOrderEntities;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

}
