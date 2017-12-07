package com.example.hsenid.taxiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
        public void gotoDriverPage(View view){
        Intent driverPage= new Intent(MainActivity.this,DriverActivity.class);
        startActivity(driverPage);
    }

    public void gotoPassengerPage(View view){
        Intent passengerPage= new Intent(MainActivity.this,PassengerActivity.class);
        startActivity(passengerPage);
    }
}



