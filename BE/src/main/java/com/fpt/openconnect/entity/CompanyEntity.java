package com.fpt.openconnect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String nameCompany;

    @Column(name = "description")
    private String descriptionCompany;

    @Column(name = "address")
    private String addressCompany;

    @Column(name = "image")
    private String imageCompany;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "companyWorkshop")
    @JsonManagedReference
    private List<WorkshopEntity> workshops;

    @OneToMany(mappedBy = "companyProduct")
    @JsonManagedReference
    private List<ProductEntity> products;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDescriptionCompany() {
        return descriptionCompany;
    }

    public void setDescriptionCompany(String descriptionCompany) {
        this.descriptionCompany = descriptionCompany;
    }

    public String getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(String addressCompany) {
        this.addressCompany = addressCompany;
    }

    public String getImageCompany() {
        return imageCompany;
    }

    public void setImageCompany(String imageCompany) {
        this.imageCompany = imageCompany;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<WorkshopEntity> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopEntity> workshops) {
        this.workshops = workshops;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }


}
