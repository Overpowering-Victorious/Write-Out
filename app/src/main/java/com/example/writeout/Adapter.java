package com.example.writeout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> list;

    public Adapter (List<ModelClass>list){
        this.list=list;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.components,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
            String a2=list.get(position).getA1();
            String d2=list.get(position).getD1();

            holder.setData(a2,d2);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textView1;
        private TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1=itemView.findViewById(R.id.a1);
            textView2=itemView.findViewById(R.id.d1);
        }

        public void setData(String a2, String d2) {
            textView1.setText(a2);
            textView2.setText(d2);
        }
    }
}
