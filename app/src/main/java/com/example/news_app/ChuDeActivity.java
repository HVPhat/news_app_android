package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;

public class ChuDeActivity extends AppCompatActivity {
    private final LinkedList<BaiViet> listBaiViet = new LinkedList<BaiViet>();
    private RecyclerView recyclerView;
    public static final String URL="https://10.0.2.2:8000/api/baiviet/";
    private HomeRecyclerViewAdapter mAdapter;
    private String Id_chude;
    EditText txtInput;
    Button btnSearch;
    String input = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_de);
        txtInput = findViewById(R.id.txt_search);
        btnSearch =findViewById(R.id.btn_search);
        new KetNoi().execute(URL);
        if (listBaiViet != null) {
            recyclerView = findViewById(R.id.RecyclerView_BV);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Id_chude = bundle.getString("Id_CHUDE");
        Log.d("KEY", Id_chude);
    }

    public void Search(View view) {
        listBaiViet.clear();
        input = txtInput.getText().toString();
        new KetNoi().execute(URL);
        if (listBaiViet == null) {
            Toast.makeText(this, "Không tìm thấy bài viết!", Toast.LENGTH_SHORT).show();
            input = "";
        }
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
                String hinhAnh;
                String Id;
                String chuDe;
                for(int i=0;i<jsonArrayItems.length();i++){
                    JSONObject jsonObjectItem = jsonArrayItems.getJSONObject(i);
                    tieuDe = jsonObjectItem.getString("tieu_de");
                    Log.d("TIEU_DE",jsonObjectItem.getString("tieu_de"));
                    noiDung = jsonObjectItem.getString("noi_dung");
                    Log.d("NOI_DUNG",noiDung);
                    hinhAnh = jsonObjectItem.getString("hinh_anh");
                    Log.d("HINH_ANH",hinhAnh);
                    Id = jsonObjectItem.getString("id");
                    chuDe = jsonObjectItem.getString("chu_de");
                    Log.d("CHU_DE",chuDe);
                    if(input.equals("")){
                        if (chuDe.equals(Id_chude)) {
                        listBaiViet.addLast(new BaiViet(tieuDe, noiDung, hinhAnh, Id));
                    }
                    }
                    else {
                        if (chuDe.equals(Id_chude) && tieuDe.equals(input)) {
                            listBaiViet.addLast(new BaiViet(tieuDe, noiDung, hinhAnh, Id));
                        }
                    }
                }
                mAdapter = new HomeRecyclerViewAdapter(listBaiViet);
                recyclerView.setAdapter(mAdapter);
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