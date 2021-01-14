package com.example.news_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class BaiVietActivity extends AppCompatActivity {

    WebView mNoiDung;
    BaiViet mBaiViet;
    String noiDung;
    String Id;
    EditText txtInput;
    private final LinkedList<BinhLuan> listBinhLuan = new LinkedList<>();
    private BinhLuanAdapter mAdapter;
    RecyclerView recyclerView;
    public static final String URL = "https://10.0.2.2:8000/api/binhluan/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);
        txtInput = findViewById(R.id.txt_cmt);
        mNoiDung = findViewById(R.id.webView_BaiViet);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Id = bundle.getString("Id");
        Log.d("HTML_CONTENT", Id);
        new KetNoi().execute(Id);
        new KetNoi1().execute(URL);
        if (listBinhLuan != null){
            recyclerView = findViewById(R.id.recyclerView_binhluan);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sentcmt(View view) {
        String ten = "Ngô Phong";
        String noidung = txtInput.getText().toString();
        String bv = "22";
        String id = "10";
        listBinhLuan.addLast(new BinhLuan(ten, noidung, bv, id));
    }

    class KetNoi extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return NetworkUtils.GetBaiVietInfo("http://10.0.2.2:8000/api/baiviet/chitiet/" + strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                Log.d("S", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONObject baiViet = jsonObject.getJSONObject("baiViet");
                noiDung = baiViet.getString("noi_dung");
                Log.d("B", baiViet.getString("noi_dung"));
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class KetNoi1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return Doc_noi_dung_url("http://10.0.2.2:8000/api/binhluan");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Log.d("S", s);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArrayItems = jsonObject.getJSONArray("binhLuan");
                String baiViet;
                String noiDung;
                String nguoiDang;
                String idBaiViet;
                String ten;
                for (int i = 0; i < jsonArrayItems.length(); i++) {
                    JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                    nguoiDang = jsonObjectItem.getString("nguoi_dang");
                    Log.d("NGUOI_DANG", nguoiDang);
                    if (nguoiDang.equals("16")) {
                        ten = "Ngô Phong";
                    }
                    else if (nguoiDang.equals("9")) {
                        ten = "Lê Tuấn Nhật";
                    }
                    else ten = "Huỳnh Vinh Phát";
                    noiDung = jsonObjectItem.getString("noi_dung");
                    Log.d("NOI_DUNG", noiDung);
                    baiViet = jsonObjectItem.getString("thuoc_ve_bai_viet");
                    Log.d("BAI_VIET", baiViet);
                    idBaiViet = jsonObjectItem.getString("id");
                    if(Id.equals(baiViet)) {
                        listBinhLuan.addLast(new BinhLuan(ten, noiDung, baiViet, idBaiViet));
                    }
                }
                mAdapter = new BinhLuanAdapter(listBinhLuan);
                recyclerView.setAdapter(mAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        public String Doc_noi_dung_url(String urls) {
            StringBuilder builder = new StringBuilder();
            String line;
            try {
                //create object url
                java.net.URL url = new URL(urls);
                //create urlconnection
                URLConnection urlConnection = url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
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
}
