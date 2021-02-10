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

import com.example.iituapp.MainActivity;
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

public class FragmentSaturday extends Fragment {
    public static FragmentSaturday getInstance() {
        return new FragmentSaturday();
    }

    ListView listView;
    ArrayList<TimeTable> list;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private String id_email;
    TimeTableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saturday, container, false);
        listView = view.findViewById(R.id.listView_saturday);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Timetable");
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        id_email=user.getEmail().substring(0,5);
        FillTimeTable();

        //System.out.println("      " + list.get(0).getName_of_class());

        return view;
    }

    public void FillTimeTable() {
        list = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot Saturday  = snapshot.child(id_email).child("Saturday");
                if(Saturday!=null){
                    List<String> keys = new ArrayList<>();
                    for(DataSnapshot ker:Saturday.getChildren()){
                        keys.add(ker.getKey());
                        TimeTable timeTable = ker.getValue(TimeTable.class);
                        list.add(timeTable);
                        Log.d("$$$@", ":size of timeTableList1  " + timeTable.getName_of_class());
                    }
                }
                adapter= new TimeTableAdapter(getContext(), list);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
