package com.example.news_app;

public class BinhLuan {
    private String TenNguoiDang;
    private String NoiDung;
    private String TenBaiViet;
    private String Id;

    public BinhLuan(String tenNguoiDang, String noiDung, String tenBaiViet, String id) {
        TenNguoiDang = tenNguoiDang;
        NoiDung = noiDung;
        TenBaiViet = tenBaiViet;
        Id = id;
    }

    public String getTenNguoiDang() {
        return TenNguoiDang;
    }

    public void setTenNguoiDang(String tenNguoiDang) {
        TenNguoiDang = tenNguoiDang;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTenBaiViet() {
        return TenBaiViet;
    }

    public void setTenBaiViet(String tenBaiViet) {
        TenBaiViet = tenBaiViet;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
