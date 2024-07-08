package com.fpt.openconnect.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ProductTicketOrderKeys {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_product")
    private Integer idProduct;

    @Column(name = "id_ticket")
    private Integer idTicket;

    @Column(name = "id_order")
    private int idOrder;

    public ProductTicketOrderKeys() {
        // Constructor không đối số
    }

    public ProductTicketOrderKeys(Integer idProduct, int idOrder, Integer idTicket) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.idTicket = idTicket;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }



}
