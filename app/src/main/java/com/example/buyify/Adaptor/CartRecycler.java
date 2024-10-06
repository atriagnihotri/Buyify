package com.example.buyify.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buyify.Models.CartModel;
import com.example.buyify.R;

import java.util.ArrayList;

public class CartRecycler extends RecyclerView.Adapter<CartRecycler.CartViewHolder> {
    ArrayList<CartModel> arrayList;
    Context context;

    public CartRecycler(ArrayList<CartModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.cartview, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel model=arrayList.get(position);
        holder.price.setText(arrayList.get(position).getPrice());
        holder.product.setText(arrayList.get(position).getProduct());
        holder.quantity.setText(arrayList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
      TextView product,quantity,price;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            product=itemView.findViewById(R.id.product);
            quantity=itemView.findViewById(R.id.quantity);
            price=itemView.findViewById(R.id.price);
        }
    }
}
