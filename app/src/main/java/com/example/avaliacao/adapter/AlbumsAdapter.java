package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Albums;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Albums> dados;

    public AlbumsAdapter(List<Albums> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_albums_vh, parent, false);
        return new AlbumsAdapter.AlbumsViewHolder(layoutVH);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Albums obj = dados.get(position);

        ((TextView) holder.itemView.findViewById(R.id.textViewVH_USERID_ALBUMS)).setText(obj.getUserId() + "");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ID_ALBUMS)).setText(obj.getId() + "");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_TITLE_ALBUMS)).setText(obj.getTitle());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class AlbumsViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public AlbumsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }


}
