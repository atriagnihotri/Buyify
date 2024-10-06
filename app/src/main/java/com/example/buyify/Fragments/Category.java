package com.example.buyify.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.buyify.Adaptor.CategoryRecycler;
import com.example.buyify.Models.CategoryModel;
import com.example.buyify.R;

import java.util.ArrayList;

public class Category extends Fragment {

RecyclerView recyclerView;
ArrayList<CategoryModel> arrayList;
CategoryRecycler categoryRecycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category, container, false);
        recyclerView=view.findViewById(R.id.recycler);
        arrayList=new ArrayList<>();
        arrayList.add(new CategoryModel("Electronics",R.drawable.elect));
        arrayList.add(new CategoryModel("Accessories",R.drawable.accessories));
        arrayList.add(new CategoryModel("Clothes",R.drawable.clothes));
        arrayList.add(new CategoryModel("Grocery",R.drawable.groc));
        arrayList.add(new CategoryModel("Health",R.drawable.health));
        categoryRecycler=new CategoryRecycler(arrayList,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(categoryRecycler);


        return view;
    }
}