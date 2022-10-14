package com.example.avaliacao.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.avaliacao.model.Comments;
import com.example.avaliacao.model.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommentsRepository implements Response.Listener<JSONArray>,Response.ErrorListener {

    private final String TAG = "CommentsRepository";
    private List<Comments> commentss;
    private static CommentsRepository instance;
    private Context contexto;
    private OnReadyListener onReadyListener;

    private CommentsRepository(Context contexto){
        super();
        this.contexto = contexto;
        commentss = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/comments",
                null, this, this);

        queue.add(jaRequest);

    }

    public static CommentsRepository getInstance(){
        return instance;
    }

    public static CommentsRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new CommentsRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getCommentss().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

    public Comments createCommentsFromJson(JSONObject json) {
        try {
            return new Comments(json.getInt("postId"), json.getInt("id"),
                    json.getString("name"), json.getString("email"), json.getString("body") );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Comments> getCommentss() {
        return commentss;
    }

    public Comments getCommentsById(int id) {
        Comments ret = null;
        for (Comments u : commentss) {
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
                commentss.add(new Comments(json.getInt("postId"), json.getInt("id"),
                        json.getString("name"), json.getString("email"), json.getString("body")));
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
