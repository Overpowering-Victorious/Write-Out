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

public class MainActivity extends AppCompatActivity {

    EditText Email1,Uname1,Pass1,RePass;
    TextView Loginpg;
    Button B1;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email1=findViewById(R.id.e1);
        Uname1=findViewById(R.id.u1);
        Pass1=findViewById(R.id.p0);
        RePass=findViewById(R.id.p1);

        Loginpg=findViewById(R.id.LIn);

        B1=findViewById(R.id.b1);

        fAuth= FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),home.class));
            finish();
        }

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getUserName = Uname1.getText().toString();
                String getEmail = Email1.getText().toString();
                String getPass = Pass1.getText().toString();
                String getRePass = RePass.getText().toString();

                if(TextUtils.isEmpty(getEmail)){
                    Email1.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(getUserName)){
                    Uname1.setError("Username is required!");
                    return;
                }

                if(TextUtils.isEmpty(getPass)){
                    Pass1.setError("Password is required!");
                    return;
                }

                if(TextUtils.isEmpty(getRePass)){
                    RePass.setError("Please re-enter the password!");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(getEmail,getPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),home.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        Loginpg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });

    }
}