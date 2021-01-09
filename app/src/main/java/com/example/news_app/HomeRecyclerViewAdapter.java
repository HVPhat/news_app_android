package com.example.news_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;


public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    final LinkedList<BaiViet> mDataSet;
    //LayoutInflater mInfater;
    //int img;

    HomeRecyclerViewAdapter(LinkedList<BaiViet> mDataSet){
        this.mDataSet=mDataSet;
    }
    @NonNull
    @Override
    public HomeRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recyclerview_item, null);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeRecyclerViewAdapter.ViewHolder holder, int position) {
        //Lấy giá trị tại position trong Dataset
        BaiViet curBaiViet = this.mDataSet.get(position);
        //Gán giá trị vào view trong ViewHolder
        holder.txtWord.setText(curBaiViet.getTenBaiViet());
        Picasso.get().load("http://10.0.2.2:8000/img/"+mDataSet.get(position).getHinhAnh()).into(holder.mImg);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtWord;
        final HomeRecyclerViewAdapter mAdapter;
        ImageView mImg;
        public ViewHolder(View itemView, HomeRecyclerViewAdapter mAdapter) {
            super(itemView);
            Context mContext = itemView.getContext();
            this.mImg = itemView.findViewById(R.id.imageView);
            this.txtWord = itemView.findViewById(R.id.textViewTieuDe);
            this.mAdapter = mAdapter;
            this.txtWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Lấy vị trí của item được click
                    int position = getLayoutPosition();
                    //Khai báo biến với kiểu dữ liệu BaiViet để chứa dữ liệu lấy ra từ linkedlist
                    BaiViet mBaiViet = mDataSet.get(position);
                    Intent intent = new Intent(mContext, BaiVietActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("Id", mBaiViet.getId());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                    Log.d("CLICK_ITEM", "Clicked item "+ position);
                    //Cập nhật giá trị của item được click
                    //mDataSet.set(position, "Click Word " + (position));
                    //mAdapter.notifyItemChanged(position);
                }
            });
        }
    }
}
