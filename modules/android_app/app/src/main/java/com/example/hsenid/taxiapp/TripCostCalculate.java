package com.example.hsenid.taxiapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.hsenid.taxiapp.passenger.PassengerPlacehireActivity;

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


public class TripCostCalculate extends AsyncTask<Void, Void, String> {

    private static final String TAG = "TripCostCalculate";
    private static final String URL = "http://192.168.100.106:50000/api/hire/costoftrip";


    private final String tripDistance;
    private final String vehicleType;
    private final Context context;
    PassengerPlacehireActivity p;

    public TripCostCalculate(Context context, String tripDistance, String vehicleType ) {
        this.tripDistance = tripDistance;
        this.vehicleType = vehicleType;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result=null;
        try {
            //String url = "http://192.168.100.106:50000/api/customer/login";

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            JSONObject json = new JSONObject();
            json.put("length", tripDistance);
            json.put("vehicleType", vehicleType);

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

            JSONObject jsonObj = new JSONObject(response.getBody());
            String s=jsonObj.get("cost").toString();
            Log.e(TAG,"JSON Object"+ s);

            Log.e(TAG,"result"+ response.getBody().getClass());
            Log.e(TAG,"result"+  response.getStatusCode());

            if (response.getStatusCode() == HttpStatus.OK ) {
                result= s;
            }
            else  {
                result="NOt";
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
    protected void onPostExecute(String success) {
        p= new PassengerPlacehireActivity();
        String x=p.ReturnThreadResult(success);
        Toast.makeText(context, "Updated Password successfully"+x, Toast.LENGTH_SHORT).show();

    }
}
