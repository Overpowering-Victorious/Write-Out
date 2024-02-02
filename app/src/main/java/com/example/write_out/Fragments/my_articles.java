package com.example.write_out.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class my_articles extends Fragment{
    RecyclerView rv;
    FirebaseFirestore fstore;
    FirestoreRecyclerAdapter<Article,ArticleViewHolder> article_adapter;
    FirebaseUser user;

    public my_articles() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user= FirebaseAuth.getInstance().getCurrentUser();

        fstore=FirebaseFirestore.getInstance();
        Query query=fstore.collection("Articles").document(user.getUid()).collection("My Articles").orderBy("title",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Article>  all_articles = new FirestoreRecyclerOptions.Builder<Article>()
                .setQuery(query, Article.class)
                .build();

        article_adapter=new FirestoreRecyclerAdapter<Article, ArticleViewHolder>(all_articles) {
            @Override
            protected void onBindViewHolder(@NonNull ArticleViewHolder holder, int position, @NonNull final Article model) {
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
                });

                ImageView imv=holder.view.findViewById(R.id.art_set);
                imv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopupMenu pm=new PopupMenu(v.getContext(),v);
                        pm.setGravity(Gravity.END);
                        pm.getMenu().add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(@NonNull MenuItem item) {
                                Intent i=new Intent(v.getContext(), Edit_Article.class);
                                i.putExtra("title",model.getTitle());
                                i.putExtra("content",model.getContent());
                                i.putExtra("articleID",docId);
                                startActivity(i);
                                return false;
                            }
                        });
                        pm.getMenu().add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(@NonNull MenuItem item) {
                                DocumentReference docref=fstore.collection("Articles").document(user.getUid()).collection("My Articles").document(docId);
                                docref.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(),"Error Deleting Article",Toast.LENGTH_SHORT).show();
                                    }
                                });
                                return false;
                            }
                        });

                        pm.show();
                    }
                });
            }

            @NonNull
            @Override
            public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v=LayoutInflater.from(getContext()).inflate(R.layout.article_layout,parent,false);
                return new ArticleViewHolder(v);
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_my_articles, container, false);

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