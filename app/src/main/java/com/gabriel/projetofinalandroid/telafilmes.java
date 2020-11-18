package com.gabriel.projetofinalandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;
import com.xwray.groupie.ViewHolder;

import java.util.List;

public class telafilmes extends AppCompatActivity {

    private RecyclerView rcPraRelaxar, rcAgitar, rcAkon, rcFunk;
    private GroupAdapter adapterPraRelaxar, adapterAgitar, adapterAkon, adapterFunk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telafilmes);

        iniciarComponetes();
        inserirComponentes();

        adapterPraRelaxar.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(getApplicationContext(), PlayMoviesActivity.class);
                Listar model =(Listar) item;
                intent.putExtra("link", model.url);
                intent.putExtra("tipo", "PraRelaxar");
                startActivity(intent);
            }
        });

        adapterAgitar.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(getApplicationContext(), PlayMoviesActivity.class);
                Listar model =(Listar) item;
                intent.putExtra("link", model.url);
                intent.putExtra("tipo", "PraRelaxar");
                startActivity(intent);
            }
        });

        adapterAkon.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(getApplicationContext(), PlayMoviesActivity.class);
                Listar model =(Listar) item;
                intent.putExtra("link", model.url);
                intent.putExtra("tipo", "PraRelaxar");
                startActivity(intent);
            }
        });

        adapterFunk.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                Intent intent = new Intent(getApplicationContext(), PlayMoviesActivity.class);
                Listar model =(Listar) item;
                intent.putExtra("link", model.url);
                intent.putExtra("tipo", "PraRelaxar");
                startActivity(intent);
            }
        });
    }

    private void iniciarComponetes(){
        rcPraRelaxar = findViewById(R.id.rcPraRelaxar);
        rcAgitar = findViewById(R.id.rcAgitar);
        rcFunk = findViewById(R.id.rcFunk);
        rcAkon = findViewById(R.id.rcAkon);

        adapterPraRelaxar = new GroupAdapter();
        adapterAgitar = new GroupAdapter();
        adapterAkon = new GroupAdapter();
        adapterFunk = new GroupAdapter();
    }

    private void inserirComponentes(){

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rcPraRelaxar.setLayoutManager(linearLayoutManager);
        rcPraRelaxar.setAdapter(adapterPraRelaxar);

        rcAgitar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcAgitar.setAdapter(adapterAgitar);

        rcAkon.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcAkon.setAdapter(adapterAkon);

        rcFunk.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rcFunk.setAdapter(adapterFunk);


        listarPraRelaxar();
        listarAgitadas();
        listarFunk();
        listarAkon();
    }

    private void listarPraRelaxar(){
        FirebaseFirestore.getInstance().collection("filmes").document("praRelaxar").collection("rilex").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null){
                    Log.e("Error", "Filmes Pra Relaxar: "+error.getMessage(), error);
                }else{
                    List<DocumentSnapshot> docs = value.getDocuments();
                    for (DocumentSnapshot doc: docs){
                        adapterPraRelaxar.add(new Listar(doc.getString("img"), doc.getString("link")));
                    }
                    adapterPraRelaxar.notifyDataSetChanged();
                }
            }
        });
    }
    private void listarAgitadas() {
        FirebaseFirestore.getInstance().collection("filmes").document("agitada").collection("agitacao").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Error", "Filmes Pra Relaxar: " + error.getMessage(), error);
                } else {
                    List<DocumentSnapshot> docs = value.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        adapterAgitar.add(new Listar(doc.get("img").toString(), doc.get("link").toString()));
                    }
                    adapterAgitar.notifyDataSetChanged();
                }
            }
        });
    }

    private void listarFunk() {
        FirebaseFirestore.getInstance().collection("filmes").document("funk").collection("funk").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Error", "Filmes Pra Relaxar: " + error.getMessage(), error);
                } else {
                    List<DocumentSnapshot> docs = value.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        adapterFunk.add(new Listar(doc.get("img").toString(), doc.get("link").toString()));
                    }
                    adapterFunk.notifyDataSetChanged();
                }
            }
        });
    }

    private void listarAkon() {
        FirebaseFirestore.getInstance().collection("filmes").document("akon").collection("akon").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("Error", "Filmes Pra Relaxar: " + error.getMessage(), error);
                } else {
                    List<DocumentSnapshot> docs = value.getDocuments();
                    for (DocumentSnapshot doc : docs) {
                        adapterAkon.add(new Listar(doc.get("img").toString(), doc.get("link").toString()));
                    }
                    adapterAkon.notifyDataSetChanged();
                }
            }
        });
    }

    private class Listar extends Item<ViewHolder>{

        final private String link, url;

        private Listar(String link, String url) {
            this.link = link;
            this.url = url;
        }

        @Override
        public void bind(@NonNull ViewHolder viewHolder, int position) {
            ImageView imageView= viewHolder.itemView.findViewById(R.id.imgCapa);
            Picasso.get().load(link).into(imageView);
        }

        @Override
        public int getLayout() {
            return R.layout.activity_item_video;
        }
    }
}