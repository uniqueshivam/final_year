package com.example.blooddonar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Donor_profile_clicked extends AppCompatActivity {
    TextView name,mail,blood_group,address,distance;
    Button get_route;
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


        final Intent intent = getIntent();
        String clicked_intent_name = intent.getStringExtra("name");
        String clicked_intent_email = intent.getStringExtra("email");
        String clicked_intent_bloodGroup = intent.getStringExtra("blood_group");
        String clicked_intent_address = intent.getStringExtra("address");
        String clicked_intent_distance = intent.getStringExtra("distance");
        String clicked_intent_latitude = intent.getStringExtra("latitude");
        String clicked_intent_longitude = intent.getStringExtra("longitude");

        name.setText(clicked_intent_name);
        mail.setText(clicked_intent_email);
        blood_group.setText(clicked_intent_bloodGroup);
        address.setText(clicked_intent_address);
        distance.setText(clicked_intent_distance);


         latitude = Double.parseDouble(clicked_intent_latitude);
         longitude = Double.parseDouble(clicked_intent_longitude);




        get_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String label = "Donor finding";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
                Uri uri = Uri.parse(uriString);
                Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
                startActivity(mapIntent);
            }
        });
    }
}
