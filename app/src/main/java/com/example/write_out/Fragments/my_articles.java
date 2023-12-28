package com.example.write_out.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.write_out.Adapters.Adapter1;
import com.example.write_out.R;

import java.util.ArrayList;
import java.util.List;

public class my_articles extends Fragment {

    ArrayList<String>titles=new ArrayList<String>();
    ArrayList<String>contents=new ArrayList<String>();
    Adapter1 adpt;
    RecyclerView rv;

    public my_articles() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_my_articles, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addData();

        adpt=new Adapter1(getContext(),titles,contents);
        rv=view.findViewById(R.id.recycler1);
        rv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(adpt);

    }

    private void addData() {
        titles.add("First Article");
        titles.add("Second Article");
        titles.add("Third Article");

        contents.add("This is the content of first article, hope you like it!");
        contents.add("This is the content of second article, hope you like it! This is the content of second article, hope you like it! This is the content of second article, hope you like it!");
        contents.add("This is the content of third article, hope you like it!");
    }
}