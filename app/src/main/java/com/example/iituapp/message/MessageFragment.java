package com.example.iituapp.message;

import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iituapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    ListView listView;
    ArrayList<Messages> list;
    MessageAdapter myAdapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String id_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_messages,container,false);
        listView = view.findViewById(R.id.list_view_message);

        id_email = getArguments().getString("id");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Message");
        addMessage();




        return view;
    }
    public void Click(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowMessage showMessage = new ShowMessage();
                Bundle args = new Bundle();
                args.putString("YourKey", list.get(position).getTitle_message());
                args.putString("YourKey2", list.get(position).getDate());
                args.putString("YourKey3", list.get(position).getMessage());
                showMessage.setArguments(args);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, showMessage).commit();
            }
        });
    }
    private void addMessage() {
        list = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds = snapshot.child(id_email);
                if (ds != null) {
                    List<String> keys = new ArrayList<>();
                    for (DataSnapshot ker : ds.getChildren()) {
                        keys.add(ker.getKey());
                        Messages transcription = ker.getValue(Messages.class);
                        list.add(transcription);
                    }
                }
                myAdapter =new MessageAdapter(getActivity().getBaseContext(),list);
                listView.setAdapter(myAdapter);
                Click();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
