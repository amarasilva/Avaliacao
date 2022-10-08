package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.avaliacao.R;

public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.LAbutton1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //intent que vai para o menu

                Intent intent = new Intent(view.getContext(), MenuActivity.class);
                startActivity(intent);
            }

        });
    }
}