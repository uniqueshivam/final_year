package com.example.blooddonar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText first,second;
    Button login;
    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        first =(EditText) findViewById(R.id.editText);
        second = (EditText) findViewById(R.id.editText2);
        login =(Button) findViewById(R.id.button2_login);
        back_button=(ImageView) findViewById(R.id.imageView3);
        login.setEnabled(false);
        first.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                first.setCursorVisible(true);
                return false;
            }
        });

        second.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                second.setCursorVisible(true);
                return false;
            }
        });

        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().trim().length()==0)
                {
                    login.setEnabled(false);
                }
                else if(s.toString().trim().length()!=0 && first.getText().toString().trim().length()!=0)
                {
                    login.setEnabled(true);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Home_Info.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    void login()
    {
        final String get_email =first.getText().toString().trim();
        final String get_password = second.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.SIGN_IN_user, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    if(!jsonObject.getBoolean("error"))
                    {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                        ShraredPreference_UserLogin.getInstance(getApplicationContext()).userlogin(jsonObject.getString("email"),
                                jsonObject.getString("name"), jsonObject.getString("mobile"),
                                jsonObject.getString("blood_group"), jsonObject.getString("postal_address"),jsonObject.getString("latitude"),jsonObject.getString("longitude")
                                // jsonObject.getString("agency_rating")
                        );
                         Intent intent = new Intent(MainActivity.this,Donor_list_container.class);
                       startActivity(intent);
                           MainActivity.this.finish();

                    }
                    else if(jsonObject.getBoolean("error"))
                    {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> params = new HashMap<>();
                params.put("email",get_email);
                params.put("password",get_password);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
