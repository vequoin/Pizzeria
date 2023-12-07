package com.example.pizzeria.model;

public class Margherita extends Pizza {

    public Margherita() {
        super();
        addTopping(Topping.HAM);
        addTopping(Topping.CHEESE);
        setSauce(Sauce.TOMATO);
    }

    @Override
    public double price() {
        double basePrice = 10.99;

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