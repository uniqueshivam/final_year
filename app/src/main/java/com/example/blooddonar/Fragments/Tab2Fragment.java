package com.example.blooddonar.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blooddonar.R;
import com.example.blooddonar.SharedPreference_for_currentLocation;
import com.example.blooddonar.ShraredPreference_UserLogin;
import com.example.blooddonar.constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tab2Fragment extends Fragment {
    TextView myprofile_name,myprofile_email,myprofile_bloodGroup,myprofile_phone,myprofile_location;
    Button update_my_location;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab2,container,false);
        myprofile_name =(TextView)view.findViewById(R.id.myprofile_name);
        myprofile_email =(TextView)view.findViewById(R.id.myprofile_email);
        myprofile_bloodGroup =(TextView)view.findViewById(R.id.myprofile_bloodGroup);
        myprofile_phone =(TextView)view.findViewById(R.id.myprofile_mobile);
        myprofile_location =(TextView)view.findViewById(R.id.myprofile_registeredLocation);
        update_my_location =(Button)view.findViewById(R.id.update_my_Location);

        myprofile_name.setText(ShraredPreference_UserLogin.getInstance(getActivity()).get_user_name());
        myprofile_email.setText(ShraredPreference_UserLogin.getInstance(getActivity()).get_user_email());
        myprofile_bloodGroup.setText(ShraredPreference_UserLogin.getInstance(getActivity()).get_user_blood_group());
        myprofile_phone.setText(ShraredPreference_UserLogin.getInstance(getActivity()).get_user_mobile());
        myprofile_location.setText(ShraredPreference_UserLogin.getInstance(getActivity()).get_user_postal_address());
        update_my_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update_location();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Tab2Fragment.this).attach(Tab2Fragment.this).commit();

            }
        });

        return  view;
    }

    void update_location()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.UPDATE_MY_LOCATION, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    // acc_dp.setImageResource(0);
                    // acc_dp.setVisibility(View.GONE);
                    Toast.makeText(getActivity(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();
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

                     //   Toast.makeText(getActivity(),error.getMessage(),Toast.LENGTH_LONG);

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", ShraredPreference_UserLogin.getInstance(getActivity()).get_user_email());
                params.put("latitude", SharedPreference_for_currentLocation.getInstance(getActivity()).get_user_latitude());
                params.put("longitude", SharedPreference_for_currentLocation.getInstance(getActivity()).get_user_longitude());
                params.put("postal_address", SharedPreference_for_currentLocation.getInstance(getActivity()).get_user_postal_address());

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
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}
