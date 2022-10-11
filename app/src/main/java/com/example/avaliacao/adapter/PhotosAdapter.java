package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Comments;
import com.example.avaliacao.model.Photos;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photos> dados;

    public PhotosAdapter(List<Photos> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_photos_vh, parent, false);
        return new PhotosAdapter.PhotosViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Photos obj = dados.get(position);

        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ALBUMID_PHOTOS)).setText(obj.getAlbumId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ID_PHOTOS)).setText(obj.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_TITLE_PHOTOS)).setText(obj.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_URL_PHOTOS)).setText(obj.getUrl());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_THUMBNAILURL_PHOTOS)).setText(obj.getThumbnailUrl());


    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class PhotosViewHolder extends RecyclerView.ViewHolder{
public View view;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

        }
    }

}
