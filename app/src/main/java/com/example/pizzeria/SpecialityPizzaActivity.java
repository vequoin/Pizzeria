package com.example.pizzeria;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.Pizza;
import com.example.pizzeria.model.PizzaMaker;
import com.example.pizzeria.model.Size;
import com.example.pizzeria.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class SpecialityPizzaActivity extends AppCompatActivity implements SP_RecyclerViewAdapter.OnPizzaSelectedListener{

    List<SpecialityPizzaModel> pizzaModelList;
    private RecyclerView recyclerViewSpecialtyPizzas;
    private SP_RecyclerViewAdapter adapter;
    ArrayList<Pizza> allSpecialityPizzas = new ArrayList<Pizza>();

    private Spinner sizeSelector;
    private CheckBox extraCheese;
    private CheckBox extraSauce;
    private Button confirmButton;

    private SpecialityPizzaModel selectedPizzaModel;

    private String[] getSizes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speciality_pizza);
        confirmButton = findViewById(R.id.confirm_button_sp);
        extraCheese = findViewById(R.id.extra_cheese_checkbox);
        extraSauce = findViewById(R.id.extra_sauce_checkbox);

        sizeSelector = findViewById(R.id.spinnerPizzaSizes);
        getSizes = getEnumNames();
        ArrayAdapter<String> adapterOne = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getEnumNames());
        adapterOne.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeSelector.setAdapter(adapterOne);

        allSpecialityPizzas = new ArrayList<>();
        recyclerViewSpecialtyPizzas = findViewById(R.id.recycle_view_sp);
        recyclerViewSpecialtyPizzas.setLayoutManager(new LinearLayoutManager(this));

        pizzaModelList = new ArrayList<>();
        populatePizzaList();

        adapter = new SP_RecyclerViewAdapter(this,pizzaModelList,this);
        recyclerViewSpecialtyPizzas.setAdapter(adapter);
        setupListeners();

        setupListeners();

    }
    private String[] getEnumNames() {
        Size[] sizes = Size.values();
        String[] names = new String[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            names[i] = sizes[i].name();
        }

        return names;
    }

    private void onPizzaModelSelected(SpecialityPizzaModel pizzaModel) {
        selectedPizzaModel = pizzaModel;
    }

    private void setupListeners() {
        sizeSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updatePizzaPrices();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        extraCheese.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrices());
        extraSauce.setOnCheckedChangeListener((buttonView, isChecked) -> updatePizzaPrices());
        confirmButton.setOnClickListener(v -> confirmSelectedPizza());
    }

    private void updatePizzaPrices() {
        Size selectedSize = Size.valueOf(getSizes[sizeSelector.getSelectedItemPosition()]);
        boolean extraCheeseChecked = extraCheese.isChecked();
        boolean extraSauceChecked = extraSauce.isChecked();

        for (SpecialityPizzaModel model : pizzaModelList) {
            Pizza pizza = PizzaMaker.createPizza(model.getPizzaName());
            pizza.setSize(selectedSize);
            pizza.setExtraCheese(extraCheeseChecked);
            pizza.setExtraSauce(extraSauceChecked);
            model.setPrice(pizza.price());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPizzaSelected(SpecialityPizzaModel selectedPizzaModel) {
        // Handle the selected pizza model here
        this.selectedPizzaModel = selectedPizzaModel;
        // Maybe show details of the selected pizza or add it to an order
    }

    private void populatePizzaList() {
        String[] pizzaNames = getResources().getStringArray(R.array.all_speciality_pizza);
        for (String pizzaName : pizzaNames) {
            Pizza pizza = PizzaMaker.createPizza(pizzaName);
            String toppings = getToppingsAsString(pizza.getToppings());
            pizza.setSize(Size.SMALL);
            int imageResId = getImageResIdByName(pizzaName); // You need to implement this method.
            double price = pizza.price();

            SpecialityPizzaModel model = new SpecialityPizzaModel(pizzaName, toppings, imageResId, price);
            pizzaModelList.add(model);
        }
    }

    private String getToppingsAsString(List<Topping> toppings) {
        StringBuilder toppingsBuilder = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsBuilder.append(topping.getDescription()).append(", ");
        }
        // Remove the last comma and space if there are toppings
        if (!toppings.isEmpty()) {
            toppingsBuilder.setLength(toppingsBuilder.length() - 2);
        }
        return toppingsBuilder.toString();
    }

    private void confirmSelectedPizza() {
        if (selectedPizzaModel != null) {
            // Add the selected pizza to the order
            // Assuming you have an Order class to manage the order
            String selectedSizeStr = sizeSelector.getSelectedItem().toString();
            Size size;
            try {
                size = Size.valueOf(selectedSizeStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                Toast.makeText(this, "Invalid pizza size selected.", Toast.LENGTH_SHORT).show();
                return;
            }
            String pizzaName = selectedPizzaModel.getPizzaName();
            Pizza pizza = PizzaMaker.createPizza(pizzaName);
            pizza.setSize(size);
            pizza.setExtraCheese(extraCheese.isChecked());
            pizza.setExtraSauce(extraSauce.isChecked());
            OrderBreaker.getOrder().addPizza(pizza);
            Toast.makeText(this, selectedPizzaModel.getPizzaName() + " added to order!", Toast.LENGTH_SHORT).show();
            resetSelections();
        } else {
            Toast.makeText(this, "Please select a pizza to add to your order.", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetSelections() {
        selectedPizzaModel = null;
        extraCheese.setChecked(false);
        extraSauce.setChecked(false);
        sizeSelector.setSelection(1); // Assuming 0 is the default position
    }


    private int getImageResIdByName(String pizzaName) {
        // This is a stub, you'll need to implement this according to your drawable resources
        switch (pizzaName) {
            case "Deluxe":
                return R.drawable.deluxe_pizza; // replace with actual drawable resource IDs
            case "Hawaiian":
                return R.drawable.hawaiian_pizza_image;
            case "Supreme":
                return R.drawable.supreme_pizza;
            case "Seafood":
                return R.drawable.seafood_pizza;
            case "Pepperoni":
                return R.drawable.pepperoni_pizza;
            case "Meatzza":
                return R.drawable.meat_pizza;
            case "Whiskey":
                return R.drawable.whiskey_pizza;
            case "Veggie":
                return R.drawable.veggie_pizza;
            case "Margherita":
                return R.drawable.margherita_pizza;
            default:
                return R.drawable.pizza_selection_placeholder; // default image if pizza is not found
        }
    }
}
