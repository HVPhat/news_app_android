package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class ChuDeActivity extends AppCompatActivity {
    private final LinkedList<BaiViet> listBaiViet=new LinkedList<BaiViet>();
    private int img = R.drawable.no_image;
    private RecyclerView recyclerView;
    public static final String URL="https://10.0.2.2:8000/api/baiviet/";
    private BaiVietAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de);
        new KetNoi().execute(URL);
        recyclerView = findViewById(R.id.RecyclerViewBV);
        mAdapter = new BaiVietAdapter(listBaiViet, img, this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    class KetNoi extends AsyncTask<String,Void,String> {

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
                    listBaiViet.add(new BaiViet(tieuDe,noiDung));
                }
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