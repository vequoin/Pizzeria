package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        List<pizzaordermodel> list = new ArrayList<>();
        for(int i = 0; i < storeOrder.getOrders().get(0).getPizzas().size(); i++){
            Pizza pizza = storeOrder.getOrders().get(0).getPizzas().get(i);
            pizzaordermodel temp = new pizzaordermodel(String.valueOf(i),pizza.getToppings().toString(),pizza.isExtraCheese(),pizza.isExtraSauce(),pizza.getSize().toString(),pizza.price());
            list.add(temp);
        }
        currentorderadapter = new storeorderadapter(list,this);
        listView.setAdapter(currentorderadapter);
        Button removebutton = findViewById(R.id.store_order_cancela);
        removebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(storeorderadapter.getDecision() < 0){
                    Toast.makeText(getBaseContext(),"Select item to delete",Toast.LENGTH_LONG).show();
                    return;
                }
                for(int i = 0; i < list.size(); i++){
                    if(OrderBreaker.getStoreOrder().getOrders().get(i).getOrderId() == storeorderadapter.getDecision()){
                        currentorderadapter.remove(i);
                        storeorderadapter.setDecision(-1);
                        break;
                    }
                }
            }
        });
    }
}