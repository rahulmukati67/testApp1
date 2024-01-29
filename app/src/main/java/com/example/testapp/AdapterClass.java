package com.example.testapp;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyAdapter> {
    private Context mcontext;
    private ArrayList<String> list;
    private RecyclerView projectNameRv;
    private OnItemClickListener onItemClickListener; // Click listener interface

    // Interface for click listener
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onItemClick();
    }

    // Constructor to initialize the adapter with data and context
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
        private TextView projectName;
        private ImageView imgcancel;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            imgcancel = itemView.findViewById(R.id.imgcancel);
            projectNameRv.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
//            itemView.setOnClickListener(v -> {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(getAdapterPosition());
//                }
//            });
        }
    }
}
