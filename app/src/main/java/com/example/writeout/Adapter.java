package com.example.writeout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.viewholder> {

    ArrayList<ModelClass> list;

    public Adapter (ArrayList<ModelClass>list){
        this.list=list;
    }

    @NonNull
    @Override
    public Adapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.components,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

          holder.textView1.setText(list.get(position).getA1());
          holder.textView2.setText(list.get(position).getD1());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            textView1=itemView.findViewById(R.id.a1);
            textView2=itemView.findViewById(R.id.d1);
        }
    }
}
