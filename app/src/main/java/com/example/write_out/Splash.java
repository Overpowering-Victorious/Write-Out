package com.example.write_out;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.write_out.Authentication.SignUp;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth fauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        fauth=FirebaseAuth.getInstance();

        Handler handler=new Handler();
        handler.postDelayed(() -> {
            if(fauth.getCurrentUser()!=null) {
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
            }
            else{
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
//                    fauth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            Toast.makeText(Splash.this,"Signed In Anonymously", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),Home.class));
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(Splash.this,"Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    });
            }
            finish();
        },1000);
    }
}