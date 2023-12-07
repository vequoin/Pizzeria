package com.example.pizzeria.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class Pizza {
    protected ArrayList<Topping> toppings; // Topping is an enum class
    protected Size size; // Size is an enum class
    protected Sauce sauce; // Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;


    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    public abstract double price(); // Polymorphism

    // Getters and Setters
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public boolean isExtraSauce() {
        return extraSauce;
    }

    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Pizza Size: ").append(size).append("\n");
        builder.append("Sauce: ").append(sauce).append("\n");

        if (!toppings.isEmpty()) {
            builder.append("Toppings: ");
            for (Topping topping : toppings) {
                builder.append(topping).append(", ");
            }
            // Remove the last comma and space
            builder.setLength(builder.length() - 2);
            builder.append("\n");
        } else {
            builder.append("Toppings: None\n");
        }

        if (extraSauce) {
            builder.append("Extra Sauce: Yes\n");
        } else {
            builder.append("Extra Sauce: No\n");
        }

        if (extraCheese) {
            builder.append("Extra Cheese: Yes\n");
        } else {
            builder.append("Extra Cheese: No\n");
        }

        builder.append("Price: $").append(String.format("%.2f", price()));

        return builder.toString();
    }

}
