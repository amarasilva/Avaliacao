package com.example.avaliacao.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.User;
import com.example.avaliacao.presenter.LoginPresenter;
import com.example.avaliacao.presenter.LoginPresenterContract;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.UserRepository;
import com.example.avaliacao.repository.UserSQLRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginPresenterContract.view {

    //instancia a classe loginPresenterContract
    private LoginPresenterContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //SharedPreferences
        SharedPreferences preferences = getPreferences(0);
        //criando variaveis - por padão falso/falso/-1
        boolean sqlUpdated = preferences.getBoolean("sqlUpdated", false);
        boolean logged = preferences.getBoolean("logged", false);
        int userId = preferences.getInt("userId", -1);

        Log.d("LoginActivity", "Preferences: " + sqlUpdated + ", " + logged + ", " + userId);
        //criando as condicionais
        if (userId >= 0 && sqlUpdated) {
            User u = UserSQLRepository.getInstance(getActivity()).getUserById(userId);
            if (u != null) {
                ((TextView) findViewById(R.id.LAeditTextTextPersonName)).setText(u.getUsername());
            }
        }

        UserRepository.getInstance(this, new OnReadyListener() {
                    @Override
                    public void onReady() {
                        if (!sqlUpdated) {
                            List<User> users = UserRepository.getInstance().getUsers();
                            for (User u : users) {
                                UserSQLRepository.getInstance(getActivity()).insertUser(u);
                            }
                            SharedPreferences preferences = getPreferences(0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("sqlUpdated", true);
                            editor.commit();
                        }
                    }
                }
        );

        //testando o BD
        Log.e("TAG", "onCreate: depois do getInstance "+UserSQLRepository.getInstance(getActivity()).getUserById(1).getUsername());

        //instancia a classe loginPresenter
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

    @Override
    public void preferencesUserUpdate(int userId) {
        SharedPreferences preferences = getPreferences(0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("userId", userId);
        editor.putBoolean("logged", true);
        editor.commit();
    }
}


