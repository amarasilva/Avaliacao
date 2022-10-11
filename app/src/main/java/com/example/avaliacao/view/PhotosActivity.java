package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.PhotosAdapter;
import com.example.avaliacao.repository.PhotosRepository;

public class PhotosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        RecyclerView rc = findViewById(R.id.RecycleViewUser);
        PhotosAdapter adapter = new PhotosAdapter(PhotosRepository.getInstance(this).getPhotos());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);

    }
}