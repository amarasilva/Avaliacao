package com.example.avaliacao.repository;

import com.example.avaliacao.model.User;

import java.util.List;

public interface UserRepositoryInterface {

    public List<User> getUsers();
    public User getUserById(int id);
    public User getUserByUserLogin(String login);
    public List<User> getUsersByName(String name);
    public User addUser(User user);
    public User updateUser(User user) ;
    public User removeUser(User user);
}
