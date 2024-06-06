package com.fpt.openconnect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "workshop")
public class WorkshopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String nameWorkshop;

    @Column(name = "description")
    private String descriptionWorkshop;

    @Column(name = "time_workshop")
    private String timeWorkshop;

    @Column(name= "address")
    private String addressWorkshop;

    @Column(name = "image")
    private String imageWorkshop;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "id_company_workshop")
    @JsonIgnoreProperties({"workshops"})
    private CompanyEntity companyWorkshop;

    @ManyToOne
    @JoinColumn(name = "id_category_workshop")
    @JsonIgnoreProperties({"workshopEntities"})
    private CategoryWorkshopEntity categoryWorkshop;

    @OneToMany(mappedBy = "workshop")
    @JsonIgnoreProperties({"workshop"})
    private List<TicketEntity> tickets;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameWorkshop() {
        return nameWorkshop;
    }

    public void setNameWorkshop(String nameWorkshop) {
        this.nameWorkshop = nameWorkshop;
    }

    public String getDescriptionWorkshop() {
        return descriptionWorkshop;
    }

    public void setDescriptionWorkshop(String descriptionWorkshop) {
        this.descriptionWorkshop = descriptionWorkshop;
    }

    public String getTimeWorkshop() {
        return timeWorkshop;
    }

    public void setTimeWorkshop(String timeWorkshop) {
        this.timeWorkshop = timeWorkshop;
    }

    public String getAddressWorkshop() {
        return addressWorkshop;
    }

    public void setAddressWorkshop(String addressWorkshop) {
        this.addressWorkshop = addressWorkshop;
    }

    public String getImageWorkshop() {
        return imageWorkshop;
    }

    public void setImageWorkshop(String imageWorkshop) {
        this.imageWorkshop = imageWorkshop;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CompanyEntity getCompanyWorkshop() {
        return companyWorkshop;
    }

    public void setCompanyWorkshop(CompanyEntity companyWorkshop) {
        this.companyWorkshop = companyWorkshop;
    }

    public CategoryWorkshopEntity getCategoryWorkshop() {
        return categoryWorkshop;
    }

    public void setCategoryWorkshop(CategoryWorkshopEntity categoryWorkshop) {
        this.categoryWorkshop = categoryWorkshop;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }


}
