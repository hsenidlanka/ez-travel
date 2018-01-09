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

public class DriverPaymentFragment extends Fragment {

    private static final String TAG= "DriverPaymentFragment";

    private Button driver_payment_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_driver_hire, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        driver_payment_btn=(Button) rootView.findViewById(R.id.driver_hire);

        driver_payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"HIre is confirmed",Toast.LENGTH_SHORT).show();

            }
        });
        return rootView;
    }


}
