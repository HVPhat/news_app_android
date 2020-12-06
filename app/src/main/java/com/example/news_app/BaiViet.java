package com.example.news_app;

import android.widget.TextView;

public class BaiViet {
    private String TenBaiViet;
    private String NoiDung;

    public BaiViet(String tenBaiViet, String noiDung) {
        TenBaiViet = tenBaiViet;
        NoiDung = noiDung;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public String getNoiDung() {
        return NoiDung;
    }
}
