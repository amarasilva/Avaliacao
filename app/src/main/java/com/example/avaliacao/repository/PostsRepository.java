package com.example.avaliacao.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.avaliacao.model.Posts;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostsRepository implements Response.Listener<JSONArray>, Response.ErrorListener{

    private final String TAG = "PostsRepository";
    private List<Posts> postss;
    private static PostsRepository instance;
    private Context contexto;

    private OnReadyListener onReadyListener;

    private PostsRepository(Context contexto){
        super();
        this.contexto = contexto;
        postss = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/posts",
                null, this, this);

        queue.add(jaRequest);

    }

    public static PostsRepository getInstance(){
        return instance;
    }

    public static PostsRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new PostsRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getPostss().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }


    public List<Posts> getPostss() {
        return postss;
    }

/*    public Posts getPostsById(int id) {
        Posts ret = null;
        for (Posts u : postss) {
            if (u.getId() == id) {
                ret = u;
            }
        }
        return ret;
    }
*/

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
                postss.add(new Posts(json.getInt("userId"), json.getInt("id"),
                        json.getString("title"), json.getString("body")));
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
