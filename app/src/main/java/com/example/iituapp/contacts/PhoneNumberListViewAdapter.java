package com.example.iituapp.contacts;

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

public class PhoneNumberListViewAdapter extends ArrayAdapter<PhoneNumber> {
    private ArrayList<PhoneNumber> list;
    private TextView number;
    public PhoneNumberListViewAdapter(@NonNull Context context, ArrayList<PhoneNumber> arrayList) {
        super(context, R.layout.list_phone_number_item);
        list = arrayList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.list_phone_number_item,parent,false);
        number = view.findViewById(R.id.contact_number);
        number.setText(list.get(position).getNumber());
        return view;
    }
}
