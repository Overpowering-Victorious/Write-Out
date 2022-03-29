package com.example.writeout;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass>list;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);
        
        tabLayout.setupWithViewPager(viewPager);
        
        VPAdapter vpAdapter= new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new Others_Articles(), "Other's Articles");
        vpAdapter.addFragment(new My_Articles(), "My Articles");
        vpAdapter.addFragment(new Favourites(), "Favourites");
        viewPager.setAdapter(vpAdapter);

        initData();
        initRecyclerView();
    }

    private void initData() {
        list=new ArrayList<>();
        list.add(new ModelClass("Article 1","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 2","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 3","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 4","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 5","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 6","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 7","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 8","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 9","--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 10","--------------------------------------------------------------------------------------------------------------------"));
    }

    private void initRecyclerView() {
        recyclerView=findViewById(R.id.recycler);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Adapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
