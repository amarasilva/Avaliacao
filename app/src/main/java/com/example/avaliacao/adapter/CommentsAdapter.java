package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Comments;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Comments> dados;

    public CommentsAdapter(List<Comments> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comments_vh, parent, false);
        return new CommentsViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comments obj = dados.get(position);

        ((TextView) holder.itemView.findViewById(R.id.textViewVH_POSTID_COMMENTS)).setText(obj.getPostId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ID_COMMENTS)).setText(obj.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_NAME_COMMENTS)).setText(obj.getName());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_EMAIL_COMMENTS)).setText(obj.getEmail());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_BODY_COMMENTS)).setText(obj.getBody());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class CommentsViewHolder extends RecyclerView.ViewHolder{
        public View view;

        public CommentsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;

        }
    }

}
