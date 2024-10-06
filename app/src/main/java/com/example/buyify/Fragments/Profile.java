package com.example.buyify.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buyify.Activity.SignUp;
import com.example.buyify.R;
import com.example.buyify.SharedPreferences.SharedPreference;

import es.dmoral.toasty.Toasty;


public class Profile extends Fragment {

    TextView name,date,address;
    Button logout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreference sharedPreference;

        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreference=new SharedPreference(getContext());
        name=view.findViewById(R.id.name);
        date=view.findViewById(R.id.date);
        address=view.findViewById(R.id.address);
        name=view.findViewById(R.id.name);
        logout=view.findViewById(R.id.logout);
       String del= sharedPreference.getString("Delivery","None");
       String Address=sharedPreference.getString("Address","None");
        String Name=sharedPreference.getString("name","None");

        date.setText("Your Order Delivery Date - "+del);
        address.setText(Address);
        name.setText(Name);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreference.clearAll();
                Toasty.warning(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), SignUp.class));
            }
        });



        return view;
    }
}