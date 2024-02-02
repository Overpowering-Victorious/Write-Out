package com.example.write_out.Articles;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;

import com.example.write_out.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class New_Article extends AppCompatActivity {

    EditText title,content;
    FirebaseFirestore fstore;
    FloatingActionButton fab;
    ProgressBar pb;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);

        user= FirebaseAuth.getInstance().getCurrentUser();
        fstore=FirebaseFirestore.getInstance();
        title=findViewById(R.id.title2);
        content=findViewById(R.id.content2);
        fab=findViewById(R.id.save_article);
        pb=findViewById(R.id.progressBar1);

        Toolbar toolbar=findViewById(R.id.tool3);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ntitle=title.getText().toString();
                String ncontent=content.getText().toString();
                if(TextUtils.isEmpty(ntitle) && TextUtils.isEmpty(ncontent))
                {
                    Toast.makeText(New_Article.this,"Error! Try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                pb.setVisibility(View.VISIBLE);

                DocumentReference docref = fstore.collection("Articles").document(user.getUid()).collection("My Articles").document();
                Map<String, Object> article = new HashMap<>();

                article.put("title", ntitle);
                article.put("content", ncontent);

                docref.set(article).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(New_Article.this, "Article saved", Toast.LENGTH_SHORT).show();
                        NavUtils.navigateUpFromSameTask(New_Article.this);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(New_Article.this, "Error: Try again", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.close_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.cancel)
            NavUtils.navigateUpFromSameTask(New_Article.this);
        return super.onOptionsItemSelected(item);
    }
}