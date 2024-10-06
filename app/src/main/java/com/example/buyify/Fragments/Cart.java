package com.example.buyify.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.buyify.Adaptor.CartRecycler;
import com.example.buyify.Models.CartModel;
import com.example.buyify.Activity.OrderPlace;
import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Cart extends Fragment {

RecyclerView recyclerView;
SharedPreference sharedPreference;
CartRecycler cartRecycler;
TextView total;
Button Cont;
String totals;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView=view.findViewById(R.id.recycler);
        total=view.findViewById(R.id.total);
        Cont=view.findViewById(R.id.cont);
        sharedPreference=new SharedPreference(getContext());
        String value=sharedPreference.getString("access","No Key");
        ArrayList<CartModel> arrayList=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRecycler=new CartRecycler(arrayList,getContext());


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = "http://192.168.43.249:8000/cart/order";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject item = response.getJSONObject(i);
                                String price=item.getString("price");
                                String quantity=item.getString("quantity");
                                JSONObject cart = item.getJSONObject("cart");
                                String Total_Price= cart.getString("total_price");
                                JSONObject product = item.getJSONObject("product");
//                                String id=product.getString("id");
                                String pr_name=product.getString("pr_name");
                                total.setText(Total_Price);
                                totals=Total_Price;
                                arrayList.add(new CartModel(pr_name,quantity,price,Total_Price));
                                recyclerView.setAdapter(cartRecycler);



                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + value);
                return headers;
            }
        };

        requestQueue.add(jsonArrayRequest);

        Cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), OrderPlace.class);
                intent.putExtra("total",totals);
                startActivity(intent);
            }
        });


        return view;
    }
}