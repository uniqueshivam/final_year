package com.example.blooddonar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;



public class signup extends AppCompatActivity {
    ImageView imageView;
    EditText email, password, name, mobile, blood_group;
    Button submit;
    ProgressDialog sign_up;

    static final int REQUEST_LOCATION = 1;
    double lattitude;
    double longitude;

    private LocationManager locationManager;
    private LocationListener locationListener;
    EditText postal_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_signup);
        imageView = (ImageView) findViewById(R.id.imageView4_back);
        postal_address = (EditText) findViewById(R.id.editText9_postal_address);
        email = (EditText) findViewById(R.id.editText3_email);
        password = (EditText) findViewById(R.id.editText4_paswrd);
        name = (EditText) findViewById(R.id.editText5_name);
        mobile = (EditText) findViewById(R.id.editText7_mobile);
        blood_group = (EditText) findViewById(R.id.editText8_blood_group);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        submit =(Button) findViewById(R.id.button_submit);
        sign_up=new ProgressDialog(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this, Home_Info.class);
                startActivity(intent);
            }
        });
        getlocation();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_user();
            }
        });

    }


    void getlocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            if (location != null) {
                lattitude = location.getLatitude();
                longitude = location.getLongitude();
              //  Log.d("value",String.valueOf(lattitude));
                // postal_address.setText(String.valueOf(lattitude));


                getCompleteAddressString(lattitude, longitude);
//

            } else {
                postal_address.setText("error lo");
//                Log.i("error lattitude","error in lattitude");
//                Log.i("error longi","eror in longitude");
            }
        }

    }

    private String getCompleteAddressString(double lattitude, double longitude) {

        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lattitude, longitude, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
//                Log.w("My Current loction address", strReturnedAddress.toString());
                postal_address.setText(strReturnedAddress.toString());
            } else {
//                Log.w("My Current loction address", "No Address returned!");
                postal_address.setText("No address");
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.w("My Current loction address", "Canont get Address!");
            postal_address.setText("Canont get Address!");
        }
        return strAdd;

    }

     void register_user() {
        final String get_email = email.getText().toString().trim();
        final String get_password = password.getText().toString().trim();
        final String get_name = name.getText().toString().trim();
        final String get_mobile = mobile.getText().toString().trim();
        final String get_blood_group = blood_group.getText().toString().trim();
        final String get_postal_address = postal_address.getText().toString().trim();

         sign_up.setMessage("Hold On ..");
         sign_up.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.REGISTER_USER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                     sign_up.dismiss();
                    // acc_dp.setImageResource(0);
                    // acc_dp.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    //   startActivity(new Intent(getActivity().getApplicationContext(),Home.class));
                    //getActivity().finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                         sign_up.dismiss();
                         Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", get_email);
                params.put("password", get_password);
                params.put("name", get_name);
                params.put("mobile", get_mobile);
                params.put("blood_group", get_blood_group);
                params.put("latitude", String.valueOf(lattitude));
                params.put("longitude", String.valueOf(longitude));
                params.put("postal_address", get_postal_address);
//                params.put("photo_id",getagency_id);
//                params.put("latitude",);
//
//                params.put("skill_wage",getwagesfor_skilled);
//                params.put("noskilled_wage",getwagesfor_semiskilled);
                return params;
            }
        };
        //requesthandler.getInstance(getActivity()).addToRequestQueue(stringRequest);
        // Mysingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
