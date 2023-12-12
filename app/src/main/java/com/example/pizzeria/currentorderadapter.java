package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzeria.model.Pizza;

import java.util.List;

public class currentorderadapter extends RecyclerView.Adapter<currentorderadapter.ViewHolder> {
    private static int selectednum;
    private List<pizzaordermodel> pizzalist;
    Context context;
    public static int getselectednum(){
        return selectednum;
    }
    public currentorderadapter(List<pizzaordermodel> pizzalist, Context context) {
        this.pizzalist = pizzalist;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleritem_pizza_co, parent, false);
        return new ViewHolder(view);
    }
    private void showMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    public void removeItem(int position) {
        pizzalist.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pizzaordermodel pizza = pizzalist.get(position);
        if(pizza == null){
            showMessage(context.getApplicationContext(), "null");
            return;
        }
        holder.pizza_name.setText(String.format("%s %s","Pizza Name:", pizza.getPizzaname()));
        holder.Toppings.setText(String.format("%s %s", "Toppings:",pizza.getToppings()));
        holder.extra_cheese.setText(pizza.getExtra_cheese() ? "Extra Cheese: Y" : "Extra Cheese: N");
        holder.extra_sauce.setText(pizza.getExtra_Sauce() ? "Extra Sauce: Y" : "Extra Sauce: N");
        holder.pizza_size.setText(pizza.getSize());
        holder.price.setText(pizza.getPrice());

    }

    @Override
    public int getItemCount() {
        return pizzalist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView pizza_name;
        public TextView Toppings;
        public TextView extra_cheese;
        public TextView extra_sauce;
        public TextView pizza_size;
        public TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pizza_name = itemView.findViewById(R.id.pizza_name);
            Toppings = itemView.findViewById(R.id.Toppings);
            extra_cheese = itemView.findViewById(R.id.extra_cheese);
            extra_sauce = itemView.findViewById(R.id.extra_sauce);
            pizza_size = itemView.findViewById(R.id.pizza_size);
            price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Assuming pizza_name.getText() returns "Pizza Name: 0"
                    String pizzaNameText = pizza_name.getText().toString();

// Split the string by ":" and trim any spaces
                    String[] parts = pizzaNameText.split(":");
                    String isolatedNumber = parts[1].trim();

// Convert the isolated number to an integer
                    try {
                        selectednum = Integer.parseInt(isolatedNumber);
                        // Now 'selectedNum' contains the integer value of the isolated number
                    } catch (NumberFormatException e) {
                        // Handle the case where the parsing fails (e.g., if the isolatedNumber is not a valid integer)
                        e.printStackTrace();
                    }

                }
            });
        }
    }
}
