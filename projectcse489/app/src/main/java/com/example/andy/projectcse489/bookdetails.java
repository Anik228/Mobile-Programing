package com.example.andy.projectcse489;

public class bookdetails {
    public  String name;
    public  String auth;
    public  String edi;
    public  String gen;
    public  String shel;
    public  String type;
    public  String copy;

    public bookdetails(){}

    public bookdetails(String name, String auth, String edi, String gen, String shel, String SCopy,String type) {
        this.name = name;
        this.auth = auth;
        this.edi = edi;
        this.gen = gen;
        this.shel = shel;
        this.copy = SCopy;
        this.type = type;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getEdi() {
        return edi;
    }

    public void setEdi(String edi) {
        this.edi = edi;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getShel() {
        return shel;
    }

    public void setShel(String shel) {
        this.shel = shel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }
}
