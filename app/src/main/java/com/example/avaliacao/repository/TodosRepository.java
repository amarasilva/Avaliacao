package com.example.avaliacao.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.avaliacao.model.Todos;
import com.example.avaliacao.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodosRepository implements Response.Listener<JSONArray>,Response.ErrorListener {

    private final String TAG = "TodosRepository";
    private List<Todos> todos;

    private static TodosRepository instance;
    private Context contexto;

    private OnReadyListener onReadyListener;

    private TodosRepository(Context contexto) {
        super();
        this.contexto = contexto;
        todos = new ArrayList<>();


        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos",
                null, this, this);

        queue.add(jaRequest);
    }

    // getInstance para ser usado sem contexto
    public static TodosRepository getInstance(){ return instance; }

    //getInstance com contexto colocando o repositorio na variavel instance se tiver null
    public static TodosRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new TodosRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getTodos().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }




    public List<Todos> getTodos() {
        return todos;
    }

    public Todos getTodosById(int id) {
        Todos ret = null;
        for (Todos u : todos) {
            if (u.getId() == id) {
                ret = u;
            }
        }
        return ret;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "onErrorResponse: " + error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {

        Log.e(TAG, "onResponse: " + response.length());
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject json = response.getJSONObject(i);
                Log.d(TAG, "onResponse: " + json.toString());
                todos.add(new Todos(json.getInt("userId"), json.getInt("id"),
                        json.getString("title"), json.getString("completed")));
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

    }

