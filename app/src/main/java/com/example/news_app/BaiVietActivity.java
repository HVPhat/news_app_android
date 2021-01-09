package com.example.news_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class BaiVietActivity extends AppCompatActivity {

    WebView mNoiDung;
    BaiViet mBaiViet;
    String noiDung;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);

        mNoiDung = findViewById(R.id.webView_BaiViet);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String Id = bundle.getString("Id");
        Log.d("HTML_CONTENT", Id);
        new KetNoi().execute(Id);
    }
    class KetNoi extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return NetworkUtils.GetBaiVietInfo("http://10.0.2.2:8000/api/baiviet/chitiet/"+strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                Log.d("S", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONObject baiViet = jsonObject.getJSONObject("baiViet");
                noiDung = baiViet.getString("noi_dung");
                Log.d("B",baiViet.getString("noi_dung"));
                /*for(int i=0;i<jsonArrayItems.length();i++){
                    JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                    Log.d("TIEU_DE",jsonObjectItem.getString("tieu_de"));
                    noiDung = jsonObjectItem.getString("noi_dung");
                    Log.d("CHI_TIET",noiDung);
                }*/
                WebSettings settings = mNoiDung.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                settings.setJavaScriptEnabled(true);
                settings.setDomStorageEnabled(true);
                mNoiDung.loadData(noiDung, "text/html; charset=utf-8", "UTF-8");
                mBaiViet.setNoiDung(noiDung);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
