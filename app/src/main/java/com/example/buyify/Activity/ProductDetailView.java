package com.example.buyify.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ProductDetailView extends AppCompatActivity {
ImageView image,add,minus;
TextView prname,detail,stock,cat,price,quantity;
SharedPreference sharedPreference;
Button Cart;
    int variable=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_view);
        image=findViewById(R.id.image);
        prname=findViewById(R.id.product);
        price=findViewById(R.id.price);
        detail=findViewById(R.id.details);
        stock=findViewById(R.id.stock);
        cat=findViewById(R.id.category);
        add=findViewById(R.id.add);
        minus=findViewById(R.id.minus);
        quantity=findViewById(R.id.quantity);
        Cart=findViewById(R.id.cart);
        sharedPreference=new SharedPreference(getApplicationContext());


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variable++;
                quantity.setText(String.valueOf(variable));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (variable==1){
                    Toast.makeText(ProductDetailView.this, "Quantity Cannot be less than 1", Toast.LENGTH_SHORT).show();
                }
                else {
                    variable--;
                    quantity.setText(String.valueOf(variable));
                }

            }
        });


        Glide.with(getApplicationContext())
           .load(getIntent().getStringExtra("image"))
           .into(image);
        price.setText(String.valueOf(getIntent().getDoubleExtra("price",1)));
        prname.setText(getIntent().getStringExtra("product"));
        detail.setText(getIntent().getStringExtra("description"));
        stock.setText(String.valueOf(getIntent().getIntExtra("stock",1)));
        cat.setText(getIntent().getStringExtra("category"));

        String value=sharedPreference.getString("access","No Key");

        int id =getIntent().getIntExtra("id",1);

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCart(value,id,variable);
            }
        });




    }
    public void AddCart(String value,int product,int quantity){
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        String url = "http://192.168.43.249:8000/cart/order";

        JSONObject postData = new JSONObject();
        try {
            postData.put("product", product);
            postData.put("quantity", quantity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toasty.success(ProductDetailView.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Toast.makeText(ProductDetailView.this, "Error :"+error, Toast.LENGTH_SHORT).show();
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

        requestQueue.add(jsonObjectRequest);
    }
}