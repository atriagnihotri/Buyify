package com.example.buyify.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buyify.Adaptor.ProductRecycler;
import com.example.buyify.Models.ProductModel;
import com.example.buyify.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryWiseView extends AppCompatActivity {
    ProductRecycler productRecycler;
    RecyclerView recyclerView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise_view);


        ArrayList<ProductModel> arrayList=new ArrayList<>();

        recyclerView=findViewById(R.id.recycler);
        textView=findViewById(R.id.cat);
        productRecycler=new ProductRecycler(arrayList,getApplicationContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        String category=getIntent().getStringExtra("category");
        textView.setText(category);


        String URL="http://192.168.43.249:8000/products?category="+category;
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

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
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });

        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}