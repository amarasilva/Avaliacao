package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;


import com.example.avaliacao.R;
import com.example.avaliacao.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> dados;

    public UserAdapter(List<User> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user_vh, parent, false);
        return new UserViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        User obj = dados.get(position);
        TextView tv1 = holder.itemView.findViewById(R.id.textViewVH_ID);
        tv1.setText(obj.getId() + "");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_NAME)).setText(obj.getName());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_USERNAME)).setText(obj.getUsername());
        ((TextView) holder.itemView.findViewById(R.id.textView_VH_EMAIL)).setText(obj.getEmail());
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}

class UserViewHolder extends RecyclerView.ViewHolder {
    public View view;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
}

