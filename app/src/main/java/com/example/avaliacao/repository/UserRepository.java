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

    //criando o atributo OnReadyListener para utilizar a interface OnReadyListener
    private OnReadyListener onReadyListener;

    private UserRepository(Context contexto) {
        super();
        this.contexto = contexto;
        users = new ArrayList<>();

        //gerando a requisição
        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/users",
                null, this, this);

        queue.add(jaRequest);
    }
// getInstance para ser usado sem contexto
    public static UserRepository getInstance(){
        return instance;
    }


//getInstance com contexto colocando o repositorio na variavel instance se tiver null
    public static UserRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new UserRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getUsers().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
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
                        json.getString("username"), json.getString("email")));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if (onReadyListener!=null) {
            onReadyListener.onReady();
        }
        onReadyListener = null;
        Log.e(TAG, "onResponse: FIM");
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse: " + error.getMessage());
    }

}