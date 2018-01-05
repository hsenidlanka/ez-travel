package com.example.hsenid.taxiapp.passenger;

import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsenid.taxiapp.HirePlace;
import com.example.hsenid.taxiapp.R;
import com.example.hsenid.taxiapp.TripCostCalculate;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PassengerPlacehireActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "PassengerPlacehire";

    private TripCostCalculate tripCost = null;
    private HirePlace placeHireTask = null;

    GoogleMap mGoogleMap;
    private GoogleApiClient mClient;
    private int PLACE_PICKER_REQUEST = 1;
    private double latitude, logitude;
    private double end_latitude, end_logitude;
    private String travelDistance;
    private String selectedItemText;
    private String tripCostCal;
    private String formattedDate;
    private String formattedTime;


    private Spinner vehicleTypeSpinner;
    private TextView dateTime;
    private Button customerPlaceHireButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(googleServicesAvailable()){
            Toast.makeText(this, "Perfect !!!", Toast.LENGTH_LONG).show();
            setContentView(R.layout.activity_passenger_placehire);
            initMap();

           // dateTime =(TextView) findViewById(R.id.dateTime);


        }else {
            Toast.makeText(this, "Sorryyyyy !!!", Toast.LENGTH_LONG).show();

        }


        /////////give all places information when one place is selected(this is the origin)////////
        PlaceAutocompleteFragment originPlace= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_origin);

        originPlace.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                ////////////// get the lattitude and logitude///////////////
                latitude = place.getLatLng().latitude;
                logitude= place.getLatLng().longitude;
                String toastMsg = String.format("Latitude: %s", place.getLatLng().latitude );
                Toast.makeText(getApplicationContext(),toastMsg,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });


        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("LK")
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();

        originPlace.setFilter(typeFilter);


        /////give all places information when one place is selected(this is the destination)//////
        PlaceAutocompleteFragment destinationPlace= (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment_destination);

        destinationPlace.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                end_latitude= place.getLatLng().latitude;
                end_logitude=place.getLatLng().longitude;
                Toast.makeText(getApplicationContext(),place.getName(),Toast.LENGTH_SHORT).show();

                //gives the distance between the original plae and the destination
                float results1[]= new float[10];
                Location.distanceBetween(latitude,logitude,end_latitude,end_logitude,results1);
                float distance=results1[0] / 1000;
                travelDistance = Float.toString(distance);
                //dateTime.setText(travelDistance);
            }

            @Override
            public void onError(Status status) {

                Toast.makeText(getApplicationContext(),status.toString(),Toast.LENGTH_SHORT).show();

            }
        });

        destinationPlace.setFilter(typeFilter);

        //to get the current date and time with the selected vehicle
        vehicleTypeSpinner = (Spinner) findViewById(R.id.vehicleSelectionSpinner);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");

        formattedDate = df1.format(c.getTime()); //current date
        formattedTime = df2.format(c.getTime()); //current time

        vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText = (String) parent.getItemAtPosition(position);
                // Notify the selected item text
                Toast.makeText
                        (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                        .show();
                //dateTime.setText(formattedDate1+ "\n" + formattedDate2+ selectedItemText);
                //costCalculate();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
    protected void onStart()
    {
        super.onStart();

        //on the click of the Place Hire button
        customerPlaceHireButton = (Button) findViewById(R.id.button_select_driver);
        customerPlaceHireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                costCalculate();
                placeHire();


               /* Intent registrationIntent = new Intent(DriverActivity.this, RegistrationDriverActivity.class);
                DriverActivity.this.startActivity(registrationIntent);*/
            }
        });
    }

    private void costCalculate(){
        tripCost= new TripCostCalculate(this,travelDistance,selectedItemText);
        tripCost.execute();
    }

    private void placeHire(){
        placeHireTask=new HirePlace(this,Double.toString(latitude),Double.toString(logitude)
                ,selectedItemText,formattedDate,formattedTime);
        placeHireTask.execute();
    }





    public String ReturnThreadResult(String result)
    {
        // TO DO:
        //dateTime.setText(result);
        tripCostCal =result;
        Log.e(TAG,"The cost :"+result);
        return tripCostCal;
       // dateTime.setText(result);
    }


    private void initMap(){
        MapFragment mapFragment =(MapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }

    public boolean googleServicesAvailable(){
        GoogleApiAvailability api= GoogleApiAvailability.getInstance();
        int isAvailable = api.isGooglePlayServicesAvailable(this);

        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        } else if(api.isUserResolvableError(isAvailable)){
            Dialog dialog = api.getErrorDialog(this,isAvailable,0);
            dialog.show();
        }else {
            Toast.makeText(this,"Cannot connect to play services",Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(6.9, 79.86);
        mGoogleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Colombo"));
        CameraPosition Liberty = CameraPosition.builder().target(sydney).zoom(16).bearing(0).tilt(45).build();
        mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(Liberty));


        // Turn on the My Location layer and the related control on the map.


        // Get the current location of the device and set the position of the map.

    }
}
