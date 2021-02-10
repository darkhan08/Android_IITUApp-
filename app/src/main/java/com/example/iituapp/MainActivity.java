package com.example.iituapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iituapp.contacts.ContactsFragment;
import com.example.iituapp.daysofweek.FragmentMonday;
import com.example.iituapp.message.MessageFragment;
import com.example.iituapp.profile.ProfileFragment;
import com.example.iituapp.systemcalendar.SystemCalendar;
import com.example.iituapp.systemcalendar.SystemCalendarFragment;
import com.example.iituapp.timetable.TimeTable;
import com.example.iituapp.timetable.TimetaleFragment;
import com.example.iituapp.transcription.TranscriptionFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private TextView names,ids;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public String id_email;
    public Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);



        // get current user and db
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        id_email=user.getEmail().substring(0,5);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Profile");
        mAuth = FirebaseAuth.getInstance();
        getData();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        bundle = new Bundle();
        bundle.putString("id",id_email);

        if(savedInstanceState ==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
        }


    }



    private void getData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name  = snapshot.child(id_email).child("fullname").getValue().toString();
                String ids = snapshot.child(id_email).getKey();
                getHeader(name,ids);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getHeader(String name,String id) {
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        View headerview = navigationView.getHeaderView(0);
        names = headerview.findViewById(R.id.name_studentss);
        ids = headerview.findViewById(R.id.id_studentss);
        names.setText(name);
        ids.setText(id);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.messages:
                MessageFragment fragment1 = new MessageFragment();
                fragment1.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment1).commit();
                drawer.closeDrawers();
                break;
            case R.id.transcription:
                TranscriptionFragment fragment = new TranscriptionFragment();
                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                drawer.closeDrawers();
                break;
            case R.id.timetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TimetaleFragment()).commit();
                drawer.closeDrawers();
                break;
            case R.id.system_calendar:
                SystemCalendarFragment calendar = new SystemCalendarFragment();
                calendar.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, calendar).commit();
                drawer.closeDrawers();
                break;
            case R.id.contacts:
                ContactsFragment contacts = new ContactsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,contacts).commit();
                drawer.closeDrawers();

                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
    public void openProfile(View v){
        if(v.getId()==R.id.header) getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

    }



}
