package com.example.pizzeria;

public class SpecialityPizzaModel {

    private String pizzaName;

    private Double price;
    private String pizzaTopping; // Simplified toppings as a String
    private int imgResourceId; // Resource ID for drawable

    public SpecialityPizzaModel(String pizzaName, String pizzaTopping, int imgResourceId, Double price) {
        this.pizzaName = pizzaName;
        this.pizzaTopping = pizzaTopping;
        this.imgResourceId = imgResourceId;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public String getPizzaTopping() {
        return pizzaTopping;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
