package com.example.hsenid.taxiapp.passenger;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by hsenid on 12/27/17.
 */

public class CustomOnItemSelectedListener implements OnItemSelectedListener {



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        Toast.makeText(adapterView.getContext(),
                "OnItemSelectedListener : " + adapterView.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
        String selected = adapterView.getItemAtPosition(pos).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
