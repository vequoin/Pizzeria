package com.example.pizzeria.model;

public class OrderBreaker {
    private static Order order;
    private static StoreOrder storeOrder;

    private OrderBreaker(){

    }
    public static Order getOrder(){
        if(order == null){
            order = new Order();
        }
        return order;
    }

    public static Order createNewOrder(){
        if(order != null){
            order = new Order();
        }
        return order;
    }
    public static StoreOrder getStoreOrder(){
        if(storeOrder == null){
            storeOrder = new StoreOrder();
        }
        return storeOrder;
    }

    public static StoreOrder createNewStoreOrder(){
        if(storeOrder != null){
            storeOrder = new StoreOrder();
        }
        return storeOrder;
    }
}