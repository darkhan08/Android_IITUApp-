package com.example.iituapp.systemcalendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iituapp.R;
import com.example.iituapp.transcription.Transcription;
import com.example.iituapp.transcription.TranscriptionListviewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SystemCalendarFragment extends Fragment {
    private ListView listView;
    private SystemCalendarAdapter myadapter;
    private ArrayList<SystemCalendar> arrayList;
    private Spinner spinner;
    public String id_email;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    public String[] term = new String[2];
    ArrayList<ArrayList<SystemCalendar>> arr ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_system_calendar,container,false);
        listView = view.findViewById(R.id.listView_systemCalendar);
        spinner = view.findViewById(R.id.spinner2);
        id_email = getArguments().getString("id");


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("SystemCalendar");

        getData();
        AddSpinner();
        return view;
    }

    private void AddSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        listView.setAdapter(null);
                        if(listView.getAdapter()==null) {
                            myadapter = new SystemCalendarAdapter(getContext(), arr.get(0));
                            listView.setAdapter(myadapter);
                        }
                        break;
                    case 1:
                        listView.setAdapter(null);
                        if(listView.getAdapter()==null) {
                            myadapter = new SystemCalendarAdapter(getContext(), arr.get(1));
                            listView.setAdapter(myadapter);
                        }
                        break;
                    default:
                        listView.setAdapter(null);
                        if(listView.getAdapter()==null) {
                            myadapter = new SystemCalendarAdapter(getContext(), arr.get(0));
                            listView.setAdapter(myadapter);
                        }
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getData() {
        term[0] = "2020-2021 Fall";
        term[1] = "2020-2021 Spring";
        arr = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(int i = 0;i<term.length;i++) {
                    arrayList = new ArrayList<>();
                    DataSnapshot ds = snapshot.child(id_email).child(term[i]);
                    if (ds != null) {
                        List<String> keys = new ArrayList<>();
                        for (DataSnapshot ker : ds.getChildren()) {
                            keys.add(ker.getKey());
                            SystemCalendar transcription = ker.getValue(SystemCalendar.class);
                            arrayList.add(transcription);
                        }
                    }
                    arr.add(arrayList);

                }
                Log.d("@@##", "  ---------------     "+ arr.size());
                if(arr.size()>0)
                    spinnerHandler();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void spinnerHandler() {
        ArrayAdapter<String> adapter =new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,term);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
