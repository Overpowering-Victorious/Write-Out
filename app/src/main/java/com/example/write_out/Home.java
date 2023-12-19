package com.example.write_out;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.example.write_out.Fragments.explore;
import com.example.write_out.Fragments.favourites;
import com.example.write_out.Fragments.my_articles;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    DrawerLayout dl;
    ActionBarDrawerToggle tg;
    NavigationView nv;

    MaterialButton B3,B4,B5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar tb=findViewById(R.id.tool);
        setSupportActionBar(tb);
        dl=findViewById(R.id.draw);
        nv=findViewById(R.id.nav);
        tg=new ActionBarDrawerToggle(Home.this,dl,tb,R.string.open,R.string.close);
        dl.addDrawerListener(tg);
        tg.setDrawerIndicatorEnabled(true);
        tg.syncState();



        my_articles my_article= new my_articles();
        FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.l3,my_article);
        tr.commit();

        B3=findViewById(R.id.b3);
        B4=findViewById(R.id.b4);
        B5=findViewById(R.id.b5);

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_articles my_article= new my_articles();
                FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.l3,my_article);
                tr.commit();
            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explore exp=new explore();
                FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.l3,exp);
                tr.commit();
            }
        });

        B5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favourites fv= new favourites();
                FragmentTransaction tr=getSupportFragmentManager().beginTransaction();
                tr.replace(R.id.l3,fv);
                tr.commit();
            }
        });
    }
}