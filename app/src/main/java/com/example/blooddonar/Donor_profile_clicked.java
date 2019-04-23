package com.example.blooddonar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import de.hdodenhof.circleimageview.CircleImageView;

public class Donor_profile_clicked extends AppCompatActivity {
    TextView name,mail,blood_group,address,distance,age,gender;
    ImageView get_route;
    CircleImageView profile_image;
    double latitude,longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile_clicked);
        name =(TextView)findViewById(R.id.profile_donor_name);
        mail =(TextView)findViewById(R.id.profile_donor_email);
        blood_group =(TextView)findViewById(R.id.profile_donor_bloodGroup);
        address =(TextView)findViewById(R.id.profile_donor_address);
        distance =(TextView)findViewById(R.id.profile_donor_distance);
        get_route = findViewById(R.id.get_route);
        profile_image = findViewById(R.id.profile_image);
        age = findViewById(R.id.age);
        gender= findViewById(R.id.profile_donor_gender);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CONSOLAB.TTF");
        Typeface typeface_gothic = Typeface.createFromAsset(getAssets(), "fonts/Century Gothic.ttf");

        final Intent intent = getIntent();
        String clicked_intent_name = intent.getStringExtra("name");
        String clicked_intent_email = intent.getStringExtra("email");
        String clicked_intent_bloodGroup = intent.getStringExtra("blood_group");
        String clicked_intent_address = intent.getStringExtra("address");
        String clicked_intent_distance = intent.getStringExtra("distance");
        String clicked_intent_latitude = intent.getStringExtra("latitude");
        String clicked_intent_longitude = intent.getStringExtra("longitude");
        String clicked_intent_sex = intent.getStringExtra("gender");
        String clicked_intent_age = intent.getStringExtra("age");
        name.setTypeface(typeface);
        mail.setTypeface(typeface_gothic);
        blood_group.setTypeface(typeface);
        address.setTypeface(typeface_gothic);
        gender.setTypeface(typeface_gothic);

        name.setText(clicked_intent_name+",");
        mail.setText(clicked_intent_email);
        blood_group.setText(clicked_intent_bloodGroup);
        address.setText(clicked_intent_address);
        age.setText(clicked_intent_age);
        distance.setText(clicked_intent_distance);
        gender.setText(clicked_intent_sex);

        ImageRequest imageRequest = new ImageRequest( constants.IMAGE_DOWNLOAD+ clicked_intent_email+".jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                profile_image.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
        Mysingleton.getInstance(getApplicationContext()).addToRequestQueue(imageRequest);



         latitude = Double.parseDouble(clicked_intent_latitude);
         longitude = Double.parseDouble(clicked_intent_longitude);




        get_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+latitude+","+longitude));
                startActivity(intent);
            }
        });
    }
}
