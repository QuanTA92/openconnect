package com.fpt.openconnect.payload.response;

public class OrderDetailTicketResponse {
    private Integer idTicket;
    private String nameWorkshop;
    private Double priceTicket;
    private Integer quantityTicket;

    // Getters and setters

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public String getNameWorkshop() {
        return nameWorkshop;
    }

    public void setNameWorkshop(String nameWorkshop) {
        this.nameWorkshop = nameWorkshop;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }

    public Integer getQuantityTicket() {
        return quantityTicket;
    }

    public void setQuantityTicket(Integer quantityTicket) {
        this.quantityTicket = quantityTicket;
    }
}
