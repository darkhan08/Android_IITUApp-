package com.example.iituapp.daysofweek;

import android.os.Bundle;
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

import java.util.ArrayList;

public class FragmentThursday extends Fragment {
    public static FragmentThursday getInstance() {
        return new FragmentThursday();
    }
    ListView listView;
    ArrayList<TimeTable> list;
    TimeTableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thursday,container,false);

        listView  = view.findViewById(R.id.listView_thursday);
        FillTimeTable();
        adapter = new TimeTableAdapter(getContext(),list);
        listView.setAdapter(adapter);

        return view;
    }
    public void FillTimeTable(){
        list = new ArrayList<>();

    }
}
