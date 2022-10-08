package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.avaliacao.R;
import com.example.avaliacao.adapter.UserAdapter;
import com.example.avaliacao.repository.UserRepository;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        //organizando o adapter
        RecyclerView rc = findViewById(R.id.RecycleViewUsers);
        UserAdapter adapter = new UserAdapter(UserRepository.getInstance(this).getUsers());
        rc.setAdapter(adapter);
        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        rc.setLayoutManager(llm1);


    }
}