package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.PostsAdapter;
import com.example.avaliacao.repository.PostsRepository;

public class PostsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        RecyclerView rc = findViewById(R.id.RecycleViewTodos);
        PostsAdapter adapter = new PostsAdapter(PostsRepository.getInstance(this).getPostss());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);


    }
}