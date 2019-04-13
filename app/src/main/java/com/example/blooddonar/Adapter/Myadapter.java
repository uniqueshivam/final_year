package com.example.blooddonar.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blooddonar.Donor_profile_clicked;
import com.example.blooddonar.Model_list.ListItem_donor;
import com.example.blooddonar.R;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>  {
    private List<ListItem_donor> listDonors;
    private Context context;

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
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder holder, int position) {
        ListItem_donor listItem_donor = listDonors.get(position);
        holder.textView_donor_name.setText(listItem_donor.getName());
        holder.textView_donor_blood_group.setText(listItem_donor.getBlood_group());
        holder.textView_donor_postal_address.setText(listItem_donor.getPostal_address());
        holder.textView_distance.setText(listItem_donor.getDistance()+" KM");

    }

    @Override
    public int getItemCount() {
        return listDonors.size();
    }



    public static class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView_donor_name,textView_donor_blood_group,textView_donor_postal_address,textView_distance;

        List<ListItem_donor> listDonors = new ArrayList<ListItem_donor>();
        Context ctx;



        public ViewHolder(View itemView, Context ctx, List<ListItem_donor> listItems) {
            super(itemView);
            this.listDonors=listItems;
            this.ctx=ctx;
            itemView.setOnClickListener(this);

            textView_donor_name=(TextView)itemView.findViewById(R.id.donor_name);
            textView_donor_blood_group =(TextView)itemView.findViewById(R.id.donor_blood_group);
            textView_donor_postal_address =(TextView)itemView.findViewById(R.id.donor_address);
            textView_distance =(TextView)itemView.findViewById(R.id.donor_distance);

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
//            i.putExtra("agency service area",listItem.getAgency_service_area());
//            i.putExtra("agency id",listItem.getAgency_id());
            this.ctx.startActivity(i);


        }
    }
}
