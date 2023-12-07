package com.example.pizzeria.model;

public class Veggie extends Pizza {

    public Veggie() {
        super();
        addTopping(Topping.MUSHROOMS);
        addTopping(Topping.GREEN_PEPPERS);
        addTopping(Topping.SPINACH);
        addTopping(Topping.BLACK_OLIVES);
        setSauce(Sauce.TOMATO);
    }

    @Override
    public double price() {
        double basePrice = 13.99;
        // Additional price logic based on size and extras
        return calculatePriceWithExtras(basePrice);
    }
    private double calculatePriceWithExtras(double basePrice) {
        switch (size){
            case SMALL: basePrice += 0.0; break;
            case MEDIUM: basePrice += 2.00; break;
            case LARGE: basePrice += 4.00; break;
        }
        if(isExtraCheese()){
            basePrice += 1;
        }
        if(isExtraSauce()){
            basePrice += 1;
        }
        return basePrice;
    }
}