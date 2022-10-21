package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Comments;
import com.example.avaliacao.repository.AlbumsRepository;
import com.example.avaliacao.repository.CommentsRepository;
import com.example.avaliacao.repository.PhotosRepository;
import com.example.avaliacao.repository.PostsRepository;
import com.example.avaliacao.repository.TodosRepository;
import com.example.avaliacao.repository.UserRepository;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        findViewById(R.id.MAbuttonUser).setOnClickListener(this);
        findViewById(R.id.MAbuttonPost).setOnClickListener(this);
        findViewById(R.id.MAbuttonComments).setOnClickListener(this);
        findViewById(R.id.MAbuttonAlbum).setOnClickListener(this);
        findViewById(R.id.MAbuttonPhotos).setOnClickListener(this);
        findViewById(R.id.MAbuttonTodos).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.MAbuttonUser) {
            Intent intent = new Intent(view.getContext(), UserActivity.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.MAbuttonPost) {
            Intent intent = new Intent(view.getContext(), PostsActivity.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.MAbuttonComments) {
            Intent intent = new Intent(view.getContext(), CommentsActivity.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.MAbuttonAlbum) {
            Intent intent = new Intent(view.getContext(), AlbumsActivity.class);
            startActivity(intent);

        }

        if (view.getId() == R.id.MAbuttonPhotos) {
            Intent intent = new Intent(view.getContext(), PhotosActivity.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.MAbuttonTodos) {
            Intent intent = new Intent(view.getContext(), TodosActivity.class);
            startActivity(intent);

        }

    }
}