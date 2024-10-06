package com.example.buyify.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.buyify.Activity.ProductDetailView;
import com.example.buyify.Models.ProductModel;
import com.example.buyify.R;

import java.util.ArrayList;

public class ProductRecycler extends RecyclerView.Adapter<ProductRecycler.ProductViewHolder> {
    ArrayList<ProductModel> arrayList;
    Context context;

    public ProductRecycler(ArrayList<ProductModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.productlistview, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel productModel=arrayList.get(position);
        holder.price.setText(String.valueOf(productModel.getPrice()));
        holder.product.setText(productModel.getPrName());
        Glide.with(context)
            .load(productModel.getImage())
            .into(holder.imageView);
        holder.cat.setText(productModel.getCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ProductDetailView.class);
                intent.putExtra("id",productModel.getId());
                intent.putExtra("product",productModel.getPrName());
                intent.putExtra("category",productModel.getCategory());
                intent.putExtra("image",productModel.getImage());
                intent.putExtra("price",productModel.getPrice());
                intent.putExtra("description",productModel.getDescription());
                intent.putExtra("stock",productModel.getStock());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
       ImageView imageView;
       TextView product,cat,price;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            product=itemView.findViewById(R.id.name);
            cat=itemView.findViewById(R.id.category);
            price=itemView.findViewById(R.id.price);
        }
    }

}
