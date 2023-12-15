package com.example.write_out;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText E1,P1,P2;
    FirebaseAuth FAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        E1=findViewById(R.id.e1);
        P1=findViewById(R.id.p1);
        P2=findViewById(R.id.p2);
        FAuth=FirebaseAuth.getInstance();

    }

    public void Loginn(View v)
    {
        Intent i=new Intent(this, Login.class);
        startActivity(i);
    }

    public void SignUp(View v)
    {
        String Email=E1.getText().toString();
        String Pass1=P1.getText().toString();
        String Pass2=P2.getText().toString();

        if(TextUtils.isEmpty(Email))
        {
            E1.setError("Email is required!");
            E1.requestFocus();
        }
        else if(TextUtils.isEmpty(Pass1))
        {
            P1.setError("Password required!");
            P1.requestFocus();
        }
        else if(Pass1.length()<6)
        {
            P1.setError("Password should contain 6 or more characters!");
            P1.requestFocus();
        }
        else if(TextUtils.isEmpty(Pass2)) {
            P2.setError("Password required!");
            P2.requestFocus();
        }
        else if(!Pass1.equals(Pass2))
        {
            P2.setError("Re-enter the correct password!");
            P2.requestFocus();
        }
        else {
            FAuth.createUserWithEmailAndPassword(Email,Pass1).addOnCompleteListener(task -> {
                if(task.isComplete())
                {
                    Toast.makeText(MainActivity.this,"Signed Up successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Home.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}