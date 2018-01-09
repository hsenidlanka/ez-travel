package com.example.hsenid.taxiapp.driver;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsenid.taxiapp.R;

/**
 * Created by hsenid on 1/9/18.
 */

public class DriverFeedbackFragment extends Fragment {


    private static final String TAG= "DriverFeedbackFragment";

    private Button driver_feedback_btn;

    public DriverFeedbackFragment() {

    }


       /*   Returns a new instance of this fragment for the given section
          number.*/

 /*   public static DriverHomePageActivity.PlaceholderFragment newInstance(int sectionNumber) {
        DriverHomePageActivity.PlaceholderFragment fragment = new DriverHomePageActivity.PlaceholderFragment();
          *//*  Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);*//*
        return fragment;
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_driver_feedback, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        driver_feedback_btn=(Button) rootView.findViewById(R.id.driver_feedback);

        driver_feedback_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"HIre is confirmed",Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }
}
