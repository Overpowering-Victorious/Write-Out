package com.example.write_out.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.write_out.Article_Details;
import com.example.write_out.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Adapter1 extends RecyclerView.Adapter<Adapter1.ViewHolder> {

    Context context;
    ArrayList<String>titles,contents;

    public Adapter1(Context cnt,ArrayList<String>ttl, ArrayList<String>ctt)
    {
        context=cnt;
        titles=ttl;
        contents=ctt;
    }

    @NonNull
    @Override
    public Adapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.article_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter1.ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.content.setText(contents.get(position));
        holder.content.setBackgroundColor(holder.v.getResources().getColor(getRandom(),null));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(), Article_Details.class);
                v.getContext().startActivity(i);
            }
        });
    }

    private int getRandom() {
        List<Integer>col=new ArrayList<>();
        col.add(R.color.yellow);
        col.add(R.color.lightGreen);
        col.add(R.color.pink);
        col.add(R.color.lightPurple);
        col.add(R.color.skyblue);
        col.add(R.color.lred);
        col.add(R.color.blue);
        col.add(R.color.notgreen);

        Random rcolor=new Random();
        int num=rcolor.nextInt(col.size());
        return col.get(num);
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,content;
        View v;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById((R.id.art_content));
            v=itemView;
        }
    }
}
