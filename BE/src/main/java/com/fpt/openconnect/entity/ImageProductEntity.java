package com.fpt.openconnect.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "image_product")
public class ImageProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "image")
    private String imageProduct;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductEntity productEntity;

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

    public String getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }


}
