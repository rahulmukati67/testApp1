package com.example.testapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyAdapter> {
    private final Context mcontext;
    private final ArrayList<String> list;
    private final RecyclerView projectNameRv;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick();
    }
    public AdapterClass(ArrayList<String> list, Context mcontext, OnItemClickListener onItemClickListener , RecyclerView projectNameRv) {
        this.list = list;
        this.mcontext = mcontext;
        this.onItemClickListener = onItemClickListener;
        this.projectNameRv = projectNameRv;
    }
    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.projectlist, parent, false);
        return new MyAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
        holder.projectName.setText(list.get(position));

        holder.imgcancel.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyAdapter extends RecyclerView.ViewHolder {
        private final TextView projectName;
        private final ImageView imgcancel;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            imgcancel = itemView.findViewById(R.id.imgcancel);
            projectNameRv.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
