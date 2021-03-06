package com.example.hsenid.taxiapp.driver;

import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hsenid.taxiapp.DialogBoxActivity;
import com.example.hsenid.taxiapp.PasswordUpdate;
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

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class DriverActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> ,DialogBoxActivity.DialogListener {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String driverUpdateUrl = "http://192.168.100.106:50000/api/driver/updatepassword";


    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String TAG = "DriverActivity";

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;
    private PasswordUpdate updatepwTask = null;


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    Button driverRegisterAccessButton;

    //shared preference references
    SharedPreferences sharedpreferences;
    public static final String mypreference = "loginpref";
    public static final String Password = "pwKey";
    public static final String Email = "emailKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_driver);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password_driver);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //initialize shared preferences
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Password)) {
            mPasswordView.setText(sharedpreferences.getString(Password, ""));
        }
        if (sharedpreferences.contains(Email)) {
            mEmailView.setText(sharedpreferences.getString(Email, ""));
        }

        //save in the shared preference

        //on the click of the sign-in button
        Button mEmailSignInButton = (Button) findViewById(R.id.sign_in_button_driver);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                Save(view);
            }
        });

        //on the click of the register button
        driverRegisterAccessButton = (Button) findViewById(R.id.register_button_driver);
        driverRegisterAccessButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registrationIntent = new Intent(DriverActivity.this, RegistrationDriverActivity.class);
                DriverActivity.this.startActivity(registrationIntent);
            }
        });

        //passenger password reset function
        TextView pwresetDialogDriver= (TextView) findViewById(R.id.infoTxtCredits_driver);
        pwresetDialogDriver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openDialog();
            }
        });
        mLoginFormView = findViewById(R.id.login_form_driver);
    }


    public void Save(View view) {
       String n = mPasswordView.getText().toString();
        String e = mEmailView.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Password, n);
        editor.putString(Email, e);
        editor.commit();

        String s=sharedpreferences.getString(Password,null);
        Log.e(TAG,"Password Shared Prefs"+s);
    }

    public void openDialog(){
        DialogBoxActivity pwUpdateDialogDriver = new DialogBoxActivity();
        pwUpdateDialogDriver.show(getSupportFragmentManager(),"Update Password");
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if(TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // perform the user login attempt.
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
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

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(DriverActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    public void gotoDriverRegistrationPage(View view) {
        Intent driverPage = new Intent(DriverActivity.this, RegistrationDriverActivity.class);
        startActivity(driverPage);
    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //authenticate to the core-server
            Boolean result=false;
            try {
                // Simulate network access.
                String url = "http://192.168.100.106:50000/api/driver/login";

                HttpHeaders headers= new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                //headers.add("Authorization",getB64Auth(mEmail,mPassword));

                JSONObject json = new JSONObject();
                json.put("email", mEmail);
                json.put("password", mPassword);
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
            } catch (InterruptedException e) {
                return false;
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                Intent driverLoginIntent = new Intent(DriverActivity.this, DriverHomePageActivity.class);
                DriverActivity.this.startActivity(driverLoginIntent);
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    @Override
    public void updatePassword(String email, String currentPw, String newPw) {

        updatepwTask = new PasswordUpdate(this,email, currentPw,newPw,driverUpdateUrl);
        Boolean updateStatus= null;

            updatepwTask.execute();
    }
}