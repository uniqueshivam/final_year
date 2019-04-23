package com.example.blooddonar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class signup extends AppCompatActivity {
    ImageView imageView,choose;
    EditText email, password, name, mobile, age;
    Button submit;
    ProgressDialog sign_up;
    Spinner drop_down_blood_group;
    Spinner drop_down_gender;

    CircleImageView acc_dp;

    static final int REQUEST_LOCATION = 1;
    double lattitude;
    double longitude;
    String blood_group_choosen;
    String gender_choosen;

    private LocationManager locationManager;
    private LocationListener locationListener;
    EditText postal_address;

    private final int IMG_REQUEST =1;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_signup);
        imageView = (ImageView) findViewById(R.id.imageView4_back);
        choose = findViewById(R.id.choose);
        acc_dp = findViewById(R.id.acc_dp);
        postal_address = (EditText) findViewById(R.id.editText9_postal_address);
        email = (EditText) findViewById(R.id.editText3_email);
        password = (EditText) findViewById(R.id.editText4_paswrd);
        name = (EditText) findViewById(R.id.editText5_name);
        mobile = (EditText) findViewById(R.id.editText7_mobile);
        age = (EditText) findViewById(R.id.editText8_age);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        submit =(Button) findViewById(R.id.button_submit);
        sign_up=new ProgressDialog(this);
        drop_down_blood_group=findViewById(R.id.drop_down_blood_group);
        drop_down_gender =findViewById(R.id.drop_down_gender);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.DropDown_blood_group,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down_blood_group.setAdapter(adapter);
        drop_down_blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                blood_group_choosen = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter_gender = ArrayAdapter.createFromResource(this,R.array.DropDown_gender,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down_gender.setAdapter(adapter_gender);

        drop_down_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender_choosen = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


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

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMG_REQUEST);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                acc_dp.setImageBitmap(bitmap);
                acc_dp.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] byteImg = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteImg,Base64.DEFAULT);
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
        final String get_age = age.getText().toString().trim();
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
                params.put("age", get_age);
                params.put("latitude", String.valueOf(lattitude));
                params.put("longitude", String.valueOf(longitude));
                params.put("postal_address", get_postal_address);
                params.put("blood_group",blood_group_choosen);
                params.put("gender",gender_choosen);
                params.put("image",imageToString(bitmap));
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
