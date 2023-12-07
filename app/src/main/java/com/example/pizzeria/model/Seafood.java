package com.example.pizzeria.model;

import java.util.ArrayList;

public class Seafood extends Pizza{

    public Seafood(){
        super();
        addTopping(Topping.SHRIMP);
        addTopping(Topping.CRAB_MEATS);
        addTopping(Topping.SQUID);
        setSauce(Sauce.ALFREDO);
    }

    @Override
    public void setSauce(Sauce sauce) {
        super.setSauce(sauce);
    }

    @Override
    public double price() {
        double price = 17.99;
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
