package com.example.news_app;

public class ChuDe {
    private String tenChuDe;
    private String Id;

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public ChuDe(String tenChuDe, String id) {
        this.tenChuDe = tenChuDe;
        Id = id;
    }
}
