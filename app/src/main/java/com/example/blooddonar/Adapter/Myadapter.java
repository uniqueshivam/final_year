package com.example.blooddonar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.example.blooddonar.Donor_profile_clicked;
import com.example.blooddonar.Model_list.ListItem_donor;
import com.example.blooddonar.Mysingleton;
import com.example.blooddonar.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>  {
    private List<ListItem_donor> listDonors;
    private Context context;
    String img_response;
    private String ROOT_URL_image="http://192.168.2.3/blood_donations/includes/sign_up/dp/";

    public Myadapter(List<ListItem_donor> listDonors, Context context) {
        this.listDonors = listDonors;
        this.context = context;
    }

    @NonNull
    @Override



    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listing_under_recycle,parent,false);
        return new ViewHolder(v,context,listDonors);
    }

    @Override
    public void onBindViewHolder(@NonNull final Myadapter.ViewHolder holder, int position) {
        ListItem_donor listItem_donor = listDonors.get(position);
        holder.textView_donor_name.setText(listItem_donor.getName());
        holder.textView_donor_blood_group.setText(listItem_donor.getBlood_group());
       // holder.textView_donor_postal_address.setText(listItem_donor.getPostal_address());
        holder.textView_distance.setText(listItem_donor.getDistance()+" KM");

        ImageRequest imageRequest = new ImageRequest( ROOT_URL_image+ listItem_donor.getEmail()+".jpg", new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                holder.donor_dp.setImageBitmap(response);

            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
        Mysingleton.getInstance(context).addToRequestQueue(imageRequest);


    }

    @Override
    public int getItemCount() {
        return listDonors.size();
    }



    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView_donor_name,textView_donor_blood_group,textView_donor_postal_address,textView_distance;
        private CircleImageView donor_dp;

        List<ListItem_donor> listDonors = new ArrayList<ListItem_donor>();
        Context ctx;


        public ViewHolder(View itemView, Context ctx, List<ListItem_donor> listItems) {
            super(itemView);
            this.listDonors=listItems;
            this.ctx=ctx;
            itemView.setOnClickListener(this);
            textView_donor_name=(TextView)itemView.findViewById(R.id.donor_name);
            textView_donor_blood_group =(TextView)itemView.findViewById(R.id.donor_blood_group);
           // textView_donor_postal_address =(TextView)itemView.findViewById(R.id.donor_address);
            textView_distance =(TextView)itemView.findViewById(R.id.donor_distance);
            donor_dp = itemView.findViewById(R.id.donor_dp);


        }

        @Override



        public void onClick(View view) {
            int position = getAdapterPosition();
            ListItem_donor listItem= this.listDonors.get(position);
            Intent i = new Intent(this.ctx, Donor_profile_clicked.class);
            i.putExtra("name",listItem.getName());
            i.putExtra("email",listItem.getEmail());
            i.putExtra("address",listItem.getPostal_address());
            i.putExtra("blood_group",listItem.getBlood_group());
            i.putExtra("distance",listItem.getDistance());
            i.putExtra("latitude",listItem.getLatitude());
            i.putExtra("longitude",listItem.getLongitude());
//            i.putExtra("agency service area",listItem.getAgency_service_area());
//            i.putExtra("agency id",listItem.getAgency_id());
            this.ctx.startActivity(i);


        }
    }
}
