package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.model.StoreOrder;

import java.util.ArrayList;
import java.util.List;

public class storeorderactivity extends AppCompatActivity {
    private StoreOrder storeOrder;
    private storeorderadapter currentorderadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storeorderactivity);
        ListView listView = findViewById(R.id.store_order_list);
        storeOrder = OrderBreaker.getStoreOrder();
        if(storeOrder.getOrders().isEmpty()){
            Toast.makeText(this,"You have no orders",Toast.LENGTH_LONG).show();
            finish();
        }
        List<pizzaordermodel> list = new ArrayList<>();
        for(int i = 0; i < storeOrder.getOrders().get(0).getPizzas().size(); i++){
            Pizza pizza = storeOrder.getOrders().get(0).getPizzas().get(i);
            pizzaordermodel temp = new pizzaordermodel(String.valueOf(i),pizza.getToppings().toString(),pizza.isExtraCheese(),pizza.isExtraSauce(),pizza.getSize().toString(),pizza.price());
            list.add(temp);
        }
        currentorderadapter = new storeorderadapter(list,this);
        listView.setAdapter(currentorderadapter);
        //currentorderadapter = new currentorderadapter(,this);
    }
}