package com.example.iituapp.timetable;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.iituapp.R;
import com.example.iituapp.ViewPagerAdapter;
import com.example.iituapp.daysofweek.FragmentFriday;
import com.example.iituapp.daysofweek.FragmentMonday;
import com.example.iituapp.daysofweek.FragmentSaturday;
import com.example.iituapp.daysofweek.FragmentThursday;
import com.example.iituapp.daysofweek.FragmentTuesday;
import com.example.iituapp.daysofweek.FragmentWednesday;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class TimetaleFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable,container,false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        getTabs();

        return view;
    }

    public void getTabs(){
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        new Handler().post(new Runnable() {
            @Override
            public void run() {
        viewPagerAdapter.addFragment(FragmentMonday.getInstance(),"Monday");
        viewPagerAdapter.addFragment(FragmentTuesday.getInstance(),"Tuesday");
        viewPagerAdapter.addFragment(FragmentWednesday.getInstance(),"Wednesday");
        viewPagerAdapter.addFragment(FragmentThursday.getInstance(),"Thursday");
        viewPagerAdapter.addFragment(FragmentFriday.getInstance(),"Friday");
        viewPagerAdapter.addFragment(FragmentSaturday.getInstance(),"Saturday");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
}
