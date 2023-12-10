package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class currentorderadapter extends RecyclerView.Adapter<currentorderadapter.ViewHolder> {

    private List<pizzaordermodel> pizzalist;
    Context context;

    public currentorderadapter(List<pizzaordermodel> pizzalist, Context context) {
        this.pizzalist = pizzalist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_specialty_pizza, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pizzaordermodel pizza = pizzalist.get(position);
        holder.pizza_name.setText("Pizza Name: " + pizza.getPizzaname());
        holder.Toppings.setText("Toppings:" + pizza.getToppings());
        holder.extra_cheese.setText(pizza.getExtra_cheese() ? "Extra Cheese: Y" : "Extra Cheese: N");
        holder.extra_sauce.setText(pizza.getExtra_Sauce() ? "Extra Sauce: Y" : "Extra Sauce: N");
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
        public Button remove_button;
        public TextView pizza_size;
        public TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pizza_name = itemView.findViewById(R.id.pizza_name);
            Toppings = itemView.findViewById(R.id.Toppings);
            extra_cheese = itemView.findViewById(R.id.extra_cheese);
            extra_sauce = itemView.findViewById(R.id.extra_sauce);
            remove_button = itemView.findViewById(R.id.remove_button);
            price = itemView.findViewById(R.id.price);
        }
    }
}
