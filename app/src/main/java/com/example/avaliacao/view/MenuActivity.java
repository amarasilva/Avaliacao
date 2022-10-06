package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avaliacao.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.MAbuttonUser).setOnClickListener(this);
        findViewById(R.id.MAbuttonPost).setOnClickListener(this);

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
}
}