package com.shreeanna.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String category; // Millet, Pulse, Value-added, etc.
    private double pricePerKg;
    private int quantityKg;
    private int farmerId;
    private String state;
    private String certification; // Organic, FPO, etc.
    private String image;   // image file name


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public double getPricePerKg() {
        return pricePerKg;
    }
    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }
    public int getQuantityKg() {
        return quantityKg;
    }
    public void setQuantityKg(int quantityKg) {
        this.quantityKg = quantityKg;
    }
    public int getFarmerId() {
        return farmerId;
    }
    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCertification() {
        return certification;
    }
    public void setCertification(String certification) {
        this.certification = certification;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

}
