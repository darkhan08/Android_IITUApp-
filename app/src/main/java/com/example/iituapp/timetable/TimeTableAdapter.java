package com.example.iituapp.timetable;

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

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {
    ArrayList<TimeTable> timeTables;
    public TimeTableAdapter(@NonNull Context context, ArrayList<TimeTable> list) {
        super(context, R.layout.list_monday_items);
        timeTables = list;
    }

    @Override
    public int getCount() {
        return timeTables.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_monday_items,parent,false);
        TextView classType = view.findViewById(R.id.classes);
        TextView startEnd = view.findViewById(R.id.start_end_class);
        TextView titleofClass = view.findViewById(R.id.name_class);
        TextView officeNumber = view.findViewById(R.id.office_number);
        TextView teacher = view.findViewById(R.id.name_of_teacher);
        classType.setText(timeTables.get(position).getClasses());
        startEnd.setText(timeTables.get(position).getDate());
        titleofClass.setText(timeTables.get(position).getName_of_class());
        officeNumber.setText(timeTables.get(position).getOffice());
        teacher.setText(timeTables.get(position).getTeacher());
        return view;
    }
}
