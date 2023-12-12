package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.model.OrderBreaker;

public class MainActivity extends AppCompatActivity {

    private ImageView imageSpecialityPizza;
    private ImageView imageCustomPizza;
    private ImageView imageCurrentOrders;
    private ImageView imageStoreOrders;
    private Button buttonSpecialityPizza;
    private Button buttonCustomPizza;
    private Button buttonCurrentOrders;
    private Button buttonStoreOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSpecialityPizza = findViewById(R.id.image_speciality_pizza);
        imageCustomPizza = findViewById(R.id.image_custom_pizza);
        imageCurrentOrders = findViewById(R.id.image_current_orders);
        imageStoreOrders = findViewById(R.id.image_store_orders);
        buttonSpecialityPizza = findViewById(R.id.open_speciality_pizza);
        buttonCustomPizza = findViewById(R.id.open_custom_pizza);
        buttonCurrentOrders = findViewById(R.id.open_current_orders);
        buttonStoreOrders = findViewById(R.id.open_store_orders);

        // Set up click listeners for ImageViews and Buttons
        setupClickListeners();
    }

    private void setupClickListeners() {
        View.OnClickListener specialityPizzaListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSpecialityPizzaActivity();
            }
        };

        View.OnClickListener customPizzaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuildyourOwnActivity();
            }
        };
        View.OnClickListener CurrentOrderListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenPizzaOrder();
            }
        };
        View.OnClickListener StoreOrderListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenStoreOrder();
            }
        };


        imageSpecialityPizza.setOnClickListener(specialityPizzaListener);
        buttonSpecialityPizza.setOnClickListener(specialityPizzaListener);

        imageCustomPizza.setOnClickListener(customPizzaListener);
        buttonCustomPizza.setOnClickListener(customPizzaListener);
        imageCurrentOrders.setOnClickListener(CurrentOrderListener);
        buttonCurrentOrders.setOnClickListener(CurrentOrderListener);
        buttonStoreOrders.setOnClickListener(StoreOrderListener);
        imageStoreOrders.setOnClickListener(StoreOrderListener);
        // Repeat the pattern for the other sections
        // ...
    }


    private void openBuildyourOwnActivity() {
        Intent intent = new Intent(MainActivity.this, BuildYourOwnActivity.class);
        startActivity(intent);
    }

    private void openSpecialityPizzaActivity() {
        // Placeholder for SpecialityPizzaActivity
        Intent intent = new Intent(MainActivity.this, SpecialityPizzaActivity.class);
        startActivity(intent);
    }

    private void OpenPizzaOrder() {
        Intent intent = new Intent(MainActivity.this, currentordersactivity.class);
        startActivity(intent);
    }
    private void OpenStoreOrder() {
        // Placeholder for SpecialityPizzaActivity
        if(OrderBreaker.getStoreOrder().getOrders().isEmpty()){
            Toast.makeText(this,"You have no orders",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, storeorderactivity.class);
        startActivity(intent);
    }

}
