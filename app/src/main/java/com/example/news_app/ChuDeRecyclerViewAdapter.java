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

public class ChuDeRecyclerViewAdapter extends RecyclerView.Adapter<ChuDeRecyclerViewAdapter.ChuDeViewHolder> {
    LinkedList<String> mDataSet;
    public ChuDeRecyclerViewAdapter(LinkedList<String> mDataSet) {
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public ChuDeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chude_recyclerview_item, null);
        return new ChuDeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuDeViewHolder holder, int position) {
        String mCur = mDataSet.get(position);
        holder.txtChuDe.setText(mCur);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ChuDeViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtChuDe;
        final ChuDeRecyclerViewAdapter mAdapter;
        public ChuDeViewHolder(@NonNull View itemView, ChuDeRecyclerViewAdapter mAdapter) {
            super(itemView);
            txtChuDe = itemView.findViewById(R.id.txt_chude);
            this.mAdapter = mAdapter;
        }
    }
}
