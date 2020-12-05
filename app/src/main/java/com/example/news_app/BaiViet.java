package com.example.news_app;

import android.widget.TextView;

public class BaiViet {
    private String TenBaiViet;
    private int HinhAnh;

    public BaiViet(String tenBaiViet, int hinhAnh) {
        TenBaiViet = tenBaiViet;
        HinhAnh = hinhAnh;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public int getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        HinhAnh = hinhAnh;
    }
}
