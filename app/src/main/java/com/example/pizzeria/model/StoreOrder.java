package com.example.pizzeria.model;



import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StoreOrder {
    private static int nextOrderNumber = 1;
    private List<Order> orders;

    public StoreOrder() {
        orders = new ArrayList<>();
    }

    // Method to add an order
    public void addOrder(Order order) {
        orders.add(order);
    }

    // Method to get all orders
    public List<Order> getOrders() {
        return orders;
    }


    public String contoString(int pos) {
        Order order = orders.get(pos);

        StringBuilder toreturn = new StringBuilder();
        toreturn.append("Order Number: ").append(order.getOrderId()).append("\n")
                .append("Order Details: \n");

        for (Pizza pizza : order.getPizzas()) {
            toreturn.append(pizza.toString()).append("\n");
        }

        toreturn.append("Total Amount: ").append(order.getTotalAmount()).append("\n")
                .append("Sales Tax: ").append(order.calculateTax()).append("\n")
                .append("Order Total: ").append(order.getOrderTotal()).append("\n\n");

        return toreturn.toString();
    }


    // Method to generate the next order number
    public static int getNextOrderNumber() {
        return nextOrderNumber++;
    }
}
