package com.example.iituapp.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.iituapp.R;

import java.util.ArrayList;

class MessageAdapter extends ArrayAdapter<Messages> {
    public ArrayList<Messages> list;
    public MessageAdapter(@NonNull Context context, ArrayList<Messages> list) {
        super(context, R.layout.lv_message_items);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.lv_message_items,parent,false);
        TextView sender_message = view.findViewById(R.id.sender_message);
        TextView title_message = view.findViewById(R.id.title_of_message);
        TextView date = view.findViewById(R.id.date_message);
        ImageView imageView = view.findViewById(R.id.message_image);
        sender_message.setText(list.get(position).getName());
        title_message .setText(list.get(position).getTitle_message());
        date.setText(list.get(position).getDate());
        return view;
    }
}
