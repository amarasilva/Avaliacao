package com.example.avaliacao.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.avaliacao.model.Albums;
import com.example.avaliacao.model.Comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumsRepository implements Response.Listener<JSONArray>,Response.ErrorListener {

    private final String TAG = "AlbumsRepository";
    private List<Albums> albumss;
    private static AlbumsRepository instance;
    private Context contexto;
    private OnReadyListener onReadyListener;

    private AlbumsRepository(Context contexto){
        super();
        this.contexto = contexto;
        albumss = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(contexto);

        JsonArrayRequest jaRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/albums",
                null, this, this);

        queue.add(jaRequest);

    }

    public static AlbumsRepository getInstance(){
        return instance;
    }

    public static AlbumsRepository getInstance(Context contexto, OnReadyListener orl) {
        if (instance == null) {
            instance = new AlbumsRepository(contexto);
            instance.onReadyListener = orl;
        }
        if (!instance.getAlbumss().isEmpty()){
            if (orl !=null){
                orl.onReady();
                instance.onReadyListener = null;
            }
        }
        return instance;
    }

/*    public Albums createAlbumsFromJson(JSONObject json) {
        try {
            return new Albums (json.getInt("usertId"), json.getInt("id"),
                    json.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
*/
    public List<Albums> getAlbumss() {
        return albumss;
    }

    /*   public Albums getAlbumsById(int id) {
           Albums ret = null;
           for (Albums u : albumss) {
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
                albumss.add(new Albums (json.getInt("userId"), json.getInt("id"),
                        json.getString("title")));
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
