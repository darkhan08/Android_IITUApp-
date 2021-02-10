package com.example.iituapp.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iituapp.R;

public class ShowMessage extends Fragment {
    TextView title, date,message;
    String text = "бмен текстовыми сообщениями или текстовыми сообщениями - это процесс составления и отправки электронных сообщений, обычно состоящих из буквенных и цифровых символов";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_show_message,container,false);
        title = view.findViewById(R.id.card_title);
        date = view.findViewById(R.id.date);
        message = view.findViewById(R.id.mess);
        String value = getArguments().getString("YourKey");
        String value2 = getArguments().getString("YourKey2");
        String value3 = getArguments().getString("YourKey3");
        title.setText(value);
        date.setText(value2);
        message.setText(value3);
        return view;
    }
}
