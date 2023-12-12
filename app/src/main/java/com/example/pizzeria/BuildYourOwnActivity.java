package com.example.pizzeria;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.model.PizzaMaker;
import com.example.pizzeria.model.Sauce;
import com.example.pizzeria.model.Size;
import com.example.pizzeria.model.Topping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BuildYourOwnActivity extends AppCompatActivity {

    private Spinner sizeSelector;

    private RadioButton tomatoSauce;
    private Button addButton;
    private Button removeButton;
    private RadioButton alfredoSauce;
    private CheckBox extraCheese;
    private CheckBox extraSauce;
    private TextView priceLabel;

    private ImageView backbtn;
    private TextView priceTextView;
    private Button confirmButton;

    private  ListView selectedToppingsListView;
    private ArrayAdapter<Topping> availableToppingsAdapter, selectedToppingsAdapter;
    private ArrayList<Topping> availableToppings, selectedToppings;
    private ListView allToppingsListView;
    private ImageView pizzaSizeImageView;

    private Pizza currentPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_own);
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        initializeViews();
        allToppingsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        selectedToppingsListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Initialize the list of toppings
        availableToppings = new ArrayList<>(Arrays.asList(Topping.values()));
        selectedToppings = new ArrayList<>();

        // Initialize the array adapters for the ListViews
        availableToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, availableToppings);
        selectedToppingsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, selectedToppings);

        // Assign the array adapters to the ListViews
        allToppingsListView.setAdapter(availableToppingsAdapter);
        selectedToppingsListView.setAdapter(selectedToppingsAdapter);

        // Set up the spinner for pizza sizes
        sizeSelector.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Size.values()));

        // Set up the current pizza
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");

        // Set up listeners for UI elements
        setupListeners();
    }

    private void initializeViews() {
        // Initialize your views here
        backbtn = findViewById(R.id.backbtn_bo);
        sizeSelector = findViewById(R.id.size_select_bo);
        alfredoSauce = findViewById(R.id.radio_btn_alfredo);
        tomatoSauce = findViewById(R.id.radio_btn_tomato);
        extraCheese = findViewById(R.id.ec_checkbox);
        extraSauce = findViewById(R.id.es_checkbox);
        priceLabel = findViewById(R.id.label_price);
        priceTextView = findViewById(R.id.text_view_price);
        confirmButton = findViewById(R.id.confirm_btn_bo);
        allToppingsListView = findViewById(R.id.all_toppings_listview);
        selectedToppingsListView = findViewById(R.id.selected_toppings_listview);
        pizzaSizeImageView = findViewById(R.id.image_pizza_size_bo);
        addButton = findViewById(R.id.addbtn);
        removeButton = findViewById(R.id.removebtn);
    }

    private void setupListeners() {

        sizeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Size selectedSize = (Size) parent.getItemAtPosition(position);
                currentPizza.setSize(selectedSize);
                handleImageSizeChange();
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
            }
        });
        // Set up listeners for your buttons and other interactive views

        backbtn.setOnClickListener(v -> onBackPressed());
        extraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());
        extraSauce.setOnCheckedChangeListener((buttonView, isChecked) -> updatePrice());


        addButton.setOnClickListener(view -> handleAddTopping());
        removeButton.setOnClickListener(view -> handleRemoveTopping());

        // Listener for confirm button
        confirmButton.setOnClickListener(view -> handleConfirmAction());

    }

    private void handleAddTopping() {
        SparseBooleanArray checkedPositions = allToppingsListView.getCheckedItemPositions();
        for (int i = 0; i < availableToppingsAdapter.getCount(); i++) {
            if (checkedPositions.get(i)) {
                Topping selectedTopping = availableToppingsAdapter.getItem(i);
                if(selectedToppings.size() >= 7){
                    Toast.makeText(this, "Cannot add more then 7 toppings! ", Toast.LENGTH_SHORT).show();
                }
                if (selectedTopping != null && !selectedToppings.contains(selectedTopping) && selectedToppings.size() < 7) {
                    selectedToppings.add(selectedTopping);
                    availableToppings.remove(selectedTopping);
                }
            }
        }
        refreshAdapters();
        updatePrice();
        for (int i = 0; i < availableToppingsAdapter.getCount(); i++) {
            allToppingsListView.setItemChecked(i, false);
        }
    }

    private void handleRemoveTopping() {
        SparseBooleanArray checkedPositions = selectedToppingsListView.getCheckedItemPositions();
        ArrayList<Topping> toRemove = new ArrayList<>();
        for (int i = 0; i < selectedToppingsAdapter.getCount(); i++) {
            if (checkedPositions.get(i)) {
                Topping selectedTopping = selectedToppingsAdapter.getItem(i);
                if (selectedTopping != null) {
                    toRemove.add(selectedTopping);
                }
            }
        }
        selectedToppings.removeAll(toRemove);
        availableToppings.addAll(toRemove);
        refreshAdapters();
        updatePrice();
        for (int i = 0; i < selectedToppingsAdapter.getCount(); i++) {
            selectedToppingsListView.setItemChecked(i, false);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // This will take the user back to the MainActivity from the current Activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Optional: If you wish to close the current activity
    }



    /*private void handleAddTopping() {
        int position = allToppingsListView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            Topping selectedTopping = availableToppingsAdapter.getItem(position);
            if (selectedTopping != null && selectedToppings.size() < 7) {
                availableToppings.remove(selectedTopping);
                selectedToppings.add(selectedTopping);
                refreshAdapters();
                updatePrice();
            } else {
                Toast.makeText(this, "You can only select up to 7 toppings.", Toast.LENGTH_SHORT).show();
            }
            allToppingsListView.setItemChecked(position, false); // Uncheck the item in the list
        }
    }*/


    private void handleImageSizeChange() {
        Size selectedSize = (Size) sizeSelector.getSelectedItem();
        if (selectedSize == null) return; // guard clause in case nothing is selected

        int imageResId;
        switch (selectedSize) {
            case SMALL:
                imageResId = R.drawable.small_pizza;
                break;
            case MEDIUM:
                imageResId = R.drawable.medium_pizza;
                break;
            case LARGE:
                imageResId = R.drawable.large_pizza;
                break;
            default:
                imageResId = R.drawable.pizza_placeholder;
                break;
        }

        pizzaSizeImageView.setImageResource(imageResId);
    }


    private void refreshAdapters() {
        availableToppingsAdapter.notifyDataSetChanged();
        selectedToppingsAdapter.notifyDataSetChanged();
    }



    private Sauce getSauceForPizza(){
        if(alfredoSauce.isChecked()){
            return Sauce.ALFREDO;
        } else if (tomatoSauce.isChecked()) {
            return Sauce.TOMATO;
        } else {
            // Handle the case where no sauce is selected if necessary
            return null;
        }
    }

    private void handleConfirmAction() {
        Size selectedSize = (Size) sizeSelector.getSelectedItem();
        if(selectedSize == null){
            Toast.makeText(this,"Please select a size.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(tomatoSauce.isChecked() || alfredoSauce.isChecked())){
            Toast.makeText(this, "Please Select a Sauce", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedToppings.size() < 3) {
            Toast.makeText(this, "Please select at least 3 toppings.", Toast.LENGTH_SHORT).show();
            return;
        }
        // Add the pizza to the order and display a confirmation message
        currentPizza.setSize(selectedSize);
        currentPizza.setSauce(getSauceForPizza());
        OrderBreaker.getOrder().addPizza(currentPizza);
        Toast.makeText(this, "Pizza added to order!", Toast.LENGTH_SHORT).show();
        // Reset the current pizza and selections for a new order
        //resetSelections();
    }

    private void resetSelections() {
        currentPizza = PizzaMaker.createPizza("BuildYourOwn");
        selectedToppings.clear();
        availableToppings.clear();
        Collections.addAll(availableToppings, Topping.values());
        extraCheese.setChecked(false);
        extraSauce.setChecked(false);
        tomatoSauce.setChecked(false);
        alfredoSauce.setChecked(false);
        refreshAdapters();
        updatePrice();
    }


    private void updatePrice() {
        currentPizza.setExtraCheese(extraCheese.isChecked());
        currentPizza.setExtraSauce(extraSauce.isChecked());
        currentPizza.getToppings().clear();
        currentPizza.getToppings().addAll(selectedToppings);
        double totalPrice = currentPizza.price();
        priceTextView.setText(String.format("%.2f", totalPrice));
    }
}
