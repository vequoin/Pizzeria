package com.example.pizzeria;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pizzeria.model.Order;
import com.example.pizzeria.model.OrderBreaker;
import com.example.pizzeria.model.StoreOrder;

import org.w3c.dom.Text;

import java.util.List;

public class storeorderadapter extends BaseAdapter {
    private LayoutInflater inflater;
    Context context;
    private static int decision = -1;

    public static int getDecision() {
        return decision;
    }

    public static void setDecision(int decision) {
        storeorderadapter.decision = decision;
    }

    public storeorderadapter(List<pizzaordermodel> pizzalist, Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context); // Initialize the inflater
    }


    @Override
    public int getCount() {
        return OrderBreaker.getStoreOrder().getOrders().size();
    }

    @Override
    public Object getItem(int position) {
        return OrderBreaker.getStoreOrder().getOrders().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void remove(int position){
        OrderBreaker.getStoreOrder().getOrders().remove(position);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StoreOrder storeOrder = OrderBreaker.getStoreOrder();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.store_order_listitem, null);
        }
        TextView text = convertView.findViewById(R.id.list_pizza_name);
        text.setText(storeOrder.contoString(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String edecision = (String) text.getText();
                int orderNumberIndex = edecision.indexOf("Order Number:");
                String orderNumberString = edecision.substring(orderNumberIndex + "Order Number:".length(), edecision.indexOf("\n", orderNumberIndex));
                orderNumberString = orderNumberString.trim();
                decision = Integer.parseInt(orderNumberString);
            }
        });
        return convertView;
    }


}
