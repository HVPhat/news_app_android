package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BaiVietActivity extends AppCompatActivity {

    TextView mNoiDung;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_viet);

        mNoiDung = findViewById(R.id.mNoiDung);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String value = bundle.getString("NoiDung");
        mNoiDung.setText(value);
    }
}
