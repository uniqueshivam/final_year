package com.example.blooddonar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Home_Info extends AppCompatActivity {
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_home__info);
        if(new ShraredPreference_UserLogin(this).isUserLogedin())
        {
            Intent intent = new Intent(Home_Info.this,Donor_list_container.class);
            startActivity(intent);
        }
        login = (Button) findViewById(R.id.button4_login);
        signup =(Button) findViewById(R.id.button_signup);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Info.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home_Info.this,signup.class);
                startActivity(intent);

            }
        });
    }
}
