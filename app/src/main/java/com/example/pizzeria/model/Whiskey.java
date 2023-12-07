package com.example.pizzeria.model;

public class Whiskey extends Pizza {
    public Whiskey() {
        super();
        addTopping(Topping.HAM);
        addTopping(Topping.BLUECHEESE);
        setSauce(Sauce.TOMATO);
        addTopping(Topping.BLUERUM);
        addTopping(Topping.SAPPHIREGOLD);
    }

    @Override
    public double price() {
        double basePrice = 25.99;

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
