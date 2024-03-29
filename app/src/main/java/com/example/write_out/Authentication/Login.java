package com.example.write_out.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.write_out.Home;
import com.example.write_out.R;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText E2,P3;
    FirebaseAuth FAuth;
    ProgressBar pb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        E2=findViewById(R.id.e2);
        P3=findViewById(R.id.p3);
        FAuth=FirebaseAuth.getInstance();
        pb=findViewById(R.id.progressBar4);
    }

    public void SignUpp(View v)
    {
        Intent i=new Intent(this, SignUp.class);
        startActivity(i);
        finish();
    }

    public void LogIn(View v)
    {
        String Email=E2.getText().toString();
        String Pass3=P3.getText().toString();

        if(TextUtils.isEmpty(Email))
        {
            E2.setError("Email is required!");
            E2.requestFocus();
        }
        else if(TextUtils.isEmpty(Pass3))
        {
            P3.setError("Password required!");
            P3.requestFocus();
        }
        else {
            FAuth.signInWithEmailAndPassword(Email, Pass3).addOnCompleteListener(task -> {
                pb.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Welcome!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, Home.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Email or Password doesn't match!", Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}