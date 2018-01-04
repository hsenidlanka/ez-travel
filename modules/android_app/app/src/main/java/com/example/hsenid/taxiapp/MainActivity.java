package com.example.hsenid.taxiapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.hsenid.taxiapp.driver.DriverActivity;
import com.example.hsenid.taxiapp.passenger.PassengerActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    Button passengerBtn;
    Button driverBtn;

    public void init(){
        passengerBtn = (Button) findViewById(R.id.homepage_cus_btn);
        driverBtn = (Button) findViewById(R.id.homepage_driver_btn);

        passengerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yandexActivity = new Intent(MainActivity.this, PassengerActivity.class);
                startActivity(yandexActivity);
            }
        });

        driverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleActivity = new Intent(MainActivity.this, DriverActivity.class);
                startActivity(googleActivity);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

}



