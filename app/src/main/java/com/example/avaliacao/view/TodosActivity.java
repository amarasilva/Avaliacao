package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.AlbumsAdapter;
import com.example.avaliacao.adapter.TodosAdapter;
import com.example.avaliacao.repository.AlbumsRepository;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.TodosRepository;
import com.example.avaliacao.repository.UserRepository;

public class TodosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        TodosRepository.getInstance(this, new OnReadyListener() {
                    @Override
                    public void onReady() {
                        RecyclerView rc = findViewById(R.id.RecycleViewTodos);
                        TodosAdapter adapter = new TodosAdapter(TodosRepository.getInstance().getTodos());
                        rc.setAdapter(adapter);
                        LinearLayoutManager llm1 = new LinearLayoutManager(getApplicationContext());
                        rc.setLayoutManager(llm1);
                    }
                    });
        }

    }
