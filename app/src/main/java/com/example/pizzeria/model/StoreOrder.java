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


    public void exportToTextFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : orders) {
                writer.write("Order Number: " + order.getOrderId() + "\n");
                writer.write("Order Details: \n");
                for (Pizza pizza : order.getPizzas()) {
                    writer.write(pizza.toString() + "\n");
                }
                writer.write("Total Amount: " + order.getTotalAmount() + "\n");
                writer.write("Sales Tax: " + order.calculateTax() + "\n");
                writer.write("Order Total: " + order.getOrderTotal() + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate the next order number
    public static int getNextOrderNumber() {
        return nextOrderNumber++;
    }
}
