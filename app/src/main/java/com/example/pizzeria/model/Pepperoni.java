package com.example.pizzeria.model;

public class Pepperoni extends Pizza{

    public Pepperoni(){
        super();
        addTopping(Topping.PEPPERONI);
        setSauce(Sauce.TOMATO);
    }


    @Override
    public double price() {

        double price = 10.99;
        switch (size){
            case SMALL: price += 0.0; break;
            case MEDIUM: price += 2.00; break;
            case LARGE: price += 4.00; break;
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
