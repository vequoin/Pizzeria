package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

                if (selectedIndex >= 0 && selectedIndex < pizzaordermodelList.size()) {
                    pizzaordermodelList.remove(selectedIndex);
                    COadapter.notifyItemRemoved(selectedIndex);

                    total = 0;
                    for (Pizza pizza : OrderBreaker.getOrder().getPizzas()) {
                        total += pizza.price();
                    }
                    price.setText(String.format("%s: %.2f", "Total", total));

                    // Update the remaining items' pizza names
                    int i = 0;
                    for (pizzaordermodel pizzaordermodel : pizzaordermodelList) {
                        pizzaordermodel.setPizzaname(String.valueOf(i));
                        i++;
                    }
                } else {
                    // Handle the case where the selected index is invalid
                    Toast.makeText(currentordersactivity.this, "Invalid selection", Toast.LENGTH_SHORT).show();
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
    }
}