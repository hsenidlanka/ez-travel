package com.example.hsenid.taxiapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class RegistrationPassengerActivity extends AppCompatActivity {

    Button passengerRegisterConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_registration);


        //on the click of Signin button
        passengerRegisterConfirmButton = (Button) findViewById(R.id.signup_register_passenger);
        passengerRegisterConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
            }
        });
    }



}
