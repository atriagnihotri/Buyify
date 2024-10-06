package com.example.buyify.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class Login extends AppCompatActivity {
    TextView newuser;
    EditText name, password;
    Button login;
    SharedPreference sharedPreference;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        newuser = findViewById(R.id.newuser);
        name = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Logging In")
                .setMessage("Please wait...")
                .setCancelable(false);
        dialog = builder.create();

        sharedPreference = new SharedPreference(getApplicationContext());


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = name.getText().toString();
                String pass1 = password.getText().toString();
                sharedPreference.saveString("name", name1);
                Data(name1, pass1);
                name.setText("");
                password.setText("");
                dialog.show();
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, SignUp.class));
                finishAffinity();
            }
        });
    }

    public void Data(String name, String password) {
        String url = "http://192.168.43.249:8000/auth/login";

        JSONObject postData = new JSONObject();
        try {
            postData.put("username", name);
            postData.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String value = response.getString("access");
                            sharedPreference.saveString("access", value);
                            sharedPreference.saveBoolean("login", true);
                            dialog.dismiss();

                            Toasty.success(Login.this, "LoggedIn Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finishAffinity();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        error.printStackTrace();
                        Toasty.error(Login.this, "Auth Failure", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


}
