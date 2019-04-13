package com.example.blooddonar;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

    private static Mysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;
    private Mysingleton(Context context){
        // Specify the application context
        mctx = context;
        // Get the request queue
        requestQueue= getRequestQueue();
    }

    public static synchronized Mysingleton getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mInstance == null){
            mInstance = new Mysingleton(context);
        }
        // Return MySingleton new Instance
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        // If RequestQueue is null the initialize new RequestQueue
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }

        // Return RequestQueue
        return requestQueue;
    }

    public<T> void addToRequestQueue(Request<T> request){
        // Add the specified request to the request queue
        getRequestQueue().add(request);
    }
}
