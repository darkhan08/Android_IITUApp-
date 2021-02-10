package com.example.iituapp.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.iituapp.MainActivity;
import com.example.iituapp.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment  {
    ListView listView;
    ArrayList<Student> list;
    ListviewAdapter myAdapter;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private TextView names,id;
    private String id_email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        listView = view.findViewById(R.id.list_view);
        names = view.findViewById(R.id.name_students);
        id = view.findViewById(R.id.id_students);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Profile");
        mAuth = FirebaseAuth.getInstance();
        readProfile();
        FirebaseUser user = mAuth.getCurrentUser();
        id_email=user.getEmail().substring(0,5);
        return view;
    }

    private void readProfile() {
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            String name  = snapshot.child(id_email).child("fullname").getValue().toString();
            String advisor  = snapshot.child(id_email).child("advisor").getValue().toString();
            String birthday  = snapshot.child(id_email).child("birthday").getValue().toString();
            String classes  = snapshot.child(id_email).child("classes").getValue().toString();
            String email  = snapshot.child(id_email).child("email").getValue().toString();
            String program  = snapshot.child(id_email).child("program").getValue().toString();
            String status  = snapshot.child(id_email).child("status").getValue().toString();
            String ids = snapshot.child(id_email).getKey();
            addData(name,advisor,birthday,classes,email,program,status);
            myAdapter =new ListviewAdapter(getActivity().getBaseContext(),list);
            listView.setAdapter(myAdapter);
            names.setText(name);
            id.setText(ids);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    }


    private void addData(String name,String advisor,String birthday,String classes,String email,String program, String status) {
        list =new ArrayList<>();
        list.add(new Student("Аты-жөні",name ));
        list.add(new Student("Туылған күні", birthday));
        list.add(new Student("Мамандық", program));
        list.add(new Student("Курс", classes));
        list.add(new Student("Кеңесші",advisor));
        list.add(new Student("Күйі", status));
        list.add(new Student("Электронды пошта",email));

    }


}
