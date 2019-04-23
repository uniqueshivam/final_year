package com.example.blooddonar.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class Tab1Fragment extends Fragment implements AdapterView.OnItemSelectedListener {

    TextView your_name,your_location;
    EditText distance_input;
    ImageView search_by_distance;
    boolean clicked=false;
    Spinner drop_down;
    int spinner_position;
    SwipeRefreshLayout mswipeRefreshLayout;

    String get_distance_by_input="";
    String  blood_group_choosen="";
    String temp="";
    android.support.v7.widget.Toolbar spinner_search_layout;




    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem_donor> listing;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);

//        spinner_search_layout = view.findViewById(R.id.spinner_search_layout);
        drop_down =view.findViewById(R.id.drop_down);
        recyclerView=(RecyclerView)view.findViewById(R.id.donor_recycle_view);
        recyclerView.setHasFixedSize(true);


        distance_input= view.findViewById(R.id.distance_input);
        search_by_distance = view.findViewById(R.id.search_by_distance);
        mswipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listing=new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.DropDown,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drop_down.setAdapter(adapter);
        drop_down.setOnItemSelectedListener(this);



        search_by_distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 temp = distance_input.getText().toString().trim();
                if(distance_input.getText().toString().trim() !=null) {
                    clicked=true;
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                    loadDataByInput();

                }


            }
        });
        if(clicked==false && spinner_position==0) {
            loadRecyclerviewData();
        }




        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!TextUtils.isEmpty(temp) && spinner_position>0) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                   loadDataByBloodGroup_distance();
                }
                if(!TextUtils.isEmpty(temp))
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                    loadDataByInput();
                }

                if(spinner_position ==0)
                {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                   // loadRecyclerviewData();
                }
               // loadRecyclerviewData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mswipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });



//        recyclerView.setOnScrollListener(new HidingScrollListner() {
//            @Override
//            public void onHide() {
//                hideViews();
//            }
//
//            @Override
//            public void onShow() {
//
//                showViews();
//            }
//        });

        return view;

    }
//
////    private void hideViews() {
////
////        spinner_search_layout.animate().translationY(-spinner_search_layout.getHeight()).setInterpolator(new AccelerateInterpolator(2));
////
////
////
////    }
////    private void showViews(){
////
////        spinner_search_layout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
//
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

             blood_group_choosen = adapterView.getItemAtPosition(i).toString();
             spinner_position=i;

            if(i>1 && TextUtils.isEmpty(temp))
            {
                Toast.makeText(getActivity(), blood_group_choosen, Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                loadDataByBloodGroup();
                drop_down.setSelection(0);

            }
            if(i==1 &&TextUtils.isEmpty(temp))
            {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                loadRecyclerviewData();
                drop_down.setSelection(0);
            }
             if(i==1 && !TextUtils.isEmpty(temp))
            {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                loadDataByInput();
                drop_down.setSelection(0);
                distance_input.setText("");

            }

            if(i >1 && !TextUtils.isEmpty(temp))
            {
                Toast.makeText(getActivity(), "see", Toast.LENGTH_SHORT).show();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(Tab1Fragment.this).attach(Tab1Fragment.this).commit();
                loadDataByBloodGroup_distance();
                drop_down.setSelection(0);
                distance_input.setText("");

            }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                                o.getString("distance"),o.getString("latitude"),o.getString("longitude"),o.getString("gender"),o.getString("age"));
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


    public void loadDataByInput()
    {

         final Double result_input = (Double.parseDouble(temp))*0.621371;



        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.DONOR_LISTING_BY_INPUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response","hii");
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem_donor obj = new ListItem_donor(o.getString("email"),o.getString("name"),o.getString("mobile"),o.getString("blood_group"),o.getString("postal_address"),
                                o.getString("distance"),o.getString("latitude"),o.getString("longitude"),o.getString("gender"),o.getString("age"));
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
                params.put("distance",result_input.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }



    public void loadDataByBloodGroup()
    {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.DONOR_LISTING_BY_BLOOD_GROUP, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response","hii");
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem_donor obj = new ListItem_donor(o.getString("email"),o.getString("name"),o.getString("mobile"),o.getString("blood_group"),o.getString("postal_address"),
                                o.getString("distance"),o.getString("latitude"),o.getString("longitude"),o.getString("gender"),o.getString("age"));
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
                params.put("blood_group",blood_group_choosen);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }

    public void loadDataByBloodGroup_distance()
    {
        final Double result_input = (Double.parseDouble(temp))*0.621371;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, constants.DONOR_LISTING_BY_BLOOD_AND_DISTANCE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("in response","hii");
                try {

                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject o = jsonArray.getJSONObject(i);
                        ListItem_donor obj = new ListItem_donor(o.getString("email"),o.getString("name"),o.getString("mobile"),o.getString("blood_group"),o.getString("postal_address"),
                                o.getString("distance"),o.getString("latitude"),o.getString("longitude"),o.getString("gender"),o.getString("age"));
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
                params.put("blood_group",blood_group_choosen);
                params.put("distance",result_input.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }



}
