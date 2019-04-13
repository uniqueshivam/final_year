package com.example.blooddonar.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.blooddonar.Adapter.Myadapter;
import com.example.blooddonar.Model_list.ListItem_donor;
import com.example.blooddonar.R;
import com.example.blooddonar.SharedPreference_for_currentLocation;
import com.example.blooddonar.constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tab1Fragment extends Fragment {

    TextView your_name,your_location;


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem_donor> listing;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);





        recyclerView=(RecyclerView)view.findViewById(R.id.donor_recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listing=new ArrayList<>();
        loadRecyclerviewData();
        return view;

    }


    private void loadRecyclerviewData() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.DONOR_LISTING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response","hii");
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem_donor obj = new ListItem_donor(o.getString("email"),o.getString("name"),o.getString("mobile"),o.getString("blood_group"),o.getString("postal_address"),
                                o.getString("distance"));
                        listing.add(obj);

                    }adapter= new Myadapter(listing,getActivity().getApplicationContext());
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "No donors found nearby", Toast.LENGTH_SHORT).show();

            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               params.put("latitude", SharedPreference_for_currentLocation.getInstance(getActivity()).get_user_latitude());
               params.put("longitude",SharedPreference_for_currentLocation.getInstance(getActivity()).get_user_longitude());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }

}
