package com.example.write_out;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.write_out.Fragments.explore;
import com.example.write_out.Fragments.favourites;
import com.example.write_out.Fragments.my_articles;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout dl;
    ActionBarDrawerToggle tg;
    NavigationView nv;

    MaterialButton B3,B4,B5;
    FloatingActionButton B6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar tb=findViewById(R.id.tool1);
        setSupportActionBar(tb);
        dl=findViewById(R.id.draw);
        tg=new ActionBarDrawerToggle(Home.this,dl,tb,R.string.open,R.string.close);
        dl.addDrawerListener(tg);
        tg.setDrawerIndicatorEnabled(true);
        tg.syncState();

        nv=findViewById(R.id.nav);
        nv.setNavigationItemSelectedListener(this);



        my_articles my_article= new my_articles();
        replaceFragment(my_article);

        B3=findViewById(R.id.b3);
        B4=findViewById(R.id.b4);
        B5=findViewById(R.id.b5);
        B6=findViewById(R.id.new_add);

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                my_articles my_article= new my_articles();
                replaceFragment(my_article);
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

        B6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, New_Article.class));
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.l3,fragment);
        ft.commit();
    }

    public static int id=0;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        id=item.getItemId();
        if(id==R.id.add_new)
            startActivity(new Intent(this, New_Article.class));
        else
            Toast.makeText(this,"Hehe...",Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inf=getMenuInflater();
        inf.inflate(R.menu.set_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.set_menu)
            Toast.makeText(this,"Settings",Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}