package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.CommentsAdapter;
import com.example.avaliacao.adapter.UserAdapter;
import com.example.avaliacao.repository.CommentsRepository;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.UserRepository;

public class CommentsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        CommentsRepository.getInstance(this, new OnReadyListener() {
            @Override
            public void onReady() {
                //organizando o adapter
                RecyclerView rc = findViewById(R.id.RecycleViewComments);
                CommentsAdapter adapter = new CommentsAdapter(CommentsRepository.getInstance().getCommentss());
                rc.setAdapter(adapter);
                LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                rc.setLayoutManager(llm1);
            }
        });
    }
}