package com.example.avaliacao.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.avaliacao.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Response.Listener<JSONArray>, Response.ErrorListener {
    private final String TAG = "UserRepository";
    private List<User> users;
    private static UserRepository instance;
    private Context contexto;

    private UserRepository(Context contexto) {
        super();
        this.contexto = contexto;
        users = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                null, this, this);

        queue.add(jaRequest);
    }


    public static UserRepository getInstance(Context contexto) {
        if (instance == null) {
            instance = new UserRepository(contexto);
        }
        return instance;
    }


    public User createUserFromJson(JSONObject json) {
        try {
            return new User(json.getInt("id"), json.getString("name"),
                    json.getString("username"), json.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(int id) {
        User ret = null;
        for (User u : users) {
            if (u.getId() == id) {
                ret = u;
            }
        }
        return ret;
    }

    public User getUserByUserLogin(String login) {
        User ret = null;
        Log.d(TAG, "getUserByUserLogin: users.size " + users.size());
        for (User u : users) {
            Log.d(TAG, "getUserByUserLogin: " + login + " ->" + u.getUsername());
            if (u.getUsername().equals(login)) {
                ret = u;
            }
        }
        return ret;
    }


    @Override
    public void onResponse(JSONArray response) {
        Log.e(TAG, "onResponse: " + response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: " + json.toString());
                users.add(new User(json.getInt("id"), json.getString("name"),
                        json.getString("username"), json.getString("username")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.e(TAG, "onResponse: terminei");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse: " + error.getMessage());
    }

}