package com.example.myapplication2;

public class Words {
    private String en_word;
    private String cn_mean;
    private String cixing;

    public Words(String en_word, String cn_mean, String cixing){
        this.en_word = en_word;
        this.cn_mean = cn_mean;
        this.cixing = cixing;
    }

    public String getEn_word() {
        return en_word;
    }

    public String getCn_mean() {
        return cn_mean;
    }

    public String getCixing() {
        return cixing;
    }
}
