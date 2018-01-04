package com.example.hsenid.taxiapp.util.vehicleType;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by hsenid on 12/22/17.
 */

public class CustomAdapter extends ArrayAdapter<String> {

    private Activity activity;
    private ArrayList data;
    public Resources res;
    SpinnerModel tempValues = null;
    LayoutInflater inflater;


    public CustomAdapter(
            CustomSpinner activitySpinner,
            int textViewsResourceId,
            ArrayList objects,
            Resources resLocal
    ){
        super(activitySpinner,textViewsResourceId,objects);

        activity = activitySpinner;
        data = objects;
        res = resLocal;

        //layout inflator to call external xml layout
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
/*
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
                return getCustomView(position,convertView,parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return getCustomView(position,convertView,parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent){

        //inflate spinner_rows.xml file for each row
        View row = inflater.inflate(R.layout.spinner_rows,parent,false);

        //get each model object from Arraylist
        tempValues = null;
        tempValues = (SpinnerModel) data.get(position);

        TextView label =(TextView) row.findViewById(R.id.company);
        TextView sub =(TextView) row.findViewById(R.id.sub);
        ImageView companyLogo= (ImageView) row.findViewById(R.id.image);

        if (position==0){

            label.setText("Please select company");
            sub.setText("");
        }else {
            //set values for spinner each row
            label.setText(tempValues.getCompanyName());;
            companyLogo.setImageResource(res.getIdentifier("com.example.hsenid.dropdownapp:drawable/"+tempValues.getImage(),null,null));
        }

        return row;
    }*/

    }



