package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

class SP_RecyclerViewAdapter extends RecyclerView.Adapter<SP_RecyclerViewAdapter.MyViewHolder> {

    private List<SpecialityPizzaModel> pizzaList;
    private Context context;

    public SP_RecyclerViewAdapter(Context context, List<SpecialityPizzaModel> pizzaList) {
        this.context = context;
        this.pizzaList = pizzaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_specialty_pizza, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SpecialityPizzaModel pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getPizzaName());
        holder.pizzaToppings.setText(pizza.getPizzaTopping());
        holder.pizzaImage.setImageResource(pizza.getImgResourceId());
        holder.textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", pizza.getPrice()));
    }

    @Override
    public int getItemCount() {
        return pizzaList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pizzaImage;
        TextView pizzaName;
        TextView pizzaToppings;
        TextView textViewPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.imageViewPizza);
            pizzaName = itemView.findViewById(R.id.textViewPizzaName);
            pizzaToppings = itemView.findViewById(R.id.textViewToppings);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
        }
    }
}
