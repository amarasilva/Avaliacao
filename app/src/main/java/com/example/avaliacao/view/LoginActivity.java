package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.avaliacao.R;
import com.example.avaliacao.presenter.LoginPresenter;
import com.example.avaliacao.presenter.LoginPresenterContract;
import com.example.avaliacao.repository.UserRepository;
import com.google.android.material.snackbar.Snackbar;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    //instancia a classe loginPresenterContract
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        UserRepository.getInstance(this);

        //insrtancia a classe loginPresenter
        this.presenter = new LoginPresenter(this);

        //captura o botao LAbutton1
        findViewById(R.id.LAbutton1).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //chama o checkLogin
                presenter.checkLogin(
                        ((TextView) findViewById(R.id.LAeditTextTextPersonName)).getText().toString(),
                        ((TextView) findViewById(R.id.LAeditTextTextPassword)).getText().toString()


                );

            }

        });
    }

    @Override
    public void message(String msg) {
        Snackbar.make(this, findViewById(R.id.LAeditTextTextPassword),
                msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}