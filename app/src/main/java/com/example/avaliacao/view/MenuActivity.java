package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avaliacao.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.MAbutton).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.MAbutton) {
                    Intent intent = new Intent(view.getContext(), UserActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}