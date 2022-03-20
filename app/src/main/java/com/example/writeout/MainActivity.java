package com.example.writeout;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private EditText Email1,Email2,Uname1,Uname2,Pass1,Pass2,RePass,OTP;
    private TextView Login,FPass,Signup;
    private Button B1,B2,B3,B4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email1=findViewById(R.id.e1);
        Email2=findViewById(R.id.e2);
        Uname1=findViewById(R.id.u1);
        Uname2=findViewById(R.id.u2);
        Pass1=findViewById(R.id.p0);
        RePass=findViewById(R.id.p1);
        Pass2=findViewById(R.id.p2);
        OTP=findViewById(R.id.otp);

        Login=findViewById(R.id.LIn);
        FPass=findViewById(R.id.p3);
        Signup=findViewById(R.id.SUp);

        B1=findViewById(R.id.b1);
        B2=findViewById(R.id.b2);
        B3=findViewById(R.id.b3);
        B4=findViewById(R.id.b4);
    }
}