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

public class FragmentWednesday extends Fragment {
    public static FragmentWednesday getInstance() {
        return new FragmentWednesday();
    }

    ListView listView;
    ArrayList<TimeTable> list;
    TimeTableAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wednesday,container,false);

        listView  = view.findViewById(R.id.listView_wednesday);
        FillTimeTable();
        adapter = new TimeTableAdapter(getContext(),list);
        listView.setAdapter(adapter);
        System.out.println("  " +list.get(0).getName_of_class() );

        return view;
    }
    public void FillTimeTable(){
        list = new ArrayList<>();
        list.add(new TimeTable("Theory","15:00 15:50","Graph Theory","E117","Aisha Yershigova"));
        list.add(new TimeTable("Theory","16:00 16:50","Advanced Algorithms","VR 20","Azamat Serek"));
        list.add(new TimeTable("Theory","17:00 17:50","Advanced Algorithms","VR 20","Azamat Serek"));
    }
}
