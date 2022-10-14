package com.example.avaliacao.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.avaliacao.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserSQLRepository {

    private final String TAG = "UserSQLRepository";
    private List<User> users;
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
        users = new ArrayList<>();
        DataBaseHelper dataBaseHelper = new DataBaseHelper(contexto);
        database = dataBaseHelper.getWritableDatabase();
        String sql = "select id, name, username, email from users where id=? and name = ?;";
        User u = new User(1, "1","1","1");
        String[] args = {u.getId()+"", "jean"};
        Cursor cursor = database.rawQuery(sql,args);
        cursor.moveToFirst();
        do {
            User user = new User(
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            users.add(user);
        } while (cursor.moveToNext());
    }
}


