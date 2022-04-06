package com.example.writeout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class My_Articles extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass> list;
    Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initData();
        initRecyclerView();
        return inflater.inflate(R.layout.fragment_my__articles, container, false);
    }

    private void initRecyclerView() {
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new Adapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
}