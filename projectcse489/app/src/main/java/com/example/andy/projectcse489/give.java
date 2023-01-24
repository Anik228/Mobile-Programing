package com.example.andy.projectcse489;

public class give {
    public String SNaB;
    public String SNaS;
    public String SIDS;
    public String SDa;

    public give() { }

    public give(String SNaB, String SNaS, String SIDS, String SDa) {
        this.SNaB = SNaB;
        this.SNaS = SNaS;
        this.SIDS = SIDS;
        this.SDa = SDa;
    }

    public String getNaB() {
        return SNaB;
    }

    public void setNaB(String SNaB) {
        this.SNaB = SNaB;
    }

    public String getname() {
        return SNaS;
    }

    public void setname(String SNaS) {
        this.SNaS = SNaS;
    }

    public String getIDS() {
        return SIDS;
    }

    public void setIDS(String SIDS) {
        this.SIDS = SIDS;
    }

    public String getDa() {
        return SDa;
    }

    public void setDa(String SDa) {
        this.SDa = SDa;
    }
}