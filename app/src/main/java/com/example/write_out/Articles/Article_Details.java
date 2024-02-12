package com.example.write_out.Articles;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.write_out.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Article_Details extends AppCompatActivity {

    Intent data;
    FloatingActionButton edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        data=getIntent();

        TextView content=findViewById(R.id.content1);
        TextView title=findViewById(R.id.title1);
        content.setMovementMethod(new ScrollingMovementMethod());
        content.setText(data.getStringExtra("content"));
        title.setText(data.getStringExtra("title"));

        Toolbar toolbar=findViewById(R.id.tool2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edit=findViewById(R.id.edit1);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Article_Details.this, Edit_Article.class);
                i.putExtra("title",data.getStringExtra("title"));
                i.putExtra("content",data.getStringExtra("content"));
                i.putExtra("articleID",data.getStringExtra("articleID"));
                startActivity(i);
                overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return super.onOptionsItemSelected(item);
    }
}