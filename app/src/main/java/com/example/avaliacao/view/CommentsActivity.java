package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.CommentsAdapter;
import com.example.avaliacao.repository.CommentsRepository;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        RecyclerView rc = findViewById(R.id.RecycleViewUser);
        CommentsAdapter adapter = new CommentsAdapter(CommentsRepository.getInstance(this).getCommentss());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);




    }
}