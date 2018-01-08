package com.example.hsenid.taxiapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hsenid on 1/4/18.
 */
public class HirePlace extends AsyncTask<Void, Void, Boolean> {

    private static final String TAG = "HirePlace";
    private static final String URL = "http://192.168.100.106:50000/api/hire/intialplacehire";


    private final String startLattitude;
    private final String startLLongitude;
    private final String vehicleType;
    private final String dateOfHire;
    private final String timeOfHire;

    private final Context context;


    public HirePlace(Context context, String startLattitude, String startLLongitude,
                     String vehicleType, String dateOfHire, String timeOfHire ) {
        this.startLattitude = startLattitude;
        this.startLLongitude = startLLongitude;
        this.vehicleType=vehicleType;
        this.dateOfHire=dateOfHire;
        this.timeOfHire=timeOfHire;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        Boolean result=false;
        try {
            //String url = "http://192.168.100.106:50000/api/customer/login";

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            JSONObject json = new JSONObject();
            json.put("customer_email", "menuka@gmail.com");
            json.put("start_location_latitude", startLattitude);
            json.put("start_location_longitude", startLLongitude);
            json.put("vehicle_type", vehicleType);
            json.put("date", dateOfHire);
            json.put("time", timeOfHire);


            String requestBody = json.toString();

            HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
            Log.e(TAG,"entity"+ entity.getBody());
            Log.e(TAG,"entity2"+ entity.getHeaders());


            RestTemplate loginTemplate = new RestTemplate();
            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

            loginTemplate.getMessageConverters().add(formHttpMessageConverter);
            loginTemplate.getMessageConverters().add(stringHttpMessageConverternew);


            ResponseEntity<String> response= null;
            response = loginTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
            //response = loginTemplate.postForObject(URL,JSONObject,);

          /*  JSONObject jsonObj = new JSONObject(response.getBody());
            String s=jsonObj.get("cost").toString();
            Log.e(TAG,"JSON Object"+ s);*/

            Log.e(TAG,"result"+ response.getBody().getClass());
            Log.e(TAG,"result"+  response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK ) {
                result= true;
            }
            else  {
                result=false;
            }
            // result=response.toString();
            Thread.sleep(2000);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            Toast.makeText(context, "Placed Hire successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "Hire Place Failed", Toast.LENGTH_SHORT).show();

        }
    }
}
