package com.example.avaliacao.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avaliacao.R;
import com.example.avaliacao.model.Todos;

import java.util.List;

public class TodosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Todos> dados;

    public TodosAdapter(List<Todos> dados) {
        this.dados = dados;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutVH = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todos, parent, false);
        return new TodosAdapter.TodosViewHolder(layoutVH);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Todos obj = dados.get(position);

        ((TextView) holder.itemView.findViewById(R.id.textViewVH_USERID_TODOS)).setText(obj.getUserId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_ID_TODOS)).setText(obj.getId()+"");
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_TITLE_TODOS)).setText(obj.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.textViewVH_COMPLETED_TODOS)).setText(obj.getCompleted());

   }

    @Override
    public int getItemCount() {
        return dados.size();

    }

    class TodosViewHolder extends RecyclerView.ViewHolder {
        public View view;

        public TodosViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }
    }


}
