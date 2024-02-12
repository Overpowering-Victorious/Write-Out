package com.example.write_out.Articles;

import android.content.Intent;
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

public class Edit_Article extends AppCompatActivity {

    Intent data;
    EditText title,content;
    ProgressBar pb;
    FloatingActionButton fab;
    FirebaseFirestore fstore;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_article);

        Toolbar toolbar=findViewById(R.id.tool4);
        setSupportActionBar(toolbar);

        data=getIntent();
        title=findViewById(R.id.title3);
        content=findViewById(R.id.content3);

        user= FirebaseAuth.getInstance().getCurrentUser();
        fstore=FirebaseFirestore.getInstance();

        fab=findViewById(R.id.save_article2);

        pb=findViewById(R.id.progressBar2);

        content.setText(data.getStringExtra("content"));
        title.setText(data.getStringExtra("title"));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ntitle=title.getText().toString();
                String ncontent=content.getText().toString();
                if(TextUtils.isEmpty(ntitle) && TextUtils.isEmpty(ncontent))
                {
                    Toast.makeText(Edit_Article.this,"Error! Try again", Toast.LENGTH_SHORT).show();
                    return;
                }

                pb.setVisibility(View.VISIBLE);

                DocumentReference docref = fstore.collection("Articles").document(user.getUid()).collection("My Articles").document(data.getStringExtra("articleID"));
                Map<String, Object> article = new HashMap<>();

                article.put("title", ntitle);
                article.put("content", ncontent);

                docref.update(article).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Edit_Article.this, "Article saved", Toast.LENGTH_SHORT).show();
                        NavUtils.navigateUpFromSameTask(Edit_Article.this);
                        overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Article.this, "Error: Try again", Toast.LENGTH_SHORT).show();
                        pb.setVisibility(View.INVISIBLE);
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
        if(item.getItemId()==R.id.cancel) {
            NavUtils.navigateUpFromSameTask(Edit_Article.this);
            overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}