package com.fpt.openconnect.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "cart")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity_product")
    private int quantityProduct;

    @Column(name = "quantity_ticket")
    private int quantityTicket;

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "id_ticket")
    private TicketEntity ticketEntity;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "price")
    private Double priceCart;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Double getPriceCart() {
        return priceCart;
    }

    public void setPriceCart(Double priceCart) {
        this.priceCart = priceCart;
    }
}


