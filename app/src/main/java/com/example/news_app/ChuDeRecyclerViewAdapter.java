package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ChuDeRecyclerViewAdapter extends RecyclerView.Adapter<ChuDeRecyclerViewAdapter.ViewHolder>{
    private final LinkedList<String> listChuDe;
    private LayoutInflater inflater;
    public ChuDeRecyclerViewAdapter(LinkedList<String> list) {
        this.listChuDe = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chude_recyclerview_item, parent,false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String chude = listChuDe.get(position);
        holder.txtChuDe.setText(chude);
    }

    @Override
    public int getItemCount() {
        return this.listChuDe.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView txtChuDe;
        final ChuDeRecyclerViewAdapter mAdapter;
        public ViewHolder(@NonNull View itemView, ChuDeRecyclerViewAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            Context mContext = itemView.getContext();
            txtChuDe = itemView.findViewById(R.id.txt_chude);
            txtChuDe.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                //Lấy vị trí của item được click
                int position = getLayoutPosition();
                Intent intent = new Intent(mContext, ChuDeActivity.class);
                mContext.startActivity(intent);
            }
            });
        }
    }
}
