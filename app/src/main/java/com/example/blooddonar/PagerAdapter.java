package com.example.blooddonar;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.blooddonar.Fragments.Tab1Fragment;
import com.example.blooddonar.Fragments.Tab2Fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int NoofTabs;
    private Context context;

    public PagerAdapter(FragmentManager fm,int NoofTabs)
    {
        super(fm);
       this.NoofTabs=NoofTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch(position)
        {
            case 0:
               // Donor_list donor_list = new Donor_list();
               // return donor_list;
                Tab1Fragment tab1Fragment = new Tab1Fragment();
                return  tab1Fragment;

            case 1:
               // M//y_Profile my_profile = new My_Profile();
               // return  my_profile;
                Tab2Fragment tab2Fragment = new Tab2Fragment();
                return  tab2Fragment;



                default:
                    return null;
        }

    }

    @Override
    public int getCount() {
        return NoofTabs;
    }


}
