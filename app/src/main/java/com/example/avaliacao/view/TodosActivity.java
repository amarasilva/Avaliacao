package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.avaliacao.R;
import com.example.avaliacao.adapter.AlbumsAdapter;
import com.example.avaliacao.adapter.TodosAdapter;
import com.example.avaliacao.repository.AlbumsRepository;
import com.example.avaliacao.repository.TodosRepository;

public class TodosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todos);

        RecyclerView rc = findViewById(R.id.RecycleViewTodos);
        TodosAdapter adapter = new TodosAdapter(TodosRepository.getInstance(this).getTodos());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);


    }
}