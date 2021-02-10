package com.example.iituapp.contacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.iituapp.R;
import com.example.iituapp.profile.ProfileFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private ArrayList<PhoneNumber> list;
    private TextView email,address;
    private ListView listView;
    private PhoneNumberListViewAdapter myAdapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private LinearLayout linearLayoutEmail,linearLayoutMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts,container,false);
        email = view.findViewById(R.id.iitu_email);
        address = view.findViewById(R.id.iitu_andress);
        listView = view.findViewById(R.id.listView_phone);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Contacts");
        linearLayoutEmail = view.findViewById(R.id.linearLayout_email);
        linearLayoutMap = view.findViewById(R.id.linearLayout_map);
        fillContactNumber();

        return view;
    }



    public void onBackPressed()
    {
            if(getFragmentManager().findFragmentById(R.id.fragment_container) != new ProfileFragment()){
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
        }
    }

    private void showDialogEmail(){
        linearLayoutEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Message").
                        setMessage("Do you want to send a message:  " + email.getText().toString())
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SendMessage(email.getText().toString());
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void SendMessage(String email) {
        String[] TO = {email};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,TO);
        intent.setType("message/rfc822");
        startActivity(intent.createChooser(intent,"Choose an email .."));
    }

    private void showDialogCall() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Call").
                        setMessage("Do you want to call:  " + list.get(position).getNumber())
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                call(position);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });

    }

    private void call(int position){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + list.get(position).getNumber()));
        System.out.println(list.get(position).getNumber());
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private void fillContactNumber() {
        list = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot ds = snapshot.child("Telephone");
                if (ds != null) {
                    for (DataSnapshot ker : ds.getChildren()) {
                        PhoneNumber transcription = ker.getValue(PhoneNumber.class);
                        list.add(transcription);
                    }
                }
                DataSnapshot address1 = snapshot.child("Address").child("location");
                DataSnapshot email1 = snapshot.child("Email").child("emial");
                email.setText(email1.getValue().toString());
                address.setText(address1.getValue().toString());

                myAdapter = new PhoneNumberListViewAdapter(getContext(),list);
                listView.setAdapter(myAdapter);
                showDialogCall();
                if(email1.getValue().toString().length()>0)
                showDialogEmail();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
