package com.example.pizzeria.model;

public enum Topping {
    PEPPERONI("Spicy Italian Pepperoni"),
    MUSHROOMS("Fresh Mushrooms"),
    ONIONS("Chopped Onions"),
    BLUECHEESE("Cheese with fungi"),
    SAUSAGE("Italian Sausage"),
    BLUERUM("Found on coral Island"),

    SAPPHIREGOLD("Classic Gold tequila"),
    BACON("Crispy Bacon"),
    CHEESE("More More Cheese"),
    SHRIMP("Crispy Shrimp"),
    BLACK_OLIVES("Black Olives"),
    GREEN_PEPPERS("Green Bell Peppers"),
    SQUID("Legendary Squid of the ocean deep"),
    CRAB_MEATS("Crab meat from Alaska"),
    BEEF("Our grass fed beef"),
    PINEAPPLE("Tropical Pineapple"),
    SPINACH("Fresh Spinach"),
    HAM("Fresh Ham");

    private final String description;



    Topping(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name() + " (" + description + ")";
    }
}
