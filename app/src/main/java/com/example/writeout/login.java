package com.example.writeout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class login extends AppCompatActivity {

    EditText Email2,Uname2,Pass2;
    TextView FPass,Signup;
    Button B2;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email2=findViewById(R.id.u2);
        Pass2=findViewById(R.id.p2);

        FPass=findViewById(R.id.p3);
        Signup=findViewById(R.id.SUp);

        B2=findViewById(R.id.b2);

        fAuth= FirebaseAuth.getInstance();

        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getEmail = Email2.getText().toString();
                String getPass = Pass2.getText().toString();

                if(TextUtils.isEmpty(getEmail)){
                    Email2.setError("Email is required!");
                    return;
                }


                if(TextUtils.isEmpty(getPass)){
                    Pass2.setError("Password is required!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(getEmail,getPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),home.class));
                        }
                        else {
                            Toast.makeText(login.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}