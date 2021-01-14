package com.example.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class BinhLuanAdapter extends RecyclerView.Adapter<BinhLuanAdapter.ViewHolder> {
    private final LinkedList<BinhLuan> listBinhLuan;

    public BinhLuanAdapter(LinkedList<BinhLuan> listBinhLuan) {
        this.listBinhLuan = listBinhLuan;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.binhluan_recyclerview_item, parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BinhLuan vt = listBinhLuan.get(position);
        holder.txtNguoiDang.setText(vt.getTenNguoiDang());
        holder.txtNdBinhLuan.setText(vt.getNoiDung());
    }

    @Override
    public int getItemCount() {
        return listBinhLuan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final BinhLuanAdapter mAdapter;
        public final TextView txtNguoiDang;
        public final TextView txtNdBinhLuan;
        public ViewHolder(@NonNull View itemView, BinhLuanAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            txtNguoiDang = itemView.findViewById(R.id.txt_nguoidang);
            txtNdBinhLuan = itemView.findViewById(R.id.txt_ndbinhluan);
        }
    }
}
