package com.example.iituapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    TextView dontRemPass, tv_back;
    Boolean isVisible = false;
    CardView cardView1, cardView2;
    Button btn_login;
    TextInputEditText student_id,student_password;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        cardView1 = findViewById(R.id.card_view);
        cardView2 = findViewById(R.id.card_view2);

        student_id = findViewById(R.id.id_of_students);
        student_password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        tv_back = findViewById(R.id.back_tv);
        btn_login = findViewById(R.id.login);
        dontRemPass = findViewById(R.id.dont_remember_pass_tv);
        dontRemPass.setPaintFlags(dontRemPass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        dontRemPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardView2.getVisibility() == View.GONE && cardView1.getVisibility() == View.VISIBLE) {
                    cardView2.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.GONE);
                }
            }
        });

        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardView2.getVisibility() ==View.VISIBLE && cardView1.getVisibility() == View.GONE){
                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                }
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = student_id.getText().toString();
                String password = student_password.getText().toString();
                if(id.length()<=0){
                    Toast.makeText(RegistrationActivity.this, "Please fill id",
                            Toast.LENGTH_SHORT).show();
                }
                else if (password.length()<=0) {
                    Toast.makeText(RegistrationActivity.this, "Please fill password",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    CallLogin(id,password);
                }
            }
        });
    }

    private void CallLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("$$$", "signInWithEmail:success");
                            Toast.makeText(RegistrationActivity.this, "email: " + user,
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("$$$$", "signInWithEmail:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
