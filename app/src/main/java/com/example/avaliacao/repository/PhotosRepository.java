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
import com.example.avaliacao.model.Photos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotosRepository implements Response.Listener<JSONArray>,Response.ErrorListener {

    private final String TAG = "PhotosRepository";
    private List<Photos> photoss;
    private static PhotosRepository instance;
    private Context contexto;
    private OnReadyListener onReadyListener;

    private PhotosRepository(Context contexto){
        super();
        this.contexto = contexto;
        photoss = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/photos",
                null, this, this);

        queue.add(jaRequest);

    }

    public static PhotosRepository getInstance(){
        return instance;
    }

    public static PhotosRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new PhotosRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getPhotos().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

    public Photos createPhotosFromJson(JSONObject json) {
        try {
            return new Photos(json.getInt("albumId"), json.getInt("id"),
                    json.getString("title"), json.getString("url"), json.getString("thumbnailUrl") );
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Photos> getPhotos() {
        return photoss;
    }

    public Photos getPhotosById(int id) {
        Photos ret = null;
        for (Photos u : photoss) {
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
                photoss.add(new Photos(json.getInt("albumId"), json.getInt("id"),
                        json.getString("title"), json.getString("url"), json.getString("thumbnailUrl")));
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

