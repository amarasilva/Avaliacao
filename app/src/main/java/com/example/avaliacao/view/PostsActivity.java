package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.PostsAdapter;
import com.example.avaliacao.adapter.UserAdapter;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.PostsRepository;
import com.example.avaliacao.repository.UserRepository;

public class PostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        PostsRepository.getInstance(this, new OnReadyListener() {
            @Override
            public void onReady() {
                //organizando o adapter
                RecyclerView rc = findViewById(R.id.RecycleViewPosts);
                PostsAdapter adapter = new PostsAdapter(PostsRepository.getInstance().getPostss());
                rc.setAdapter(adapter);
                LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                rc.setLayoutManager(llm1);
            }
        });
    }
}