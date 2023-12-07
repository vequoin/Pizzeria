package com.example.pizzeria.model;

public class BuildYourOwn extends Pizza{

    @Override
    public double price() {
        double basePrice = 8.99;
        if(isExtraCheese()){
            basePrice += 1;
        }
        if(isExtraSauce()){
            basePrice += 1;
        }
        switch(size){
            case SMALL: basePrice += 0.0;
            case MEDIUM: basePrice += 2.0;
            case LARGE: basePrice += 4.0;
            default: basePrice += 0.0;
        }
        double extraToppingCost = 0.0;
        if(this.getToppings().size() > 3){
            int numToppings = this.getToppings().size();
            extraToppingCost += (numToppings-3)*1.49;
        }
        basePrice += extraToppingCost;
        return basePrice;
    }
}
