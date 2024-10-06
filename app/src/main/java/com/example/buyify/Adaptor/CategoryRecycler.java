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

import com.example.buyify.Activity.CategoryWiseView;
import com.example.buyify.Models.CategoryModel;
import com.example.buyify.R;

import java.util.ArrayList;

public class CategoryRecycler extends RecyclerView.Adapter<CategoryRecycler.CategoryViewHolder> {
    ArrayList<CategoryModel> arrayList;
    Context context;

    public CategoryRecycler(ArrayList<CategoryModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.categoryview, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryModel model=arrayList.get(position);
          holder.text.setText(arrayList.get(position).getCategory());
          holder.image.setImageResource(arrayList.get(position).getImg());
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  String lower=model.getCategory().toLowerCase();
                  Intent intent=new Intent(context, CategoryWiseView.class);
                  intent.putExtra("category",lower);
                  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
           ImageView image;
           TextView text;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            text=itemView.findViewById(R.id.text);
        }
    }
}
