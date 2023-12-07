package com.example.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderId = 1; // Static counter for unique order IDs
    private final int orderId;
    private final List<Pizza> pizzas;
    private double totalAmount; // Total amount before tax

    public Order() {
        this.orderId = nextOrderId++;
        this.pizzas = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        totalAmount += pizza.price();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double calculateTax() {
        final double TAX_RATE = 0.06625;
        return totalAmount * TAX_RATE;
    }

    public double getOrderTotal() {
        return totalAmount + calculateTax();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Pizzas in Order: \n");

        for (Pizza pizza : pizzas) {
            sb.append(pizza).append("\n"); // Assuming Pizza class has a meaningful toString implementation
        }

        sb.append("Total Amount: ").append(String.format("%.2f", totalAmount)).append("\n");
        sb.append("Sales Tax: ").append(String.format("%.2f", calculateTax())).append("\n");
        sb.append("Order Total (with Tax): ").append(String.format("%.2f", getOrderTotal())).append("\n");

        return sb.toString();
    }



}
