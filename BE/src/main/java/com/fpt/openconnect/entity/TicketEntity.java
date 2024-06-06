package com.fpt.openconnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String nameTicket;

    @Column(name = "price")
    private double priceTicket;

    @Column(name = "description")
    private String descriptionTicket;

    @Column(name = "quantity")
    private int quantityTicket;

    @Column(name = "image")
    private String imageTicket;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_workshop")
    @JsonIgnoreProperties({"tickets"})
    private WorkshopEntity workshop;

    @OneToMany(mappedBy = "ticketEntity")
    private List<ProductTicketOrderEntity> productTicketOrderEntities;

    @OneToMany(mappedBy = "ticketEntity")
    private List<CartEntity> cartEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTicket() {
        return nameTicket;
    }

    public void setNameTicket(String nameTicket) {
        this.nameTicket = nameTicket;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public String getDescriptionTicket() {
        return descriptionTicket;
    }

    public void setDescriptionTicket(String descriptionTicket) {
        this.descriptionTicket = descriptionTicket;
    }

    public int getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(int quantityTicket) {
        this.quantityTicket = quantityTicket;
    }

    public String getImageTicket() {
        return imageTicket;
    }

    public void setImageTicket(String imageTicket) {
        this.imageTicket = imageTicket;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public WorkshopEntity getWorkshop() {
        return workshop;
    }

    public void setWorkshop(WorkshopEntity workshop) {
        this.workshop = workshop;
    }

    public List<ProductTicketOrderEntity> getProductTicketOrderEntities() {
        return productTicketOrderEntities;
    }

    public void setProductTicketOrderEntities(List<ProductTicketOrderEntity> productTicketOrderEntities) {
        this.productTicketOrderEntities = productTicketOrderEntities;
    }

    public List<CartEntity> getCartEntities() {
        return cartEntities;
    }

    public void setCartEntities(List<CartEntity> cartEntities) {
        this.cartEntities = cartEntities;
    }
}
