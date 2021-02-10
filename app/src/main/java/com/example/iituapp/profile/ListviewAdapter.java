package com.example.iituapp.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.iituapp.R;
import com.example.iituapp.profile.Student;

import java.util.ArrayList;

public class ListviewAdapter extends ArrayAdapter<Student> {
    ArrayList<Student> list;
    public ListviewAdapter(@NonNull Context context, ArrayList<Student> list) {
        super(context, R.layout.list_items);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(getContext());
        View view =inflater.inflate(R.layout.list_items,parent,false);
        TextView info_data = view.findViewById(R.id.info_data);
        TextView student_data = view.findViewById(R.id.student_data);
        info_data.setText(list.get(position).getInfo_data());
        student_data.setText(list.get(position).getStudent_data());
        return view;
    }
}
