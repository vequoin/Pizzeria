package com.example.pizzeria.model;


public class Meatzza extends Pizza{

    public Meatzza(){
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.HAM);
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.BEEF);
        setSauce(Sauce.TOMATO);
    }
    @Override
    public double price() {
        double price = 16.99;
        switch (size){
            case SMALL: price += 0.0;
            case MEDIUM: price += 2.00;
            case LARGE: price += 4.00;
        }
        if(isExtraCheese()){
            price += 1;
        }
        if(isExtraSauce()){
            price += 1;
        }
        return price;
    }
}
