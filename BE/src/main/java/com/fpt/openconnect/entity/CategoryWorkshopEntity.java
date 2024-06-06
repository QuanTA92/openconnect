package com.fpt.openconnect.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "category_workshop")
public class CategoryWorkshopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String nameCategoryWorkshop;

    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(mappedBy = "categoryWorkshop")
    @JsonManagedReference
    private List<WorkshopEntity> workshopEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCategoryWorkshop() {
        return nameCategoryWorkshop;
    }

    public void setNameCategoryWorkshop(String nameCategoryWorkshop) {
        this.nameCategoryWorkshop = nameCategoryWorkshop;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public List<WorkshopEntity> getWorkshopEntities() {
        return workshopEntities;
    }

    public void setWorkshopEntities(List<WorkshopEntity> workshopEntities) {
        this.workshopEntities = workshopEntities;
    }


}
