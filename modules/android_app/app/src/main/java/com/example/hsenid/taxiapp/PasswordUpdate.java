package com.example.hsenid.taxiapp;

import android.util.Log;

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
 * Created by hsenid on 12/14/17.
 */

public class PasswordUpdate  {

    private static final String TAG = "PasswordUpdate";

    private final String cEmail;
    private final String cPassword;
    private final String nPassword;
    private final String requestUrl;



    public PasswordUpdate(String email, String cpassword, String npassword, String requestUrl) {
        this.cEmail = email;
        this.cPassword = cpassword;
        this.nPassword=npassword;
        this.requestUrl = requestUrl;
    }


    public Boolean updatePassword() {
        Boolean result=false;
        try {
            //String url = "http://192.168.100.106:50000/api/customer/login";

            HttpHeaders headers= new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);


            JSONObject json = new JSONObject();
            json.put("email", cEmail);
            json.put("currentPassword", cPassword);
            json.put("newPassword", nPassword);

            String requestBody = json.toString();

            HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
            Log.e(TAG,"entity"+ entity.getBody());
            Log.e(TAG,"entity2"+ entity.getHeaders());


            RestTemplate loginTemplate = new RestTemplate();
            HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
            HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

            loginTemplate.getMessageConverters().add(formHttpMessageConverter);
            loginTemplate.getMessageConverters().add(stringHttpMessageConverternew);

            ResponseEntity<String> response=loginTemplate.exchange(requestUrl, HttpMethod.POST, entity, String.class);
            Log.e(TAG,"result"+ response.getBody());
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


}
