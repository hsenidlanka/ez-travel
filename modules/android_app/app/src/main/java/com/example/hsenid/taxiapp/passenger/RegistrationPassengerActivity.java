package com.example.hsenid.taxiapp.passenger;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.hsenid.taxiapp.MainActivity;
import com.example.hsenid.taxiapp.R;

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

public class RegistrationPassengerActivity extends AppCompatActivity {

    private UserRegistrationTask pRegisterTask = null;

    private static final String TAG = "PassengerRegister";

    //UI references
    Button passengerRegisterConfirmButton;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    RadioButton radioMaleButton;
    EditText passengerFname;
    EditText passengerLname;
    EditText passengerMobile;
    EditText passengerNIC;
    EditText passengerDOB;
    AutoCompleteTextView passengerEmail;
    EditText passengerPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_registration);

        //set up register form
        passengerEmail = (AutoCompleteTextView) findViewById(R.id.email_register_passenger);
        passengerPassword = (EditText) findViewById(R.id.password_register_passenger);
        passengerDOB = (EditText) findViewById(R.id.dob_register_passenger);
        passengerNIC = (EditText) findViewById(R.id.nic_register_passenger);
        passengerMobile = (EditText) findViewById(R.id.mobile_register_passenger);
        passengerLname = (EditText) findViewById(R.id.lname_register_passenger);
        passengerFname = (EditText) findViewById(R.id.fname_register_passenger);
        radioMaleButton=(RadioButton)findViewById(R.id.radioMale);
        radioMaleButton.setChecked(true);

        //radio button selection
        radioSexGroup=(RadioGroup)findViewById(R.id.radioSex);

        //on the click of Signin button
        passengerRegisterConfirmButton = (Button) findViewById(R.id.signup_register_passenger);
        passengerRegisterConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });


    }
    /**
     * Attempts to  register the account specified by the register form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegister() {
        if (pRegisterTask != null) {
            return;
        }

        // Reset errors.
        passengerEmail.setError(null);
        passengerPassword.setError(null);
        passengerDOB.setError(null);
        passengerNIC.setError(null);
        passengerMobile.setError(null);
        passengerLname.setError(null);
        passengerFname.setError(null);

        // Store values at the time of the login attempt.
        String email = passengerEmail.getText().toString();
        String password = passengerPassword.getText().toString();
        String birthday = passengerDOB.getText().toString();
        String nic = passengerNIC.getText().toString();
        String mobile = passengerMobile.getText().toString();
        String lName = passengerLname.getText().toString();
        String fName = passengerFname.getText().toString();
        //String gender = radioSexButton.getText().toString();

        int radioButtonID = radioSexGroup.getCheckedRadioButtonId();
        View radioButton = radioSexGroup.findViewById(radioButtonID);
        int idx = radioSexGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton)radioSexGroup.getChildAt(idx);
        String gender = r.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a non-empty birthday
        if(TextUtils.isEmpty(birthday)){
            passengerDOB.setError(getString(R.string.error_field_required));
            focusView = passengerDOB;
            cancel = true;
        }
        // Check for a non-empty and a valid NIC number, if the user entered one.
        if(TextUtils.isEmpty(nic)){
            passengerNIC.setError(getString(R.string.error_field_required));
            focusView = passengerNIC;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(nic) && !isNICValid(nic)) {
            passengerNIC.setError(getString(R.string.error_invalid_nic));
            focusView = passengerNIC;
            cancel = true;
        }

        // Check for a non-empty and a valid mobile number, if the user entered one.
        if(TextUtils.isEmpty(mobile)){
            passengerMobile.setError(getString(R.string.error_field_required));
            focusView = passengerMobile;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(mobile) && !isMobileValid(mobile)) {
            passengerMobile.setError(getString(R.string.error_invalid_mobile));
            focusView = passengerMobile;
            cancel = true;
        }

        // Check for a non-empty first name.
        if(TextUtils.isEmpty(fName)){
            passengerLname.setError(getString(R.string.error_field_required));
            focusView = passengerLname;
            cancel = true;
        }
        // Check for a non-empty last name.
        if(TextUtils.isEmpty(lName)){
            passengerFname.setError(getString(R.string.error_field_required));
            focusView = passengerFname;
            cancel = true;
        }
        // Check for a non-empty and a valid password, if the user entered one.
        if(TextUtils.isEmpty(password)){
            passengerPassword.setError(getString(R.string.error_field_required));
            focusView = passengerPassword;
            cancel = true;
        }

        else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passengerPassword.setError(getString(R.string.error_invalid_password));
            focusView = passengerPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            passengerEmail.setError(getString(R.string.error_field_required));
            focusView = passengerEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            passengerEmail.setError(getString(R.string.error_invalid_email));
            focusView = passengerEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            pRegisterTask = new UserRegistrationTask(email, password,fName,lName,mobile,birthday,nic
                    ,gender);
            pRegisterTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    private boolean isMobileValid(String mobile) {
        //TODO: Replace this with your own logic
        return mobile.length() < 10;
    }
    private boolean isNICValid(String nic) {
        //TODO: Replace this with your own logic
        return (nic.length() ==9);
    }


    /**
     * Represents an asynchronous registration task used to authenticate
     * the customer.
     */
    public class UserRegistrationTask extends AsyncTask<Void, Void, Boolean> {

        private final String cEmail;
        private final String cPassword;
        private final String cfirstName;
        private final String cLastName;
        private final String cMobile;
        private final String cBirthday;
        private final String cNIC;
        private final String cGender;

        UserRegistrationTask(String email, String password,String fName,String lName, String mobile,
                             String birthDate, String NIC, String gender) {
            cEmail = email;
            cPassword = password;
            cfirstName=fName;
            cLastName=lName;
            cMobile=mobile;
            cBirthday= birthDate;
            cNIC= NIC;
            cGender=gender;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Boolean result=false;
            try {
                // Simulate network access.
                String url = "http://192.168.100.106:50000/api/customer/register";

                HttpHeaders headers= new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.add("Authorization",getB64Auth(mEmail,mPassword));

                JSONObject json = new JSONObject();
                json.put("email", cEmail);
                json.put("password", cPassword);
                json.put("first_name", cfirstName);
                json.put("last_name", cLastName);
                json.put("birthday", cBirthday);
                json.put("contact_number", cMobile);
                json.put("nic", cNIC);
                json.put("gender", cGender);
                //json.put("status", "1");
                String requestBody = json.toString();

                HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
                Log.e(TAG,"entity"+ entity.getBody());
                Log.e(TAG,"entity2"+ entity.getHeaders());


                RestTemplate loginTemplate = new RestTemplate();
                HttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
                HttpMessageConverter stringHttpMessageConverternew = new StringHttpMessageConverter();

                loginTemplate.getMessageConverters().add(formHttpMessageConverter);
                loginTemplate.getMessageConverters().add(stringHttpMessageConverternew);

               ResponseEntity<String> response=loginTemplate.exchange(url, HttpMethod.POST, entity, String.class);
                //RegisterReplyModal response=loginTemplate.postForObject(url,entity,RegisterReplyModal.class);

               /* Log.e(TAG,"result"+ response.getHttpStatusCode());
                Log.e(TAG,"result"+  response.getUserCreation());*/

                Log.e(TAG,"result"+ response.getStatusCode());
                Log.e(TAG,"result"+  response.getBody());

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
        protected void onPostExecute(final Boolean success) {
            pRegisterTask = null;

            //if the login validation is a success
            if (success) {
                Intent passengerSuccessIntent = new Intent(RegistrationPassengerActivity.this, MainActivity.class);
                RegistrationPassengerActivity.this.startActivity(passengerSuccessIntent);
                //finish();

                //if the login validation fails
            } else {

                Intent play22Intent = new Intent(RegistrationPassengerActivity.this, MainActivity.class);
                RegistrationPassengerActivity.this.startActivity(play22Intent);
             /*   Intent intent = getIntent();
                finish();
                startActivity(intent);*/

              /*  Toast toast = Toast.makeText(getApplicationContext(),"bbbbbb", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();*/
               /* Toast toast = Toast.makeText(context, text, duration);
                toast.show();*/

                /*mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();*/
            }
        }

        private String getB64Auth (String login, String pass) {
            String source=login+":"+pass;
            String ret="Basic "+ Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
            return ret;
        }

        @Override
        protected void onCancelled() {
            pRegisterTask = null;
            //showProgress(false);
        }
    }



}
