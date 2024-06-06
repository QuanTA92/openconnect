package com.fpt.openconnect.payload.response;

public class TicketResponse {

    private String nameTicket;

    private double priceTicket;

    private String descriptionTicket;

    private int quantityTicket;

    private String imageTicket;

    private String nameWorkshop;

    private int idTicket;

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

    public String getNameWorkshop() {
        return nameWorkshop;
    }

    public void setNameWorkshop(String nameWorkshop) {
        this.nameWorkshop = nameWorkshop;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
}
