package com.example.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Others_Articles extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ModelClass> list;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_others__articles, container, false);
        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        list.add(new ModelClass("Article 1", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 2", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 3", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 4", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 5", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 6", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 7", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 8", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 9", "--------------------------------------------------------------------------------------------------------------------"));
        list.add(new ModelClass("Article 10", "--------------------------------------------------------------------------------------------------------------------"));

        recyclerView.setAdapter(new Adapter(list));
        return view;
    }
}

