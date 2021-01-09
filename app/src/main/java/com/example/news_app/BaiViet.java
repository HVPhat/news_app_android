package com.example.news_app;

import android.widget.TextView;

public class BaiViet {
    private String TenBaiViet;
    private String NoiDung;
    private String HinhAnh;
    private String Id;
    public BaiViet(String tenBaiViet, String noiDung, String hinhAnh, String Id) {
        TenBaiViet = tenBaiViet;
        NoiDung = noiDung;
        HinhAnh = hinhAnh;
        this.Id = Id;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public void setNoiDung(String noiDung) {
        this.NoiDung = noiDung;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public String getId() {
        return Id;
    }
}
