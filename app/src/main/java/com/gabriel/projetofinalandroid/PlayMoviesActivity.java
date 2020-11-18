package com.gabriel.projetofinalandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class PlayMoviesActivity extends AppCompatActivity {

    private String link;
    private  VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_movies);

        link = getIntent().getExtras().getString("link");

        MediaController controller = new MediaController(this);
        videoView = findViewById(R.id.videoView);
        videoView.setMediaController(controller);
        controller.setAnchorView(videoView);

        findMovie();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    private void findMovie(){
        Uri uri = Uri.parse(link);
        videoView.setVideoURI(uri);
        videoView.start();
    }
}