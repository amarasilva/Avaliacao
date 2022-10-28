package com.example.avaliacao.presenter;

import android.content.Intent;

import com.example.avaliacao.model.User;
import com.example.avaliacao.repository.OnReadyListener;
import com.example.avaliacao.repository.UserRepository;
import com.example.avaliacao.service.UserServices;
import com.example.avaliacao.view.MenuActivity;
import com.example.avaliacao.view.UserActivity;

public class LoginPresenter implements LoginPresenterContract.presenter {
    private LoginPresenterContract.view view;

    public LoginPresenter(LoginPresenterContract.view view) {
        this.view = view;
    }

    @Override
    public void checkLogin(String login, String senha) {
        //UserRepository repo = UserRepository.getInstance();
        //User u = repo.getUserByUserLogin(login);
        UserServices userServices = new UserServices(UserServices.REST_REPO, view.getActivity());
        User u = userServices.getUserByUserLogin(login);

        if (u == null || !u.getUsername().equals(senha)) {
            view.message("Usuário ou senha Inválido");
        } else {
             validLogin(u);
        }
    }

    @Override
    public void validLogin(User user) {
        Intent intent = new Intent(view.getActivity(),MenuActivity.class);
        view.preferencesUserUpdate(user.getId());
        view.getActivity().startActivity(intent);
    }
}