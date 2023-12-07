package com.example.pizzeria.model;

public class Supreme extends Pizza{

    public Supreme(){
        super();
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.GREEN_PEPPERS);
        addTopping(Topping.ONIONS);
        addTopping(Topping.BLACK_OLIVES);
        addTopping(Topping.MUSHROOMS);
        addTopping(Topping.HAM);
        setSauce(Sauce.TOMATO);
    }

    @Override
    public double price() {
        double price = 15.99;
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
