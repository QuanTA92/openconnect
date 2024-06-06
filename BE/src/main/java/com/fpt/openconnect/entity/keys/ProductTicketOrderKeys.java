package com.fpt.openconnect.entity.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductTicketOrderKeys {
    private static final long serialVersionUID = 1L;

    @Column(name = "id_product")
    private int idProduct;

    @Column(name = "id_ticket")
    private int idTicket;

    @Column(name = "id_order")
    private int idOrder;

    public ProductTicketOrderKeys() {
        // Constructor không đối số
    }

    public ProductTicketOrderKeys(int idProduct, int idOrder,int idTicket) {
        this.idProduct = idProduct;
        this.idOrder = idOrder;
        this.idTicket = idTicket;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }


}
