package com.example.news_app;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class HomeFragment extends Fragment {
    LinkedList<String> wordList;
    int img = R.drawable.no_image;
    RecyclerView recyclerViewWordList;
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //Tạo nguồn dữ liệu
        this.wordList = new LinkedList<String>();
        for(int i=0; i<=50; i++)
            this.wordList.add("Bài viết "+i);

        //Thiết lập Adapter và LayoutManager cho RecyclerView
        recyclerViewWordList = (RecyclerView) rootView.findViewById(R.id.homeRecyclerView);
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(this.wordList, this.img);
        recyclerViewWordList.setAdapter(homeRecyclerViewAdapter);
        recyclerViewWordList.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tạo nguồn dữ liệu
        this.wordList = new LinkedList<String>();
        for(int i=0; i<=50; i++)
            this.wordList.add("Word "+i);

        //Thiết lập Adapter và LayoutManager cho RecyclerView
        recyclerViewWordList = (RecyclerView) findViewById(R.id.homeRecyclerView);
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(this.wordList);
        recyclerViewWordList.setAdapter(homeRecyclerViewAdapter);
        recyclerViewWordList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }*/
}
