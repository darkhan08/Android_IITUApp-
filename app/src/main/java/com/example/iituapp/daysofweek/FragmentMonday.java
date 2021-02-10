package com.example.iituapp.daysofweek;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iituapp.R;
import com.example.iituapp.timetable.TimeTable;
import com.example.iituapp.timetable.TimeTableAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class FragmentMonday extends Fragment {
   public static FragmentMonday getInstance(){
       FragmentMonday monday = new FragmentMonday();
       return monday;
   }
    ListView listView;
    TimeTableAdapter adapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    String id_email;
    private ArrayList<TimeTable> timeTableList ;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monday,container,false);
        listView  = view.findViewById(R.id.listView_monday);

        timeTableList = new ArrayList<>();


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Timetable");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        id_email=user.getEmail().substring(0,5);
        readData();

        return view;
    }

    private void readData() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot monday  = snapshot.child(id_email).child("Monday");
                if(monday!=null){
                List<String> keys = new ArrayList<>();
                for(DataSnapshot ker:monday.getChildren()){
                    keys.add(ker.getKey());
                    TimeTable timeTable = ker.getValue(TimeTable.class);
                    timeTableList.add(timeTable);
                }
            }
                adapter = new TimeTableAdapter(getContext(),timeTableList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
