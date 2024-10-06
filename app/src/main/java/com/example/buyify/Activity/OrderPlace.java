package com.example.buyify.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class OrderPlace extends AppCompatActivity {
    SharedPreference sharedPreference;
    TextView user, total;
    EditText address, phone, landmark, city, pincode;
    RadioGroup radioGroup;
    Button order;
    String payment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_place);
        sharedPreference = new SharedPreference(getApplicationContext());
        user = findViewById(R.id.user);
        total = findViewById(R.id.total);
        address = findViewById(R.id.address);
        order = findViewById(R.id.order);
        phone = findViewById(R.id.phone);
        landmark = findViewById(R.id.landmark);
        city = findViewById(R.id.city);
        pincode = findViewById(R.id.pincode);
        radioGroup = findViewById(R.id.groupradio);
        String users = sharedPreference.getString("name", "none");
        String totals = getIntent().getStringExtra("total");
        user.setText(users);
        total.setText(totals);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                payment = radioButton.getText().toString();
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreference.saveString("Address",address.getText().toString());
                String url = "http://192.168.43.249:8000/cart/place";

                JSONObject postData = new JSONObject();
                try {
                    postData.put("amount", totals);
                    postData.put("username", users);
                    postData.put("address", address.getText().toString());
                    postData.put("city", city.getText().toString());
                    postData.put("phone", phone.getText().toString());
                    postData.put("pincode", pincode.getText().toString());
                    postData.put("landmark", landmark.getText().toString());
                    postData.put("payment_mode", payment);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        postData,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String order = response.getString("order");
                                    String date = response.getString("Delivery");
                                    sharedPreference.saveString("Delivery",date);
                                    Toasty.success(OrderPlace.this, "Order Created", Toast.LENGTH_SHORT).show();
                                    addNotification(order, date);
                                    Intent intent=new Intent(getApplicationContext(), PlacedView.class);
                                    startActivity(intent);
                                    finishAffinity();

                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                                error.printStackTrace();
                            }
                        }
                );

                requestQueue.add(jsonObjectRequest);

            }
        });


    }

    private void addNotification(String title, String context) {
        String channelId = "Channel";
        int notificationId = 1;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence channelName = "Simple Notification";
            String channelDescription = "A channel for simple notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(title)
                .setContentText("Delivery Date - "+context)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        notificationManagerCompat.notify(notificationId, builder.build());

    }
}