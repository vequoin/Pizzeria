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

    public interface OnPizzaSelectedListener {
        void onPizzaSelected(SpecialityPizzaModel selectedPizzaModel);
    }

    private int selectedPos = RecyclerView.NO_POSITION;

    private List<SpecialityPizzaModel> pizzaList;
    private Context context;
    private OnPizzaSelectedListener listener;

    public SP_RecyclerViewAdapter(Context context, List<SpecialityPizzaModel> pizzaList, OnPizzaSelectedListener listener) {
        this.context = context;
        this.pizzaList = pizzaList;
        this.listener = listener;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_specialty_pizza, parent, false);
        return new MyViewHolder(itemView, listener, pizzaList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SpecialityPizzaModel pizza = pizzaList.get(position);
        holder.pizzaName.setText(pizza.getPizzaName());
        holder.pizzaToppings.setText(pizza.getPizzaTopping());
        holder.pizzaImage.setImageResource(pizza.getImgResourceId());
        holder.textViewPrice.setText(String.format(Locale.getDefault(), "$%.2f", pizza.getPrice()));
        holder.itemView.setSelected(selectedPos == position);
        holder.itemView.setOnClickListener(v -> {
            notifyItemChanged(selectedPos); // Reset old selection
            selectedPos = holder.getLayoutPosition(); // Get new selection
            notifyItemChanged(selectedPos); // Refresh new selection
            if (listener != null) {
                listener.onPizzaSelected(pizzaList.get(selectedPos));
            }
        });
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

        public MyViewHolder(@NonNull View itemView, OnPizzaSelectedListener listener, List<SpecialityPizzaModel> pizzaList) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.imageViewPizza);
            pizzaName = itemView.findViewById(R.id.textViewPizzaName);
            pizzaToppings = itemView.findViewById(R.id.textViewToppings);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onPizzaSelected(pizzaList.get(position));
                }
            });
        }
    }
}
