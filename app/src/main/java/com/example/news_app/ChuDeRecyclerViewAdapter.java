package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ChuDeRecyclerViewAdapter extends RecyclerView.Adapter<ChuDeRecyclerViewAdapter.ViewHolder>{
    private final LinkedList<ChuDe> listChuDe;
    private LayoutInflater inflater;
    public ChuDeRecyclerViewAdapter(LinkedList<ChuDe> list) {
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
        ChuDe chude = listChuDe.get(position);
        holder.txtChuDe.setText(chude.getTenChuDe());
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
                int position = getLayoutPosition();
                //Khai báo biến với kiểu dữ liệu BaiViet để chứa dữ liệu lấy ra từ linkedlist
                ChuDe vt = listChuDe.get(position);
                Intent intent = new Intent(mContext, ChuDeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Id_CHUDE", vt.getId());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
                Log.d("CLICK_ITEM", "Clicked item "+ position);
            }
            });
        }
    }
}
