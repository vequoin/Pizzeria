package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pizzeria.model.Order;
import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class currentordersactivity extends AppCompatActivity {
    private static int index;
    private double total = 0;
    private RecyclerView recyclerView;
    private currentorderadapter COadapter;
    private List<pizzaordermodel> pizzaordermodelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_orders);
        TextView price = findViewById(R.id.pizza_total);
        recyclerView = findViewById(R.id.recyclerView);
        Button cancel_order = findViewById(R.id.cancel_order);
        cancel_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedIndex = currentorderadapter.getselectednum();
                for (int i = 0; i < pizzaordermodelList.size(); i++) {
                    pizzaordermodel p = pizzaordermodelList.get(i);
                    if (Integer.parseInt(p.getPizzaname().substring(p.getPizzaname().length() - 1)) == selectedIndex) {
                        pizzaordermodelList.remove(i);
                        OrderBreaker.getOrder().getPizzas().remove(i);
                        COadapter.notifyItemRemoved(i);

                        total = 0;
                        for (int j = 0; j < OrderBreaker.getOrder().getPizzas().size(); j++) {
                            total += OrderBreaker.getOrder().getPizzas().get(j).price();
                        }
                        price.setText(String.format("%s: %.2f", "Total", total));
                    }
                }
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzaordermodelList = new ArrayList<>();
        int i = 0;
        double total = 0;
        for(Pizza pizza : OrderBreaker.getOrder().getPizzas()){
            pizzaordermodelList.add(new pizzaordermodel(String.valueOf(i),pizza.getToppings().toString(),pizza.isExtraCheese(),pizza.isExtraSauce(),pizza.getSize().toString(),pizza.price()));
            total += pizza.price();
            i++;
        }
        price.setText(String.format("%s: %.2f", "Total", total));
        COadapter = new currentorderadapter(this.pizzaordermodelList,this);
        recyclerView.setAdapter(COadapter);
        Button confirmorder = findViewById(R.id.place_order);
        confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();
                for(int i = 0; i < OrderBreaker.getOrder().getPizzas().size(); i++){
                    order.addPizza(OrderBreaker.getOrder().getPizzas().get(i));
                }
                OrderBreaker.getStoreOrder().addOrder(order);
            }
        });
    }
}