package com.fpt.openconnect.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "status")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status")
    private List<OrdersEntity> ordersEntities;

    @Column(name = "create_date")
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrdersEntity> getOrdersEntities() {
        return ordersEntities;
    }

    public void setOrdersEntities(List<OrdersEntity> ordersEntities) {
        this.ordersEntities = ordersEntities;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
