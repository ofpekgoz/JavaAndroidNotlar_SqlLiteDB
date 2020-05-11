package com.omerfpekgoz.notlaruygulamasisqllite;

import java.io.Serializable;

public class Notlar implements Serializable {

    private int notId;
    private String dersAdi;
    private int notVize;
    private int notFinal;

    public Notlar() {
    }

    public Notlar(int notId, String dersAdi, int notVize, int notFinal) {
        this.notId = notId;
        this.dersAdi = dersAdi;
        this.notVize = notVize;
        this.notFinal = notFinal;
    }

    public int getNotId() {
        return notId;
    }

    public void setNotId(int notId) {
        this.notId = notId;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public int getNotVize() {
        return notVize;
    }

    public void setNotVize(int notVize) {
        this.notVize = notVize;
    }

    public int getNotFinal() {
        return notFinal;
    }

    public void setNotFinal(int notFinal) {
        this.notFinal = notFinal;
    }
}
