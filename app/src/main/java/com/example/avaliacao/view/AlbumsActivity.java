package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.AlbumsAdapter;
import com.example.avaliacao.adapter.UserAdapter;
import com.example.avaliacao.repository.AlbumsRepository;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.UserRepository;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        AlbumsRepository.getInstance(this, new OnReadyListener() {
            @Override
            public void onReady() {
                //organizando o adapter
                RecyclerView rc = findViewById(R.id.RecycleViewAlbums);
                AlbumsAdapter adapter = new AlbumsAdapter(AlbumsRepository.getInstance().getAlbumss());
                rc.setAdapter(adapter);
                LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                rc.setLayoutManager(llm1);
            }
        });
    }
}