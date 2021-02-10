package com.example.iituapp.systemcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iituapp.R;

import java.util.ArrayList;

public class SystemCalendarAdapter extends ArrayAdapter<SystemCalendar> {
    ArrayList<SystemCalendar> arrayList;
    private TextView number,event;
    private TextView startTime,endTime,status;
    public SystemCalendarAdapter(@NonNull Context context,ArrayList<SystemCalendar> arrayList ) {
        super(context, R.layout.list_systemcalendar_item);
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_systemcalendar_item,parent,false);
        number = view.findViewById(R.id.number_id);
        event = view.findViewById(R.id.event_title);
        startTime = view.findViewById(R.id.start_time);
        endTime = view.findViewById(R.id.end_time);
        status = view.findViewById(R.id.status);


        number.setText(arrayList.get(position).getId().toString());
        event.setText(arrayList.get(position).getEvent());
        startTime.setText(arrayList.get(position).getStartTime());
        endTime.setText(arrayList.get(position).getEndTime());
        status.setText(arrayList.get(position).getStatus());
        return view;
    }
}
