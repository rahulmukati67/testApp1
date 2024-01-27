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
    private Context mcontext;
           private ArrayList<String> list;
           public  AdapterClass(ArrayList<String> list , Context mcontext){
               this.list = list;
               this.mcontext = mcontext;
           }
    @NonNull
    @Override
    public MyAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.projectlist, parent ,false);
        return new MyAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter holder, int position) {
          holder.projectName.setText(list.get(position));
         }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyAdapter extends RecyclerView.ViewHolder{
           private TextView projectName;
           private ImageView imgcancel;
        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            projectName = itemView.findViewById(R.id.projectName);
            imgcancel = itemView.findViewById(R.id.imgcancel);
        }
    }
}
