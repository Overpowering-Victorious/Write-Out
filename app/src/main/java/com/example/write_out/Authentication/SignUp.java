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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUp extends AppCompatActivity {

    EditText N1,E1,P1,P2;
    FirebaseAuth FAuth;
    ProgressBar pb;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        N1=findViewById(R.id.n1);
        E1=findViewById(R.id.e1);
        P1=findViewById(R.id.p1);
        P2=findViewById(R.id.p2);
        FAuth=FirebaseAuth.getInstance();
        pb=findViewById(R.id.progressBar3);

    }

    public void Loginn(View v)
    {
        Intent i=new Intent(this, Login.class);
        startActivity(i);
        overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
        finish();
    }

    public void Sign_Up(View v)
    {
        String Name=N1.getText().toString();
        String Email=E1.getText().toString();
        String Pass1=P1.getText().toString();
        String Pass2=P2.getText().toString();

        if(TextUtils.isEmpty(Name))
        {
            N1.setError("Name is required!");
            N1.requestFocus();
        }
        else if(TextUtils.isEmpty(Email))
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
                pb.setVisibility(View.VISIBLE);
                if(task.isComplete())
                {
                    Toast.makeText(SignUp.this,"Signed Up successfully",Toast.LENGTH_SHORT).show();
                    FirebaseUser user=FAuth.getCurrentUser();
                    UserProfileChangeRequest req= new UserProfileChangeRequest.Builder().setDisplayName(Name).build();
                    user.updateProfile(req);
                    
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                    finish();
                }
                else{
                    Toast.makeText(SignUp.this,"Error",Toast.LENGTH_SHORT).show();
                    pb.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}