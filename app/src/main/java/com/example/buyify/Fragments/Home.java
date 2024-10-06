package com.example.buyify.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buyify.Models.ProductModel;
import com.example.buyify.Adaptor.ProductRecycler;
import com.example.buyify.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ProductRecycler productRecycler;
        RecyclerView recyclerView;
        ArrayList<ProductModel> arrayList=new ArrayList<>();

        View view= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycler);
         productRecycler=new ProductRecycler(arrayList,getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);


        String URL="http://192.168.43.249:8000/products";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray dataArray = response.getJSONArray("data");
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject dataObject = dataArray.getJSONObject(i);
                                int id = dataObject.getInt("id");
                                String prName = dataObject.getString("pr_name");
                                String image = dataObject.getString("image");
                                double price = dataObject.getDouble("price");
                                String description = dataObject.getString("description");
                                int stock = dataObject.getInt("stock");
                                JSONObject categoryObject = dataObject.getJSONObject("category");
                                String categoryName = categoryObject.getString("category");

                                arrayList.add(new ProductModel(id,categoryName,prName,"http://192.168.43.249:8000"+image,price,description,stock));
                                recyclerView.setAdapter(productRecycler);

                            }

                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
        return view;
    }



}