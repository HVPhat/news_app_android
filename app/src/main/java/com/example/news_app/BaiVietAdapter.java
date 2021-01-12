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

import java.util.LinkedList;

public class BaiVietAdapter extends RecyclerView.Adapter<BaiVietAdapter.ViewHolder> {
    private final LinkedList<BaiViet> mDataSet;
    private LayoutInflater mInfater;
    int img;

    public BaiVietAdapter(LinkedList<BaiViet> mDataSet, int img, Context context) {
        this.mDataSet = mDataSet;
        this.img = img;
        mInfater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInfater.inflate(R.layout.home_recyclerview_item, parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Lấy giá trị tại position trong Dataset
        BaiViet curBaiViet = this.mDataSet.get(position);
        //Gán giá trị vào view trong ViewHolder
        holder.txtWord.setText(curBaiViet.getTenBaiViet());
        holder.mImg.setImageResource(img);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtWord;
        final BaiVietAdapter mAdapter;
        ImageView mImg;
        public ViewHolder(View itemView, BaiVietAdapter mAdapter) {
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
                    bundle.putString("NoiDung", mBaiViet.getNoiDung());
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
