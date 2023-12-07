package com.example.pizzeria.model;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType) {
        switch (pizzaType) {
            case "Deluxe":
                return new Deluxe();
            case "Supreme":
                return new Supreme();
            case "Meatzza":
                return new Meatzza();
            case "Pepperoni":
                return new Pepperoni();
            case "Seafood":
                return new Seafood();
            case "Hawaiian":
                return new Hawaiian();
            case "Margherita":
                return new Margherita();
            case "MeatLovers":
                return new MeatLovers();
            case "Veggie":
                return new Veggie();
            case "Whiskey":
                return new Whiskey();
            case "BuildYourOwn":
                return new BuildYourOwn();
            default:
                throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        }
    }
}
