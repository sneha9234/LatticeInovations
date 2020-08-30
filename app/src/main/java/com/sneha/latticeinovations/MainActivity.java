package com.sneha.latticeinovations;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity{

    EditText name, address, email, phone, password, location;
    Button btn_signup;
    int PLACE_PICKER_REQUEST=1;
    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        location = findViewById(R.id.location);
        btn_signup = findViewById(R.id.btn_signup);
        password = findViewById(R.id.password);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        name.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        address.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        phone.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        email.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        location.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        password.addTextChangedListener (new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2){
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                Intent intent;
                try {
                    intent= builder.build(MainActivity.this);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean l = isPhone(phone.getText().toString().trim());
                    boolean m = isEmail(email.getText().toString().trim());
                    boolean n = isPass(password.getText().toString().trim());
                    if(!m) {
                        Toast.makeText(MainActivity.this, "Please enter a valid email id", Toast.LENGTH_LONG).show();
                    }
                    else if(!n){
                         Toast.makeText(MainActivity.this, "Please enter a valid phone number", Toast.LENGTH_LONG).show();
                    }
                    else if(!l){
                        Toast.makeText(MainActivity.this, "Please enter a valid phone number", Toast.LENGTH_LONG).show();
                    }
                    else if(address.getText().toString().length()<10){
                        Toast.makeText(MainActivity.this, "Address should at least be 10 characters", Toast.LENGTH_LONG).show();
                    }
                    else if(name.getText().toString().length()<4){
                        Toast.makeText(MainActivity.this, "Name should at least be 4 characters", Toast.LENGTH_LONG).show();
                    }
                    else {
                        String nam = name.getText().toString();
                        String add = address.getText().toString();
                        String em = email.getText().toString();
                        String ph = phone.getText().toString();
                        String pass = password.getText().toString();
                        String loc = location.getText().toString();
                        performSignUp(nam,add,em,ph,pass,loc);
                    }

            }
        });


    }

    private void performSignUp(String nam, String add, String em, String ph, String pass, String loc) {

        Call<user> call = MainActivity.apiInterface.performRegistration(nam, add, em, ph, pass ,loc);

        call.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                if(response.body().getResponse().equals("successful")) {
                    Intent intent = new Intent(MainActivity.this, table_contents.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check your network connection.", Toast.LENGTH_LONG).show();
            }
        });

    }

    public static boolean isEmail(String text) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isPass(String text) {
        String expression ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
        Pattern p = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    public static boolean isPhone(String text) {
        if(text.length()==10){
            return TextUtils.isDigitsOnly(text);
        } else{
            return false;
        }
    }


    private void checkRequiredFields() {
        if (!name.getText().toString().isEmpty() && !address.getText().toString().isEmpty()&& !email.getText().toString().isEmpty()
                && !phone.getText().toString().isEmpty()&& !password.getText().toString().isEmpty()&& !location.getText().toString().isEmpty()) {
            btn_signup.setVisibility(View.VISIBLE);
        } else {
            btn_signup.setVisibility(View.GONE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

                Place place = PlacePicker.getPlace(MainActivity.this,data);

                double lat = place.getLatLng().latitude;
                double lon = place.getLatLng().longitude;

                try {
                    Geocoder geo = new Geocoder(MainActivity.this.getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(lat, lon, 1);
                    if (addresses.isEmpty()) {
                        location.setText("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            location.setText(addresses.get(0).getFeatureName() + ", " + addresses.get(0).getLocality());
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}