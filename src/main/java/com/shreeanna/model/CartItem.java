package com.shreeanna.model;

public class CartItem {
    private Product product;
    private int quantityKg;

    public CartItem(Product product, int quantityKg) {
        this.product = product;
        this.quantityKg = quantityKg;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantityKg() {
        return quantityKg;
    }

    public void setQuantityKg(int quantityKg) {
        this.quantityKg = quantityKg;
    }

    public double getTotalPrice() {
        return product.getPricePerKg() * quantityKg;
    }
}
