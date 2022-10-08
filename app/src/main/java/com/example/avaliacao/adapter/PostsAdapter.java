package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Posts;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Posts> dados;

    public PostsAdapter(List<Posts> dados) {
        this.dados = dados;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_posts_vh, parent, false);
        return new PostsViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Posts obj = dados.get(position);
        TextView tv1 = holder.itemView.findViewById(R.id.textViewVH_USERID_POSTS);
        tv1.setText(obj.getUserId() + "");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ID_POSTS)).setText(obj.getId() + "");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_TITLE_POSTS)).setText(obj.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_BODY_POSTS)).setText(obj.getBody());

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class PostsViewHolder extends RecyclerView.ViewHolder{
        public View view;

        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }


}
