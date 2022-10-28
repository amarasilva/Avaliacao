package com.example.avaliacao.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.avaliacao.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSQLRepository implements UserRepositoryInterface {

    private final String TAG = "UserSQLRepository";
    private static UserSQLRepository instance;
    private Context contexto;
    private SQLiteDatabase database;

    public static UserSQLRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserSQLRepository(contexto);

        }

        return instance;
    }

    private UserSQLRepository(Context contexto) {
        super();
        this.contexto = contexto;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
    }

    @Override
    public List<User> getUsers() {
        String sql = "select id, name, userName, email from users where id=? and name = ?;";
        User u = new User(1, "1", "1", "1");
        String[] args = {u.getId() + "", "jean"};
        Cursor cursor = database.rawQuery(sql, args);
        cursor.moveToFirst();
        List<User> users = new ArrayList<>();
        do {
            users.add(userFromCursor(cursor));
        } while (cursor.moveToNext());
        return users;
    }

    @Override
    public User getUserById(int id) {
        String sql = "select id, name, userName, email from users where id=? ;";
        String[] args = {"" + id};
        Cursor cursor = database.rawQuery(sql, args);
        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    @Override
    public User getUserByUserLogin(String username) {
        String sql = "select id, name, username, email from users where username=? ;";
        String[] args = {"" +username};
        Cursor cursor = database.rawQuery(sql, args);
        if (cursor.moveToFirst()) {
            return userFromCursor(cursor);
        } else {
            return null;
        }
    }

    @Override
    public List<User> getUsersByName(String name) {
        String sql = "select id, name, userName, email from users where name like ?;";
        User u = new User(1, "1", "1", "1");
        String[] args = {"%" + name + "%"};
        Cursor cursor = database.rawQuery(sql, args);
        cursor.moveToFirst();
        List<User> users = new ArrayList<>();
        do {
            users.add(userFromCursor(cursor));
        } while (cursor.moveToNext());
        return users;
    }

    @Override
    public User addUser(User user) {
        String sql = "insert into users (id, name, userName, email) values (?, ?, ?, ?);";
        //para usar execSQL os args são um array de Object, não de Strings
        Object[] args = {user.getId(), user.getName(), user.getUsername(), user.getUsername()};
        database.execSQL(sql, args);
        return user;
    }

    @Override
    public User updateUser(User user) {
        String sql = "update  users set name = ?, userName = ?, userName = ?;";
        //para usar execSQL os args são um array de Object, não de Strings
        Object[] args = {user.getName(), user.getUsername(), user.getUsername()};
        database.execSQL(sql, args);
        return user;
    }

    @Override
    public User removeUser(User user) {
        String sql = "delete from users where id = ?;";
        //para usar execSQL os args são um array de Object, não de Strings
        Object[] args = {user.getId()};
        database.execSQL(sql, args);
        return user;
    }


    private User userFromCursor(Cursor cursor) {
        User user = new User(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));
        return user;
    }

}
