package com.example.iituapp.transcription;

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

public class TranscriptionListviewAdapter extends ArrayAdapter<Transcription> {
    private TextView code,course_name;
    private TextView CR,ECTS,grade,LG,point,traditional;
    ArrayList<Transcription> mylist;
    public TranscriptionListviewAdapter(@NonNull Context context, ArrayList<Transcription> arrayList) {
        super(context, R.layout.list_transcription_items);
        mylist = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_transcription_items,parent,false);
        code = view.findViewById(R.id.code_of_class);
        course_name = view.findViewById(R.id.courses);
        CR = view.findViewById(R.id.credits);
        ECTS = view.findViewById(R.id.ects);
        grade = view.findViewById(R.id.grade);
        LG = view.findViewById(R.id.lg);
        point = view.findViewById(R.id.point);
        traditional = view.findViewById(R.id.traditional);

        code.setText(mylist.get(position).getCode());
        course_name.setText(mylist.get(position).getCourse_name());
        CR.setText(mylist.get(position).getCR().toString());
        ECTS.setText(mylist.get(position).getECTS().toString());
        grade.setText(mylist.get(position).getGrade().toString());
        LG.setText(mylist.get(position).getLG());
        point.setText(mylist.get(position).getPoint().toString());
        traditional.setText(mylist.get(position).getTraditional());
        return view;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }
}
