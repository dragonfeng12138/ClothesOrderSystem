package com.demo.model;

/**
 * @author dragon
 * @create 2021-06-26 14:20
 */
public class CartItem {
    private Clothes clothes;
    private int quantity;
    private double price;

    public Clothes getClothes() {
        return clothes;
    }

    public void setClothes(Clothes clothes) {
        this.clothes = clothes;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
