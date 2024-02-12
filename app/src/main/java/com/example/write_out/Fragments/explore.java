package com.example.write_out.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.write_out.Adapters.Article;
import com.example.write_out.Articles.Article_Details;
import com.example.write_out.Articles.Edit_Article;
import com.example.write_out.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class explore extends Fragment {

    RecyclerView rv;
    FirebaseFirestore fstore;
    FirestoreRecyclerAdapter<Article, explore.ArticleViewHolder> article_adapter;

    public explore() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String user= FirebaseAuth.getInstance().getCurrentUser().getUid();

        fstore=FirebaseFirestore.getInstance();
        Query query=fstore.collectionGroup("My Articles").whereNotEqualTo("UserId",user);

        FirestoreRecyclerOptions<Article> all_articles = new FirestoreRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();

        article_adapter=new FirestoreRecyclerAdapter<Article, explore.ArticleViewHolder>(all_articles) {
            @Override
            protected void onBindViewHolder(@NonNull explore.ArticleViewHolder holder, int position, @NonNull final Article model) {
                holder.title.setText(model.getTitle());
                holder.content.setText(model.getContent());
                final int col=getRandom();
                holder.content.setBackgroundColor(holder.view.getResources().getColor(col,null));
                final String docId=article_adapter.getSnapshots().getSnapshot(position).getId();

                holder.view.setOnClickListener(v -> {
                    Intent i=new Intent(v.getContext(), Article_Details.class);
                    i.putExtra("title",model.getTitle());
                    i.putExtra("content",model.getContent());
                    i.putExtra("color",col);
                    i.putExtra("articleID",docId);
                    v.getContext().startActivity(i);
                    getActivity().overridePendingTransition(R.anim.slide_up,R.anim.slide_down);
                });
            }

            @NonNull
            @Override
            public explore.ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(getContext()).inflate(R.layout.article_layout,parent,false);
                return new explore.ArticleViewHolder(v);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_explore, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv=view.findViewById(R.id.recycler1);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv.setAdapter(article_adapter);

    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{

        TextView title,content;
        View view;
        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById((R.id.art_content));
            view=itemView;
        }
    }

    private int getRandom() {
        List<Integer> col=new ArrayList<>();
        col.add(R.color.yellow);
        col.add(R.color.lightGreen);
        col.add(R.color.pink);
        col.add(R.color.lightPurple);
        col.add(R.color.skyblue);

        Random rcolor=new Random();
        int num=rcolor.nextInt(col.size());
        return col.get(num);
    }

    @Override
    public void onStart() {
        super.onStart();
        article_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        article_adapter.stopListening();
    }
}