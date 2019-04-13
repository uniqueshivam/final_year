package com.example.blooddonar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Donor_profile_clicked extends AppCompatActivity {
    TextView name,mail,blood_group,address,distance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile_clicked);
        name =(TextView)findViewById(R.id.profile_donor_name);
        mail =(TextView)findViewById(R.id.profile_donor_email);
        blood_group =(TextView)findViewById(R.id.profile_donor_bloodGroup);
        address =(TextView)findViewById(R.id.profile_donor_address);
        distance =(TextView)findViewById(R.id.profile_donor_distance);


        Intent intent = getIntent();
        String clicked_intent_name = intent.getStringExtra("name");
        String clicked_intent_email = intent.getStringExtra("email");
        String clicked_intent_bloodGroup = intent.getStringExtra("blood_group");
        String clicked_intent_address = intent.getStringExtra("address");
        String clicked_intent_distance = intent.getStringExtra("distance");

        name.setText(clicked_intent_name);
        mail.setText(clicked_intent_email);
        blood_group.setText(clicked_intent_bloodGroup);
        address.setText(clicked_intent_address);
        distance.setText(clicked_intent_distance);
    }
}
