package com.example.hsenid.taxiapp.driver;

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

public class RegistrationDriverActivity extends AppCompatActivity {

    private UserRegistrationTask dRegisterTask = null;


    private static final String TAG = "DriverRegister";

    //UI references
    Button driverRegisterConfirmButton;
    RadioGroup radioSexGroup;
    RadioButton radioSexButton;
    RadioButton radioMaleButton;
    EditText driverFname;
    EditText driverLname;
    EditText driverMobile;
    EditText driverNIC;
    EditText driverDOB;
    AutoCompleteTextView driverEmail;
    EditText driverPassword;
    EditText driverLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);

        //set up register form
        driverEmail = (AutoCompleteTextView) findViewById(R.id.email_register_driver);
        driverPassword = (EditText) findViewById(R.id.password_register_driver);
        driverDOB = (EditText) findViewById(R.id.dob_register_driver);
        driverNIC = (EditText) findViewById(R.id.nic_register_driver);
        driverMobile = (EditText) findViewById(R.id.mobile_register_driver);
        driverLname = (EditText) findViewById(R.id.lname_register_driver);
        driverFname = (EditText) findViewById(R.id.fname_register_driver);
        driverLicense =(EditText) findViewById(R.id.label_license_no);

        radioMaleButton=(RadioButton)findViewById(R.id.radioDriverMale);
        radioMaleButton.setChecked(true);

        //radio button selection
        radioSexGroup=(RadioGroup)findViewById(R.id.radioSex);

        //on the click of Signin button
        driverRegisterConfirmButton = (Button) findViewById(R.id.register_driver);
        driverRegisterConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptDriverRegister();
            }
        });
    }

    /**
     * Attempts to  register the driver account specified by the register form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptDriverRegister() {
        if (dRegisterTask != null) {
            return;
        }

        // Reset errors.
        driverEmail.setError(null);
        driverPassword.setError(null);
        driverDOB.setError(null);
        driverNIC.setError(null);
        driverMobile.setError(null);
        driverLname.setError(null);
        driverFname.setError(null);
        driverLicense.setError(null);

        // Store values at the time of the login attempt.
        String email = driverEmail.getText().toString();
        String password = driverPassword.getText().toString();
        String birthday = driverDOB.getText().toString();
        String nic = driverNIC.getText().toString();
        String mobile = driverMobile.getText().toString();
        String lName = driverLname.getText().toString();
        String fName = driverFname.getText().toString();
        String license = driverLicense.getText().toString();

        //String gender = radioSexButton.getText().toString();

        int radioButtonID = radioSexGroup.getCheckedRadioButtonId();
        View radioButton = radioSexGroup.findViewById(radioButtonID);
        int idx = radioSexGroup.indexOfChild(radioButton);
        RadioButton r = (RadioButton)  radioSexGroup.getChildAt(idx);
        String gender = r.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if(TextUtils.isEmpty(birthday)){
            driverDOB.setError(getString(R.string.error_field_required));
            focusView = driverDOB;
            cancel = true;
        }

        // Check for a non-empty and a valid NIC number, if the user entered one.
        if(TextUtils.isEmpty(nic)){
            driverNIC.setError(getString(R.string.error_field_required));
            focusView = driverNIC;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(nic) && !isNICValid(nic)) {
            driverNIC.setError(getString(R.string.error_invalid_nic));
            focusView = driverNIC;
            cancel = true;
        }

        // Check for a non-empty and a valid mobile number, if the user entered one.
        if(TextUtils.isEmpty(mobile)){
            driverMobile.setError(getString(R.string.error_field_required));
            focusView = driverMobile;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(mobile) && !isMobileValid(mobile)) {
            driverMobile.setError(getString(R.string.error_invalid_mobile));
            focusView = driverMobile;
            cancel = true;
        }

        // Check for a non-empty first name.
        if(TextUtils.isEmpty(fName)){
            driverFname.setError(getString(R.string.error_field_required));
            focusView = driverFname;
            cancel = true;
        }
        // Check for a non-empty last name.
        if(TextUtils.isEmpty(lName)){
            driverLname.setError(getString(R.string.error_field_required));
            focusView = driverLname;
            cancel = true;
        }

        // Check for a non-empty driving license number.
        if(TextUtils.isEmpty(license)){
            driverLicense.setError(getString(R.string.error_field_required));
            focusView = driverLicense;
            cancel = true;
        }
        // Check for a non-empty and a valid password, if the user entered one.
        if(TextUtils.isEmpty(password)){
            driverPassword.setError(getString(R.string.error_field_required));
            focusView = driverPassword;
            cancel = true;
        }

        else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            driverPassword.setError(getString(R.string.error_invalid_password));
            focusView = driverPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            driverEmail.setError(getString(R.string.error_field_required));
            focusView = driverEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            driverEmail.setError(getString(R.string.error_invalid_email));
            focusView = driverEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            dRegisterTask = new UserRegistrationTask(email, password,fName,lName,mobile,birthday,nic
                    ,gender,license);
            dRegisterTask.execute((Void) null);
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
        return mobile.length() < 11;
    }
    private boolean isNICValid(String nic) {
        //TODO: Replace this with your own logic
        return (nic.length() ==9);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserRegistrationTask extends AsyncTask<Void, Void, Boolean> {

        private final String dEmail;
        private final String dPassword;
        private final String dfirstName;
        private final String dLastName;
        private final String dMobile;
        private final String dBirthday;
        private final String dNIC;
        private final String dGender;
        private final String dLicenseNumber;


        UserRegistrationTask(String email, String password,String fName,String lName, String mobile,
                             String birthDate, String NIC, String gender, String licenseNo) {
            dEmail = email;
            dPassword = password;
            dfirstName =fName;
            dLastName =lName;
            dMobile =mobile;
            dBirthday = birthDate;
            dNIC = NIC;
            dGender =gender;
            dLicenseNumber=licenseNo;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            Boolean result=false;
            try {
                // Simulate network access.
                String url = "http://192.168.100.106:50000/api/driver/register";

                HttpHeaders headers= new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.add("Authorization",getB64Auth(mEmail,mPassword));

                JSONObject json = new JSONObject();
                json.put("email", dEmail);
                json.put("password", dPassword);
                json.put("first_name", dfirstName);
                json.put("last_name", dLastName);
                json.put("birthday", dBirthday);
                json.put("contact_number", dMobile);
                json.put("nic", dNIC);
                json.put("gender", dGender);
                json.put("license_number", dLicenseNumber);
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

        @Override
        protected void onPostExecute(final Boolean success) {
            dRegisterTask = null;

            //if the login validation is a success
            if (success) {
                Intent driverSuccessIntent = new Intent(RegistrationDriverActivity.this, MainActivity.class);
                RegistrationDriverActivity.this.startActivity(driverSuccessIntent);
                //finish();

                //if the login validation fails
            } else {

                Intent play2Intent = new Intent(RegistrationDriverActivity.this, MainActivity.class);
                RegistrationDriverActivity.this.startActivity(play2Intent);

            }
        }

        private String getB64Auth (String login, String pass) {
            String source=login+":"+pass;
            String ret="Basic "+ Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
            return ret;
        }

        @Override
        protected void onCancelled() {
            dRegisterTask = null;
            //showProgress(false);
        }
    }

}
