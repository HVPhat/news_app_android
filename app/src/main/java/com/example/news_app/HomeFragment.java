package com.example.news_app;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class HomeFragment extends Fragment {
    LinkedList<BaiViet> lisBaiViet=new LinkedList<BaiViet>();
    int img = R.drawable.no_image;
    RecyclerView recyclerViewWordList;
    HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    View rootView;
    public static final String URL="https://10.0.2.2:8000/api/baiviet/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        new KetNoi().execute(URL);
        if(lisBaiViet!=null){
        //Thiết lập Adapter và LayoutManager cho RecyclerView
        recyclerViewWordList = (RecyclerView) rootView.findViewById(R.id.homeRecyclerView);
        }
        return rootView;
    }


    class KetNoi extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return Doc_noi_dung_url("http://10.0.2.2:8000/api/baiviet");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("S", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArrayItems=jsonObject.getJSONArray("baiViet");
                String tieuDe;
                String noiDung;
                for(int i=0;i<jsonArrayItems.length();i++){
                    JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                        tieuDe=jsonObjectItem.getString("tieu_de");
                        Log.d("TIEU_DE",jsonObjectItem.getString("tieu_de"));
                        noiDung = jsonObjectItem.getString("noi_dung");
                        Log.d("NOI_DUNG",noiDung);
                        lisBaiViet.add(new BaiViet(tieuDe,noiDung));
                }
                homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(lisBaiViet, img);
                recyclerViewWordList.setAdapter(homeRecyclerViewAdapter);
                recyclerViewWordList.setLayoutManager(new LinearLayoutManager(getActivity()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String Doc_noi_dung_url(String urls) {
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            //create object url
            java.net.URL url=new URL(urls);
            //create urlconnection
            URLConnection urlConnection=url.openConnection();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");

            }
            bufferedReader.close();
            if (builder.length() == 0) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        Log.d("LOG_TAG", builder.toString());
        return builder.toString();
    }

}
