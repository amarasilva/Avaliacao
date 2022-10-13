package com.example.avaliacao.presenter;

import android.app.Activity;

import com.example.avaliacao.model.User;

public class LoginPresenterContract {

    public interface view {
        public void message(String msg);
        public Activity getActivity();
    }

    public interface presenter {
        public void checkLogin(String login, String senha);
        public void validLogin(User user);

    }

}



