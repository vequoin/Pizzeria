package com.example.pizzeria;

import java.util.Locale;

public class pizzaordermodel {
    private String pizzaname;
    private String toppings;
    private boolean extra_cheese;
    private boolean extra_Sauce;
    private String size;
    private double price;

    public pizzaordermodel(String pizzaname, String toppings, boolean extra_cheese, boolean extra_Sauce, String size, double price) {
        this.pizzaname = pizzaname;
        this.toppings = toppings;
        this.extra_cheese = extra_cheese;
        this.extra_Sauce = extra_Sauce;
        this.size = size;
        this.price = price;
    }

    public String getPizzaname() {
        return pizzaname;
    }

    public String getToppings() {
        return toppings;
    }

    public boolean getExtra_cheese() {
        return extra_cheese;
    }

    public boolean getExtra_Sauce() {
        return extra_Sauce;
    }

    public String getSize() {
        return size;
    }

    public String getPrice() {
        return String.format(Locale.getDefault(), "$%.2f", price);
    }

    public void setPizzaname(String pizzaname) {
        this.pizzaname = pizzaname;
    }
}
