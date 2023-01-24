package com.example.andy.firebasedata;

public class student {

    public  String name;
    public  String id;


    public student(String name, String id) {
        this.name = name;
        this.id = id;
        /*System.out.println("working");
        System.out.println(name);
        System.out.println(id);*/
    }

    public student() {

    }

    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
