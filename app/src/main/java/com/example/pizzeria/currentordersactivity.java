package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.Pizza;

import java.util.ArrayList;
import java.util.List;

public class currentordersactivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private currentorderadapter COadapter;
    private List<pizzaordermodel> pizzaordermodelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_orders);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        pizzaordermodelList = new ArrayList<>();
        int i = 0;
        for(Pizza pizza : OrderBreaker.getOrder().getPizzas()){
            pizzaordermodelList.add(new pizzaordermodel(String.valueOf(i),pizza.getToppings().toString(),pizza.isExtraCheese(),pizza.isExtraSauce(),pizza.getSize().toString(),pizza.price()));
        }
        COadapter = new currentorderadapter(this.pizzaordermodelList,this);
        recyclerView.setAdapter(COadapter);
    }
}