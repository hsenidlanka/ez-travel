package com.example.hsenid.taxiapp.util;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

/**
 * Created by faceless on 12/11/17.
 * Utility class that implements a service to find the current location of the device. Uses Google's location services provided in Google Play Services.
 * This class can only be used from within an AppCompatActivity.
 */
public class LocationServiceProvider implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private static final int REQUEST_CODE = 1000;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Location location;

    private AppCompatActivity currentActivity;
    private ProgressDialog waitForLoc;
    private LocationServiceListener listener;

    /**
     * Constructor for the utility class.
     *
     * @param a Current activity that instantiates the class.
     */
    public LocationServiceProvider(AppCompatActivity a) {
        this.currentActivity = a;
        this.listener = null;
        this.location = null;
    }

    /**
     * Sets the LocationServiceListener with a custom LocationServiceListener
     *
     * @param l LocationServiceListener instance. This should implement the interface LocationServiceListener.
     */
    public void setLocationServiceListener(LocationServiceListener l) {
        this.listener = l;
    }

    /**
     * Standard getter for the location
     *
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Initiates the connection if the connection is not established. If already connected, triggers the onConnected() event.
     */
    public void findLocation() {
        if (mGoogleApiClient.isConnected())
            this.onConnected(null);
        else
            mGoogleApiClient.connect();
    }

    /**
     * Creates a location request by instantiating the internal attributes of the class.
     */
    public void createLocationRequest() {
        if (!isGooglePlayServicesAvailable()) {
            showMessageDialog("This device currently does not support for this service!");
            return;
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        mGoogleApiClient = new GoogleApiClient.Builder(currentActivity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    /**
     * Commences the location update request. First it checks if the necessary permissions prevail.
     * If the permissions are not found, it will initiate request permission routine and halt this.
     * Otherwise the location update routine is continued.
     */
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(currentActivity, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(currentActivity, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(currentActivity, new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, REQUEST_CODE);
            return;
        }
        waitForLoc = ProgressDialog.show(currentActivity, "", "Waiting for location...", true);
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    /**
     * Halts the location update process.
     */
    public void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    /**
     * Disconnects from the location services and gracefully shutdown the process.
     */
    public void disconnectClient() {
        this.mGoogleApiClient.disconnect();
    }

    /**
     * Checks if the Google's Play Services are available.
     *
     * @return Boolean value indicates the availability of the services.
     */
    private boolean isGooglePlayServicesAvailable() {
        int status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(currentActivity);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GoogleApiAvailability.getInstance().getErrorDialog(currentActivity, status, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            return false;
        }
    }

    /**
     * Displays a message box with a custom text.
     *
     * @param message
     */
    private void showMessageDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(currentActivity);
        builder.setMessage(message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        waitForLoc.dismiss();
        if (listener != null) {
            listener.onLocationDataReady();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Locale l;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            l = currentActivity.getResources().getConfiguration().getLocales().get(0);
        } else {
            l = currentActivity.getResources().getConfiguration().locale;
        }
        showMessageDialog(String.format(l, "Failed to connect! (%d)", connectionResult.getErrorCode()));
    }

    /**
     * Listener interface that monitors the changes of the location and signals to the activity.
     * This interface should be implemented from within the activity that instantiates this class.
     * Up-to-date location data can be accessed and used in the implementation of the method void onLocationDataReady().
     */
    public interface LocationServiceListener {
        void onLocationDataReady();
    }
}

